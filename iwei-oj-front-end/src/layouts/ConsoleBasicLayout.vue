<template>
  <div class="common-layout">
    <el-container class="common-layout-container">
      <el-aside class="common-layout-aside">
        <el-menu
          mode="vertical"
          @select="doMenuClick"
          :default-active="selectedKeys"
          style="border: none"
        >
          <el-menu-item
            v-for="item in consoleVisibleRoutes"
            :key="item.name"
            :index="item.path"
          >
            <el-icon v-html="item.icon"></el-icon>
            <el-text>{{ item.name }}</el-text>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main class="comment-layout-main">
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { computed, ref } from "vue";
import checkAccess from "@/access/checkAccess";
import { useStore } from "vuex";
import { consoleRoutes } from "@/router/childRouter/consoleRouter";

const router = useRouter();
const route = useRoute();
const store = useStore();

const loginUser = ref(store.state.user.loginUser);

const consoleVisibleRoutes = computed(() => {
  return consoleRoutes.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    return checkAccess(loginUser.value, item?.meta?.access);
  });
});

const selectedKeys = ref(route?.path ?? "/console/index");

router.afterEach((to) => {
  selectedKeys.value = to.path;
});

const doMenuClick = (key) => {
  router.push({
    path: key,
    replace: true,
  });
};
</script>

<style scoped>
.common-layout {
  height: 90vh;
  box-shadow: #eee 1px 1px 5px;

  .common-layout-container {
    height: 100%;

    .common-layout-aside {
      width: 200px;
      box-shadow: #eee 1px 1px 5px;
      margin-right: 5px;
    }

    .comment-layout-main {
    }
  }
}
</style>
