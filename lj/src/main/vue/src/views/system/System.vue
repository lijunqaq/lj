
<template>
    <div class="login" >


        <br>

        <br>
        <Row>
            <Table border ref="selection" stripe="stripe" :columns="columns4" :data="data1" ></Table>
        </Row>
        <br>
        <br>
        <Row>
            <Page :total="totalCount" :page-size="pageSize" :current="pageNo" @on-change="gopage"
                  align="center"></Page>
        </Row>








    </div>







</template>


<script>
    import fetch from '../../utils/fetch'
    export default {
        data () {
            return {
                columns4: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '用户名',
                        key: 'userName'
                    },
                    {
                        title: '计算机域名',
                        key: 'userDomain',

                    },
                    {
                        title: '本地ip地址',
                        key: 'ip'
                    },
                    {
                        title: '本地主机名',
                        key: 'hostName'
                    },
                    {
                        title: 'JVM可以使用的总内存',
                        key: 'totalMemory'
                    },
                    {
                        title: 'JVM可以使用的处理器个数',
                        key: 'availableProcessors'
                    },
                    {
                        title: '操作系统的版本',
                        key: 'propertyVersion'
                    },
                    {
                        title: '操作系统的名称',
                        key: 'propertyName'
                    },

                ],
                data1: [


                ],
                /*分页数据*/
                totalCount:0,
                pageNo:1,
                keyWord:'',
                pageSize:9,
                /*验证*/
                firstRules:{
                    deptName: [
                        { required: true, message: '部门名字不能为空', trigger: 'blur' }
                ]
                },

                /*对话框需要的部门列表*/
                deptList:null,
                //控制加载状态
                loading:true,
                //存储选中的数据
                groupId:[],
            };
        },
        methods: {
            //分页
            gopage:function (pageNo) {
                fetch({
                    url:'system/sys/info',
                    method:'get',
                    params:{

                    }
                }).then((resp)=>{
                    this.data1=resp.data;
                })
            },








        },
        created(){


        /*查询所有部门*/
            fetch({
                url:'system/sys/info',
                method:'get',
            }).then((resp)=>{
                this.data1=resp.data;
                console.log(resp.data)

            })
        }
    };
</script>