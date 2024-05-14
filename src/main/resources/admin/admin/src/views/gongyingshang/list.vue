<template>
  <div>
    <div class="container">
      <div class="login-form">
        <el-form ref="rgsForm" class="rgs-form" :model="rgsForm">
          <el-input v-model="ruleForm.id" autocomplete="off" placeholder="ID" style="opacity:0" />
          <el-form-item label="温度" class="input">
            <el-input v-model="ruleForm.heat" autocomplete="off" placeholder="温度"  />
          </el-form-item>
          <el-form-item label="湿度" class="input">
            <el-input v-model="ruleForm.humidity" autocomplete="off" placeholder="湿度"  />
          </el-form-item>
          <el-form-item label="道路状态" class="input" v-if="role==3 || role==4">
            <el-input v-model="ruleForm.road" autocomplete="off" placeholder="道路状态"  />
          </el-form-item>
          <el-form-item label="粮食存储数量" class="input" v-if="role!=3">
            <el-input v-model="ruleForm.num" autocomplete="off" placeholder="粮食存储数量"  />
          </el-form-item>
          <el-form-item label="粮食存储时间" class="input" v-if="role!=3">
            <el-input v-model="ruleForm.stockTime" autocomplete="off" placeholder="粮食存储时间"  />
          </el-form-item>
<!--          <el-form-item label="密码" class="input" v-if="tableName=='yonghu'">-->
<!--            <el-input v-model="ruleForm.password" autocomplete="off" placeholder="密码" type="password" #elsetype="text" />-->
<!--          </el-form-item>-->
<!--          <el-form-item label="确认密码" class="input" v-if="tableName=='yonghu'">-->
<!--            <el-input v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password"/>-->
<!--          </el-form-item>-->
          <!-- <div v-if="tableName=='yonghu'" class="input-group">
             <div class="label">年龄</div>
             <div class="input-container">
               <input v-model="ruleForm.nianling" class="input" type="text" placeholder="年龄">
             </div>
           </div> -->
          <!-- <div v-if="tableName=='yonghu'" class="input-group">
             <div class="label">手机号</div>
             <div class="input-container">
               <input v-model="ruleForm.shoujihao" class="input" type="text" placeholder="手机号">
             </div>
           </div> -->
          <div style="display: flex;flex-wrap: wrap;width: 100%;justify-content: center;">
            <el-button class="btn" type="primary" @click="login()">提交</el-button>
          </div>
        </el-form>
      </div>
      <!-- <div class="nk-navigation">
        <a href="#">
          <div @click="login()">注册</div>
        </a>
      </div> -->
    </div>
  </div>
</template>
<script>


