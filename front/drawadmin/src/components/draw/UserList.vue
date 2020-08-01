<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>抽奖管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 搜索区域 -->
      <el-row :gutter="50">
        <el-col :span="6">
          <el-input
            placeholder="请输入userId"
            v-model="queryInfo.userId"
            clearable
            @clear="getQueryList"
          ></el-input>
        </el-col>
        <el-col :span="6">
          <el-input
            placeholder="请输入userName"
            v-model="queryInfo.userName"
            clearable
            @clear="getQueryList"
          >
            <el-button slot="append" icon="el-icon-search" @click="getQueryList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary">添加用户</el-button>
        </el-col>
      </el-row>
      <!-- 查询列表区 -->
      <el-table border stripe :data="queryList">
        <el-table-column type="index" width="55"></el-table-column>
        <el-table-column label="用户Id" prop="userId"></el-table-column>
        <el-table-column label="用户名" prop="userName"></el-table-column>
        <el-table-column label="创建时间" prop="createTime"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="修改" :enterable="false" placement="top">
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit-outline"
                circle
                @click="editDrawDialog(scope.row)"
              >修改</el-button>
            </el-tooltip>
          </template>
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="修改" :enterable="false" placement="top">
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit-outline"
                @click="editDrawDialog(scope.row)"
              >删除</el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 3, 4]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 问题： 还应该有个日期？
      queryInfo: {
        userId: "",
        userName: '',
        // 当前页码
        pageNum: 1,
        // 当前每页显示数据
        pageSize: 2,
      },
      queryList: [],
      total: 0,
    };
  },
  created() {
    this.getQueryList();
  },
  methods: {
    async getQueryList() {
      const { data: res } = await this.$http.get(
        "systemUser/list",
        {
          params: this.queryInfo,
        }
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