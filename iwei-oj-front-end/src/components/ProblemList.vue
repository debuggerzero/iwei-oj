<template>
  <div class="prob-list-box">
    <el-row align="middle">
      <el-col :span="12">
        <el-text size="large" line-clamp="1" style="font-size: 30px"
          >题目列表
        </el-text>
      </el-col>
      <el-col :span="12" style="text-align: right">
        <el-input
          v-model="baseQuery.keyword"
          class="w-50 m-2"
          placeholder="Input Keyword"
          :prefix-icon="Search"
          style="width: 200px; margin-right: 10px"
        />
        <el-button type="primary" @click="doQuery">查询</el-button>
      </el-col>
    </el-row>
    <el-row style="margin-top: 20px">
      <el-col :span="24">
        <el-table
          :data="probInfoList.collection"
          stripe
          style="width: 100%"
          height="calc(100vh - 250px)"
          table-layout="auto"
          @row-click="doClick"
        >
          <el-table-column prop="id" label="序号">
            <template #default="{ $index }">
              {{ $index + 1 }}
            </template>
          </el-table-column>
          <el-table-column prop="title" label="title" />
          <el-table-column prop="difficulty" sortable label="题目难度">
            <template #default="{ row }">
              <el-tag
                type="success"
                class="mx-1"
                effect="dark"
                v-if="row.difficulty === 1"
                >简单
              </el-tag>
              <el-tag
                type="warning"
                class="mx-1"
                effect="dark"
                v-else-if="row.difficulty === 2"
                >中等
              </el-tag>
              <el-tag type="danger" class="mx-1" effect="dark" v-else
                >困难
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="submitCnt" label="提交数" />
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
      </el-col>
    </el-row>
    <el-row align="middle" justify="start" style="margin-top: 20px">
      <el-pagination
        :current-page="baseQuery.page.pageNumber"
        :page-size="baseQuery.page.pageSize"
        :page-sizes="[10, 20, 30, 40, 50, 100]"
        layout="total, prev, pager, next, sizes"
        :total="probInfoList.count"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-row>
  </div>
</template>

<script setup>
import { Search } from "@element-plus/icons-vue";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import ProblemService from "@/service/ProblemService";
import { colors, percentageCal } from "@/utils/CommonUtil";

const route = useRoute();
const router = useRouter();

const baseQuery = ref({
  keyword: "",
  page: {
    pageNumber: parseInt(route.query?.pageNumber ?? 1),
    pageSize: parseInt(route.query?.pageSize ?? 10),
  },
  status: null,
});

const probInfoList = ref({});

const handleSizeChange = (val) => {
  baseQuery.value.page.pageSize = val;
  router.push({
    query: {
      pageNumber: baseQuery.value.page.pageNumber,
      pageSize: val,
    },
  });
  doGetProbInfoList(baseQuery);
};

const handleCurrentChange = (val) => {
  baseQuery.value.page.pageNumber = val;
  router.push({
    query: {
      pageNumber: val,
      pageSize: baseQuery.value.page.pageSize,
    },
  });
  doGetProbInfoList(baseQuery);
};

onMounted(() => {
  doInit();
});

const doClick = (row) => {
  console.log(row);
  router.push({
    path: "/views/problem/" + row.id,
  });
};

const doInit = () => {
  doGetProbInfoList(baseQuery);
};

const doQuery = () => {
  doGetProbInfoList(baseQuery);
};

const doGetProbInfoList = async (baseQuery) => {
  const obj = await ProblemService.getProbInfoVOList(baseQuery.value);
  if (obj.code === 0) {
    probInfoList.value = obj.data;
  }
};
</script>

<style scoped>
.prob-list-box {
  height: 100%;
  padding: 15px;
}
</style>
