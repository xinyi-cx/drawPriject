<template>
  <el-container class="home_container">
    <!-- 头部区域 -->
    <el-header>
      <div class="title">
        <img src="../assets/image/logo.jpg" alt />
        <span>抽奖后台管理系统</span>
      </div>
      <el-button class="logout" @click="logout">退出</el-button>
    </el-header>

    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse ? '64px' : '300px'">
        <div class="toggle-btn" @click="toggleCollapse">|||</div>
        <el-menu
          background-color="#344058"
          text-color="#fff"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
          :default-active="activePath"
        >
          <el-submenu :index="item.id + ''" v-for="item in menuList" :key="item.id">
            <template slot="title">
              <i class="el-icon-s-tools"></i>
              <span>{{item.authName}}</span>
            </template>
            <el-menu-item
              :index="subItem.path"
              @click="saveNavState(subItem.path)"
              v-for="subItem in item.children"
              :key="subItem.id"
            >
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>{{subItem.authName}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!-- 主体区 -->
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      menuList: [],
      isCollapse: false,
      activePath: ''
    };
  },
  created() {
    this.getMenuList();
    this.activePath = window.sessionStorage.getItem('activePath');
    console.log(this.activePath);
  },
  methods: {
    logout: function () {
      // window.sessionStorage.clear();
      this.$router.push("/login");
    },
    getMenuList: function () {
      this.menuList = [
        {
          id: 1,
          authName: "抽奖管理",
          path: "/users",
          order: 1,
          children: [
            {
              id: 11,
              authName: "金额设置",
              path: "/money",
            },
            {
              id: 12,
              authName: "打码量设置",
              path: "/draw",
            },
            {
              id: 13,
              authName: "上传数据",
              path: "/upload",
            },
            {
              id: 14,
              authName: "查询列表",
              path: "/query",
            },
            {
              id: 15,
              authName: "用户列表",
              path: "/userList",
            },
          ],
        },
      ];
    },
    toggleCollapse: function () {
      this.isCollapse = !this.isCollapse;
    },
    // 保存链接的激活状态
    saveNavState: function(activePath) {
        window.sessionStorage.setItem('activePath', activePath);
        this.activePath = activePath;
    }
  }
};
</script>

<style lang="scss" scoped>
.home_container {
  height: 100%;
}
.el-header {
  background: #1e9fff;
  color: #fff;
  padding-left: 0;
  padding-top: 5px;
  position: relative;
  .title {
    font-size: 0;
    img {
      width: 50px;
      height: 50px;
      vertical-align: middle;
      margin-right: 20px;
      border-radius: 50%;
    }
    span {
      font-size: 20px;
      vertical-align: middle;
    }
  }

  .logout {
    position: absolute;
    right: 0;
    top: 10px;
    margin-right: 20px;
  }
}
.el-aside {
  background: #344058;
  .el-menu {
    border-right: none;
  }
}
.el-main {
  background: #f2f2f2;
}
.toggle-btn {
  background: #4a5064;
  font-size: 14px;
  line-height: 30px;
  color: #fff;
  letter-spacing: 0.3em;
  text-align: center;
  cursor: pointer;
}
</style>