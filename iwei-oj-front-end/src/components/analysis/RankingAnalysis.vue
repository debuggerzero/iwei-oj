<template>
  <div id="ranking-analysis"></div>
</template>

<script setup>
import * as echarts from "echarts";
import { onMounted, defineProps, watch } from "vue";

const props = defineProps({
  name: {
    type: Array,
    default: () => [],
  },
  acceptCnt: {
    type: Array,
    default: () => [],
  },
  submitCnt: {
    type: Array,
    default: () => [],
  },
});

let myChart;
let option;

watch([props.name, props.submitCnt, props.acceptCnt], () => {
  doChange();
});

const doInit = () => {
  myChart = echarts.init(document.getElementById("ranking-analysis"));
  option = option && myChart.setOption(option);
};

const doChange = () => {
  myChart.setOption({
    xAxis: {
      data: props.name,
    },
    series: [
      {
        data: props.submitCnt,
      },
      {
        data: props.acceptCnt,
      },
    ],
  });
};

onMounted(() => {
  option = {
    title: {
      text: "AC 排行",
      subtext: "top10",
    },
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["acceptCnt", "submitCnt"],
    },
    calculable: true,
    xAxis: {
      type: "category",
      data: [],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        name: "submitCnt",
        type: "bar",
        data: [],
        markPoint: {
          data: [
            { type: "max", name: "Max" },
            { type: "min", name: "Min" },
          ],
        },
        markLine: {
          data: [{ type: "average", name: "Avg" }],
        },
      },
      {
        name: "acceptCnt",
        type: "bar",
        data: [],
        markPoint: {
          data: [
            { type: "max", name: "Max" },
            { type: "min", name: "Min" },
          ],
        },
        markLine: {
          data: [{ type: "average", name: "Avg" }],
        },
      },
    ],
  };
  doInit();
});
</script>

<style scoped>
#ranking-analysis {
  width: 100%;
  height: 300px;
}
</style>