export default {
  data() {
    return {
      ruleForm: {
      },
      role:1,
      rules: {},
    };
  },
  mounted(){
    this.role = this.$storage.get("role");
    this.$http({
      url: `threshold/list`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
  },
  created() {

  },
  methods: {
    // 获取uuid
    getUUID () {
      return new Date().getTime();
    },
    close(){
      this.$router.push({ path: "/login" });
    },
    // 注册
    login() {
      var url="threshold/save";
      if((!this.ruleForm.heat)){
        this.$message.error(`溫度不能为空`);
        return
      }
      if((!this.ruleForm.humidity)){
        this.$message.error(`濕度不能为空`);
        return
      }
      if((!this.ruleForm.road) && (this.role==3||this.role==4)){
        this.$message.error(`道路狀態不能为空`);
        return
      }
      if((this.ruleForm.password!=this.ruleForm.mima2) && `yonghu` == this.tableName){
        this.$message.error(`两次密码输入不一致`);
        return
      }
      if(`yonghu` == this.tableName && this.ruleForm.age&&(!this.$validate.isIntNumer(this.ruleForm.age))){
        this.$message.error(`年龄应输入整数`);
        return
      }
      if(`yonghu` == this.tableName && this.ruleForm.idNumber&&(!this.$validate.checkIdCard(this.ruleForm.idNumber))){
        this.$message.error(`身份证号格式不正确`);
        return
      }
      this.$http({
        url: url,
        method: "post",
        data:this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "设置成功",
            type: "success",
            duration: 1500,
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.el-radio__input.is-checked .el-radio__inner {
  border-color: #00c292;
  background: #00c292;
}

.el-radio__input.is-checked .el-radio__inner {
  border-color: #00c292;
  background: #00c292;
}

.el-radio__input.is-checked .el-radio__inner {
  border-color: #00c292;
  background: #00c292;
}

.el-radio__input.is-checked+.el-radio__label {
  color: #00c292;
}

.el-radio__input.is-checked+.el-radio__label {
  color: #00c292;
}

.el-radio__input.is-checked+.el-radio__label {
  color: #00c292;
}

.h1 {
  margin-top: 10px;
}

body {
  padding: 0;
  margin: 0;
}

// .container {
//    min-height: 100vh;
//    text-align: center;
//    // background-color: #00c292;
//    padding-top: 20vh;
//    background-image: url(../assets/img/bg.jpg);
//    background-size: 100% 100%;
//    opacity: 0.9;
//  }

// .login-form:before {
// 	vertical-align: middle;
// 	display: inline-block;
// }

// .login-form {
// 	max-width: 500px;
// 	padding: 20px 0;
// 	width: 80%;
// 	position: relative;
// 	margin: 0 auto;

// 	.label {
// 		min-width: 60px;
// 	}

// 	.input-group {
// 		max-width: 500px;
// 		padding: 20px 0;
// 		width: 80%;
// 		position: relative;
// 		margin: 0 auto;
// 		display: flex;
// 		align-items: center;

// 		.input-container {
// 			display: inline-block;
// 			width: 100%;
// 			text-align: left;
// 			margin-left: 10px;
// 		}

// 		.icon {
// 			width: 30px;
// 			height: 30px;
// 		}

// 		.input {
// 			position: relative;
// 			z-index: 2;
// 			float: left;
// 			width: 100%;
// 			margin-bottom: 0;
// 			box-shadow: none;
// 			border-top: 0px solid #ccc;
// 			border-left: 0px solid #ccc;
// 			border-right: 0px solid #ccc;
// 			border-bottom: 1px solid #ccc;
// 			padding: 0px;
// 			resize: none;
// 			border-radius: 0px;
// 			display: block;
// 			width: 100%;
// 			height: 34px;
// 			padding: 6px 12px;
// 			font-size: 14px;
// 			line-height: 1.42857143;
// 			color: #555;
// 			background-color: #fff;
// 		}

// 	}
// }

.nk-navigation {
  margin-top: 15px;

  a {
    display: inline-block;
    color: #fff;
    background: rgba(255, 255, 255, .2);
    width: 100px;
    height: 50px;
    border-radius: 30px;
    text-align: center;
    display: flex;
    align-items: center;
    margin: 0 auto;
    justify-content: center;
    padding: 0 20px;
  }

  .icon {
    margin-left: 10px;
    width: 30px;
    height: 30px;
  }
}

.register-container {
  margin-top: 10px;

  a {
    display: inline-block;
    color: #fff;
    max-width: 500px;
    height: 50px;
    border-radius: 30px;
    text-align: center;
    display: flex;
    align-items: center;
    margin: 0 auto;
    justify-content: center;
    padding: 0 20px;

    div {
      margin-left: 10px;
    }
  }
}

.container {
  height: 100vh;
  background-position: center center;
  background-size: cover;
  background-repeat: no-repeat;

  .login-form {
    right: 50%;
    top: 50%;
    transform: translate3d(50%, -50%, 0);
    border-radius: 10px;
    background-color: rgba(255,255,255,.5);
    font-size: 14px;
    font-weight: 500;
    box-sizing: border-box;

    width: 360px;
    height: auto;
    padding: 15px;
    margin: 0 auto;
    border-radius: 10px;
    border-width: 0;
    border-style: solid;
    border-color: rgba(255,0,0,0);
    background-color: rgba(229, 238, 229, 0.46);
    box-shadow: 0 0 6px rgba(255,0,0,.1);

    .h1 {
      width: auto;
      height: auto;
      line-height:auto;
      color: rgba(24, 103, 228, 1);
      font-size: 28px;
      padding: 0;
      margin: 0 auto;
      border-radius: 0;
      border-width: 0;
      border-style: solid;
      border-color: rgba(255,0,0,0);
      background-color: rgba(255,0,0,0);
      box-shadow: 0 0 6px rgba(255,0,0,0);
      text-align: center;
    }

    .rgs-form {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      .el-form-item {
        width: 100%;
        display: flex;

        & /deep/ .el-form-item__content {
          flex: 1;
          display: flex;
        }
      }

      .input {
        width: 100%;
        height:auto;
        padding: 0;
        margin: 0 0 12px 0;
        border-radius: 0;
        border-width: 0;
        border-style: solid;
        border-color: rgba(255,0,0,0);
        background-color: rgba(255,0,0,0);
        box-shadow: 0 0 6px rgba(255,0,0,0);

        & /deep/ .el-form-item__label {
          width: 84px;
          line-height:44px;
          color: rgba(30, 144, 255, 1);
          font-size: #606266;
          padding: 0 10px 0 0;
          margin: 0;
          border-radius: 0;
          border-width: 0;
          border-style: solid;
          border-color: rgba(255,0,0,0);
          background-color: rgba(255,0,0,0);
          box-shadow: 0 0 6px rgba(255,0,0,0);
        }

        & /deep/ .el-input__inner {
          width: 100%;
          height: 40px;
          line-height:40px;
          color: rgba(42, 102, 221, 1);
          font-size: 14px;
          padding: 0 12px;
          margin: 0;
          border-radius: 4px;
          border-width: 1px;
          border-style: solid;
          border-color: #606266;
          background-color: #fff;
          box-shadow: 0 0 6px rgba(255,0,0,0);
          text-align: left;
        }
      }

      .send-code {
        & /deep/ .el-input__inner {
          width: 180px;
          height: 44px;
          line-height:44px;
          color: #606266;
          font-size: 14px;
          padding: 0 12px;
          margin: 0;
          border-radius: 0;
          border-width: 1;
          border-style: solid;
          border-color: #606266;
          background-color: #fff;
          box-shadow: 0 0 6px rgba(255,0,0,0);
          text-align: left;
        }

        .register-code {
          margin: 0;
          padding: 0;
          width: 86px;
          height: 44px;
          line-height:44px;
          color: #fff;
          font-size: 14px;
          border-width: 0;
          border-style: solid;
          border-color: rgba(255,0,0,0);
          border-radius: 10px;
          background-color: rgba(0, 206, 209, 1);
          box-shadow: 0 0 6px rgba(255,0,0,0);
        }
      }

      .btn {
        margin: 0 10px;
        padding: 0;
        width: 88px;
        height: 45px;
        line-height:45px;
        color: #fff;
        font-size: 14px;
        border-width: 0px;
        border-style: solid;
        border-color: #409EFF;
        border-radius: 4px;
        background-color: rgba(24, 144, 255, 1);
        box-shadow: 0 0 6px rgba(255,0,0,0);
      }

      .close {
        margin: 0 10px;
        padding: 0;
        width: 88px;
        height: 44px;
        line-height:44px;
        color: #409EFF;
        font-size: 14px;
        border-width: 1px;
        border-style: solid;
        border-color: #409EFF;
        border-radius: 4px;
        background-color: #FFF;
        box-shadow: 0 0 6px rgba(255,0,0,0);
      }

    }
  }
}
</style>
