<template>
  <el-row id="globalHeader" align="middle" :gutter="8">
    <el-col :span="22">
      <el-menu
        mode="horizontal"
        @select="doMenuClick"
        :default-active="selectedKeys"
        style="border: none"
      >
        <el-menu-item style="opacity: 0.8" disabled>
          <div class="title-bar">
            <img class="logo" src="../../public/logo.png" alt="" />
            <div class="title">IWEI OJ</div>
          </div>
        </el-menu-item>
        <el-menu-item
          v-for="item in clientVisibleRoutes"
          :index="item.path"
          :key="item.path"
        >
          <template #title>
            <el-icon v-html="item.icon"></el-icon>
            <el-text>{{ item.name }}</el-text>
          </template>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="2">
      <el-dropdown trigger="click" style="width: 100%" @command="doMenuClick">
        <el-row align="middle" :gutter="4">
          <el-col :span="10">
            <el-avatar
              :icon="UserFilled"
              v-if="loginUser.avatar === 'default.png'"
            />
            <el-avatar :src="loginUser.avatar" v-else />
          </el-col>
          <el-col :span="14" style="text-align: center">
            <el-row align="middle" :gutter="4">
              <el-col :span="22">
                <el-text size="default" :line-clamp="1">
                  {{ loginUser.name }}
                </el-text>
              </el-col>
              <el-col :span="2">
                <el-icon class="el-icon--left">
                  <arrow-down />
                </el-icon>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item
              v-for="item in userVisibleRoutes"
              :command="item.path"
              :key="item.name"
            >
              <el-icon v-html="item.icon" />
              {{ item.name }}
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-col>
  </el-row>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { computed, ref } from "vue";
import { clientRoutes } from "@/router/childRouter/clientRoutes";
import { ArrowDown, UserFilled } from "@element-plus/icons-vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";
import { userRoutes } from "@/router/childRouter/userRoutes";
import { USER_ROLE_ENUM } from "@/constant/accessEnum";
import UserService from "@/service/UserService";
import { loginRoutes } from "@/router/childRouter/loginRoutes";

const router = useRouter();
const route = useRoute();
const store = useStore();

const loginUser = ref(store.state.user.loginUser);

const clientVisibleRoutes = computed(() => {
  return clientRoutes.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    return checkAccess(loginUser.value, item?.meta?.access);
  });
});

const userVisibleRoutes = computed(() => {
  let resultRouters = [];
  resultRouters = resultRouters.concat(loginRoutes).concat(userRoutes);
  return resultRouters.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    const user = loginUser.value;
    if (
      user.role?.name === USER_ROLE_ENUM.NO_LOGIN &&
      item.meta?.access === USER_ROLE_ENUM.NO_LOGIN
    ) {
      return true;
    } else if (
      user.role?.name !== USER_ROLE_ENUM.NO_LOGIN &&
      item.meta?.access !== USER_ROLE_ENUM.NO_LOGIN
    ) {
      return checkAccess(user, item?.meta?.access);
    }
  });
});

const selectedKeys = ref(route?.path ?? "/");

router.afterEach((to) => {
  selectedKeys.value = to.path;
});

const doLogout = async () => {
  const data = await UserService.logout();
  return data === 0;
};

const doMenuClick = (key) => {
  if (key === "/user/logout") {
    if (doLogout()) {
      store.dispatch("user/getLoginUser");
      router.go(0);
    }
  } else {
    router.push({
      path: key,
      replace: true,
    });
  }
};
</script>

<style scoped>
#globalHeader {
  width: 100%;

  .title-bar {
    display: flex;
    align-items: center;

    .title {
      color: #444;
      margin-left: 16px;
    }

    .logo {
      height: 48px;
    }
  }
}
</style>
