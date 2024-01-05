<template>
  <div id="ranking-list">
    <el-table
      :data="data.collection"
      stripe
      style="width: 100%"
      :height="height"
      table-layout="auto"
    >
      <el-table-column label="序号">
        <template #default="{ $index }">
          {{ $index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="avatar" label="头像">
        <template #default="{ row }">
          <el-image
            :src="row.avatar"
            fit="cover"
            style="width: 48px; height: 48px"
            lazy
          />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="用户名">
        <template #default="{ row }">
          <el-text :line-clamp="1"> {{ row.name }}</el-text>
        </template>
      </el-table-column>
      <el-table-column prop="submitCnt" label="提交数" />
      <el-table-column prop="acceptCnt" label="通过数" />
      <el-table-column label="通过率">
        <template #default="{ row }">
          <el-progress
            :text-inside="true"
            :stroke-width="16"
            :percentage="percentageCal(row.acceptCnt, row.submitCnt)"
            :color="colors"
          />
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="baseQuery.page.pageNumber"
      :page-size="baseQuery.page.pageSize"
      :page-sizes="[10, 20, 30, 40, 50, 100]"
      layout="total, prev, pager, next, sizes"
      :total="data.count"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="margin-top: 10px"
    />
  </div>
</template>

<script setup>
import { defineProps } from "vue";
import { colors, percentageCal } from "@/utils/CommonUtil";

defineProps({
  height: {
    type: Number,
    default: 450,
  },
  data: {
    type: Object,
    default: () => {
      return {};
    },
  },
  baseQuery: {
    type: Object,
    default: () => {
      return {
        page: {
          pageNumber: 1,
          pageSize: 0,
        },
      };
    },
  },
  handleSizeChange: {
    type: Function,
    default: (val) => {
      console.log(val);
    },
  },
  handleCurrentChange: {
    type: Function,
    default: (val) => {
      console.log(val);
    },
  },
});
</script>

<style scoped></style>
