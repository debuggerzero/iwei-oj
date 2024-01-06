<template>
  <div id="view-problem-view">
    <el-row justify="center">
      <el-col :span="11">
        <el-tabs
          type="border-card"
          v-model="activeName"
          style="width: 95%; height: 800px"
        >
          <el-tab-pane label="题目" name="first">
            <problem-browse
              :prob-info="form.problem"
              :scrollbar-height="580"
              style="width: 100%; height: 100%"
            />
          </el-tab-pane>
          <el-tab-pane label="提交记录" name="second">
            <records-table
              :table-height="700"
              :submit-info-list="submitInfoList"
              :base-query="baseQuery"
              :handle-size-change="handleSizeChange"
              :handle-current-change="handleCurrentChange"
            />
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :span="11">
        <el-form :inline="true">
          <el-form-item label="Language">
            <el-select
              v-model="form.language"
              placeholder="选择语言"
              @change="handleLanguage"
            >
              <el-option
                v-for="(languageItem, key) in languageOptions"
                :key="key"
                :label="languageItem"
                :value="languageItem"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Theme">
            <el-select
              v-model="editorTheme"
              placeholder="设置主题"
              @change="handleTheme"
            >
              <el-option label="Visual Studio Dark" value="vs-dark"></el-option>
              <el-option label="Visual Studio" value="vs"></el-option>
              <el-option
                label="High Contrast Dark"
                value="hc-black"
              ></el-option>
            </el-select>
          </el-form-item>
          <code-editor
            :language="form.language"
            :code="form.code"
            :editor-theme="editorTheme"
            :handle-change="changeCode"
            style="width: 100%"
          />
          <el-button type="primary" @click="doSubmit" :loading="loading">
            提交
          </el-button>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import ProblemService from "@/service/ProblemService";
import ProblemBrowse from "@/views/problem/ProblemBrowse.vue";
import CodeEditor from "@/components/CodeEditor.vue";
import { ElMessage } from "element-plus";
import RecordsTable from "@/components/RecordsTable.vue";
import { useStore } from "vuex";

const activeName = ref("first");
const form = ref({
  language: "java",
  code: "",
});

const route = useRoute();
const router = useRouter();
const store = useStore();

const languageOptions = ref(["cpp", "java"]);
const editorTheme = ref("vs-dark");

const pid = ref(route.params?.id ?? 1);

const loading = ref(false);

const submitInfoList = ref({});

const baseQuery = ref({
  uid: store.state.user.loginUser.id,
  pid: pid,
  pageNumber: parseInt(route.query?.pageNumber ?? 1),
  pageSize: parseInt(route.query?.pageSize ?? 10),
});

onMounted(() => {
  doInit();
});

const doInit = () => {
  doGetOneProblemInfo();
  doGetSubmitList(baseQuery);
};

const doGetSubmitList = async (baseQuery) => {
  const obj = await ProblemService.getProbSubmit(baseQuery.value);
  if (obj.code === 0) {
    submitInfoList.value = obj.data;
  }
};

const doGetOneProblemInfo = async () => {
  const obj = await ProblemService.getOneProbInfo(pid.value);
  if (obj.code === 0) {
    form.value.problem = obj.data;
  }
};

const doSubmit = async () => {
  loading.value = true;
  const obj = await ProblemService.doProbSubmit({
    pid: pid.value,
    language: form.value.language,
    code: form.value.code,
  });
  if (obj.code === 0) {
    ElMessage.success("提交成功");
    doInit();
  }
  loading.value = false;
};

const changeCode = (value) => {
  form.value.code = value;
};

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

const handleTheme = (item) => {
  editorTheme.value = item;
};
const handleLanguage = (item) => {
  form.value.language = item;
};
</script>

<style scoped></style>
