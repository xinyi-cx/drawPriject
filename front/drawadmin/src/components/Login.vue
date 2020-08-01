<template>
  <div class="login_container">
    <div class="login_box">
      <!-- Logo区域 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" alt />
      </div>
      <!-- 表单区域 -->
      <el-form class="login_form" ref="loginFormRef" :model="loginForm" :rules="loginFormRules">
        <el-form-item prop="userId">
          <el-input placeholder="请输入用户Id" v-model="loginForm.userId" prefix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
        <el-form-item prop="userName">
          <el-input placeholder="请输入用户名" v-model="loginForm.userName" prefix-icon="el-icon-s-goods"></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            loginForm: {
                userId: '',
                userName: ''
            },
            loginFormRules: {
                userId: [
                    { required: true, message: '请输入用户Id', trigger: 'blur' }
                ],
                userName: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        //重置
        resetLoginForm: function() {
            this.$refs.loginFormRef.resetFields();
        },
        //登录
        login: function() {
            this.$router.push('/home');
            this.$refs.loginFormRef.validate(valid => {
                console.log(valid);
                if(!valid) return this.$message.warning('请输入正确的用户Id和用户名');
                const {data: res} = this.$http.post("userInfo/login", this.loginForm);
                console.log(res);
                if(res.code !== 0) return this.$message.error('登录失败');
                this.$message.success('登录成功过');
                // 
                // window.sessionStorage.setItem('token', res.data.token);
                this.$router.push('/home');
            })

            /**
             this.$refs.loginFormRef.validate(async valid => {
                console.log(valid);
                if(!valid) return;
                // 如果返回的是promise 可以用async await 简化promise操作
                const result = await this.$http.post("userInfo/login", this.loginForm);
                //result 返回的是axios封装好的数据 需要用的只有data下的数据 所以可以解构赋值
                const {data: res} = await this.$http.post("userInfo/login", this.loginForm);
            })
             */
        }
    }
};
</script>

<style lang="scss" scoped>
.login_container {
  width: 100%;
  height: 100%;
  background: url(../assets/image/login-background.png) center no-repeat;
  background-size: cover;
}
.login_box {
  width: 450px;
  height: 300px;
  background: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .avatar_box {
    width: 130px;
    height: 130px;
    border-radius: 50%;
    border: 1px solid #eee;
    padding: 6px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    img {
      width: 100%;
      height: 100%;
      border-radius: 100%;
      background: #eee;
    }
  }
  .btns {
      text-align: center;
  }
  .login_form {
      position: absolute;
      bottom: 0;
      width: 100%;
      padding: 0 20px;
      box-sizing: border-box;
  }
}
</style>