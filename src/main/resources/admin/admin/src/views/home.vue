<template>
<div class="content">
			<div class="text main-text" :style='{"padding":"0","boxShadow":"0 0 0px rgba(0,0,0,.1)","margin":"0 auto","borderColor":"rgba(0,0,0,.3)","backgroundColor":"#f7f7f7","color":"#333","borderRadius":"6px","borderWidth":"0","width":"auto","lineHeight":"70px","fontSize":"25px","borderStyle":"solid"}'>欢迎使用 {{this.$project.projectName}}</div>
  <!-- 4.新增/编辑窗体 -->
  <el-dialog :visible.sync="dialogFormVisible" @close="doSub()" class="warn">
    <div class="alert-warning" v-if="count==4">
      <div class="alert-content">
        <img src="../assets/img/warn.png" alt=""><br>
        <slot>红色预警</slot> <!-- 这里可以插入警报内容 -->
      </div>
    </div>
    <div class="yellow-warning" v-if="count==2">
      <div class="alert-content">
        <img src="../assets/img/warn.png" alt=""><br>
        <slot>黄色预警</slot> <!-- 这里可以插入警报内容 -->
      </div>
    </div>
    <div class="orange-warning" v-if="count==3">
      <div class="alert-content">
        <img src="../assets/img/warn.png" alt=""><br>
        <slot>橙色预警</slot> <!-- 这里可以插入警报内容 -->
      </div>
    </div>
    <div class="blue-warning" v-if="count==1">
      <div class="alert-content">
        <img src="../assets/img/warn.png" alt=""><br>
        <slot>蓝色预警</slot> <!-- 这里可以插入警报内容 -->
      </div>
    </div>
<!--    <div slot="footer" class="dialog-footer">-->
<!--      <el-button type="primary" @click="doSub()">确 定</el-button>-->
<!--    </div>-->
  </el-dialog>
	</div>

</template>
<script>
import router from '@/router/router-static'
export default {
	data() {
		return {
      dialogFormVisible: false,
      count: 0,
      title: '',
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
            if (data && data.code == 0 && data.count!=0) {
            this.count = data.count;
            // if(data.count ==2){
            //   this.title = '黄色预警';
            // }
            this.dialogFormVisible=true;
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
  font-weight: bold;
  font-size: 28px;
  color: #000;
  background-color: #C00000; /* 红色警报背景 */
  box-sizing: border-box;
  text-align: center;
}

.blue-warning {
  font-weight: bold;
  font-size: 28px;
  color: #000;
  background-color: #0000ff;
  box-sizing: border-box;
  text-align: center;
}

.warn {
  margin:  0 auto;
  width: 40%;
}

.orange-warning {
  font-weight: bold;
  font-size: 28px;
  color: #000;
  background-color: #FF7D00;
  box-sizing: border-box;
  text-align: center;
}

.yellow-warning {
  font-weight: bold;
  font-size: 28px;
  color: #000;
  background: linear-gradient(82deg, #ffc700, #ffb300);
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