<template>
  <div class="list-area">
            <div class="title">抽奖设置管理端</div>
            <div class="list">
                <div class="list-item" v-for="item in formData">
                    <label class="label">{{item.pos}}:</label>
                    <input type="text" v-model="item['min']" placeholder="点击设定最小值">
                    <input type="text" v-model="item['max']" placeholder="点击设定最大值">
                </div>
            </div>
            <div class="rule-list">
                <div class="desc"><span class="rule-label">打码量</span><span class="rule-label">奖金位数</span></div>
                <div class="rule-item" v-for="item in ruleData" :key='item.id'>
                    <input class="rule-input" type="text" v-model="item['total']">
                    <span class="rule-input">{{item.level}}</span>
                </div>
            </div>
            <button class="btn-save" @click="save">保存</button>
        </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      formData: [
          {
              pos: '个位',
              min: '',
              max: ''
          },
          {
              pos: '十位',
              min: '',
              max: ''
          },
          {
              pos: '百位',
              min: '',
              max: ''
          },
          {
              pos: '千位',
              min: '',
              max: ''
          },
          {
              pos: '万位',
              min: '',
              max: ''
          },
          {
              pos: '十万位',
              min: '',
              max: ''
          },
          {
              pos: '百万位',
              min: '',
              max: ''
          }
      ],
      ruleData: [
          {
              total: '3万',
              level: '个位'
          },
          {
              total: '5万',
              level: '十位'
          },
          {
              total: '10万',
              level: '百位'
          }
      ]
    }
  },
  methods: {
    save: function() {
              console.log(this.formData);
              console.log(this.ruleData);
              // 保存设置数据
              // 这里需要两个保存按钮还是需要一个就可以？
              /***
              this.$axios({
                  url: '',
                  method: 'post',
                  params: {}
              }).then(function (res) {
                  console.log('数据提交成功');
                  console.log(res.data);
              }).catch(function (err) {
                console.log(err);
              })
              ***/
          }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.list-area {
    width: 100%;
    margin: 0 auto;
}    
.title {
    font-size: 24px;
    font-weight: bold;
    text-align: center;
    line-height: 3;
}    
.list, .rule-list {
    width: 600px;
    box-sizing: border-box;
    margin: 0 auto;
    border: 2px solid goldenrod;
}
.list .list-item {
    font-size: 0;
    padding: 10px 20px;
    border-bottom: 1px solid goldenrod;
}
.list .label,
.rule-list .rule-label {
    font-size: 16px;
    line-height: 1.5;
    font-weight: bold;
    vertical-align: middle;
    padding-right: 10px;
}
.list input {
    font-size: 16px;
    line-height: 1.5;
    vertical-align: middle;
    margin-right: 20px;
    padding: 0 10px;
    text-align: center;
}
.list input:last-child {
    margin-right: 0;
}
::-webkit-input-placeholder { /* WebKit browsers */
    color: #888888;
    font-size: 16px;
    line-height: 1.5;
}
:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color: #888888;
    font-size: 16px;
    line-height: 1.5;
}
::-moz-placeholder { /* Mozilla Firefox 19+ */
    color: #888888;
    font-size: 16px;
    line-height: 1.5;
}
:-ms-input-placeholder { /* Internet Explorer 10+ */
    color: #888888;
    font-size: 16px;
    line-height: 1.5;
}
.btn-save {
    display: block;
    width: 300px;
    margin: 30px auto 0;
    height: 60px;
    background: goldenrod;
    text-align: center;
    color: #fff;
    font-size: 26px;
    font-weight: bold;
    border-radius: 36px;
    letter-spacing: 20px;
}
.rule-list {
    margin-top: 30px;
}
.desc, .rule-item {
    display: flex;
    padding: 10px 20px;
    border-bottom: 1px solid goldenrod;
    font-size: 0;
}
.rule-list .rule-label,
.rule-list .rule-input {
    flex: 1;
    text-align: center;
    font-size: 16px;
    line-height: 24px;
}
</style>
