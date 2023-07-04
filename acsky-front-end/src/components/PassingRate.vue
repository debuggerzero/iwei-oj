<template>
    <div id="chart" :style="{ width: this.width, height: this.height }"></div>
</template>

<script>
import {onMounted, toRefs} from "vue";
import * as echarts from "echarts";

export default {
    name: "PassingRate",
    props: [
        'width',
        'height',
        'pass_cnt',
        'submit_cnt'
    ],
    setup(props) {

        const { pass_cnt, submit_cnt } = toRefs(props);

        onMounted(() => {
            initChart();
        })

        const initChart = () => {

            let chart = echarts.init(document.getElementById("chart"), "purple-passion");
            chart.setOption({
                title: {
                    text: (pass_cnt.value / submit_cnt.value * 100).toFixed(2) + "%",
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
                            {value: submit_cnt.value, name: 'commit'},
                            {value: pass_cnt.value, name: 'pass'}
                        ]
                    }
                ]
            });
        }
        return {
            initChart,
        };
    }
}
</script>

<style scoped>

</style>
