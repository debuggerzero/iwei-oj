<!-- 题库 -->
<template>
    <ContentBase>
        <div class="table">
            <el-table :data="problem_info" stripe style="width: 100%" height="900" :table-layout="auto"
                @row-click="handleRowClick">
                <el-table-column prop="id" label="编号" />
                <el-table-column prop="title" label="题目" />
                <el-table-column prop="submit_cnt" label="总提交数" />
                <el-table-column prop="pass_cnt" label="通过数" />
                <el-table-column prop="passing_rate" label="通过率" />
                <el-table-column prop="difficulty" label="难度" />
            </el-table>
        </div>
    </ContentBase>
    <div class="pagination">
        <el-pagination v-model:current-page="page_num" v-model:page-size="page_size" :disabled="disabled" :background="true"
            layout="prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
            @current-change="handleCurrentChange" />
    </div>
</template>

<script>
import ContentBase from "../components/ContentBase.vue"
import { reactive, onMounted, ref } from "vue"
import { useRouter } from "vue-router"
import axios from "axios"
import { ElMessage } from "element-plus";

export default {
    name: 'ProblemList',

    setup() {
        const problem_info = reactive([]);

        const router = useRouter();
        const handleRowClick = (row) => {
            router.push({ name: 'detail', params: { id: row.id } });
            console.log("Clicked row:", row.id);
        };

        let total = ref(100);
        let page_num = ref(1);
        const page_size = 20;

        onMounted(async () => {

            axios.get('/path/problem/total')
                .then((res) => {
                    total.value = res.data;
                    handleCurrentChange(page_num.value);
                })
                .catch((err) => {
                    ElMessage.error(err.response.data.message);
                })
        });

        const handleCurrentChange = (number) => {
            page_num.value = number;
            axios.get('/path/problem/list/' + page_num.value + '/' + page_size)
                .then((res) => {
                    problem_info.splice(0, problem_info.length);
                    for (let i = 0; i < res.data.length; i++) {
                        problem_info.push({
                            id: res.data[i].id,
                            title: res.data[i].title,
                            difficulty: res.data[i].difficulty,
                            submit_cnt: res.data[i].submitCnt,
                            pass_cnt: res.data[i].passCnt,
                            passing_rate: (res.data[i].passCnt / (res.data[i].submitCnt === 0 ? 1 : res.data[i].submitCnt) * 100).toFixed(2) + "%"
                        });
                        switch (problem_info[i].difficulty) {
                            case 1: problem_info[i].difficulty = '简单'; break;
                            case 2: problem_info[i].difficulty = '中等'; break;
                            case 3: problem_info[i].difficulty = '较难'; break;
                            case 4: problem_info[i].difficulty = '困难'; break;
                            default: problem_info[i].difficulty = '新';
                        }
                    }

                })
                .catch((err) => {
                    ElMessage.error(err.response.data.message);
                })
        }

        return {
            problem_info,
            handleRowClick,
            total,
            page_num,
            page_size,
            handleCurrentChange,
        };

    },

    components: {
        ContentBase,

    },
};
</script>

<style scoped>
.pagination {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}

.table {
    min-height: 900px;
}
</style>
