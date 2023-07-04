<!-- 历史题目 -->
<template>
    <div class="home">
        <div class="container">
            <div class="card">
                <div class="card-body">

                        <el-table :data="tableData" height="250" style="width: 100%">
                            <el-table-column prop="date" label="Date" width="180" />
                            <el-table-column prop="name" label="Name" width="180" />
                            <el-table-column prop="address" label="Address" />
                        </el-table>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue';

export default {
    name: 'ProblemHistory',
    setup() {
        const tableData = ref([
            {
                date: '2016-05-03',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles',
            },
            {
                date: '2016-05-02',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles',
            },
        ])

        return {
            tableData,
        }
    },
}
</script>

<style scoped>
.container {
    margin-top: 10px;
}
</style>

