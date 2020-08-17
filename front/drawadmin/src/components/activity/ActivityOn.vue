<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>活动管理</el-breadcrumb-item>
      <el-breadcrumb-item>活动是否开启</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      活动是否开启：
      <el-switch
              v-model="configValue"
              @change="statusChanged(configValue)">
            </el-switch>
    </el-card>
  </div>
</template>

<script>
import moment from "moment";

export default {
  data() {
    return {
      configValue: true,
      activityInfo: {}
    };
  },
  created() {
    this.getActivityStatus();
  },
  methods: {
    async getActivityStatus() {
      const {data: res} = await this.$http.post('systemConfig/selectByPrimaryKey');
      if(res.code !== 0) { return this.$message.error('获取活动状态失败！') };
      this.configValue = (res.data.configValue === '1');
      this.activityInfo = res.data;
    },
    // 活动开启状态切换函数
    async statusChanged(value) {
      console.log(value);
      this.activityInfo.configValue = value ? '1' : '0';
      const {data: res} = await this.$http.post('systemConfig/update', this.activityInfo);
      if(res.code !== 0) return this.$message.error('活动状态更改失败！');

      this.$message.success('活动开启状态已更新！');
      this.getActivityStatus();
    }
  },
};
</script>

<style lang="scss" scoped>
.el-table {
  margin-top: 15px;
  font-size: 14px;
}
</style>
