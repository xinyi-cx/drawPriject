<template>
  <div class="list-area">
    <div class="title">
      抽奖设置管理端
      <router-link class="title router" :to="{name: 'QueryList'}">后台查询列表</router-link>
    </div>

    <div class="list">
      <div class="list-item" v-for="item in formData" v-bind:key="item.id">
        <label class="label">{{item.digit}}:</label>
        <input type="text" v-model="item['digitStart']" v-number-only placeholder="点击设定最小值" />
        <input type="text" v-model="item['digitEnd']" v-number-only placeholder="点击设定最大值" />
      </div>
      <button class="btn-save" @click="saveDraw">保存</button>
    </div>
    <div class="rule-list">
      <div class="desc">
        <span class="rule-label">打码量</span>
        <span class="rule-label">奖金位数</span>
      </div>
      <div class="rule-item" v-for="item in ruleData" :key="item.id">
        <input class="rule-input" v-number-only type="text" v-model="item['code']" />
        <span class="rule-input">{{item.drawDigit}}</span>
      </div>
    </div>
    <button class="btn-save" @click="save">保存</button>

    <el-upload
      ref="upload"
      :limit="1"
      accept=".xlsx, .xls"
      :headers="upload.headers"
      :action="upload.url + '?updateSupport=' + upload.updateSupport"
      :disabled="upload.isUploading"
      :on-progress="handleFileUploadProgress"
      :on-success="handleFileSuccess"
      :auto-upload="false"
      drag
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">
        将文件拖到此处，或
        <em>点击上传</em>
      </div>
      <div class="el-upload__tip" style="color:white" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
    </el-upload>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitFileForm">确 定</el-button>
      <el-button @click="cancelFile">取 消</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/request";

export default {
  name: "HelloWorld",
  directives: {
    numberOnly: {
      bind(el) {
        el.handler = function() {
          el.value = el.value.replace(/\D+/, '');
        };
        el.addEventListener('input', el.handler);
      },
      unbind(el) {
        el.removeEventListener('input', el.handler);
      }
    }
  },
  data() {
    return {
      formData: [],
      ruleData: [],
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 上传的地址
        url: "http://47.107.76.247:8848/tt-manage/userCodeRef/excelIn",
      },
    };
  },
  created() {
    this.getDate();
    this.getRuleData();
  },
  methods: {
    getDate: function () {
      const that = this;
      this.$axios({
        url: "http://47.107.76.247:8848/tt-manage/drawConfig/list",
        method: "post",
      }).then(function (res) {
        const resultDate = res.data;
        that.formData = resultDate.data;
        console.log("数据提交成功");
        console.log(res.data);
      });
    },
    getRuleData: function () {
      const that = this;
      debugger;
      this.$axios({
        url: "http://47.107.76.247:8848/tt-manage/codeDrawRef/list",
        method: "post",
      }).then(function (res) {
        const resultDate = res.data;
        that.ruleData = resultDate.data;
        console.log("数据提交成功");
        console.log(res.data);
      });
    },
    save: function () {
      console.log(this.ruleData);
      const rule = this.ruleData;
      const that = this;
      // 保存设置数据
      // 这里需要两个保存按钮还是需要一个就可以？
      this.$axios({
        url: "http://47.107.76.247:8848/tt-manage/codeDrawRef/updateByList",
        method: "post",
        data: rule,
      })
        .then(function (res) {
          const resultDate = res.data;
          that.ruleData = rule;
          alert("更新成功");
        })
        .catch(function (err) {
          alert("更新失败，请联系管理员");
          console.log(err);
        });
    },
    saveDraw: function () {
      console.log(this.formData);
      const form = this.formData;
      const that = this;
      // 保存设置数据
      // 这里需要两个保存按钮还是需要一个就可以？
      this.$axios({
        url: "http://47.107.76.247:8848/tt-manage/drawConfig/update",
        method: "post",
        data: form,
      })
        .then(function (res) {
          const resultDate = res.data;
          that.formData = form;
          alert("更新成功");
        })
        .catch(function (err) {
          alert("更新失败，请联系管理员");
          console.log(err);
        });
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      // this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    cancelFile() {
      this.$refs.upload.clearFiles();
    }
  },
};
</script>

<!-- style -->
<style scoped>
.list-area {
  width: 100%;
  min-height: 100%;
  margin: 0 auto 20px;
  background: #fff;
}
.title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  line-height: 3;
  position: relative;
}
.title.router {
  position: absolute;
  top: 0;
  left: calc(50% + 150px);
  color: #fadfa1;
}
.list,
.rule-list {
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
  display: inline-block;
  width: 100px;
  text-align: right;
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
::-webkit-input-placeholder {
  /* WebKit browsers */
  color: #888888;
  font-size: 16px;
  line-height: 1.5;
}
:-moz-placeholder {
  /* Mozilla Firefox 4 to 18 */
  color: #888888;
  font-size: 16px;
  line-height: 1.5;
}
::-moz-placeholder {
  /* Mozilla Firefox 19+ */
  color: #888888;
  font-size: 16px;
  line-height: 1.5;
}
:-ms-input-placeholder {
  /* Internet Explorer 10+ */
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
  margin-bottom: 20px;
}
.rule-list {
  margin-top: 30px;
}
.desc,
.rule-item {
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
.dialog-footer {
  margin-top: 10px;
}
</style>
