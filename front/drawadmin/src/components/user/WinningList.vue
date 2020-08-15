<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>中奖列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 搜索区域 -->
      <el-row :gutter="30">
        <el-col :span="8">
          <el-date-picker
            v-model="queryInfo.dateRange"
            type="daterange"
            value-format="yyyy-MM-dd"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-col>
        <el-col :span="6">
          <el-input
            placeholder="请输入用户ID"
            v-model="queryInfo.userId"
            clearable
            @clear="getQueryList"
          ></el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="getQueryList">查询</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="excelOut">导出</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="distribute">派发</el-button>
        </el-col>
      </el-row>
      <!-- 查询列表区 -->
      <el-table border stripe :data="queryList" ref="tabelRef" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column label="用户Id" prop="userId"></el-table-column>
        <el-table-column label="用户名" prop="userName"></el-table-column>
        <el-table-column label="中奖金额" prop="reward"></el-table-column>
        <el-table-column label="中奖时间" prop="creatTimeStr"></el-table-column>
        <el-table-column label="状态" prop="rewardStatus"></el-table-column>
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
    </el-card>
  </div>
</template>

<script>
import { export2Excel } from '@/common/js/utils'
import moment from 'moment'

export default {
  data() {
    return {
      // 问题： 还应该有个日期？
      queryInfo: {
        userId: "",
        // 当前页码
        pageNum: 1,
        // 当前每页显示数据
        pageSize: 5,
        dateRange: [],
        beginTime:'',
        endTime:''
      },
      queryList: [],
      total: 0,
      exportInfo: {
        userId: "",
        beginTime:'',
        endTime:''
      },
      exportList: [],
      columns: [{title: '用户ID', key: 'userId'},
        {title: '用户姓名', key: 'userName'},
        {title: '中奖金额', key: 'reward'},
        {title: '中奖时间', key: 'creatTimeStr'},
        {title: '状态', key: 'rewardStatus'} ],
      multipleSelection: []
    };
  },
  created() {
    this.getQueryList();
  },
  methods: {
    // 格式化日期
    dateFormat(row, column) {
      const date = row[column.property];
      if (date == undefined) {
        return "";
      }
      return moment(date).format("YYYY-MM-DD  HH:mm:ss");
    },
    async getQueryList() {
      if (null != this.queryInfo.dateRange && '' != this.queryInfo.dateRange) {
        this.queryInfo.beginTime = this.queryInfo.dateRange[0];
        this.queryInfo.endTime = this.queryInfo.dateRange[1];
      }
      if (null == this.queryInfo.dateRange || '' == this.queryInfo.dateRange) {
        this.queryInfo.beginTime = '';
        this.queryInfo.endTime = '';
      }
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
    async excelOut () {
      this.exportInfo.userId = this.queryInfo.userId;
      this.exportInfo.beginTime = this.queryInfo.beginTime;
      this.exportInfo.endTime = this.queryInfo.endTime;
      const { data: res } = await this.$http.get("userReward/list", {
        params: this.exportInfo,
      });
      if (res.code !== 0) return this.$message.error("获取查询列表失败");
      this.exportList = res.data;
      export2Excel(this.columns, this.exportList);
    },
    handleSelectionChange(rows) {
      this.multipleSelection = [];
      rows.forEach(row => {
        this.multipleSelection.push(row.userRewardId);
      });
    },
    async distribute() {
      console.log(this.multipleSelection);
      debugger;
      const {data: res} = await this.$http.post(`userReward/updateStatus/${this.multipleSelection}`);
      if(res.code !== 0) return this.$message.error('派发失败，请联系管理员');
      this.$message.success('派发成功！');
      this.getQueryList();
    }
  }
};
</script>

<style lang="scss" scoped>
.el-table {
  margin-top: 15px;
  font-size: 14px;
}
</style>
