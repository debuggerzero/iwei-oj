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
      <el-button style="width: 47%" type="primary" @click="doLogin"
        >登录
      </el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import { reactive, ref } from "vue";
import UserService from "@/service/UserService";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const route = useRoute();
const store = useStore();

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

//执行登录
const doLogin = async () => {
  const data = await UserService.login(form.account, form.password);
  if (data.code === 0) {
    await store.dispatch("user/getLoginUser");
    let path = route.query?.redirect ?? "/";
    await router.push({
      path: path,
      replace: true,
    });
  }
};
</script>

<style scoped></style>
