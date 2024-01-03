<template>
  <div id="basicLayout">
    <el-container class="basicLayout-container">
      <el-header
        class="basicLayout-header"
        style="padding-left: 0; padding-right: 5px"
      >
        <global-header />
      </el-header>
      <el-main class="basicLayout-main">
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>

<style scoped>
#basicLayout {
  height: 100vh;
  width: 100vw;

  .basicLayout-container {
    min-height: 100%;
    max-height: 100%;

    .basicLayout-header {
      margin-bottom: 16px;
      box-shadow: #eee 1px 1px 5px;
    }

    .basicLayout-main {
      --el-main-padding: 5px;
      background: antiquewhite linear-gradient(to right, #fefefe, #fff);
    }
  }
}
</style>
