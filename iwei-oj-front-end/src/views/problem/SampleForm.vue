<template>
  <el-form :model="sampleList">
    <el-row>
      <el-text type="info" :line-clamp="1"
        >测试用例 (考生不可见，用于测试考生代码正确性):
      </el-text>
    </el-row>
    <el-row style="margin-top: 16px">
      <el-button type="primary" @click="editOneDialogVisible = true"
        >添加用例
      </el-button>
      <el-button type="primary">批量添加</el-button>
    </el-row>
    <el-row style="margin-top: 16px">
      <el-table
        :data="sampleList"
        height="60vh"
        table-layout="auto"
        style="width: 100%"
      >
        <el-table-column prop="id" label="id" />
        <el-table-column prop="input" label="input">
          <template #default="{ row }">
            <el-text :line-clamp="1">{{ row.input }}</el-text>
          </template>
        </el-table-column>
        <el-table-column prop="output" label="output">
          <template #default="{ row }">
            <el-text :line-clamp="1">{{ row.output }}</el-text>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="200px">
          <template #default="{ row }">
            <el-button-group>
              <el-button type="info" @click="doUpdate(row)">修改</el-button>
              <el-button type="danger" @click="doDelete(row)">删除</el-button>
            </el-button-group>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </el-form>
  <el-dialog
    v-model="editOneDialogVisible"
    title="测试用例"
    width="30%"
    align-center
  >
    <el-row>
      <el-col :span="3">输入：</el-col>
      <el-col :span="21">
        <el-input
          v-model="sample.input"
          :autosize="{ minRows: 1, maxRows: 3 }"
          type="textarea"
          placeholder="Please input"
        />
      </el-col>
    </el-row>
    <el-row style="margin-top: 10px">
      <el-col :span="3">输出：</el-col>
      <el-col :span="21">
        <el-input
          v-model="sample.output"
          :autosize="{ minRows: 1, maxRows: 3 }"
          type="textarea"
          placeholder="Please input"
        />
      </el-col>
    </el-row>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="doSubmit"> Confirm </el-button>
        <el-button @click="doCancel">Cancel</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { defineProps, ref, toRefs } from "vue";

const props = defineProps({
  sampleList: {
    type: Array,
    default: () => {
      return [];
    },
  },
});
const { sampleList } = toRefs(props);
const sample = ref({
  id: -1,
  input: "",
  output: "",
});

const editOneDialogVisible = ref(false);

const doDelete = (row) => {
  for (let i = 0; i < sampleList.value.length; i++) {
    if (sampleList.value[i].id === row.id) {
      sampleList.value.splice(i, 1);
    }
  }
};

const doUpdate = (row) => {
  const { id, input, output } = row;
  sample.value = { id, input, output };
  editOneDialogVisible.value = true;
};

const doCancel = () => {
  editOneDialogVisible.value = false;
};

const doInitSample = () => {
  sample.value.id = -1;
  sample.value.input = "";
  sample.value.output = "";
};

const doSubmit = () => {
  const list = sampleList.value;
  const { id, input, output } = sample.value;
  console.log(id);
  if (id !== -1) {
    sampleList.value[id] = { id, input, output };
  } else {
    sampleList.value.push({
      id: (list[list.length - 1]?.id ?? -1) + 1,
      input: input,
      output: output,
    });
  }
  doInitSample();
  editOneDialogVisible.value = false;
};
</script>

<style scoped></style>
