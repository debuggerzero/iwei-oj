<template>
  <div class="tag-list-box">
    <el-row align="middle" style="text-align: center">
      <el-col :span="24">
        <el-text
          size="large"
          :line-clamp="1"
          style="font-size: 20px; margin-top: 20px; margin-bottom: 20px"
          >主题库标签
        </el-text>
      </el-col>
    </el-row>
    <el-scrollbar height="80%">
      <el-row align="middle" style="text-align: center">
        <el-col :span="24">
          <el-tag
            v-for="item in randomTagTypeList(tagInfoList)"
            :key="item.name"
            :type="item.type"
            effect="dark"
            style="margin: 5px"
            @click="doClick"
          >
            {{ item.name }}
          </el-tag>
        </el-col>
      </el-row>
    </el-scrollbar>
  </div>
</template>

<script setup>
import { randomTagTypeList } from "@/utils/CommonUtil";
import { onMounted, ref } from "vue";
import TagService from "@/service/TagService";

const tagInfoList = ref([]);

const doClick = (event) => {
  console.log(event);
};

onMounted(() => {
  doGetTagInfoList();
});

const doGetTagInfoList = async () => {
  const obj = await TagService.getTagInfoVOList();
  if (obj.code === 0) {
    tagInfoList.value = obj.data.collection;
  }
};
</script>

<style scoped>
.tag-list-box {
}
</style>
