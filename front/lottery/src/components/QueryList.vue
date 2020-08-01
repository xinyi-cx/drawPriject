<template>
  <div class="query-bd">
    <div class="title">
      红包查询
      <router-link v-show="isExport" class="title router" :to="{name: 'Admin'}">抽奖设置管理端</router-link>
    </div>

    <div class="query-area" v-show="isExport">
      <label class="label" for>用户id:</label>
      <input class="user-id" v-model="userid" type="text" placeholder="请输入用户id" />
      <button class="btn btn-query" @click="getQueryList">查询</button>
      <button class="btn" @click="exportData">导出</button>
    </div>
    <table class="query-list" v-if="queryList.length > 0">
      <tr>
        <th>用户ID</th>
        <th>用户姓名</th>
        <th>中奖金额</th>
        <th>中奖时间</th>
      </tr>
      <tr v-for="item in queryList" v-bind:key="item.id">
        <td>{{item.userId}}</td>
        <td>{{item.userName}}</td>
        <td>{{item.reward}}</td>
        <td>{{item.creatTimeStr}}</td>
      </tr>
    </table>
    <table class="query-list" v-else>
      <tr>
        <th>暂无结果</th>
      </tr>
    </table>
  </div>
</template>

<script>
import { export2Excel } from '@/common/js/utils'

export default {
  name: "QueryList",
  data() {
    return {
      userid: "",
      queryList: [],
      isExport: false,
      columns: [{title: '用户ID', key: 'userId'}, {title: '用户姓名', key: 'userName'}, {title: '中奖金额', key: 'reward'}, {title: '中奖时间', key: 'creatTimeStr'}],
      tableData: []
    };
  },
  created() {
      this.isPersonalQuery();
  },
  methods: {
    // 查询功能
    getQueryList: function (id) {
      var id = id ? id : this.userid;
      const that = this;
      console.log(id);
      //if(id !== '') {
      // to-do
      this.$axios({
        url: 'http://192.168.0.101:8848/tt-manage/userReward/list',
        method: "post",
        data: {
          userId: id,
        },
      })
        .then((res) => {
          var result = res.data;
          that.queryList = result.data;
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      //}
    },
    // 导出功能  访问导出接口有问题
    derive: function () {
      var id = this.userId;
      this.$axios({
        url: "http://47.107.76.247:8848/tt-manage/userReward/excelOut",
        method: "post",
        headers: {
          "Content-Type": "application/json",
        },
        data: {
          userId: id,
        },
      })
        .then((res) => {
          //可以访问后台 但是还没实现下载
          var result = res.data;
          console.log(result);
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    isPersonalQuery: function() {
        const userId = this.$route.params.userId;
        if(userId) {
            this.isExport = false;
            this.getQueryList(userId);
        } else {
            this.isExport = true;
            this.getQueryList();
        }
    },
    exportData: function() {
      export2Excel(this.columns, this.queryList);
    }
  },
  // mounted() {
  //   this.getQueryList();
  // }
};
</script>

<style scoped>
.query-bd {
  min-height: 100%;
  background: #9d0013;
  padding-bottom: 30px;
}
.title {
  background: #a40014;
  text-align: center;
  height: 50px;
  font-size: 24px;
  line-height: 50px;
  font-weight: bold;
  color: #fadfa1;
  position: relative;
}
.title.router {
  position: absolute;
  top: 0;
  left: calc(50% + 120px);
  color: #2c3e50;
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
  background: rgba(100, 0, 0, 0.9);
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
  cursor: pointer;
}
.btn-query {
  margin-right: 20px;
}
table {
  width: 580px;
  margin: 0 auto;
  background: rgba(100, 0, 0, 0.9);
  border: 1px solid rgba(221, 181, 19, 0.8);
  color: #fadfa1;
}
tr,
td,
th {
  border: 1px solid rgba(221, 181, 19, 0.8);
}
td,
th {
  padding: 10px;
  text-align: center;
}
.empty {
  font-size: 14px;
  color: #fadfa1;
  text-align: center;
}
@media screen and (max-width: 750px) {
  .query-bd {
    height: auto;
  }
  .query-list {
    width: 98%;
  }
  .user-id {
    width: 110px;
  }
  .title {
    font-size: 18px;
  }
  .title.router {
    position: absolute;
    top: 0;
    left: calc(50% + 48px);
    color: #2c3e50;
  }
}
</style>
