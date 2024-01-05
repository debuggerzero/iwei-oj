<template>
  <div class="view-box">
    <el-row style="height: 100%; box-shadow: #eee 1px 1px 5px">
      <el-col :span="20" style="box-shadow: #eee 1px 1px 5px">
        <problem-list />
      </el-col>
      <el-col :span="4" style="box-shadow: #eee 1px 1px 5px">
        <tag-list />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import ProblemList from "@/components/ProblemList.vue";
import TagList from "@/components/TagList.vue";
</script>

<style scoped>
.view-box {
}
</style>
