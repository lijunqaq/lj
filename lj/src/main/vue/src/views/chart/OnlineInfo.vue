<template>
    <div style="width:100%;height:500px;" id="productChart"></div>
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
                let chart = echarts.init(document.getElementById('productChart'));
                chart.title = '坐标轴刻度与标签对齐';

                fetch({
                    url:'chart/onlineInfoSale',
                    method:'get'

                }).then(resp=>{
                    this.titles=resp.data.titles;
                    this.values=resp.data.values;
                 const   option = {
                        title: {
                            text: '系统访问压力',
                            subtext: '实时数据'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'cross'
                            }
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        xAxis:  {
                            type: 'category',
                            boundaryGap: false,
                            data: this.titles
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} 个'
                            },
                            axisPointer: {
                                snap: true
                            }
                        },
                        visualMap: {
                            show: false,
                            dimension: 0,
                            pieces: [{
                                lte: 6,
                                color: 'green'
                            }, {
                                gt: 6,
                                lte: 8,
                                color: 'red'
                            }, {
                                gt: 8,
                                lte: 14,
                                color: 'green'
                            }, {
                                gt: 14,
                                lte: 17,
                                color: 'red'
                            }, {
                                gt: 17,
                                color: 'green'
                            }]
                        },
                        series: [
                            {
                                name:'用电量',
                                type:'line',
                                smooth: true,
                                data:this.values,
                                markArea: {
                                    data: [ [{
                                        name: '早高峰',
                                        xAxis: '07:30'
                                    }, {
                                        xAxis: '10:00'
                                    }], [{
                                        name: '晚高峰',
                                        xAxis: '17:30'
                                    }, {
                                        xAxis: '21:15'
                                    }] ]
                                }
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
