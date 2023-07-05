<!-- 个人信息 -->
<template>
    <div :key="showComponent">
        <div v-if="showComponent">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <el-row :gutter="1">
                            <el-col :span="9">
                                <div class="demo-type">
                                    <div>
                                        <el-avatar :size="80" :src="store.state.user.avatar" />
                                    </div>
                                </div>
                                <div class="button-container">
                                    <button type="button" class="btn btn-secondary btn-sm" @click="toggleComponent">修改
                                    </button>
                                </div>
                            </el-col>
                            <el-col :span="1">
                                <el-divider direction="vertical" class="line" />
                            </el-col>
                            <el-col :span="14">
                                <div class="info">
                                    <div class="username">{{ store.state.user.name }}</div>
                                    <div class="account">{{ store.state.user.email }}</div>
                                    <div class="profile">个人简介</div>
                                    <div class="profiles">{{ store.state.user.profile }}</div>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </div>
            </div>
            <div class='card_container'>
                <div class="card ">
                    <div class="card-body ojinfo">
                        <PassingRate :width="'350px'" :height="'380px'" :pass_cnt="store.state.user.pass_cnt"
                            :submit_cnt="store.state.user.submit_cnt" />
                    </div>
                </div>
            </div>
        </div>
        <div v-if="!showComponent">
            <UpdataInfo @button-click="toggleComponent" />
        </div>
    </div>
</template>

<script>

import { ref, computed } from 'vue';
import { useStore } from 'vuex';
import UpdataInfo from './UpdataInfo.vue';
import * as echarts from 'echarts';
import { onMounted, onUnmounted } from "vue";
import PassingRate from "@/components/PassingRate.vue";

export default {
    name: "UserInfo",
    setup() {
        const store = useStore();
        const passing_rate = computed(() => {
            return (store.state.user.pass_cnt / store.state.user.submit_cnt * 100).toFixed(2) + "%";
        });

        let showComponent = ref(true);
        const toggleComponent = () => {
            showComponent.value = !showComponent.value;  // 点击按钮时切换组件的显示状态
        }

        return {
            store,
            passing_rate,
            showComponent,
            toggleComponent
        };
    },
    components: {
        PassingRate,
        UpdataInfo,
    },
};
</script>

<style scoped>
.username {
    font-weight: bold;
    font-size: 20px;
}

.account {
    font-size: 12px;
    color: gray;
}

.profile {
    margin-top: 10px;
    font-weight: bold;
    /*font-size: 15px;*/
}

.button-container {
    display: flex;
    justify-content: center;
}

.btn {
    display: inline-block;
    margin-top: 20px;
    background: linear-gradient(135deg, #87CEFA, #00FA9A);
    color: #fff;
    /* 阴影效果 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);

    /* 动画效果 */
    transition: all 0.3s ease;
    border: none;
}

/* 鼠标悬停效果 */
.btn:hover {
    transform: scale(1.05);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
}

/* 点击效果 */
.btn:active {
    transform: scale(0.95);
}

/* 禁用状态 */
.btn:disabled {
    opacity: 0.5;
    cursor: not-allowed;
}

.ojinfo {
    margin-left: auto;
    margin-right: auto;
    text-align: center;
}

.card {
    margin: 10px;
}

.demo-type {
    display: flex;
}

.demo-type>div {
    flex: 1;
    text-align: center;
}

.demo-type>div:not(:last-child) {
    border-right: 1px solid var(--el-border-color);
}

.line {
    height: 140px;
}

.profiles {
    font-size: 12px;
    color: gray;
    margin-top: 5px;
    margin-left: 0px;
}

.info {
    margin-left: 9px;
}

.card_container {
  margin-top:20px;
}
</style>
