<template>
  <Editor
    :locale="zhHans"
    :upload-images="handleUploadFile"
    :value="content"
    :plugins="pluginsList"
    @change="handleChange"
  />
</template>
<script setup>
import { defineProps } from "vue";
import { Editor } from "@bytemd/vue-next";
import gfm from "@bytemd/plugin-gfm";
import gemoji from "@bytemd/plugin-gemoji";
import highlight from "@bytemd/plugin-highlight";
import frontmatter from "@bytemd/plugin-frontmatter";
import mediumZoom from "@bytemd/plugin-medium-zoom";
import breaks from "@bytemd/plugin-breaks";
import zhHans from "bytemd/locales/zh_Hans.json";
import FileService from "@/service/FileService";
import { ElMessage } from "element-plus";
import "highlight.js/styles/default.css";
import "bytemd/dist/index.css";
import "juejin-markdown-themes/dist/juejin.min.css";

const pluginsList = [
  gfm(),
  gemoji(),
  highlight(),
  frontmatter(),
  mediumZoom(),
  breaks(),
];
defineProps({
  content: {
    type: String,
    default: "",
  },
  handleChange: {
    type: Function,
    default: (val) => {
      console.log(val);
    },
  },
});

const handleUploadFile = async (files) => {
  const obj = await FileService.uploadFile(files[0]);
  if (obj.code === 0) {
    ElMessage.success("上传成功");
    return [
      {
        title: obj.data.name,
        url: obj.data.url,
      },
    ];
  }
};
</script>
<style>
.bytemd {
  height: 430px;
}

.bytemd-toolbar-icon.bytemd-tippy.bytemd-tippy-right:last-child {
  display: none;
}
</style>
