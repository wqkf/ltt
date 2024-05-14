<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :rules="rules"
      :model="ruleForm"
      label-width="80px"
    >
      <el-form-item    label="姓名" prop="name">
        <el-input v-model="ruleForm.name"              placeholder="用户名" ></el-input>
      </el-form-item>
      <!--      </el-col>-->
      <!--       <el-col :span="12">-->
      <el-form-item   label="年龄" prop="age">
        <el-input v-model="ruleForm.age"               placeholder="年龄" ></el-input>
      </el-form-item>
      <!--       </el-col>-->
      <!--       <el-col :span="12">-->
      <el-form-item   label="身份证号" prop="idNumber">
        <el-input v-model="ruleForm.idNumber"               placeholder="身份证号"></el-input>
      </el-form-item>
      <!--       </el-col>-->
      <!--      <el-col :span="12">-->
      <el-form-item     label="驾驶车型" prop="model">
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
      <el-form-item  label="车牌号" prop="carNumber">
        <el-input v-model="ruleForm.carNumber" placeholder="车牌号"></el-input>
      </el-form-item>
      <el-form-item label="审批状态" prop="status">
        <el-input v-model="ruleForm.status" ></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialogVisible: false,
      ruleForm: {},
      user: {},
      rules: {
        password: [
          {
            required: true,
            message: "密码不能为空",
            trigger: "blur"
          }
        ],
        newpassword: [
          {
            required: true,
            message: "新密码不能为空",
            trigger: "blur"
          }
        ],
        repassword: [
          {
            required: true,
            message: "确认密码不能为空",
            trigger: "blur"
          }
        ]
      }
    };
  },
  mounted() {
    this.$http({
      url: `approve/query`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
  },
  methods: {
    onLogout() {
      this.$storage.remove("Token");
      this.$router.replace({ name: "login" });
    },
    // 修改密码
    onUpdateHandler() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          var password = "";
          if (this.user.mima) {
            password = this.user.mima;
          } else if (this.user.password) {
            password = this.user.password;
          }
          if (this.ruleForm.password != password) {
            this.$message.error("原密码错误");
            return;
          }
          if (this.ruleForm.newpassword != this.ruleForm.repassword) {
            this.$message.error("两次密码输入不一致");
            return;
          }
          this.user.password = this.ruleForm.newpassword;
          this.user. mima = this.ruleForm.newpassword;
          this.$http({
            url: `${this.$storage.get("sessionTable")}/update`,
            method: "post",
            data: this.user
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "修改密码成功,下次登录系统生效",
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
      });
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
