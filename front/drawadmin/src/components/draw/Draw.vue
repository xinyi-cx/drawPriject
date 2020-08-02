<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>抽奖管理</el-breadcrumb-item>
      <el-breadcrumb-item>打码量设置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 查询列表区 -->
      <el-table border stripe :data="drawList">
        <el-table-column label="打码数量" prop="code"></el-table-column>
        <el-table-column label="奖金位数" prop="drawDigit"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="修改" :enterable="false" placement="top">
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit-outline"
                circle
                @click="editDrawDialog(scope.row)"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 编辑打码量 -->
    <el-dialog
      title="修改打码量"
      :visible.sync="drawDialogVisible"
      width="30%"
      @close="drawDialogClosed"
    >
      <!-- 主体区 -->
      <el-form
        :model="drawForm"
        :rules="editDrawFormRules"
        ref="drawFormRef"
        label-width="70px"
      >
        <el-form-item label="奖金位数">
          <el-input v-model="drawForm.drawDigit" disabled></el-input>
        </el-form-item>
        <el-form-item label="打码量" prop="code">
          <el-input v-model="drawForm.code"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="drawDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editDialogSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      drawList: [],
      drawForm: {},
      drawDialogVisible: false,
      editDrawFormRules: {
        code: [
          { required: true, message: "请输入最大值", trigger: "blur" },
          // { validator: checkNumber, trigger: "blur" }
        ]
      },
    };
  },
  created() {
    this.getDrawList();
  },
  methods: {
    async getDrawList() {
      const { data: res } = await this.$http.post("codeDrawRef/list");
      if (res.code !== 0) return this.$message.error("获取打码量列表失败");
      this.drawList = res.data;
    },
    editDrawDialog(row) {
      this.drawDialogVisible = true;
      this.drawForm = row;
    },
    drawDialogClosed() {
      this.$refs.drawFormRef.resetFields();
    },
    editDialogSubmit() {
      this.$refs.drawFormRef.validate(async (valid) => {
        if (!valid) return;
        // 可以发起保存请求
        const { data: res } = await this.$http.post(
          "codeDrawRef/update",
          this.drawForm
        );
        if (res.code !== 0)
          return this.$message.error("更新失败，请联系管理员");
        this.$message.success("更新成功");
        this.drawDialogVisible = false;
        this.drawList = res.data;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>