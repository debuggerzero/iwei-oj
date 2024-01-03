<template>
  <div style="position: relative; height: 100%; width: 100%">
    <el-row align="middle">
      <el-col :span="12">
        <div>
          <el-button type="primary">添加用户</el-button>
          <el-button type="primary">批量添加用户</el-button>
        </div>
      </el-col>
      <el-col :span="12" style="text-align: right">
        <div>
          <el-input
            v-model="baseQuery.keyword"
            class="w-50 m-2"
            placeholder="Input Keyword"
            :prefix-icon="Search"
            style="width: 200px; margin-right: 10px"
          />
          <el-button type="primary">查询</el-button>
        </div>
      </el-col>
    </el-row>
    <div>
      <el-row align="middle" style="text-align: center">
        <el-table
          :data="data.collection"
          height="75vh"
          highlight-current-row
          style="width: 100%; text-align: center"
          table-layout="auto"
        >
          <el-table-column prop="id" label="ID" sortable>
            <template #default="{ row }">
              <el-text :line-clamp="1"> {{ row.id }}</el-text>
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
          <el-table-column prop="account" label="账号">
            <template #default="{ row }">
              <el-text :line-clamp="1"> {{ row.account }}</el-text>
            </template>
          </el-table-column>
          <el-table-column prop="createPerson" label="创建人">
            <template #default="{ row }">
              <el-text :line-clamp="1"> {{ row.createPerson ?? -1 }}</el-text>
            </template>
          </el-table-column>
          <el-table-column prop="createDate" label="创建时间" sortable>
            <template #default="{ row }">
              <el-text :line-clamp="1">
                {{ formatDate(row.createDate) ?? -1 }}
              </el-text>
            </template>
          </el-table-column>
          <el-table-column prop="updatePerson" label="修改人">
            <template #default="{ row }">
              <el-text :line-clamp="1">
                {{ row.updatePerson ?? -1 }}
              </el-text>
            </template>
          </el-table-column>
          <el-table-column prop="updateDate" label="修改时间" sortable>
            <template #default="{ row }">
              <el-text :line-clamp="1">
                {{ formatDate(row.updateDate) ?? -1 }}
              </el-text>
            </template>
          </el-table-column>
          <el-table-column prop="role" label="用户权限">
            <template #default="{ row }">
              <el-text :line-clamp="1">
                {{ row.role?.describe ?? USER_ROLE_ENUM.NO_LOGIN }}
              </el-text>
            </template>
          </el-table-column>
          <el-table-column label="通过率">
            <template #default="{ row }">
              <el-progress
                :percentage="
                  (row.acceptCnt / (row.submitCnt === 0 ? 1 : row.submitCnt)) *
                  100
                "
                :color="[
                  { color: '#f56c6c', percentage: 20 },
                  { color: '#e6a23c', percentage: 40 },
                  { color: '#5cb87a', percentage: 60 },
                  { color: '#1989fa', percentage: 80 },
                  { color: '#6f7ad3', percentage: 100 },
                ]"
              />
            </template>
          </el-table-column>
          <el-table-column prop="status" sortable label="状态">
            <template #default="{ row }">
              <el-tag
                effect="dark"
                class="mx-1"
                type="success"
                v-if="row.status === 0"
                round
                >正常
              </el-tag>
              <el-tag effect="dark" class="mx-1" type="danger" v-else round
                >禁用
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="280px">
            <template #default>
              <el-button-group>
                <el-button type="primary">浏览</el-button>
                <el-button type="info">修改</el-button>
                <el-button type="danger">删除</el-button>
              </el-button-group>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row>
        <div class="demo-pagination-block">
          <el-pagination
            :current-page="baseQuery.page.pageNumber"
            :page-size="baseQuery.page.pageSize"
            :page-sizes="[10, 20, 30, 40, 50, 100]"
            layout="total, sizes, prev, pager, next"
            :total="data.count"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { Search } from "@element-plus/icons-vue";
import { onMounted, ref } from "vue";
import UserService from "@/service/UserService";
import { useRoute, useRouter } from "vue-router";
import USER_ROLE_ENUM from "../../constant/accessEnum";

const router = useRouter();
const route = useRoute();

const baseQuery = ref({
  keyword: "",
  page: {
    pageNumber: parseInt(route.query?.pageNumber ?? 1),
    pageSize: parseInt(route.query?.pageSize ?? 10),
  },
  status: null,
});

const data = ref({
  collection: [],
  count: 0,
});

const handleSizeChange = (val) => {
  baseQuery.value.page.pageSize = val;
  router.push({
    query: {
      pageNumber: baseQuery.value.page.pageNumber,
      pageSize: val,
    },
  });
  getUserInfoList(baseQuery);
};

const handleCurrentChange = (val) => {
  baseQuery.value.page.pageNumber = val;
  router.push({
    query: {
      pageNumber: val,
      pageSize: baseQuery.value.page.pageSize,
    },
  });
  getUserInfoList(baseQuery);
};

onMounted(() => {
  doInit();
});

const formatDate = (date) => {
  if (date === undefined) {
    return null;
  }
  date = new Date(date);
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, "0");
  const day = date.getDate().toString().padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const doInit = () => {
  getUserInfoList(baseQuery);
};

const getUserInfoList = async (baseQuery) => {
  const obj = await UserService.queryUserInfoList(baseQuery.value);
  if (obj.code === 0) {
    data.value = obj.data;
  }
};
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}
</style>
