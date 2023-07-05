<!-- 历史题目 -->
<template>
    <div class="home">
        <div class="container">
            <div class="center-div-titel">提交记录</div>
            <div class="card">
                <div class="card-body">
                    <el-table :data="tableData" height="500" style="width: 100%">
                        <el-table-column prop="pid" label="题号" width="100" />
                        <el-table-column prop="type" label="语言类别" width="130" />
                        <el-table-column prop="proTime" label="时间" width="180" />
                        <el-table-column label="提交状态">
                            <template #default="{ row }">
                                <el-tag :type="row.button_color">{{ row.status }}</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column fixed="right" width="170">
                            <template #default="{ row }">
                                <el-button :type="row.button_color"
                                    @click="centerDialogVisible = readCode(row.code, row.status)">查看代码
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                    <el-dialog v-model="centerDialogVisible" title="Code" width="30%" align-center center>
                        <el-divider />
                        <div class="code-block">
                            <div v-html="renderedHTML"></div>
                        </div>
                        <div style="margin-right: 10%;text-align: right;">{{ now_status }}</div>
                    </el-dialog>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import axios from "axios";
import store from "@/store";
import MarkdownIt from "markdown-it";
import { ElMessage } from "element-plus";
import { useRouter } from 'vue-router';

export default {
    name: 'ProblemHistory',
    setup() {
        const tableData = ref();
        const centerDialogVisible = ref(false);
        const router = useRouter();
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
                    router.push({ name: 'login' });
                })
        });

        const renderedHTML = ref('error');

        const readCode = (code, status) => {
            now_status.value = status;
            renderHTML(code);
            return true;
        }

        const renderHTML = (code) => {
            const md = new MarkdownIt();
            const context = '```\n' + code.trimStart().trimEnd() + '\n```';
            renderedHTML.value = md.render(context.toString());
        };

        var now_status = ref('');

        return {
            tableData,
            renderedHTML,
            centerDialogVisible,
            readCode,
            now_status
        }
    },
}
</script>

<style scoped>
.container {
    margin-top: 10px;
}

.center-div-titel {
    text-align: center;
    font-size: 24px;
    padding: 10px;
    border-radius: 5px;
    font-weight: bold;
    letter-spacing: 5px;
    margin-bottom: 17px;
}

div.code-block {
    margin: 30px;
    background-color: #f3f3f3;
    border-radius: 4px;
    padding: 10px;
    font-family: Consolas, Monaco, monospace;
    font-size: 16px;
    line-height: 1.5;
}
</style>

