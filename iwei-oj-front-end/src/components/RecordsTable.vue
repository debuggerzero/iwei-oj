<template>
  <div id="records-table">
    <el-row>
      <el-table
        :data="submitInfoList.collection"
        stripe
        style="width: 100%"
        :height="tableHeight"
        table-layout="auto"
      >
        <el-table-column prop="id" label="序号">
          <template #default="{ $index }">
            {{ $index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="java" label="language">
          <template #default="{ row }">
            <el-tag type="info" effect="plain">{{ row.language }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="judgeInfo.time" label="耗时">
          <template #default="{ row }">
            {{ row.judgeInfo?.time ?? 0 }} ms
          </template>
        </el-table-column>
        <el-table-column prop="judgeInfo.memory" label="内存">
          <template #default="{ row }">
            {{ row.judgeInfo?.memory ?? 0 }} MB
          </template>
        </el-table-column>
        <el-table-column label="状态">
          <template #default="{ row }">
            <el-text type="warning" :line-clamp="1" v-if="row.status !== 2"
              >提交失败
            </el-text>
            <el-text type="success" :line-clamp="1" v-else>提交成功</el-text>
          </template>
        </el-table-column>
        <el-table-column prop="judgeInfo.message" label="信息">
          <template #default="{ row }">
            <div v-if="row.judgeInfo.message !== null">
              <el-tag
                type="success"
                effect="dark"
                v-if="row.judgeInfo.message === 'Accepted'"
              >
                <el-text size="small" :line-clamp="1"
                  >{{ row.judgeInfo?.message }}
                </el-text>
              </el-tag>
              <el-tag type="warning" effect="dark" v-else>
                <el-text size="small" :line-clamp="1"
                  >{{ row.judgeInfo?.message }}
                </el-text>
              </el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column fixed="right">
          <template #default="{ row }">
            <el-button type="primary" @click="readCode(row.language, row.code)"
              >查看代码
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row align="middle">
      <el-pagination
        style="margin-top: 5px"
        :current-page="baseQuery.pageNumber"
        :page-size="baseQuery.pageSize"
        :page-sizes="[10, 20, 30, 40, 50, 100]"
        layout="total, prev, pager, next, sizes"
        :total="submitInfoList.count"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-row>
    <el-dialog
      v-model="codeDialogVisible"
      title="查看代码"
      width="30%"
      align-center
      v-if="codeDialogVisible"
    >
      <span>
        <md-viewer :value="mdCode" />
      </span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="codeDialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="centerDialogVisible = false">
            Confirm
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { onMounted, ref, defineProps, toRefs } from "vue";
import ProblemService from "@/service/ProblemService";
import { useRoute, useRouter } from "vue-router";
import MdViewer from "@/components/markdowm/MdViewer.vue";

const route = useRoute();
const router = useRouter();

const baseQuery = ref({
  pageNumber: parseInt(route.query?.pageNumber ?? 1),
  pageSize: parseInt(route.query?.pageSize ?? 10),
});

const submitInfoList = ref({});

const props = defineProps({
  tableHeight: {
    type: Number,
    default: 450,
  },
});
const { tableHeight } = toRefs(props);

const codeDialogVisible = ref(false);

const handleSizeChange = (val) => {
  baseQuery.value.pageSize = val;
  router.push({
    query: {
      pageNumber: baseQuery.value.pageNumber,
      pageSize: val,
    },
  });
  doGetSubmitList(baseQuery);
};

const handleCurrentChange = (val) => {
  baseQuery.value.pageNumber = val;
  router.push({
    query: {
      pageNumber: val,
      pageSize: baseQuery.value.pageSize,
    },
  });
  doGetSubmitList(baseQuery);
};

const mdCode = ref("");
const readCode = (language, code) => {
  mdCode.value = "```" + language + "\n" + code + "\n" + "```";
  codeDialogVisible.value = true;
};

onMounted(() => {
  doInit();
});

const doInit = () => {
  doGetSubmitList(baseQuery);
};

const doGetSubmitList = async (baseQuery) => {
  const obj = await ProblemService.getProbSubmit(baseQuery.value);
  if (obj.code === 0) {
    submitInfoList.value = obj.data;
    console.log(submitInfoList.value);
  }
};
</script>

<style scoped>
#records-table {
  width: 100%;
  height: 100%;
}
</style>
