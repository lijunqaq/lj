<template>
    <div style="width:100%;height:500px;" id="factoryChart"></div>
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
                let chart = echarts.init(document.getElementById('factoryChart'));
                chart.title = '坐标轴刻度与标签对齐';

                fetch({
                    url:'chart/productPrice',
                    method:'get'

                }).then(resp=>{
                    this.titles=resp.data.titles;
                    this.values=resp.data.values;
                    const option = {
                        color: ['#8bdbbd'],
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis : [
                            {
                                type : 'category',
                                data : this.titles,
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'市场价格',
                                type:'bar',
                                barWidth: '60%',
                                data:this.values
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
