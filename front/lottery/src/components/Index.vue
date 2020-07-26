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
              <p class="digits">1</p>
              <p class="units">百万</p>
            </div>
            <div class="digit-item">
              <p class="digits">?</p>
              <p class="units">十万</p>
            </div>
            <div class="digit-item">
              <p class="digits">?</p>
              <p class="units">万</p>
            </div>
            <div class="digit-item">
              <p class="digits">?</p>
              <p class="units">千</p>
            </div>
            <div class="digit-item">
              <p class="digits">?</p>
              <p class="units">百</p>
            </div>
            <div class="digit-item">
              <p class="digits">?</p>
              <p class="units">十</p>
            </div>
            <div class="digit-item mr-0">
              <p class="digits">?</p>
              <p class="units">个</p>
            </div>
            <div class="rg"></div>
          </div>
        </div>
        <div class="btn-wrap">
          <button class="btn">立即邀请好友</button>
          <button class="btn btn-share">连续分享</button>
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
        <div class="res-list">
          <div class="item">
            <span class="point"></span>
            <span class="nickname">梦想的声音</span>
            <span class="phone">130****7397</span>
            <span class="result">抽中100元红包</span>
            <span class="date">2020/07/15</span>
          </div>
          <div class="item">
            <span class="point"></span>
            <span class="nickname">梦想的声音</span>
            <span class="phone">130****7397</span>
            <span class="result">抽中100元红包</span>
            <span class="date">2020/07/15</span>
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
      msg: "欢迎来到抽奖管理设置后台",
    };
  },
};
</script>

<style scoped>
  @import '../assets/css/index.css';
</style>
