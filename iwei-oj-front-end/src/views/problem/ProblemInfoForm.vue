<template>
  <div>
    <el-form :model="probInfo">
      <el-form-item prop="title" label="标题：">
        <el-input v-model="probInfo.title" style="width: 50%" />
      </el-form-item>
      <el-row>
        <el-col :span="16">
          <el-form-item label="标签：">
            <el-select
              v-model="probInfo.tagInfos"
              multiple
              collapse-tags
              collapse-tags-tooltip
              :max-collapse-tags="2"
              placeholder="Select"
              style="width: 50%"
              value-key="id"
            >
              <el-option
                v-for="item in tagInfo"
                :key="item.id"
                :label="item.name"
                :value="item"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="状态：">
            <el-select v-model="probInfo.status">
              <el-option :value="0" label="正常" />
              <el-option :value="1" label="废弃" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="时间限制(秒)：">
            <el-input-number v-model="probInfo.timeLimit" :min="1" :max="10" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="空间限制(MB)：">
            <el-input-number
              v-model="probInfo.spaceLimit"
              :min="32"
              :max="1024"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="难度：">
            <el-select v-model="probInfo.difficulty" placeholder="Select">
              <el-option label="简单" :value="1" />
              <el-option label="中等" :value="2" />
              <el-option label="困难" :value="3" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="题目描述：" />
      <MdEdit
        :content="probInfo.description"
        :handle-change="onContentChange"
        style="width: 100%"
      />
    </el-form>
  </div>
</template>

<script setup>
import MdEdit from "@/components/MdEdit.vue";
import { defineProps, onMounted, ref, toRefs } from "vue";
import TagService from "@/service/TagService";

const props = defineProps({
  probInfo: {
    type: Object,
    default: () => {
      return {
        title: "",
        difficulty: 1,
        timeLimit: 1,
        spaceLimit: 256,
        description: "",
        tagInfos: [],
      };
    },
  },
});

const { probInfo } = toRefs(props);
const tagInfo = ref([]);

const onContentChange = (val) => {
  probInfo.value.description = val;
};

onMounted(() => {
  console.log(probInfo.value);
  doInit();
});

const doInit = () => {
  getTagInfoVO();
};

const getTagInfoVO = async () => {
  const obj = await TagService.getTagInfoVOList();
  if (obj.code === 0) {
    tagInfo.value = obj.data.collection;
  }
};
</script>

<style></style>
