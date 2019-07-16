<template>


    <div class="animated fadeIn">
        <!--<div>-->
        <!--<Row style="margin-bottom: 25px;">-->
        <!--<Col span="8">登录名：-->
        <!--<Input v-model="loginName" placeholder="请输入..." style="width:200px"></Input>-->
        <!--</Col>-->
        <!--<Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button></Col>-->
        <!--</Row>-->
        <!--</div>-->
        <div>
            <ul>
                <li>
                    <Button type="success" icon="wrench" @click="edit()">修改</Button>
                    <Button type="error" icon="trash-a" @click="remove()">删除</Button>
                    <Button type="warning"  @click="submit()">提交</Button>
                    <Button type="info"  @click="cancel()">取消</Button>
                    <Button type="success"  @click="exports()">电子报运</Button>
                     <Button type="error"  @click="packing()">装箱</Button>
                </li>
                <li>
                    <div style="padding: 10px 0;">
                        <Table border :columns="columns1" :data="data1" @on-selection-change="s=>{change(s)}"></Table>
                    </div>
                </li>
                <li>
                    <div style="text-align: right;">
                        <Page :total="totalCount" :page-size="pageSize" :current="pageNo" @on-change="gopage"
                              align="center"></Page>
                    </div>
                </li>
            </ul>
        </div>
        <Modal
                v-model="addModal"
                title="添加装箱单"
                :mask-closable="false"
                :loading="loading"
                @on-ok="addPackingList"
                @on-cancel="cancel"
                width="60%">
            <Form ref="packingForm" :model="packingForm" :rules="formRule" :label-width="80">
                <!--一次性取两个元素放在row集合中 -->
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="卖方" prop="seller">
                                <Input type="text" v-model="packingForm.seller"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="买方" prop="buyer">
                                <Input type="text" v-model="packingForm.buyer"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="发票号" prop="invoiceNo">
                                <Input type="text" v-model="packingForm.invoiceNo"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="发票日期" prop="invoiceDate">
                            	 <DatePicker type="date" v-model="packingForm.invoiceDate" placeholder="Select date" style="width: 200px"></DatePicker>

                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="唛头" prop="marks">
                                <Input type="text" v-model="packingForm.marks"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="描述" prop="descriptions">
                                <Input type="text" v-model="packingForm.descriptions"/>
                            </FormItem>
                            </Col>
                    </Row>
                    
            </Form>
        </Modal>


    </div>
</template>

