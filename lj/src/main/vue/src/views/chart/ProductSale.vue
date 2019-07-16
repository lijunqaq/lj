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
                    url:'chart/productSale',
                    method:'get'

                }).then(resp=>{
                    this.titles=resp.data.titles;
                    this.values=resp.data.values;
                    const option = {
                        title : {
                            text: '货物销售排行',
                            subtext: '实时数据',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data:this.titles
                        },
                        series : [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:this.values,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
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
