<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <el-row align="middle">
          <el-col :span="10">
            <el-row align="middle">
              <el-text :line-clamp="1" style="margin-right: 10px"
                >{{ probInfo.title }}
              </el-text>
            </el-row>
          </el-col>
          <el-col :span="14" style="text-align: right">
            <el-tag
              effect="dark"
              type="success"
              v-if="probInfo.difficulty === 1"
              >简单
            </el-tag>
            <el-tag
              effect="dark"
              type="warning"
              v-else-if="probInfo.difficulty === 2"
            >
              中等
            </el-tag>
            <el-tag effect="dark" type="danger" v-else> 困难</el-tag>
          </el-col>
        </el-row>
        <el-row align="middle" style="margin-top: 20px">
          <el-col :span="12">
            <el-text :line-clamp="1">
              <el-tag
                v-for="item in randomTagTypeList(probInfo.tagInfos)"
                :key="item.id"
                :type="item.type"
                round
                style="margin-right: 10px"
              >
                {{ item.name }}
              </el-tag>
            </el-text>
          </el-col>
          <el-col :span="12" style="text-align: right">
            <el-text :line-clamp="1"
              >时 / 空限制: {{ probInfo.timeLimit ?? 0 }} ms /
              {{ probInfo.spaceLimit ?? 0 }} MB
            </el-text>
          </el-col>
        </el-row>
      </div>
    </template>
    <el-scrollbar :height="scrollbarHeight">
      <md-viewer :value="probInfo.description" />
    </el-scrollbar>
  </el-card>
</template>

<script setup>
import { defineProps } from "vue";
import { randomTagTypeList } from "@/utils/CommonUtil";
import MdViewer from "@/components/markdowm/MdViewer.vue";

defineProps({
  probInfo: {
    type: Object,
    default: () => {
      return {
        title: "",
        difficulty: 1,
        timeLimit: 1,
        spaceLimit: 256,
        description: "",
        tagInfos: [],
      };
    },
  },
  scrollbarHeight: {
    type: Number,
    default: 450,
  },
});
</script>

<style scoped>
.el-descriptions__body
  .el-descriptions__table:not(.is-bordered)
  .el-descriptions__cell {
  padding-bottom: 0;
}

.box-card {
}
</style>
