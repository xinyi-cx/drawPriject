<template>
  <div class="index">
    <div class="bg"></div>
    <header class="content">
      <a href="###" class="logo"></a>
      <ul>
        <li>
          <a :href="indexUrl" target="_blank">首页</a>
        </li>
        <li>
          <a :href="registerUrl" target="_blank">立即注册</a>
        </li>
        <li>
          <a :href="onlineServiceUrl" target="_blank">在线客服</a>
        </li>
        <li>
          <a href="javascript: void(0);" @click="getQueryList">中奖查询</a>
        </li>
        <li>
          <a href="#activeRule">活动规则</a>
        </li>

        <li>
          <a href="javascript: void(0);" @click="loginClick">{{currentState}}</a>
        </li>
      </ul>
    </header>
    <div class="banner"></div>

    <div class="bd-content">
      <!-- 抽奖活动 -->
      <div class="lottery">
        <div class="lg-title">
          抽奖活动
          <div class="celebrate left"></div>
          <div class="celebrate right"></div>
        </div>
        <div class="money-area">
          <div class="border border-tl"></div>
          <div class="border border-tr"></div>
          <div class="border border-bl"></div>
          <div class="border border-br"></div>
          <div class="digit-list clearfix">
            <div class="lf"></div>
            <div class="digit-item">
              <div class="digits">
                <transition name="down-up-translate-fade">
                  <p class="number" :key="reward.bw">{{reward.bw}}</p>
                </transition>
              </div>
              <p class="units">百万</p>
            </div>
            <div class="digit-item">
              <div class="digits">
                <transition name="down-up-translate-fade">
                  <p class="number" :key="reward.sw">{{reward.sw}}</p>
                </transition>
              </div>
              <p class="units">十万</p>
            </div>
            <div class="digit-item">
              <div class="digits">
                <transition name="down-up-translate-fade">
                  <p class="number" :key="reward.w">{{reward.w}}</p>
                </transition>
              </div>
              <p class="units">万</p>
            </div>
            <div class="digit-item">
              <div class="digits">
                <transition name="down-up-translate-fade">
                  <p class="number" :key="reward.q">{{reward.q}}</p>
                </transition>
              </div>
              <p class="units">千</p>
            </div>
            <div class="digit-item">
              <div class="digits">
                <transition name="down-up-translate-fade">
                  <p class="number" :key="reward.b">{{reward.b}}</p>
                </transition>
              </div>
              <p class="units">百</p>
            </div>
            <div class="digit-item">
              <div class="digits">
                <transition name="down-up-translate-fade">
                  <p class="number" :key="reward.s">{{reward.s}}</p>
                </transition>
              </div>
              <p class="units">十</p>
            </div>
            <div class="digit-item mr-0">
              <div class="digits">
                <transition name="down-up-translate-fade">
                  <p class="number" :key="reward.g">{{reward.g}}</p>
                </transition>
              </div>
              <p class="units">个</p>
            </div>
            <div class="rg"></div>
          </div>
        </div>
        <div class="btn-wrap">
          <button class="btn btn-share" @click="drawClick">立即抽奖</button>
        </div>
      </div>
      <!-- 中奖查询 -->
      <div class="query-area">
        <div class="lg-title">
          中奖查询
          <div class="rule-decor left">
            <div class="latern-l"></div>
          </div>
          <div class="rule-decor right">
            <div class="latern-r"></div>
          </div>
        </div>
        <div class="res-list">
          <div class="item" v-for="item in listData" v-bind:key="item.id">
            <span class="point"></span>
            <span class="nickname">{{item.userId}}</span>
            <span class="result">抽中{{item.reward}}元</span>
            <span class="date">{{item.creatTimeStr}}</span>
          </div>
        </div>
      </div>
      <!-- 抽奖规则 -->
      <div class="rule-area" id="activeRule">
        <div class="lg-title">
          抽奖规则
          <div class="rule-decor left">
            <div class="cracker-l">
              <div class="fireOne lo-f"></div>
              <div class="fireOne lo-s"></div>
              <div class="fireOne lo-t"></div>
              <div class="fireTwo lt-f"></div>
              <div class="fireTwo lt-s"></div>
              <div class="fireTwo lt-t"></div>
            </div>
          </div>
          <div class="rule-decor right">
            <div class="cracker-r">
              <div class="fireOne ro-f"></div>
              <div class="fireOne ro-s"></div>
              <div class="fireOne ro-t"></div>
              <div class="fireTwo rt-f"></div>
              <div class="fireTwo rt-s"></div>
              <div class="fireTwo rt-t"></div>
            </div>
          </div>
        </div>
        <div class="res-list">
          <p>1.活动时间：2020年7月1日 —— 2020年7月2日；</p>
          <p>2.活动期间，每位注册用户每天有3次抽奖机会；</p>
          <p>3.奖品使用规则在抽奖记录中查看；</p>
          <p>4.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX XXXX</p>
          <p>5.活动期间如遇问题，可拨打客服电话：123456789；</p>
          <p>6.在法律允许范围内，本活动最终解释为XXXX所有；</p>
        </div>
      </div>
    </div>

    <!-- 查询弹框 -->
    <el-dialog title="中奖记录" :visible.sync="dialogTableVisible">
      <el-table :data="queryList" border stripe>
        <el-table-column property="reward" label="中奖金额" width></el-table-column>
        <el-table-column property="creatTimeStr" label="中奖时间" width></el-table-column>
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
    </el-dialog>

    <div class="login-modal" v-show="loginDialogVisible">
      <div class="login_dialog">
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
              <div class="title">
                登录弹框
                <i class="el-icon-circle-close icon-close" @click="closeLoginDialog"></i>
              </div>
              <el-form-item prop="userId">
                <el-input
                  type="text"
                  v-model="ruleForm2.userId"
                  auto-complete="off"
                  placeholder="用户Id"
                ></el-input>
              </el-form-item>
              <el-form-item prop="userName">
                <el-input
                  type="text"
                  v-model="ruleForm2.userName"
                  auto-complete="off"
                  placeholder="用户名"
                ></el-input>
              </el-form-item>
              <!-- <el-checkbox v-model="checked" class="rememberme">记住密码</el-checkbox> -->
              <el-form-item style="width:100%;">
                <el-button
                  type="primary"
                  style="width:100%;"
                  @click="handleSubmit"
                  :loading="logining"
                >登录</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
    <div class="backToTop" v-show="btnFlag" @click="backTop">返回顶部</div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      reward: {
        bw: "?",
        sw: "?",
        w: "?",
        q: "?",
        b: "?",
        s: "?",
        g: "?",
      },
      listData: [],
      //进入页面后会有一个用户的数据，根据用户数据判断是否可以抽奖
      userInfo: {},
      interval: null,
      intervalS: null,
      intervalB: null,
      intervalQ: null,
      intervalW: null,
      intervalSw: null,
      intervalBw: null,
      dialogTableVisible: false,
      queryList: [],
      queryInfo: {
        userId: "",
        userName: "",
        pageNum: 1,
        pageSize: 5,
      },
      total: 0,
      currentState: "请登录",
      user: "",
      path: "",
      loginDialogVisible: false,
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
      logining: false,
      toPathName: "",
      drawNum: 0,
      indexUrl: "",
      onlineServiceUrl: "",
      registerUrl: "",
      btnFlag: false,
      activeValue: false
    };
  },
  created() {
    this.getUrls();
    this.allowDraw();
    this.getDate();
    if (this._isMobile()) {
      this.toPathName = "IndexMobile";
      this.$router.replace("/mobile");
    } else {
      this.toPathName = "Index";
      this.$router.replace("/index");
    }
  },
  mounted() {
    window.addEventListener("scroll", this.scrollToTop);
  },
  destroyed() {
    window.removeEventListener("scroll", this.scrollToTop);
  },

  methods: {
    // 点击图片回到顶部方法，加计时器是为了过渡顺滑
    backTop() {
      const that = this;
      let timer = setInterval(() => {
        let ispeed = Math.floor(-that.scrollTop / 5);
        document.documentElement.scrollTop = document.body.scrollTop =
          that.scrollTop + ispeed;
        if (that.scrollTop === 0) {
          clearInterval(timer);
        }
      }, 16);
    },

    // 为了计算距离顶部的高度，当高度大于60显示回顶部图标，小于60则隐藏
    scrollToTop() {
      const that = this;
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      that.scrollTop = scrollTop;
      if (that.scrollTop > 60) {
        that.btnFlag = true;
      } else {
        that.btnFlag = false;
      }
    },

    async getUrls() {
      var that = this;
      const { data: res } = await this.$http.get("systemConfig/list");
      if (res.code !== 0) return this.$message.error("获取链接失败");
      var urls = res.data;
      urls.forEach(function (item, index) {
        if (item.configKey == "indexUrl") {
          that.indexUrl = item.configValue;
        }
        if (item.configKey == "onlineServiceUrl") {
          that.onlineServiceUrl = item.configValue;
        }
        if (item.configKey == "registerUrl") {
          that.registerUrl = item.configValue;
        }
      });
    },
    async getActive(){
      const {data: res} = await this.$http.post('systemConfig/selectByPrimaryKey');
      if(res.code !== 0) { return this.$message.error('获取活动状态失败！') };
      this.activeValue = (res.data.configValue === '1');
    },
    _isMobile() {
      let flag = navigator.userAgent.match(
        /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
      );
      return flag;
    },
    start: function () {
      const _this = this;
      if (!this.interval) {
        this.interval = setInterval(function () {
          _this.reward.g = Math.floor(Math.random() * 10);
        }, 100);
      }
      if (!this.intervalS) {
        this.intervalS = setInterval(function () {
          _this.reward.s = Math.floor(Math.random() * 10);
        }, 100);
      }
      if (!this.intervalB) {
        this.intervalB = setInterval(function () {
          _this.reward.b = Math.floor(Math.random() * 10);
        }, 100);
      }
      if (!this.intervalQ) {
        this.intervalQ = setInterval(function () {
          _this.reward.q = Math.floor(Math.random() * 10);
        }, 100);
      }
      if (!this.intervalW) {
        this.intervalW = setInterval(function () {
          _this.reward.w = Math.floor(Math.random() * 10);
        }, 100);
      }
      if (!this.intervalSw) {
        this.intervalSw = setInterval(function () {
          _this.reward.sw = Math.floor(Math.random() * 10);
        }, 100);
      }
      if (!this.intervalBw) {
        this.intervalBw = setInterval(function () {
          _this.reward.bw = Math.floor(Math.random() * 10);
        }, 100);
      }
      setTimeout(function () {
        _this.createReward();
      }, 150);
    },
    end: function (flag) {
      if (flag == 1) {
        clearInterval(this.interval);
        this.interval = null;
      }
      if (flag == 2) {
        clearInterval(this.intervalS);
        this.intervalS = null;
      }
      if (flag == 3) {
        clearInterval(this.intervalB);
        this.intervalB = null;
      }
      if (flag == 4) {
        clearInterval(this.intervalQ);
        this.intervalQ = null;
      }
      if (flag == 5) {
        clearInterval(this.intervalW);
        this.intervalW = null;
      }
      if (flag == 6) {
        clearInterval(this.intervalSw);
        this.intervalSw = null;
      }
      if (flag == 7) {
        clearInterval(this.intervalBw);
        this.intervalBw = null;
      }
    },
    async getDate() {
      const _this = this;
      clearInterval(this.queryInterval);
      const { data: res } = await this.$http.post("userReward/listForDraw", {});
      if (res.code !== 0) return this.$message.error("获取用户列表失败");
      _this.listData = res.data;
      _this.queryInterval = setInterval(function () {
        _this.getDate();
      }, 60000);
    },
    async createReward() {
      const userId = this.userInfo.userId || "";
      const that = this;
      const { data: res } = await this.$http.post(
        `userReward/createReward/${userId}`
      );
      if (res.code !== 0) return this.$message.error("获取失败");

      let rdata = res.data;
      that.drawNum = 0;
      that.userInfo.drawNum= 0;
      sessionStorage.setItem("user", JSON.stringify(that.userInfo));

      that.end(1);
      that.reward.g = Math.floor(
        (rdata.reward - Math.floor(rdata.reward / 10) * 10) / 1
      );
      setTimeout(function () {
        that.end(2);
        that.reward.s = Math.floor(
          (rdata.reward - Math.floor(rdata.reward / 100) * 100) / 10
        );
        setTimeout(function () {
          that.end(3);
          that.reward.b = Math.floor(
            (rdata.reward - Math.floor(rdata.reward / 1000) * 1000) / 100
          );
          setTimeout(function () {
            that.end(4);
            that.reward.q = Math.floor(
              (rdata.reward - Math.floor(rdata.reward / 10000) * 10000) / 1000
            );
            setTimeout(function () {
              that.end(5);
              that.reward.w = Math.floor(
                (rdata.reward - Math.floor(rdata.reward / 100000) * 100000) /
                  10000
              );
              setTimeout(function () {
                that.end(6);
                that.reward.sw = Math.floor(
                  (rdata.reward -
                    Math.floor(rdata.reward / 1000000) * 1000000) /
                    100000
                );
                setTimeout(function () {
                  that.end(7);
                  that.reward.bw = Math.floor(rdata.reward / 1000000);
                }, 1000);
              }, 1000);
            }, 1000);
          }, 1000);
        }, 1000);
      }, 1000);
    },
    async allowDraw(){
      await this.loginState();
      this.queryInfo.userId = this.userInfo.userId || "";
      this.queryInfo.userName = this.userInfo.userName || "";
    },
    async getQueryList() {
      if (!sessionStorage.getItem("user")) {
        return this.$message.warning("您还没有登录，请登录后查看;");
      }
      this.dialogTableVisible = true;
      this.queryInfo.userId = this.userInfo.userId;
      const { data: res } = await this.$http.get("userReward/list", {
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
    async drawClick() {
      await this.getActive();
      if (!sessionStorage.getItem("user")) {
        return this.$message.error("登录后才有抽奖资格！");
      }
      if (!this.activeValue){
        return this.$message.error("活动暂未开启，请咨询在线客服");
      }
      if (this.drawNum > 0) {
        this.start();
      } else {
        return this.$message.warning("您还没有抽奖次数！");
      }
    },
    loginState() {
      let vip = false;
      if (sessionStorage.getItem("user")) {
        this.userInfo = JSON.parse(sessionStorage.getItem("user"));
        this.currentState = `${this.userInfo.userName},退出`;
        this.drawNum = this.userInfo.drawNum;
      } else {
        this.currentState = "请登录";
      }
    },
    loginClick() {
      if (sessionStorage.getItem("user")) {
        window.sessionStorage.clear();
        this.userInfo = {};
        this.loginState();
        this.$message.success("成功退出");
      } else {
        this.loginDialogVisible = true;
      }
    },
    handleSubmit(event) {
      this.$refs.ruleForm2.validate(async (valid) => {
        if (!valid) return this.$message.warning("请输入正确的用户Id和用户名");
        this.logining = true;

        const { data: res } = await this.$http.post(
          "userInfo/login",
          this.ruleForm2
        );
        if (res.code !== 0) {
          this.logining = false;
          return this.$message.error("用户Id或用户名错误！");
        }
        sessionStorage.setItem("user", JSON.stringify(res.data));
        this.userInfo = res.data;
        this.$message.success("登录成功");
        this.loginState();
        this.ruleForm2 = {};
        this.logining = false;
        this.loginDialogVisible = false;
        this.drawNum = res.data.drawNum;
      });
    },
    closeLoginDialog() {
      this.loginDialogVisible = false;
      this.ruleForm2 = {};
    },
  },
};
</script>

<style scoped>
@import "../assets/css/index.css";

.index >>> .el-table th,
.index >>> .el-table td {
  text-align: center;
}
.el-pagination {
  margin-top: 15px;
}

/**登录弹窗 */

.login_dialog {
  width: 100%;
  height: 100%;
  overflow: hidden;
  bottom: 20%;
}

.login_dialog >>> .el-dialog__title {
  color: #409eff;
}
.login_dialog >>> .el-input {
  background-image: linear-gradient(to right, #e8198b, #3b65bb);
  border-radius: 25px;
  height: 45px;
  width: 100%;
}
.login_dialog >>> .el-input__inner {
  margin-top: 2px;
  height: calc(100% - 4px);
  width: calc(100% - 4px);
  border-radius: 25px;
  font-size: 14px;
}
.login_dialog >>> .el-button {
  border-radius: 25px;
}
.form-box {
  width: 100%;
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
  width: 350px;
  padding: 0 35px 15px;
  background: #fff;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}

.login-modal {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}
.login-page .title {
  padding-top: 20px;
  font-size: 20px;
  font-weight: bold;
  color: #409eff;
}
.login-page .icon-close {
  position: absolute;
  right: 15px;
  top: 15px;
  cursor: pointer;
}
.backToTop {
  position: fixed;
  bottom: 20px;
  right: 20px;
  width: 66px;
  height: 46px;
  background-color: white;
  border: solid 1px #e4e4e4;
  text-decoration: none;
  font-size: 14px;
  line-height: 46px;
  cursor: pointer;
}
</style>
