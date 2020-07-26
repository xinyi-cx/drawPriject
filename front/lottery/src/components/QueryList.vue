<template>
    <div class="query-bd">
        <div class="title">红包查询</div>
        <div class="query-area">
            <label class="label" for="">用户id:</label>
            <input class="user-id" v-model="userid" type="text" placeholder="请输入用户id">
            <button class="btn btn-query" @click="getQueryList">查询</button>
            <button class="btn" @click="derive">导出</button>
        </div>
        <table class="query-list" v-if="queryList.length > 0" >
            <tr ><th>用户ID</th><th>用户姓名</th><th>中间金额</th><th>中奖时间</th></tr>
            <tr v-for="item in queryList"><td>{{item.userId}}</td>
              <td>{{item.userName}}</td>
              <td>{{item.reward}}</td>
              <td>{{item.creatTime}}</td>
            </tr>

        </table>
        <table class="query-list" v-else>
            <tr><th>暂无结果</th></tr>
        </table>
    </div>
</template>

<script>
export default {
  name: 'QueryList',
  data () {
    return {
      userid: '',
      queryList: []
    }
  },
  methods: {
        // 查询功能
        getQueryList: function() {
            var id = this.userid;
            const that=this;
            console.log(id);
            //if(id !== '') {
                // to-do
                this.$axios({
                    url: 'http://localhost:8011/tt-manage/userReward/list',
                    method: 'post',
                    data: {
                            userId:id
                            }
                }).then(res => {
                var result = res.data;
                that.queryList = result.data;
                    console.log(res);
                }).catch(err => {
                    console.log(err);
                });
            //}
        },
        // 导出功能
        derive: function() {
                var id = this.userId;
                this.$axios({
                    url: 'http://localhost:8011/tt-manage/userReward/excelOut',
                    method: 'post',
                    headers: {
                          'Content-Type': 'application/json',
                        },
                    data: {
                            userId:id
                            }
                }).then(res => {
                //可以访问后台 但是还没实现下载
                    var result = res.data;
                    console.log(res);
                }).catch(err => {
                    console.log(err);
                });
        }
  },
  mounted() {
      this.getQueryList();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.query-bd {
    height: 100%;
    background: #9d0013;
}
.title {

    background: #a40014;
    text-align: center;
    height: 50px;
    font-size: 24px;
    line-height: 50px;
    font-weight: bold;
    color: #fadfa1;
}
.query-area {
    text-align: center;
    font-size: 0;
    margin-top: 20px;
    margin-bottom: 10px;
}
.query-area .label {
    font-size: 14px;
    color: #fadfa1;
    margin-right: 10px;
    display: inline-block;
    vertical-align: middle;
}
.user-id {
    font-size: 14px;
    color: #fadfa1;
    height: 26px;
    line-height: 26px;
    padding: 0 10px;
    background: rgba(100,0,0, .9);
    vertical-align: middle;
    border-radius: 26px;
    margin-right: 20px;
    width: 300px;
}
.btn {
    display: inline-block;
    width: 60px;
    height: 26px;
    border-radius: 26px;
    background: #fadfa1;
    line-height: 26px;
    text-align: center;
    color: #9d0013;
    font-size: 14px;
    vertical-align: middle;
}
.btn-query {
    margin-right: 20px;
}
table {
    width: 580px;
    margin: 0 auto;
    background: rgba(100,0,0, .9);
    border: 1px solid rgba(221, 181, 19, 0.8);
    color: #fadfa1;
}
tr,td,th {
    border: 1px solid rgba(221, 181, 19, 0.8);
}
td,th {
    padding: 10px;
    text-align: center;
}
.empty {
    font-size: 14px;
    color: #fadfa1;
    text-align:center;
}
</style>
