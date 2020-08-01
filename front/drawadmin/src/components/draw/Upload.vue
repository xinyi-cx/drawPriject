<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>抽奖管理</el-breadcrumb-item>
      <el-breadcrumb-item>上传数据</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 搜索区域 -->
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入userId">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
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
        <el-table-column label="打码量" prop="code"></el-table-column>
        <el-table-column label="上传时间" prop="updateTime" :formatter="dateFormat"></el-table-column>
      </el-table>
    </el-card>

    <!-- 上传弹框 -->
    <el-dialog
      title="数据导入"
      :visible.sync="uploadDialogVisiable"
      width="400px"
    >
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
  </div>
</template>

<script>
import moment from 'moment'

export default {
  data() {
    return {
      queryInfo: {
        userId: "",
        pageNum: 1,
        pageSize: 2,
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
        // url: `${axios.defaults.baseURL} + 'userCodeRef/excelIn'`,
        url: "http://192.168.0.101:8848/tt-manage/userCodeRef/excelIn"
      },
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
    async getQueryList() {
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
      // this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    cancelFile() {
      this.$refs.upload.clearFiles();
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