<script type="text/ecmascript-6">
    import fetch from '../../../utils/fetch.js';
    import dateFormat from '../../../utils/date';

    export default {
        data() {

            return {
                count: 0,
                gourpId: null,
                pageSize: 20,
                pageNo: 1,
                totalPage: 0,
                totalCount: 0,
                addModal:false,
                loading:true,
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    }/*,
                    {
                        title: '保运号',
                        key: 'id'
                    }*/,
                    {
                        title: '货物数/附件数',
                        key: 'nums'
                    },
                    {
                        title: '信用证号',
                        key: 'lcno'
                    },
                    {
                        title: '装运港',
                        width: 90,
                        key: 'shipmentPort'
                    },
                    {
                        title: '目的港',
                        key: 'destinationPort'
                    },
                    {
                        title: '运输方式',
                        key: 'transportMode'
                    },
                    {
                        title: '价格条件',
                        key: 'priceCondition'
                    },
                    {
                        title: '制单日期',
                        key: 'inputDate',
                        render: function (h, params) {
                            return h('div', dateFormat(this.row.inputDate))
                        }
                    },
                    {
                        title: '状态',
                        key: 'state',
                        width: 80,
                        render: (h, params) => {
//                            0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
                            const task_status = parseInt(params.row.state);
                            if (task_status === 0) {
                            	return h('div', [
                                    h('strong', '草稿')
                                ]);

                            }
                            else if (task_status === 1) {
                            	return h('div', [
                                    h('strong', '已上报')
                                ]);

                            }
                            else if (task_status === 2) {
                            	return h('div', [
                                    h('strong', '装箱')
                                ]);

                            }
                            else if (task_status === 3) {
                            	return h('div', [
                                    h('strong', '委托')
                                ]);

                            }
                            else if (task_status === 4) {
                            	return h('div', [
                                    h('strong', '发票')
                                ]);

                            }
                            else if (task_status === 5) {
                            	return h('div', [
                                    h('strong', '财务')
                                ]);

                            }
                            else {

                                return h('div', [
                                    h('strong', '未知')
                                ]);
                            }
                        }
                    }
                ],
                self: this,
                data1: [],
                packingForm: {
                    id:'',
                        seller:"",
                        buyer:"",
                        invoiceNo:"",
                        invoiceDate:"",
                        marks:"",
                        descriptions:"",
                        exportIds:"",
                        exportNos:"",
                        state:"0"
                },
                formRule: {
                    seller: [
                        {required: true, message:'卖方不能为空',trigger:'blur'}
                    ]
                    ,
                    buyer: [
                        {required: true, message:'买方不能为空',trigger:'blur'}
                    ]

                    ,
                    marks: [
                        {required: true, message:'唛头不能为空',trigger:'blur'}
                    ]
                    ,
                    descriptions: [
                        {required: true, message:'描述不能为空',trigger:'blur'}
                    ]
                    
                },
                //用来记录已经选中的报运单状态
                states:""
                
            }
        },
        methods: {
            reset(form){
                this.$refs[form].resetFields();
            },
            change(e){
                if (e.length == 1) {
                    this.updateForm = e[0];
                    //将importNum 转成字符串
                    this.updateForm.importNum = this.updateForm.importNum + "";
                }
                this.setGroupId(e);
            },
            setGroupId(e)
            {
                this.groupId = [];
                this.states="";
                this.count = e.length;
                for (var i = 0; i < e.length; i++) {
                    this.groupId.push(e[i].id);

					this.states+=e[i].state;
                }
            },
            edit () {
                if (this.count != 1) {
                    this.modal1 = false;
                    this.$Message.warning('请至少并只能选择一项');
                }
                else {
                    //跳转到编辑页面
                    this.$router.push({path:'/cargo/exportEdit/'+(this.groupId[0])});
                }
            },
            remove () {
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/cargo/export',
                        method: 'delete',
                        data: this.groupId
                    }).then((result) => {
                        if (result.data == 'ok') {
                            this.$Message.success('Success!');
                            this.gopage(this.pageNo);
                        }
                    });
                } else {
                    this.$Message.warning('请至少选择一项');
                }
            },
            handleReset (form) {
                this.$refs[form].resetFields();
            },
            gopage(pageNo){
                const pageSize = this.pageSize;
                fetch({
                    url: '/cargo/export',
                    method: 'get',
                    params: {pageNo, pageSize}
                }).then((result) => {

                    this.data1 = result.data.list;
                    this.pageNo = pageNo;
                    this.pageSize = pageSize;
                    this.totalCount = result.data.totalCount;
                });
            },
            submit(){
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/cargo/export/submit',
                        method: 'put',
                        data: this.groupId
                    }).then((result) => {
                        if (result.data == 'ok') {
                            this.$Message.success('Success!');
                            this.gopage(this.pageNo);
                        }
                    });
                } else {
                    this.$Message.warning('请至少选择一项');
                }
            },
            cancel(){
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/cargo/export/cancel',
                        method: 'put',
                        data: this.groupId
                    }).then((result) => {
                        if (result.data == '1') {
                            this.$Message.success('Success!');
                            this.gopage(this.pageNo);
                        }
                    });
                } else {
                    this.$Message.warning('请至少选择一项');
                }
            },
            exports(){
                if (this.groupId != null && this.groupId != ""&& this.groupId.length == 1) {
                    fetch({
                        url: '/cargo/export/export',
                        method: 'put',
                        data: this.groupId
                    }).then((result) => {
                        if (result.data == 'ok') {
                            this.$Message.success('Success!');
                            this.gopage(this.pageNo);
                        }
                        else if (result.data=='false')
                        {
                            this.$Message.error('报运失败!');
                        }
                    }).catch(resp=>{
                    	this.$Message.error('电子报运失败');
                    });
                } else {
                    this.$Message.warning('请至少并只能选择一项');
                }
            },
            packing(){
            	//判断是否有选中已装箱的报运单
            	if(this.states.indexOf("2")!=-1){
                	this.$Message.warning('不能再次装箱已经装箱的报运单');
                }
            	else if(this.states.indexOf("0")!=-1){
                	this.$Message.warning('不能装箱草稿状态的报运单');
                }
                 else if (this.groupId != null && this.groupId != ""&& this.groupId.length > 0) {
                    this.addModal=true;
                }
                else {
                    this.$Message.warning('请至少选择一项开始装箱');
                }
            },
            //添加装箱单
            addPackingList(){

                this.packingForm.exportIds = "";
                this.groupId.forEach((id,index)=>{
                    this.packingForm.exportIds+=id;
                    if(index+1<this.groupId.length){
                        this.packingForm.exportIds+=",";
                    }
                });
                this.$refs['packingForm'].validate((valid)=>{
                    if(valid)
                    {
                        const packingList = this.packingForm;
                        fetch({
                            url: '/cargo/packingList/',
                            method: 'post',
                            data: packingList,
                        }).then((result) => {
                            this.gopage(this.pageNo);
                            this.$refs['packingForm'].resetFields();
                            this.$Message.success('Success!');
                            this.addModal = false;

                        });
                    }
                    else
                    {
                        this.$Message.error("表单验证失败");
                        setTimeout(()=>{
                            this.loading=false;
                            this.$nextTick(()=>{
                                this.loading=true;
                            });
                        },1000);
                    }
                })
            },
            cancel () {
                this.$Message.info('点击了取消');
            }

        },
        mounted: function () {
            this.gopage(this.pageNo)
        }
    }


</script>
<style scoped="scoped">
    li{list-style-type: none}
</style>