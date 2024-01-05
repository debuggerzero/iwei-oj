<template>
  <div class="wrapper">
    <div id="codeEditBox" ref="codeEditorRef" />
  </div>
</template>

<script setup>
import * as monaco from "monaco-editor";
import { ref, defineProps, toRefs, toRaw, watch, onMounted } from "vue";
import {
  pythonCompletion,
  sqlCompletion,
  cppCompletion,
  javaCompletion,
  csharpCompletion,
} from "@/utils/completion";

const codeEditorRef = ref();
const codeEditor = ref();

const props = defineProps({
  language: {
    type: String,
    default: "",
  },
  code: {
    type: String,
    default: "",
  },
  editorTheme: {
    type: String,
    default: "",
  },
  handleChange: {
    type: Function,
    default: (v) => {
      console.log(v);
    },
  },
});

const { language, editorTheme } = toRefs(props);

watch(
  () => language.value,
  () => {
    if (codeEditor.value) {
      monaco.editor.setModelLanguage(
        toRaw(codeEditor.value).getModel(),
        language.value
      );
    }
  }
);

watch(
  () => props.editorTheme,
  () => {
    monaco.editor.setTheme(editorTheme.value);
  }
);

onMounted(() => {
  // Hover on each property to see its docs!
  codeEditor.value = monaco.editor.create(codeEditorRef.value, {
    value: props.code, //编辑器初始显示文字
    language: language.value, //语言支持自行查阅demo
    theme: editorTheme.value, //官方自带三种主题vs, hc-black, or vs-dark
    selectOnLineNumbers: true, //显示行号
    roundedSelection: false,
    readOnly: false, // 只读
    cursorStyle: "line", //光标样式
    automaticLayout: true, //自动布局
    glyphMargin: true, //字形边缘
    useTabStops: false,
    fontSize: 15, //字体大小
    autoIndent: true, //自动布局
    quickSuggestionsDelay: 100, //代码提示延时
  });

  // 编辑 监听内容变化
  codeEditor.value.onDidChangeModelContent(() => {
    props.handleChange(toRaw(codeEditor.value).getValue());
  });

  // 创建代码提醒
  pythonCompletion;
  sqlCompletion;
  cppCompletion;
  csharpCompletion;
  javaCompletion;
});
</script>

<style scoped>
.wrapper {
  margin: 20px auto;
}

#codeEditBox {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  height: 680px;
  padding: 0;
  overflow: hidden;
  margin-bottom: 15px;
}

.resultBox {
  margin-top: 50px;
  height: 400px;
}

.resultBox .resultTitle {
  color: #409eff;
}

.resultBox .result {
  font-size: 15px;
  color: #969696;
}
</style>
