<!-- 题库 -->
<template>
    <ContentBase>
        <div class="table">
            <el-table :data="problem_info" stripe style="width: 100%"  height="900" :table-layout="auto" @row-click="handleRowClick">
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
        <el-pagination v-model:current-page="currentPage3" v-model:page-size="pageSize3" :disabled="disabled"
            :background="true" layout="prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
            @current-change="handleCurrentChange" />
    </div>
</template>

<script>
import ContentBase from "../components/ContentBase.vue"
import { reactive, onMounted } from "vue"
import { useRouter } from "vue-router"
import axios from "axios"

export default {
    name: 'ProblemList',

    setup() {
        const problem_info = reactive([]);

        const router = useRouter();
        const handleRowClick = (row) => {
            router.push({ name: 'detail', params: { id: row.id } });
            console.log("Clicked row:", row.id);
        };
        const total = 100;
        const page_num = 1;
        const page_size = 20;

        onMounted(async () => {
            axios.get('/path/problem/list/' + page_num + '/' + page_size, {})
                .then((res) => {
                    for (let i = 0; i < res.data.length; i++) {
                        problem_info.push({
                            id: res.data[i].id,
                            title: res.data[i].title,
                            difficulty: res.data[i].difficulty,
                            submit_cnt: res.data[i].submitCnt,
                            pass_cnt: res.data[i].passCnt,
                            passing_rate: (res.data[i].passCnt / res.data[i].submitCnt * 100).toFixed(2)+"%"
                        });
                    }
                })
                .catch((err) => {
                    console.log(err);
                })
        });

        return {
            problem_info,
            handleRowClick,
            total,
            page_num,
            page_size,
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
