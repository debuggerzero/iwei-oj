<template>
  <div style="position: relative; height: 100%; width: 100%">
    <el-row align="middle">
      <el-col :span="12">
        <div>
          <el-button type="primary" @click="addOneDialogVisible = true"
            >添加标签
          </el-button>
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
          <el-table-column prop="name" label="标签名">
            <template #default="{ row }">
              <el-text :line-clamp="1"> {{ row.name }}</el-text>
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
          <el-table-column fixed="right" label="操作" width="200px">
            <template #default="{ row }">
              <el-button-group>
                <el-button
                  type="info"
                  @click="updateClick({ id: row.id, name: row.name })"
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
    v-model="addOneDialogVisible"
    title="添加标签"
    width="20%"
    align-center
  >
    <el-row align="middle" style="text-align: center">
      <el-col :span="8">标签名：</el-col>
      <el-col :span="16">
        <el-input
          v-model="tagRequest.tagInfoVO.name"
          placeholder="input tag name"
        />
      </el-col>
    </el-row>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="doAdd"> 添加 </el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog
    v-model="updateOneDialogVisible"
    title="修改标签"
    width="20%"
    align-center
  >
    <el-row align="middle" style="text-align: center">
      <el-col :span="8">ID：</el-col>
      <el-col :span="16">
        <el-input
          disabled
          v-model="tagRequest.tagInfoVO.id"
          placeholder="input tag name"
        />
      </el-col>
    </el-row>
    <el-row align="middle" style="text-align: center">
      <el-col :span="8">标签名：</el-col>
      <el-col :span="16">
        <el-input
          v-model="tagRequest.tagInfoVO.name"
          placeholder="input tag name"
        />
      </el-col>
    </el-row>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="doUpdate"> 修改 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { Search } from "@element-plus/icons-vue";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import TagService from "@/service/TagService";
import { ElMessage, ElMessageBox } from "element-plus";

const router = useRouter();
const route = useRoute();

const addOneDialogVisible = ref(false);
const updateOneDialogVisible = ref(false);

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

const tagRequest = ref({
  tagInfoVO: {
    id: -1,
    name: "",
  },
});
const handleSizeChange = (val) => {
  baseQuery.value.page.pageSize = val;
  router.push({
    query: {
      pageNumber: baseQuery.value.page.pageNumber,
      pageSize: val,
    },
  });
  getTagInfoList(baseQuery);
};

const handleCurrentChange = (val) => {
  baseQuery.value.page.pageNumber = val;
  router.push({
    query: {
      pageNumber: val,
      pageSize: baseQuery.value.page.pageSize,
    },
  });
  getTagInfoList(baseQuery);
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
  getTagInfoList(baseQuery);
};

const updateClick = ({ id, name }) => {
  updateOneDialogVisible.value = true;
  tagRequest.value.tagInfoVO.id = id;
  tagRequest.value.tagInfoVO.name = name;
};

const doQuery = () => {
  getTagInfoList(baseQuery);
};

const doDelete = async (id) => {
  ElMessageBox.confirm("确定删除这个标签吗?", "Warning", {
    confirmButtonText: "OK",
    cancelButtonText: "Cancel",
    type: "warning",
  })
    .then(async () => {
      const obj = await TagService.deleteTagInfoById(id);
      if (obj.code === 0) {
        ElMessage.success(obj.message);
        doInit();
      }
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "Delete canceled",
      });
    });
};

const doUpdate = async () => {
  const obj = await TagService.updateTagInfoById({
    tagInfoVO: {
      id: tagRequest.value.tagInfoVO.id,
      name: tagRequest.value.tagInfoVO.name,
    },
  });
  if (obj.code === 0) {
    ElMessage.success(obj.message);
    updateOneDialogVisible.value = false;
    doInit();
  }
};

const doAdd = async () => {
  const obj = await TagService.insertTagInfo({
    tagInfoVO: {
      name: tagRequest.value.tagInfoVO.name,
    },
  });
  console.log(obj);
  if (obj.code === 0) {
    ElMessage.success(obj.message);
    addOneDialogVisible.value = false;
    doInit();
  }
};

const getTagInfoList = async (baseQuery) => {
  const obj = await TagService.getTagInfoList(baseQuery.value);
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
