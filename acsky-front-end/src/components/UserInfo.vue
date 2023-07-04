<!-- 个人信息 -->
<template>
    <div :key="showComponent">
        <div v-if="showComponent">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-5">
                            <div class="demo-type">
                                <div>
                                    <el-avatar :size="80" :src="store.state.user.avatar"/>
                                </div>
                            </div>
                            <div class="button-container">
                                <button type="button" class="btn btn-secondary btn-sm" @click="toggleComponent">修改
                                </button>
                            </div>
                        </div>
                        <div class="col-7">
                            <div class="username">{{ store.state.user.name }}</div>
                            <div class="account">{{ store.state.user.email }}</div>
                            <div>个人简介</div>
                            <div class="profiles">{{ store.state.user.profile }}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-body ojinfo">
                    <div id="chart" class="ojinfo" :style="{ width: '350px', height: '380px' }"></div>
                </div>
            </div>
        </div>
        <div v-if="!showComponent">
            <UpdataInfo @button-click="toggleComponent"/>
        </div>
    </div>
</template>

<script>

import {ref, computed} from 'vue';
import {useStore} from 'vuex';
import UpdataInfo from './UpdataInfo.vue';
import * as echarts from 'echarts';
import {onMounted, onUnmounted} from "vue";

export default {
    name: "UserInfo",
    setup() {
        const store = useStore();
        const passing_rate = computed(() => {
            return (store.state.user.pass_cnt / store.state.user.submit_cnt * 100).toFixed(2) + "%";
        });

        onMounted(() => {
            initChart();
        })

        let showComponent = ref(true);
        const toggleComponent = () => {
            showComponent.value = !showComponent.value;  // 点击按钮时切换组件的显示状态
            if(showComponent.value == true)
            {
                initChart();
            }
        }

        let chart;
        let flag = ref(false);
        const initChart = () => {

            if(flag.value == false) {
                chart = echarts.init(document.getElementById("chart"), "purple-passion");
                flag.value = true;
            }
            chart.setOption({
                title: {
                    text: (store.state.user.pass_cnt / store.state.user.submit_cnt * 100).toFixed(2) + "%",
                    left: "center",
                    top: "46%",
                    textStyle: {
                        color: "#91cc75",
                        fontSize: 20,
                        align: "center"
                    }
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        // name: 'Access From',
                        type: 'pie',
                        radius: ['30%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            {value: store.state.user.submit_cnt, name: 'commit'},
                            {value: store.state.user.pass_cnt, name: 'pass'}
                        ]
                    }
                ]
            });
        }
        return {
            store,
            passing_rate,
            showComponent,
            initChart,
            toggleComponent
        };
    },
    components: {
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

.demo-type > div {
    flex: 1;
    text-align: center;
}

.demo-type > div:not(:last-child) {
    border-right: 1px solid var(--el-border-color);
}
</style>
