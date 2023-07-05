<!-- 排行榜 -->
<template>
    <ContentBase>
       <h4 class="title">排行榜</h4>
    </ContentBase>
    <ContentBase>
        <div class="table">
            <el-table :data="rank_info" height="800" style="width: 100%">
                <el-table-column label=" " width="80"/>
                <el-table-column prop="rank" label="排名" width="180"/>
                <el-table-column label=" " width="80">
                    <template #default="{ row }">
                        <el-avatar :src="row.avatar"/>
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="用户" width="220"/>
                <el-table-column prop="pass_cnt" label="通过数" width="180"/>
                <el-table-column prop="profile" label="个人简介"/>
            </el-table>
        </div>
    </ContentBase>
</template>

<script>
import ContentBase from "../components/ContentBase.vue"
import {reactive, onMounted} from "vue"
import axios from "axios"
import {ElMessage} from "element-plus";

export default {
    name: 'RankingList',
    components: {
        ContentBase,
    },
    setup() {

        const page_num = 1;
        const page_size = 50;
        const rank_info = reactive([]);
        onMounted(async () => {
            axios.get('/path/user/query/ranking/' + page_num + '/' + page_size, {})
                .then((res) => {
                    for (let i = 0; i < res.data.length; i++) {
                        rank_info.push({
                            rank: i + 1,
                            id: res.data[i].id,
                            name: res.data[i].name + ' (' + res.data[i].id + ')',
                            avatar: res.data[i].avatar,
                            pass_cnt: res.data[i].passCnt,
                            submit_cnt: res.data[i].submitCnt,
                            profile: res.data[i].profile
                        });
                    }
                })
                .catch((err) => {
                    ElMessage.error(err.response.data.message);
                })
        });

        return {
            rank_info,
        }
    }

}
</script>

<style scoped>

.title {
  font-family: 'Arial', sans-serif;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  letter-spacing: 10px; /* 设置文字间的空隙，可以根据需要进行调整 */
}
</style>
