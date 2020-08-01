<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>抽奖管理</el-breadcrumb-item>
      <el-breadcrumb-item>金额设置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 查询列表区 -->
      <el-table border stripe :data="moneyList">
        <el-table-column label="单位" prop="digit"></el-table-column>
        <el-table-column label="最小值" prop="digitStart"></el-table-column>
        <el-table-column label="最大值" prop="digitEnd"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="修改" :enterable="false" placement="top">
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit-outline"
                circle
                @click="showEditDialog(scope.row)"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 编辑金额弹窗 -->
    <el-dialog
      title="修改金额范围"
      :visible.sync="editDialogVisible"
      width="30%"
      @close="editDialogClosed"
    >
      <!-- 主体区 -->
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="单位">
          <el-input v-model="editForm.digit" disabled></el-input>
        </el-form-item>
        <el-form-item label="最小值" prop="digitStart">
          <el-input v-model="editForm.digitStart"></el-input>
        </el-form-item>
        <el-form-item label="最大值" prop="digitEnd">
          <el-input v-model="editForm.digitEnd"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editDialogSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    // 自定义校验规则 数字校验有问题；
    // var checkNumber = (rule, value, cb) => {
    //   const regNumber = //;
    //   if (regNumber.test(value)) {
    //     return cb();
    //   }

    //   cb(new Error("请输入合法的数值"));
    // };

    return {
      moneyList: [],
      editDialogVisible: false,
      editForm: {},
      editFormRules: {
        digitStart: [
          { required: true, message: "请输入最小值", trigger: "blur" },
          // { validator: checkNumber, trigger: "blur" }
        ],
        digitEnd: [
          { required: true, message: "请输入最大值", trigger: "blur" },
          // { validator: checkNumber, trigger: "blur" }
        ]
      },
    };
  },
  created() {
    this.getMoneyList();
  },
  methods: {
    async getMoneyList() {
      const { data: res } = await this.$http.post("drawConfig/list");
      if (res.code !== 0) return this.$message.error("获取金额设置失败");
      this.moneyList = res.data;
    },
    // 展示编辑对话框
    showEditDialog(row) {
      this.editDialogVisible = true;
      this.editForm = row;
    },
    // 监听对话框关闭事件
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    // 表单提交预校验
    editDialogSubmit() {
      this.$refs.editFormRef.validate(async valid => {
        if(this.editForm.digitStart > this.editForm.digitEnd) {
          valid = false;
          this.$message.error('最小值不能大于最大值');
        }
        
        if (!valid) return;
        // 可以发起保存请求
        const {data: res} = await this.$http.post("drawConfig/updateOne", this.editForm);
        if(res.code !== 0) return this.$message.error('更新失败，请联系管理员');
        this.$message.success('更新成功');
        this.editDialogVisible = false;
        debugger;
        this.moneyList = res.data;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>