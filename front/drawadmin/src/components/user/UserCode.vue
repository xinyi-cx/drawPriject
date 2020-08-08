<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>抽奖管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户打码量</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 搜索区域 -->
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input placeholder="请输入用户ID" v-model="queryInfo.userId"></el-input>
        </el-col>
        <el-col :span="4">
          <el-select v-model="queryInfo.isVip" placeholder="是否是vip用户">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="getQueryList">搜索</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="addDialogVisible = true">添加</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="uploadDialogVisiable=true">导入</el-button>
        </el-col>
      </el-row>
      <!-- 查询列表区 -->
      <el-table border stripe :data="queryList">
        <el-table-column type="index" width="55"></el-table-column>
        <el-table-column label="用户Id" prop="userId"></el-table-column>
        <el-table-column label="用户名" prop="userName"></el-table-column>
        <el-table-column label="奖励" prop="reward"></el-table-column>
        <el-table-column label="打码量" prop="code"></el-table-column>
        <el-table-column label="是否Vip" prop="isVip" :formatter="vipFormat"></el-table-column>
        <el-table-column label="上传时间" width="180" prop="updateTime" :formatter="dateFormat"></el-table-column>
        <el-table-column label="操作" width="200">
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
        :page-sizes="[5, 10, 15, 20]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-card>

    <!-- 上传弹框 -->
    <el-dialog title="数据导入" :visible.sync="uploadDialogVisiable" width="400px" @close="addDialogClosed">
      <!-- 上传文件选择区域 -->
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :on-error="handleFileError"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <!-- 底部区域 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="cancelFile">取 消</el-button>
      </div>
    </el-dialog>

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
        <el-form-item label="用户Id" prop="userId">
          <el-input v-model="addForm.userId"></el-input>
        </el-form-item>
        <el-form-item label="打码量" prop="code">
          <el-input v-model="addForm.code"></el-input>
        </el-form-item>
        <el-form-item label="奖励" prop="reward">
          <el-input v-model="addForm.reward"></el-input>
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
import moment from "moment";

export default {
  data() {
    return {
      queryInfo: {
        userId: "",
        pageNum: 1,
        pageSize: 5,
        isVip: ''
      },
      queryList: [],
      total: 0,
      dateRange: "",
      // 上传文件
      uploadDialogVisiable: false,
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 上传的地址
        url: `${this.baseURL}userCodeRef/excelIn`,
      },
      // 添加用户
      addDialogVisible: false,
      addForm: {},
      addFormRules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        userId: [{ required: true, message: "请输入用户Id", trigger: "blur" }],
      },
      dialogTitle: "",
      isAdd: false,
      userId: "",
      // 是否Vip
      options: [
        {
          value: "1",
          label: "是",
        },
        {
          value: "0",
          label: "否",
        },
        {
          value: '',
          label: ''
        }
      ],
      value: "是否是vip用户",
    };
  },
  created() {
    this.getQueryList();
  },
  methods: {
    // 格式化日期
    dateFormat(row, column) {
      const date = row[column.property];
      if (date == undefined) {
        return "";
      }
      return moment(date).format("YYYY-MM-DD  HH:mm:ss");
    },
    vipFormat(row, column) {
      const isVip = row[column.property];
      let isVipUser = isVip ? '是' : '否';
      return isVipUser;
    },
    async getQueryList() {
      console.log(this.queryInfo);
      
      const { data: res } = await this.$http.get("userCodeRef/list", {
        params: this.queryInfo,
      });
      if (res.code !== 0) return this.$message.error("获取查询列表失败");
      this.queryList = res.data;
      this.total = res.total;
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.uploadDialogVisiable = false;
      this.getQueryList();
    },
    handleFileError(err, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(err.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.uploadDialogVisiable = false;
      this.getQueryList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    cancelFile() {
      this.$refs.upload.clearFiles();
    },
    handleSizeChange(newPageSize) {
      this.queryInfo.pageSize = newPageSize;
      this.getQueryList();
    },
    // 监听页码值改变的函数
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getQueryList();
    },
    // 添加用户
    addFormSubmit() {
      debugger;
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return this.$message.warning("请添加正确的用户名密码");

        // 有值 更新
        if (this.userId !== "") {
          const { data: res } = await this.$http.post(
            "userCodeRef/update",
            this.addForm
          );
          if (res.code !== 0)
            return this.$message.error("更新信息失败，请联系管理员");
          this.$message.success("修改信息成功");

          this.getQueryList();
        } else {
          const { data: res } = await this.$http.post(
            "userCodeRef/insert",
            this.addForm
          );
          if (res.code !== 0)
            return this.$message.error("添加信息失败，请联系管理员");
          this.$message.success("添加信息成功");

          this.getQueryList();
        }
        this.addDialogVisible = false;
      });
    },
    addDialogClosed() {
      console.log('qingkong');
      this.$refs.addFormRef.resetFields();
    },
    //展示编辑用户的对话框
    editUserDialog(row) {
      this.addDialogVisible = true;
      this.userId = row.userId;
      this.addForm = row;
    },
    // 删除用户
    async removeUser(id) {
      const confirmResult = await this.$confirm(
        "此操作将永久删除该信息, 是否继续?",
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
      const { data: res } = await this.$http.post(
        "userCodeRef/deleteByPrimaryKey/" + id
      );
      if (res.code !== 0) {
        return this.$message.error("删除信息失败！");
      }

      this.$message.success("删除信息成功！");
      this.getQueryList();
    },
  },
};
</script>

<style lang="scss" scoped>
.el-table {
  margin-top: 15px;
}
.el-dialog {
  width: 400px;
}
</style>
