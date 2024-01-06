<template>
  <div style="position: relative; height: 100%; width: 100%">
    <el-row align="middle">
      <el-col :span="12">
        <div>
          <el-button type="primary" @click="doAdd">添加题目</el-button>
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
          <el-button type="primary" @click="doQuery">查询</el-button>
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
          <el-table-column prop="title" label="题目名">
            <template #default="{ row }">
              <el-text :line-clamp="1"> {{ row.title }}</el-text>
            </template>
          </el-table-column>
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
          <el-table-column prop="tagInfos" label="标签">
            <template #default="{ row }">
              <el-text :line-clamp="1">
                <el-tag
                  v-for="item in randomTagTypeList(row.tagInfos)"
                  :key="item.id"
                  :type="item.type"
                  round
                  style="margin-right: 2px"
                >
                  {{ item.name }}
                </el-tag>
              </el-text>
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
          <el-table-column label="通过率">
            <template #default="{ row }">
              <el-progress
                :percentage="percentageCal(row.acceptCnt, row.submitCnt)"
                :color="colors"
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
                >废弃
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作">
            <template #default="{ $index, row }">
              <el-button-group>
                <el-button type="primary" @click="showBrowseDialog(row)"
                  >浏览
                </el-button>
                <el-button type="info" @click="doUpdate($index, row)"
                  >修改
                </el-button>
                <el-button type="danger" @click="doDelete(row.id)"
                  >删除
                </el-button>
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
  <el-dialog
    v-model="editDialogVisible"
    title="题目信息"
    width="70%"
    align-center
    :before-close="handleClose"
    v-if="editDialogVisible"
  >
    <problem-edit-layout
      :form="temp"
      :do-back="
        () => {
          editDialogVisible = false;
        }
      "
    />
  </el-dialog>
  <el-dialog
    v-model="browseDialogVisible"
    title="题目信息"
    width="50%"
    v-if="browseDialogVisible"
    align-center
  >
    <problem-browse :prob-info="temp" />
  </el-dialog>
</template>

<script setup>
import { Search } from "@element-plus/icons-vue";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import ProblemService from "@/service/ProblemService";
import ProblemEditLayout from "@/layouts/ProblemEditLayout.vue";
import { ElMessage, ElMessageBox } from "element-plus";
import {
  colors,
  formatDate,
  percentageCal,
  randomTagTypeList,
} from "@/utils/CommonUtil";
import ProblemBrowse from "@/views/problem/ProblemBrowse.vue";

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

const samples = ref([]);

const editDialogVisible = ref(false);
const browseDialogVisible = ref(false);

let temp = null;
const showBrowseDialog = (row) => {
  temp = row;
  browseDialogVisible.value = true;
};

const handleSizeChange = (val) => {
  baseQuery.value.page.pageSize = val;
  router.push({
    query: {
      pageNumber: baseQuery.value.page.pageNumber,
      pageSize: val,
    },
  });
  getProblemList(baseQuery);
};

const handleCurrentChange = (val) => {
  baseQuery.value.page.pageNumber = val;
  router.push({
    query: {
      pageNumber: val,
      pageSize: baseQuery.value.page.pageSize,
    },
  });
  getProblemList(baseQuery);
};

onMounted(() => {
  doInit();
});

const handleClose = () => {
  ElMessageBox.confirm("Are you sure to close this dialog?")
    .then(() => {
      editDialogVisible.value = false;
    })
    .catch(() => {});
};

const doDelete = (pid) => {
  ElMessageBox.confirm("确定删除这个标签吗?", "Warning", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    type: "warning",
  })
    .then(() => {
      deleteProblemById(pid);
      doInit();
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "Delete canceled",
      });
    });
};

const doUpdate = async (index, row) => {
  await getSamplesList(row.id);
  temp = {
    operate: "update",
    probInfo: data.value.collection[index],
    samples: samples.value,
  };
  editDialogVisible.value = true;
};

const doAdd = () => {
  temp = {
    operate: "add",
    probInfo: ref({
      title: "",
      difficulty: 1,
      timeLimit: 1,
      spaceLimit: 256,
      description: "",
      status: 0,
      tagInfos: [],
    }).value,
    samples: ref([]).value,
  };
  editDialogVisible.value = true;
};

const doQuery = () => {
  getProblemList(baseQuery);
};

const doInit = () => {
  getProblemList(baseQuery);
};

const deleteProblemById = async (pid) => {
  const obj = await ProblemService.deleteProbInfo(pid);
  if (obj.code === 0) {
    ElMessage.success("删除成功...");
  }
};

const getSamplesList = async (pid) => {
  const obj = await ProblemService.getSampleList(pid);
  if (obj.code === 0) {
    samples.value = obj.data.collection;
  }
};

const getProblemList = async (baseQuery) => {
  const obj = await ProblemService.getProbInfoList(baseQuery.value);
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
