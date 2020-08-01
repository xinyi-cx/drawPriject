<template>
  <div class="index">
    <div class="bg"></div>
    <header class="content">
      <a href="###" class="logo"></a>
      <ul>
        <li>
          <a href="javascript: void(0);" class="is-active">网站首页</a>
        </li>
        <li>
          <a href="#">客服</a>
        </li>
        <li>
          <a href="javascript: void(0);" @click="getQueryList">中奖查询</a>
        </li>
        <li>
          <a href="#">活动规则</a>
        </li>
        <li>
          <a href="#">关于我们</a>
        </li>
      </ul>
    </header>
    <div class="banner"></div>

    <div class="bd-content">
      <!-- 抽奖活动 -->
      <div class="lottery content">
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
          <button
            class="btn btn-share"
            :class="[isDraw ? 'notAllowed' : '']"
            @click="start"
            :disabled="isDraw"
          >立即抽奖</button>
        </div>
      </div>
      <!-- 中奖查询 -->
      <div class="query-area content">
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
            <span class="nickname">{{item.userName}}</span>
            <span class="result">抽中{{item.reward}}元</span>
            <span class="date">{{item.creatTimeStr}}</span>
          </div>
        </div>
      </div>
      <!-- 抽奖规则 -->
      <div class="rule-area content">
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
      isDraw: false,
      userId: "",
      interval: null,
      dialogTableVisible: false,
      queryList: [],
      queryInfo: {
        userId: "",
        userName: "",
        pageNum: 1,
        pageSize: 5,
      },
      total: 0
    };
  },
  created() {
    this.allowDraw();
    this.getDate();
  },
  methods: {
    start: function () {
      const _this = this;
      if (!this.interval) {
        this.interval = setInterval(function () {
          _this.reward.bw = Math.floor(Math.random() * 10);
          _this.reward.sw = Math.floor(Math.random() * 10);
          _this.reward.w = Math.floor(Math.random() * 10);
          _this.reward.q = Math.floor(Math.random() * 10);
          _this.reward.b = Math.floor(Math.random() * 10);
          _this.reward.s = Math.floor(Math.random() * 10);
          _this.reward.g = Math.floor(Math.random() * 10);
        }, 10);
      }
      setTimeout(function () {
        _this.createReward();
      }, 150);
    },
    end: function () {
      clearInterval(this.interval);
      this.interval = null;
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
      const userId = this.userId;
      const that = this;
      const { data: res } = await this.$http.post(
        `userReward/createReward/${userId}`
      );
      if (res.code !== 0) return this.$message.error("获取失败");

      let rdata = res.data;
      that.isDraw = rdata.isDraw > 0 ? false : true;
      that.end();
      // rdata.reward = 234567;
      that.reward.bw = Math.floor(rdata.reward / 1000000);
      that.reward.sw = Math.floor(
        (rdata.reward - Math.floor(rdata.reward / 1000000) * 1000000) / 100000
      );
      that.reward.w = Math.floor(
        (rdata.reward - Math.floor(rdata.reward / 100000) * 100000) / 10000
      );
      that.reward.q = Math.floor(
        (rdata.reward - Math.floor(rdata.reward / 10000) * 10000) / 1000
      );
      that.reward.b = Math.floor(
        (rdata.reward - Math.floor(rdata.reward / 1000) * 1000) / 100
      );
      that.reward.s = Math.floor(
        (rdata.reward - Math.floor(rdata.reward / 100) * 100) / 10
      );
      that.reward.g = Math.floor(
        (rdata.reward - Math.floor(rdata.reward / 10) * 10) / 1
      );
    },
    allowDraw: function () {
      this.userId = this.$route.params.userId;
      const drawNum = this.$route.params.drawNum;
      this.queryInfo.userId = this.userId;
      this.queryInfo.userName = this.$route.params.userName;
      if (drawNum > 0) {
        this.isDraw = false;
      } else {
        this.isDraw = true;
      }
    },
    async getQueryList() {
      this.dialogTableVisible = true;
      let id = this.userId;
      
      const { data: res } = await this.$http.get(
        "userReward/list",{params: this.queryInfo}
      );
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
    }
  }
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
</style>
