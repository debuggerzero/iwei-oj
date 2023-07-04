<!-- 历史题目 -->
<template>
    <div class="home">
        <div class="container">
            <div class="card">
                <div class="card-body">
                    <el-table :data="tableData" height="100%" style="width: 100%">
                        <el-table-column prop="pid" label="题号" width="100"/>
                        <el-table-column prop="type" label="语言类别" width="130"/>
                        <el-table-column prop="proTime" label="时间" width="180"/>
                        <el-table-column label="提交状态">
                            <template #default="{ row }">
                                <el-tag :type="row.button_color">{{ row.status }}</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column fixed="right" width="170">
                            <template #default="{ row }">
                                <el-button :type="row.button_color"
                                           @click="centerDialogVisible = readCode(row.code)">查看代码
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-dialog
                            v-model="centerDialogVisible"
                            title="Code"
                            width="30%"
                            align-center
                    >
                        <div>
                            <div v-html="renderedHTML"></div>
                        </div>
                    </el-dialog>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {onMounted, ref} from 'vue';
import axios from "axios";
import store from "@/store";
import MarkdownIt from "markdown-it";
import {ElMessage} from "element-plus";

export default {
    name: 'ProblemHistory',
    setup() {
        const tableData = ref();
        const centerDialogVisible = ref(false);

        onMounted(async () => {
            axios.get('/path/problem/history/' + store.state.user.id, {})
                .then((res) => {
                    tableData.value = res.data;
                    for (let i = 0; i < tableData.value.length; i++) {
                        const date = new Date(tableData.value[i].proTime);
                        tableData.value[i].proTime = `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
                        switch (tableData.value[i].status) {
                            case 'Accept':
                                tableData.value[i].button_color = 'success';
                                break;
                            case 'Wrong Answer':
                            case 'Exception':
                                tableData.value[i].button_color = 'danger';
                                break;
                            case 'Time Out':
                                tableData.value[i].button_color = 'warning';
                                break;
                            case 'Compile Error':
                                tableData.value[i].button_color = 'info';
                                break;
                        }
                    }
                })
                .catch((err) => {
                    ElMessage.error(err.response.data.message);
                })
        });

        const renderedHTML = ref('');

        const readCode = (code) => {
            renderHTML(code);
            return true;
        }

        const renderHTML = (code) => {
            const md = new MarkdownIt();
            const context = '```\n' + code + '\n```';
            renderedHTML.value = md.render(context.toString());
        };

        return {
            tableData,
            renderedHTML,
            centerDialogVisible,
            readCode,
        }
    },
}
</script>

<style scoped>
.container {
    margin-top: 10px;
}
</style>

