<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :model="ruleForm"
      label-width="80px"
	  style="background: transparent;"
    >  
     <el-row>
<!--      <el-col :span="12">-->
        <el-form-item   v-if="flag=='yonghu'"  label="姓名" prop="name">
          <el-input v-model="ruleForm.name"              placeholder="用户名" ></el-input>
        </el-form-item>
<!--      </el-col>-->
<!--       <el-col :span="12">-->
         <el-form-item   v-if="flag=='yonghu'"  label="年龄" prop="age">
           <el-input v-model="ruleForm.age"               placeholder="年龄" ></el-input>
         </el-form-item>
<!--       </el-col>-->
<!--       <el-col :span="12">-->
         <el-form-item   v-if="flag=='yonghu'"  label="身份证号" prop="idNumber">
           <el-input v-model="ruleForm.idNumber"               placeholder="身份证号"></el-input>
         </el-form-item>
<!--       </el-col>-->
<!--      <el-col :span="12">-->
        <el-form-item   v-if="flag=='yonghu'"  label="驾驶车型" prop="model">
          <el-input v-model="ruleForm.model"               placeholder="驾驶车型" ></el-input>
        </el-form-item>
<!--      </el-col>-->
<!--      <el-col :span="12">-->
<!--        <el-form-item v-if="flag=='yonghu'"  label="性别" prop="xingbie">-->
<!--          <el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">-->
<!--            <el-option-->
<!--                v-for="(item,index) in yonghuxingbieOptions"-->
<!--                v-bind:key="index"-->
<!--                :label="item"-->
<!--                :value="item">-->
<!--            </el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--      </el-col>-->
      <el-form-item v-if="flag=='yonghu'" label="车牌号" prop="carNumber">
        <el-input v-model="ruleForm.carNumber" placeholder="车牌号"></el-input>
      </el-form-item>
      <el-col :span="24">
      <el-form-item>
        <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
      </el-form-item>
      </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
      ruleForm: {},
      flag: '',
      usersFlag: false,
      yonghuxingbieOptions: [],
    };
  },
  mounted() {
    var table = this.$storage.get("sessionTable");
    this.flag = 'yonghu';
    this.$http({
      url: `yonghu/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
    // this.yonghuxingbieOptions = "男,女".split(',')
  },
  methods: {
    onUpdateHandler() {
      if((!this.ruleForm.name)&& 'yonghu'==this.flag){
        this.$message.error('用户名不能为空');
        return
      }
      if((!this.ruleForm.carNumber)&& 'yonghu'==this.flag){
        this.$message.error('车牌号不能为空');
        return
      }
      if((!this.ruleForm.password)&& 'yonghu'==this.flag){
        this.$message.error('密码不能为空');
        return
      }
      if( 'yonghu' ==this.flag && this.ruleForm.age&&(!isIntNumer(this.ruleForm.age))){
       this.$message.error(`年龄应输入整数`);
        return
      }
      if( 'yonghu' ==this.flag && this.ruleForm.idNumber&&(!checkIdCard(this.ruleForm.idNumber))){
        this.$message.error(`请输入正确的身份证格式`);
        return
      }
      if('yonghu'==this.flag && this.ruleForm.model.trim().length<1) {
	this.$message.error(`车型不能为空`);
        return	
      }
      this.$http({
        url: `yonghu/approve`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "提交信息成功，等待审批",
            type: "success",
            duration: 1500,
            onClose: () => {
            }
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
</style>
