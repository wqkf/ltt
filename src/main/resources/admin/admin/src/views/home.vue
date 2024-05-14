<template>
<div class="content">
			<div class="text main-text" :style='{"padding":"0","boxShadow":"0 0 0px rgba(0,0,0,.1)","margin":"0 auto","borderColor":"rgba(0,0,0,.3)","backgroundColor":"#f7f7f7","color":"#333","borderRadius":"6px","borderWidth":"0","width":"auto","lineHeight":"70px","fontSize":"25px","borderStyle":"solid"}'>欢迎使用 {{this.$project.projectName}}</div>
	</div>
  <!-- 4.新增/编辑窗体 -->
  <el-dialog :title="风险预警" :visible.sync="dialogFormVisible" @close="doSub()">
    <div class="alert-warning">
      <div class="alert-content">
        <slot>红色预警</slot> <!-- 这里可以插入警报内容 -->
      </div>
      <button class="close-button" @click="closeAlert">&times;</button>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="doSub()">确 定</el-button>
    </div>
  </el-dialog>

</template>
<script>
import router from '@/router/router-static'
export default {
	data() {
		return {
      dialogFormVisible: false,
      count: 0,
    };
	},
  mounted(){
    this.init();
  },
  methods:{
    init(){
        this.$http({
            url: `risk/remind`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code == 0) {
            this.count = data.count;
            }
        });

    }, doSub() {
      this.dialogFormVisible = false;
    },
  }
};
</script>

<style lang="scss" scoped>
.alert-warning {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  padding: 15px;
  margin: 0;
  color: white;
  background-color: #C00000; /* 红色警报背景 */
  box-sizing: border-box;
  text-align: center;
}

.alert-content {
  margin: 0 auto;
  padding: 10px 20px;
  text-align: center;
  border-radius: 5px;
}
.content {
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  min-height: 500px;
  text-align: center;
  background: transparent;
  .main-text{
    font-size: 38px;
    font-weight: bold;
    margin-top: 15%;
  }
  .text {
    font-size: 24px;
    font-weight: bold;
    color: #333;
  }
}
</style>