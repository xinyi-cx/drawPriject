<template>
  <div class="form-box">
    <div class="login-container">
      <el-form
        :model="ruleForm2"
        :rules="rules2"
        status-icon
        ref="ruleForm2"
        label-position="left"
        label-width="0px"
        class="demo-ruleForm login-page"
      >
        <h3 class="title">系统登录</h3>
        <el-form-item prop="userId">
          <el-input type="text" v-model="ruleForm2.userId" auto-complete="off" placeholder="用户Id"></el-input>
        </el-form-item>
        <el-form-item prop="userName">
          <el-input type="text" v-model="ruleForm2.userName" auto-complete="off" placeholder="用户名"></el-input>
        </el-form-item>
        <el-checkbox v-model="checked" class="rememberme">记住密码</el-checkbox>
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/request";

export default {
  data() {
    return {
      logining: false,
      ruleForm2: {
        userId: "",
        userName: "",
      },
      rules2: {
        userId: [
          {
            required: true,
            message: "please enter your userId",
            trigger: "blur",
          },
        ],
        userName: [
          {
            required: true,
            message: "enter your userName",
            trigger: "blur",
          },
        ],
      },
      checked: false,
    };
  },
  methods: {
    handleSubmit(event) {
      this.$refs.ruleForm2.validate(async (valid) => {
        if (!valid) return this.$message.warning('请输入正确的用户Id和用户名');
        this.logining = true;

        const { data: res } = await this.$http.post(
          "userInfo/login",
          this.ruleForm2
        );
        if (res.code !== 0) return this.$message.error("用户Id或用户名错误！");

        this.logining = false;
        // sessionStorage.setItem("user", this.ruleForm2.username);
        this.$router.push({ name: "Index", params: res.data });
        this.$message.success('登录成功');

      });
    },
  },
};
</script>

<style scoped>
.form-box {
  width: 100%;
  padding-top: 80px;
}
.form-box .title {
  margin-bottom: 20px;
}
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}

@media screen and (max-width: 750px) {
  .login-page {
    width: 100%;
    margin: 20px auto;
    padding: 15px;
    box-sizing: border-box;
  }
  .login-page >>> .el-input__inner {
    width: 90% !important;
  }
}
</style>