<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>抽奖管理</el-breadcrumb-item>
      <el-breadcrumb-item>中奖列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 搜索区域 -->
      <el-row :gutter="30">
        <el-col :span="8">
          <el-date-picker
            v-model="queryInfo.dateRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-col>
        <el-col :span="6">
          <el-input
            placeholder="请输入userId"
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
      </el-row>
      <!-- 查询列表区 -->
      <el-table border stripe :data="queryList">
        <el-table-column type="index" width="55"></el-table-column>
        <el-table-column label="用户Id" prop="userId"></el-table-column>
        <el-table-column label="用户名" prop="userName"></el-table-column>
        <el-table-column label="中奖金额" prop="reward"></el-table-column>
        <el-table-column label="中奖时间" prop="creatTimeStr"></el-table-column>
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
        dateRange: "",
      },
      queryList: [],
      total: 0,
      columns: [{title: '用户ID', key: 'userId'}, {title: '用户姓名', key: 'userName'}, {title: '中奖金额', key: 'reward'}, {title: '中奖时间', key: 'creatTimeStr'}]
    };
  },
  created() {
    this.getQueryList();
  },
  methods: {
    
    async getQueryList() {
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
    excelOut: function () {
      export2Excel(this.columns, this.queryList);
      this.queryList();
    },
  },
};
</script>

<style lang="scss" scoped>
.el-table {
  margin-top: 15px;
  font-size: 14px;
}
</style>