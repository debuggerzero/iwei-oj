<template>
  <el-form
    ref="ruleFormRef"
    :model="form"
    status-icon
    :rules="rules"
    label-width="100px"
    class="demo-ruleForm"
    style="transform: translate(-30px)"
  >
    <el-form-item label="账号：" prop="account">
      <el-input
        v-model="form.account"
        placeholder="请输入账号"
        maxlength="20"
      />
    </el-form-item>
    <el-form-item label="密码：" prop="password">
      <el-input
        v-model="form.password"
        type="password"
        placeholder="请输入密码"
        maxlength="20"
        show-password
      />
    </el-form-item>
    <el-form-item>
      <el-button style="width: 47%" type="primary">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { reactive, ref } from "vue";

const ruleFormRef = ref();

const form = reactive({
  account: "",
  password: "",
});

const validatePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请输入密码"));
  } else {
    callback();
  }
};
const validateAccount = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请输入账号"));
  } else {
    callback();
  }
};
//校验
const rules = reactive({
  password: [{ validator: validatePassword, trigger: "blur" }],
  account: [{ validator: validateAccount, trigger: "blur" }],
});
</script>

<style scoped></style>
