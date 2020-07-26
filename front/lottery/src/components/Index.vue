<template>
  <div class="index">
    <div class="bg"></div>
    <header class="content">
      <a href="###" class="logo"></a>
      <ul>
        <li>
          <a href="#" class="is-active">网站首页</a>
        </li>
        <li>
          <a href="#">客服</a>
        </li>
        <li>
          <a href="#">中奖查询</a>
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
              <p class="digits">{{reward.bw}}</p>
              <p class="units">百万</p>
            </div>
            <div class="digit-item">
              <p class="digits">{{reward.sw}}</p>
              <p class="units">十万</p>
            </div>
            <div class="digit-item">
              <p class="digits">{{reward.w}}</p>
              <p class="units">万</p>
            </div>
            <div class="digit-item">
              <p class="digits">{{reward.q}}</p>
              <p class="units">千</p>
            </div>
            <div class="digit-item">
              <p class="digits">{{reward.b}}</p>
              <p class="units">百</p>
            </div>
            <div class="digit-item">
              <p class="digits">{{reward.s}}</p>
              <p class="units">十</p>
            </div>
            <div class="digit-item mr-0">
              <p class="digits">{{reward.g}}</p>
              <p class="units">个</p>
            </div>
            <div class="rg"></div>
          </div>
        </div>
        <div class="btn-wrap">
          <button class="btn">立即邀请好友</button>
          <button class="btn btn-share" :class="[isDraw ? 'notAllowed' : '']" @click="createReward" :disabled="isDraw">立即抽奖</button>
        </div>
      </div>
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
        <div class="res-list" >
          <div class="item" v-for="item in listData" v-bind:key="item.id">
            <span class="point"></span>
            <span class="nickname">{{item.userName}}</span>
            <span class="phone">{{item.reward}}</span>
            <span class="result">抽中{{item.reward}}元红包</span>
            <span class="date">{{item.creatTimeStr}}</span>
          </div>
        </div>
      </div>
      <div class="rule-area content">
        <div class="lg-title">
          抽奖规则
          <div class="rule-decor left">
            <div class="cracker-l"></div>
          </div>
          <div class="rule-decor right">
            <div class="cracker-r"></div>
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
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      reward:{
        bw:'?',
        sw:'?',
        w:'?',
        q:'?',
        b:'?',
        s:'?',
        g:'?'
      },
      listData:[],
      //进入页面后会有一个用户的数据，根据用户数据判断是否可以抽奖
      isDraw: true
    };
  },
  created() {
    this.getDate();
  },
  methods: {
    getDate: function() {
      const that = this;
      this.$axios({
        url: 'http://localhost:8011/tt-manage/userReward/listForDraw',
        method: 'post',
        data: {}
      }).then(function (res) {
        const resultDate = res.data;
        that.listData = resultDate.data;
        console.log('数据提交成功');
        console.log(res.data);
      })
    },
    createReward: function() {
      const userId = '10';
      const that = this;
      // private String userId;
      //
      // private String userName;
      //
      // private Long reward;
      //
      // private Date creatTime;
      //
      // private String rewardStatus;
      // 这个是返回值数据的类型，测试可以直接返回这个 立即抽奖应该不可点击
      this.$axios({
        url: 'http://localhost:8011/tt-manage/userReward/createReward/'+userId,
        method: 'post'
      }).then(function (res) {
        const resultDate = res.data;
        let rdata = resultDate.data;
        // rdata.reward = 234567;
        that.reward.bw = Math.floor(rdata.reward/1000000);
        that.reward.sw = Math.floor((rdata.reward-Math.floor(rdata.reward/1000000)*1000000)/100000);
        that.reward.w = Math.floor((rdata.reward-Math.floor(rdata.reward/100000)*100000)/10000);
        that.reward.q = Math.floor((rdata.reward-Math.floor(rdata.reward/10000)*10000)/1000);
        that.reward.b = Math.floor((rdata.reward-Math.floor(rdata.reward/1000)*1000)/100);
        that.reward.s = Math.floor((rdata.reward-Math.floor(rdata.reward/100)*100)/10);
        that.reward.g = Math.floor((rdata.reward-Math.floor(rdata.reward/10)*10)/1);
        console.log('数据提交成功');
        console.log(res.data);
      })
    },
  }
};
</script>

<style scoped>
  @import '../assets/css/index.css';
</style>
