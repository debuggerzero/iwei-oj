<template>
  <Viewer :value="value" :plugin="pluginsList" :locale="zhHans" />
</template>

<script setup>
import { defineProps } from "vue";

import { Viewer } from "@bytemd/vue-next";
import gfm from "@bytemd/plugin-gfm";
import gemoji from "@bytemd/plugin-gemoji";
import highlight from "@bytemd/plugin-highlight";
import frontmatter from "@bytemd/plugin-frontmatter";
import mediumZoom from "@bytemd/plugin-medium-zoom";
import breaks from "@bytemd/plugin-breaks";
import zhHans from "bytemd/locales/zh_Hans.json";
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
  value: {
    type: String,
    default: "",
  },
});
</script>

<style scoped></style>
