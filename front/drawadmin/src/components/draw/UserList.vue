<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>抽奖管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 搜索区域 -->
      <el-row :gutter="50">
        <el-col :span="6">
          <el-input
            placeholder="请输入userId"
            v-model="queryInfo.userId"
            clearable
            @clear="getQueryList"
          ></el-input>
        </el-col>
        <el-col :span="6">
          <el-input
            placeholder="请输入userName"
            v-model="queryInfo.userName"
            clearable
            @clear="getQueryList"
          ></el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="getQueryList">搜索</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
        </el-col>
      </el-row>
      <!-- 查询列表区 -->
      <el-table border stripe :data="queryList">
        <el-table-column type="index" width="55"></el-table-column>
        <el-table-column label="用户Id" prop="userId"></el-table-column>
        <el-table-column label="用户名" prop="userName"></el-table-column>
        <el-table-column label="创建时间" prop="createTime"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit-outline"
              @click="editUserDialog(scope.row)"
            >修改</el-button>
            <el-button
              type="warning"
              size="mini"
              icon="el-icon-edit-outline"
              @click="removeUser(scope.row.userId)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 3, 4]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-card>
    <!-- 添加用户 -->
    <el-dialog
      :title="dialogTitle"
      :visible.sync="addDialogVisible"
      width="30%"
      @close="addDialogClosed"
    >
      <!-- 主体区 -->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
        <el-form-item label="用户名">
          <el-input v-model="addForm.userName" prop="userName"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addFormSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 问题： 还应该有个日期？
      queryInfo: {
        userId: "",
        userName: "",
        // 当前页码
        pageNum: 1,
        // 当前每页显示数据
        pageSize: 2,
      },
      queryList: [],
      total: 0,
      // 添加用户
      addDialogVisible: false,
      addForm: {},
      addFormRules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
      dialogTitle: "",
      isAdd: false,
      userId: "",
    };
  },
  created() {
    this.getQueryList();
  },
  methods: {
    async getQueryList() {
      const { data: res } = await this.$http.get("systemUser/list", {
        params: this.queryInfo,
      });
      if (res.code !== 0) return this.$message.error("获取查询列表失败");

      this.queryList = res.data;
      this.total = res.total;
    },
    // 监听PageSize改变的函数
    handleSizeChange(newPageSize) {
      this.queryInfo.pageSize = newPageSize;
      this.getQueryList();
    },
    // 监听页码值改变的函数
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getQueryList();
    },
    addFormSubmit() {
      debugger;
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return this.$message.warning("请添加正确的用户名密码");

        // 有值 更新
        if (this.userId !== "") {
          const { data: res } = await this.$http.post(
            "systemUser/update",
            this.addForm
          );
          if (res.code !== 0)
            return this.$message.error("更新用户失败，请联系管理员");
          this.$message.success("修改用户成功");

          this.queryList = res.data;

        } else {
          const { data: res } = await this.$http.post(
            "systemUser/insert",
            this.addForm
          );
          if (res.code !== 0)
            return this.$message.error("添加用户失败，请联系管理员");
          this.$message.success("添加用户成功");

          this.queryList = res.data;

        }
        this.addDialogVisible = false;
      });
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    async removeUser(id) {
      const confirmResult = await this.$confirm(
        "此操作将永久删除该用户, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirmResult !== "confirm") {
        return this.$message.info("已取消删除");
      }
      const { data: res } = await this.$http.delete(
        "systemUser/deleteByPrimaryKey" + id
      );
      if (res.code !== 0) {
        return this.$message.error("删除用户失败！");
      }

      this.$message.success("删除用户成功！");
      this.getQueryList();
    },
    //展示编辑用户的对话框
    editUserDialog(row) {
      this.addDialogVisible = true;
      this.userId = row.userId;
      this.addForm = row;
    },
  },
};
</script>

<style lang="scss" scoped>
.el-table {
  margin-top: 15px;
  font-size: 14px;
}
</style>