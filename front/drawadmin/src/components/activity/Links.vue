<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>活动管理</el-breadcrumb-item>
      <el-breadcrumb-item>链接管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 查询列表区 -->
      <el-table border stripe :data="queryList">
        <el-table-column label="链接名称" prop="configName"></el-table-column>
        <el-table-column label="链接地址" prop="configValue"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit-outline"
              @click="editUserDialog(scope.row)"
            >修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 添加用户 -->
    <el-dialog title="修改弹窗" :visible.sync="editDialogVisible" width="30%" @close="editDialogClosed">
      <!-- 主体区 -->
      <el-form :model="editForm" ref="editFormRef" label-width="100px">
        <el-form-item label="链接名称">
          <el-input v-model="editForm.configName" disabled></el-input>
        </el-form-item>
        <el-form-item label="链接地址">
          <el-input v-model="editForm.configValue"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelEditDialog">取 消</el-button>
        <el-button type="primary" @click="editFormSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import moment from "moment";

export default {
  data() {
    return {
      queryList: [],
      editForm: {},
      editDialogVisible: false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async getList() {
      const { data: res } = await this.$http.get("systemConfig/list");
      if (res.code !== 0) {
        return this.$message.error("获取列表失败！");
      }
      this.queryList = res.data;
    },
    editUserDialog(value) {
      this.editDialogVisible = true;
      this.editForm = value;
    },
    cancelEditDialog() {
      this.editDialogVisible = false;
    },
    async editFormSubmit() {
      const { data: res } = await this.$http.post(
        "systemConfig/update",
        this.editForm
      );
      if (res.code !== 0)
        return this.$message.error("修改信息失败，请联系管理员");
      this.$message.success("修改信息成功");
      this.editDialogVisible = false;
      this.getList();
    },
    editDialogClosed() {
      this.editForm = {};
      this.$refs.editFormRef.resetFields();
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