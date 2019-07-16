
<template>
    <div class="login" >

        <Row>
            <Col span="8">部门名字:&nbsp&nbsp&nbsp<Input v-model="keyWord" placeholder="请输入..." style="width: 200px"></Input></Col>
            <Col span="14"> <Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button></Col>
        </Row>
        <br>
        <Row>
            <Col span="10"><Button type="info" icon="plus-round"  @click="addDept">新建</Button>
            &nbsp; &nbsp;
            <Button type="success" icon="edit" @click="editDept">修改</Button>
            &nbsp; &nbsp;
            <Button type="warning" icon="trash-a" @click="deleteDept">删除</Button>
            <Button type="warning" icon="trash-a" @click="txt">下载</Button>
            </Col>
        </Row>
        <br>
        <Row>
            <Table border ref="selection" stripe="stripe" :columns="columns4" :data="data1"  @on-selection-change="change"></Table>
        </Row>
        <br>
        <!--on-change 会自动传入当前页码-->
        <Page :total="totalCount"  :page-size="pageSize"  align="center" @on-change="gopage"></Page>







<!--新建对话框-->
        <Modal
                v-model="addModal"
                title="添加部门"
                @on-ok="add"
                :loading="loading"
                width="60%">
            <Form ref="addForm" :model="addForm" :rules="firstRules"  :label-width="80">
                <Row>
                    <Col span="11">
                        <FormItem label="部门名字" prop="deptName" >
                            <Input type="text" v-model="addForm.deptName"/>
                        </FormItem>
                        <FormItem label="父部门">
                            <Select v-model="addForm.parentId">
                                <Option v-for="dept in deptList"  :value="dept.id">{{dept.deptName}}</Option>
                            </Select>
                        </FormItem>
                        <FormItem label="状态">
                            <RadioGroup v-model="addForm.state">
                                <Radio label="1">可用</Radio>
                                <Radio label="0">不可用</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                </Row>

            </Form>
        </Modal>
        <!--更新对话框-->
        <Modal
                v-model="editModal"
                title="编辑部门"
                @on-ok="update"
                :loading="loading"
        >
            <Form ref="editForm" :model="editForm" :label-width="80" :rules="firstRules">
                <FormItem label="部门名字" prop="deptName">
                    <Input v-model="editForm.deptName" placeholder="输入部门名字..."></Input>
                </FormItem>
                <FormItem label="父部门">
                    <Select v-model="editForm.parentId">
                        <Option v-for="dept in deptList" :value="dept.id">{{dept.deptName}}</Option>
                    </Select>
                </FormItem>
                <FormItem label="状态">
                    <RadioGroup v-model="editForm.state">
                        <Radio label="1">可用</Radio>
                        <Radio label="0">不可用</Radio>
                    </RadioGroup>
                </FormItem>

            </Form>
        </Modal>
    </div>







</template>


<script>
    import fetch from '../../utils/fetch';
    import util from '../../libs/util';
    import Cookies from 'js-cookie';
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
                        title: '部门名字',
                        key: 'deptName'
                    },
                    {
                        title: '状态',
                        key: 'state',
                        render(h,params){
                            return h('div',
                            [h('span',params.row.state==1?'可用':'不可用')])
                        }
                    }
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
                /*添加对话框*/
                addModal:false,
                addForm:{
                    deptName:'',
                    parentId:null,
                    state:1
                },
                //编辑对话框
                editModal:false,
                editForm:{
                    deptName:'',
                    parentId:null,
                    state:1
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
                    url:'system/dept',
                    method:'get',
                    params:{
                        pageNo,
                        pageSize:this.pageSize,
                        keyWord:this.keyWord
                    }
                }).then((resp)=>{
                    this.data1=resp.data.list;
                    this.totalCount=resp.data.totalCount;
                    this.pageNo=resp.data.pageNo;

                })
            },
                //控制对话框显示
            addDept:function () {
                this.addModal=true;
            },
                //控制编辑对话框显示
               editDept:function () {
                    //如果选择的数据大于1条则不能编辑
                   if(this.groupId.length==1){
                       this.editModal=true;
                   }
                   else {
                       this.$Message.error("只能编辑一条数据");
                   }
                },
            //添加数据
             add:function () {
                this.$refs['addForm'].validate((value)=>
                {
                    if(value){
                    //提交表单数据
                        fetch({
                            url:'system/dept',
                            method:'post',
                            data:this.addForm,
                        }).then((resp)=>{
                            //成功后关闭对话框
                            this.addModal=false;
                            this.$Message.success("新建部门成功");
                            //重置表单
                            this.$refs['addForm'].resetFields();
                            //刷新页面
                           this.gopage(this.pageNo)

                        })
                    }
                    else{
                        //验证没通过 不能提交
                        //2秒后恢复正常（loading=false），dom刷新后，修改loading=true
                        setTimeout(()=>{
                            //恢复正常
                            this.loading=false;
                            //dom刷新后
                            this.$nextTick(()=>{
                                this.loading=true;
                            })
                        },2000)
                    }
                })

             },
                //修改数据
            update:function () {
                this.$refs['editForm'].validate((value)=>
                {
                    if(value){
                        fetch({
                            url:'system/dept',
                            method:'put',
                            data:this.editForm,
                        }).then((resp)=> {

                            //成功后关闭对话框
                            this.editModal=false;
                            this.$Message.success("修改部门成功");
                            //刷新页面
                            this.gopage(this.pageNo)
                        })




                    }
                    else{
                        //验证没通过 不能提交
                        //2秒后恢复正常（loading=false），dom刷新后，修改loading=true
                        setTimeout(()=>{
                            //恢复正常
                            this.loading=false;
                            //dom刷新后
                            this.$nextTick(()=>{
                                this.loading=true;
                            })
                        },2000)
                    }
                })

            },

             //保存选中数据id
            change:function(selection){
               this.groupId=[];
               //循环放入
                for(let i=0;i<selection.length;i++){
                    this.groupId.push(selection[i].id);
                };
                //只选中一条数据的时候，将数据保存下来
                if(this.groupId.length==1){
                         this.editForm=selection[0];
                   }


            },
             //删除部门
                deleteDept:function () {
                //判断groupId的长度（删除至少需要选中一个）
                if(this.groupId.length!='' &&this.groupId.length!=null){
                    fetch({
                        url:'system/dept',
                        method:'delete',
                        data:this.groupId,
                    }).then((resp)=>{
                        this.$Message.success("删除成功");
                        //刷新页面


                    })
                }else{

                    this.$Message.error("请至少选中一项");
                }

                },

                txt:function () {
                    const url = util.ajaxUrl+"/system/user/txt?access_token="+Cookies.get('access_token');
                    console.log(url)
                    window.open(url);
                }


        },
        created(){
            //分页列表
        this.gopage(1);
        /*查询所有部门*/
            fetch({
                url:'system/dept/all',
                method:'get',
            }).then((resp)=>{
                this.deptList=resp.data;

            })
        }
    };
</script>