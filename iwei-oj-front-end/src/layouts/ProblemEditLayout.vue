<template>
  <div>
    <el-row align="middle" type="flex" justify="center">
      <el-steps
        :active="active"
        finish-status="success"
        simple
        style="width: 100%"
      >
        <el-step title="题干" />
        <el-step title="测试用例" />
        <el-step title="浏览题目" />
      </el-steps>
    </el-row>
    <el-row align="middle" type="flex" justify="center">
      <div v-if="active === 0" class="step-box">
        <problem-edit-layout :prob-info="form.probInfo" />
      </div>
      <div v-if="active === 1" class="step-box">
        <sample-form :sample-list="form.samples" />
      </div>
      <div v-if="active === 2" class="step-box">
        <problem-browse :prob-info="form.probInfo" :scrollbar-height="470" />
      </div>
    </el-row>
    <el-row align="middle" type="flex" justify="center">
      <el-button type="primary" @click="doPre" v-if="active > 0"
        >上一步
      </el-button>
      <el-button type="primary" @click="doNext" v-if="active < 2"
        >下一步
      </el-button>
      <el-button type="primary" @click="doSubmit" v-if="active === 2"
        >提交
      </el-button>
      <el-button type="primary" @click="doBack">返回</el-button>
    </el-row>
  </div>
</template>

<script setup>
import { onMounted, ref, defineProps, toRefs } from "vue";
import ProblemEditLayout from "@/views/problem/ProblemInfoForm.vue";
import SampleForm from "@/views/problem/SampleForm.vue";
import { ElMessage } from "element-plus";
import ProblemBrowse from "@/views/problem/ProblemBrowse.vue";
import ProblemService from "@/service/ProblemService";
import { useRouter } from "vue-router";

const router = useRouter();

const props = defineProps({
  doBack: {
    type: Function,
    default: () => {
      console.log("close");
    },
  },
  form: {
    type: Object,
    default: () => {
      return {
        operate: "add",
        probInfo: {
          title: "",
          difficulty: 1,
          timeLimit: 1,
          spaceLimit: 256,
          description: "",
          status: 0,
          tagInfos: [],
        },
        samples: [],
      };
    },
  },
});
const { form, doBack } = toRefs(props);

const active = ref(0);

onMounted(() => {
  console.log(form.value);
});

const doSubmit = () => {
  if (form.value.operate === "add") {
    insertProblemInfo();
  } else if (form.value.operate === "update") {
    updateProblemInfo();
  }
  router.go(0);
  doBack.value();
};

const doPre = () => {
  if (active.value < 0) {
    active.value = 2;
  }
  active.value--;
};

const doNext = () => {
  if (active.value >= 3) {
    active.value = 0;
  }
  if (active.value === 0) {
    if (form.value.probInfo.title === "") {
      ElMessage.warning("标题不能为空....");
      return;
    }
    if (form.value.probInfo.description === "") {
      ElMessage.warning("描述不能为空....");
      return;
    }
  }
  active.value++;
};

const insertProblemInfo = async () => {
  const { probInfo, samples } = form.value;
  const obj = await ProblemService.insertProbInfo({ probInfo, samples });
  if (obj.code === 0) {
    ElMessage.success("插入成功...");
  }
};

const updateProblemInfo = async () => {
  const { probInfo, samples } = form.value;
  const obj = await ProblemService.updateProbInfo({ probInfo, samples });
  if (obj.code === 0) {
    ElMessage.success("修改成功...");
  }
};
</script>

<style>
.step-box {
  width: 70%;
  height: 70vh;
  margin-top: 20px;
}
</style>
