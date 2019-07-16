<template>
    <div style="width: 100%;height:500px;" id="onlineChart"></div>
</template>

<script>
    import echarts from 'echarts';
    import fetch from '../../utils/fetch';
    export default {
        name: 'chart',
        data () {
            return {
                titles:null,
                values:null
            };
        },
        mounted () {
            this.$nextTick(() => {
                let chart = echarts.init(document.getElementById('onlineChart'));
                chart.title = '坐标轴刻度与标签对齐';
                var dataAxis = ['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
                var data = [20, 12, 11, 24, 20, 30, 30, 13, 42, 31, 9, 19, 20, 12, 13, 34, 18, 13, 15, 20];
                var yMax = 0;
                var dataShadow = [];
                // Enable data zoom when user click bar.
                var zoomSize = 6;
                chart.on('click', function (params) {
                    console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
                    chart.dispatchAction({
                        type: 'dataZoom',
                        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
                    });
                });


                for (var i = 0; i < data.length; i++) {
                    dataShadow.push(yMax);
                }
                //ajax请求获取报表数据
                fetch({
                    url:'chart/onlineInfoSale/IpNums',
                    method:'get'
                }).then(resp=>{
                    dataAxis  = resp.data.titles;
                    data   = resp.data.values;


                    let option = {
                        title: {
                            text: '统计IP登录次数',
                        },
                        xAxis: {
                            data: dataAxis,
                            axisLabel: {

                                textStyle: {
                                    color: 'black'
                                }
                            },
                            axisTick: {
                                show: false
                            },
                            axisLine: {
                                show: false
                            },
                            z: 10
                        },
                        yAxis: {
                            axisLine: {
                                show: false
                            },
                            axisTick: {
                                show: false
                            },
                            axisLabel: {
                                textStyle: {
                                    color: '#999'
                                }
                            }
                        },
                        dataZoom: [
                            {
                                type: 'inside'
                            }
                        ],
                        series: [
                            { // For shadow
                                type: 'bar',
                                itemStyle: {
                                    normal: {color: 'rgba(0,0,0,0.05)'}
                                },
                                barGap:'-100%',
                                barCategoryGap:'40%',
                                data: dataShadow,
                                animation: false
                            },
                            {
                                type: 'bar',
                                itemStyle: {
                                    normal: {
                                        color: new echarts.graphic.LinearGradient(
                                            0, 0, 0, 1,
                                            [
                                                {offset: 0, color: '#83bff6'},
                                                {offset: 0.5, color: '#4edbac'},
                                                {offset: 1, color: '#8bdbbd'}
                                            ]
                                        )
                                    },
                                    emphasis: {
                                        color: new echarts.graphic.LinearGradient(
                                            0, 0, 0, 1,
                                            [
                                                {offset: 0, color: '#8bdbbd'},
                                                {offset: 0.7, color: '#4edbac'},
                                                {offset: 1, color: '#83bff6'}
                                            ]
                                        )
                                    }
                                },
                                data: data
                            }
                        ]
                    };

                    chart.setOption(option);
                })

                window.addEventListener('resize', function () {
                    chart.resize();
                });
            });
        }
    };
</script>

<style scoped>

</style>