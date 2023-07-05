<template>
    <div id="calendar_chart" :style="{ width: this.width, height: this.height }"></div>
</template>

<script>
import * as echarts from 'echarts';
import {onMounted} from "vue";
import 'echarts/theme/macarons';

export default {
    name: "CalendarChart",
    props: [
        'width',
        'height',
    ],
    setup() {

        onMounted(() => {
            initChart();
        })

        const getVirtualData = (year) => {
            const date = +echarts.time.parse(year + '-01-01');
            const end = +echarts.time.parse(+year + 1 + '-01-01');
            const dayTime = 3600 * 24 * 1000;
            const data = [];
            for (let time = date; time < end; time += dayTime) {
                data.push([
                    echarts.time.format(time, '{yyyy}-{MM}-{dd}', false),
                    Math.floor(Math.random() * 10)
                ]);
            }
            return data;
        };

        const initChart = () => {
            let chart = echarts.init(document.getElementById("calendar_chart"), "purple-passion");
            chart.setOption({
                title: {
                    top: 10,
                    left: 'center',
                    // text: '历史记录'
                },
                tooltip: {},
                visualMap: [{
                    type: 'piecewise',
                    pieces: [{
                        value: 0,
                        color: '#f0f0f2'
                    }, {
                        min: 1,
                        max: 10,
                        color: '#daf3f0'
                    }, {
                        min: 11,
                        max: 50,
                        color: '#cceeea'
                    }, {
                        min: 51,
                        max: 100,
                        color: '#99ddd4'
                    }, {
                        min: 101,
                        max: 200,
                        color: '#73c0de'
                    }, {
                        min: 201,
                        color: '#73c0de'
                    }],
                    orient: 'horizontal',
                    left: 'center',
                    show: false,
                }],
                calendar: {
                    top: 70,
                    left: 30,
                    right: 30,
                    cellSize: [20, 20],
                    range: '2023',
                    splitLine: {
                        show: false
                    },
                    dayLabel: {
                        nameMap: 'ZH'
                    },
                    monthLabel: {
                        nameMap: 'ZH'
                    },
                    yearLabel: {
                        show: false
                    }
                },
                itemStyle: {
                    borderWidth: '100px',
                    borderColor: '#fff',
                },
                series: [{
                    type: 'heatmap',
                    coordinateSystem: 'calendar',
                    data: getVirtualData('2023')
                }]
            })
        };

        return {
            initChart,
            getVirtualData,
        };
    }
}
</script>

<style scoped>

</style>
