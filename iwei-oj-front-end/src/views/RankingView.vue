<template>
  <div id="rank-view">
    <el-row style="height: calc(100vh - 100px)">
      <el-col :span="6" />
      <el-col :span="12" style="box-shadow: #bebebe 1px 1px 5px; padding: 10px">
        <ranking-analysis
          style="width: 100%"
          :name="data.name"
          :accept-cnt="data.acceptCnt"
          :submit-cnt="data.submitCnt"
        />
        <ranking-list
          :data="ranking"
          :base-query="baseQuery"
          :handle-current-change="handleCurrentChange"
          :handle-size-change="handleSizeChange"
        />
      </el-col>
      <el-col :span="6" />
    </el-row>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import UserService from "@/service/UserService";
import { useRoute, useRouter } from "vue-router";
import RankingAnalysis from "@/components/analysis/RankingAnalysis.vue";
import RankingList from "@/components/RankingList.vue";

const route = useRoute();
const router = useRouter();

const baseQuery = ref({
  page: {
    pageNumber: parseInt(route.query?.pageNumber ?? 1),
    pageSize: parseInt(route.query?.pageSize ?? 10),
  },
});

const ranking = ref({});

const topRanking = ref({});

onMounted(() => {
  doInit();
});

const doInit = () => {
  getTopRaking(1, 10);
  getRankingList(baseQuery);
};

const data = ref({
  name: [],
  acceptCnt: [],
  submitCnt: [],
});
const getTopRaking = async (start, end) => {
  const obj = await UserService.queryRanking({
    page: {
      pageNumber: start,
      pageSize: end,
    },
  });
  if (obj.code === 0) {
    topRanking.value = obj.data;
    let collection = topRanking.value?.collection ?? [];
    for (let i = 0; i < collection.length; i++) {
      data.value.name.push(collection[i].name);
      data.value.acceptCnt.push(collection[i].acceptCnt);
      data.value.submitCnt.push(collection[i].submitCnt);
    }
  }
};

const handleSizeChange = (val) => {
  baseQuery.value.page.pageSize = val;
  router.push({
    query: {
      pageNumber: baseQuery.value.page.pageNumber,
      pageSize: val,
    },
  });
  console.log(baseQuery);
  getRankingList(baseQuery);
};

const handleCurrentChange = (val) => {
  baseQuery.value.page.pageNumber = val;
  router.push({
    query: {
      pageNumber: val,
      pageSize: baseQuery.value.page.pageSize,
    },
  });
  console.log(baseQuery);
  getRankingList(baseQuery);
};

const getRankingList = async (baseQuery) => {
  const obj = await UserService.queryRanking(baseQuery.value);
  if (obj.code === 0) {
    ranking.value = obj.data;
  }
};
</script>

<style scoped></style>
