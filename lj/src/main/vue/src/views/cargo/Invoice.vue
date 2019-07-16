<template>
    <div class="animated fadeIn">
        <Row>
        <Col span="6">
            委托单：<Input v-model="keyWord" placeholder="请输入..." style="width:200px"/>
        </Col>
        <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button></Col>
        </Row>
        <br>
        <Row>
                    <Button type="success" icon="wrench" @click="edit()">修改</Button>
                    <Button type="error" icon="trash-a" @click="remove()">删除</Button>
                    <Button type="primary" icon="plus-round" @click="finance()">新建财务报运单</Button>
        </Row>
        <br>
        <Row>
                        <Table border :columns="columns1" :data="data1" @on-selection-change="change"></Table>
        </Row>
        <br>
        <Row>
                        <Page :total="totalCount" :page-size="pageSize" :current="pageNo" @on-change="gopage"
                              align="center"></Page>
        </Row>
        <br>

       


        <Modal
                v-model="updateModal"
                title="编辑委托单"
                :mask-closable="false"
                :loading="loading"
                @on-ok="update"
                @on-cancel="cancel"
                width="60%">
            <Form ref="updateForm" :model="updateForm" :rules="formRule" :label-width="80">

                    <Row>
                             <Col span="11">
                            <FormItem label="状态" prop="state">
                                <RadioGroup v-model="updateForm.state" type="button">
		                            <Radio label="0">草稿</Radio>
		                            <Radio label="1">已上报</Radio>
		                        </RadioGroup>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="报运合同号" prop="scNo">
                                <Input type="text" readonly="readonly" v-model="updateForm.scNo"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="提货单号" prop="blNo">
                                <Input type="text" v-model="updateForm.blNo"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="贸易条款" prop="tradeTerms">
                                <Input type="text" v-model="updateForm.tradeTerms"/>
                            </FormItem>
                            </Col>
                    </Row>


            </Form>
        </Modal>
        
        
        <Modal
                v-model="addFinanceModal"
                title="添加财务报运单"
                :mask-closable="false"
                :loading="loading"
                @on-ok="addFinance"
                @on-cancel="cancel"
                width="60%">
            <Form ref="addFinanceForm" :model="addFinanceForm" :rules="formRule" :label-width="80">
                <!--一次性取两个元素放在row集合中 -->
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="制单日期" prop="inputDate">

                                <DatePicker type="date" v-model="addFinanceForm.inputDate" placeholder="Select date" style="width: 200px"></DatePicker>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="制单人" prop="inputBy">
                                <Input type="text" v-model="addFinanceForm.inputBy"/>
                            </FormItem>
                            </Col>
                    </Row>
 
            </Form>
        </Modal>


    </div>
</template>

<script type="text/ecmascript-6">
    import fetch from '../../utils/fetch';
    import dateFormat from '../../utils/date';


    export default {
        data() {
            return {
                loading:true,
                count: 0,
                gourpId: null,
                pageSize: 20,
                pageNo: 1,
                totalPage: 0,
                totalCount: 0,
                keyWord:"",
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '报运合同号',
                        key: 'scNo'
                    },
                    {
                        title: '提货单号',
                        key: 'blNo'
                    },
                    {
                        title: '贸易条款',
                        key: 'tradeTerms'
                    },
                    {
                        title: '状态',
                        key: 'state',
                        render: (h, params) => {

                            //0-草稿 1-已上报
                            const task_status = parseInt(params.row.state);
                            if (task_status === 0) {
                            	return h('div', [
                                    h('strong', '草稿')
                                ]);

                            }
                            else if (task_status === 1) {

                                return h('div', [
                                    h('strong', '已开财务单')
                                ]);
                            }
                            else {
                            	return h('div', [
                                    h('strong', 'unkown')
                                ]);

                            }
                        }
                    }
                ],
                self: this,
                data1: [],
                updateModal: false,
                addFinanceModal: false,
                updateForm: {
                        id:"",
                        scNo:"",
                        blNo:"",
                        tradeTerms:"",
                        state:""
                },
                addFinanceForm: {
                        inputDate:"",
                        inputBy:"",
                        state:"0"
                },
                formRule: {
                    invoiceId: [
                        {required: true, message:'PACKING_LIST_ID值不能为空',trigger:'blur'}
                    ]
                    ,
                    scNo: [
                        {required: true, message:'packing.getExportNos S/C就是报运的合同号不能为空',trigger:'blur'}
                    ]
                    ,
                    blNo: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    tradeTerms: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    state: [
                        {required: true, message:'0草稿 1已上报不能为空',trigger:'blur'}
                    ]
                },
                states:""
            }
        },
        methods: {
            change(e){
                this.count = e.length;
                if (e.length == 1) {
                    this.updateForm = e[0];
                    
                    this.updateForm.state = e[0].state+"";
                }
                this.setGroupId(e);
            },
            setGroupId(e)
            {
                this.groupId = [];
				this.states="";
                for (var i = 0; i < e.length; i++) {
                    this.groupId.push(e[i].id);
                    this.states+=e[i].state;
                }
            },
            reset(form){
                this.$refs[form].resetFields();
            },
            addInvoice(){
                this.addFinanceModal = true;
            },
            edit () {
                if (this.count != 1) {
                    this.updateModal = false;
                    this.$Message.warning('请至少并只能选择一项');
                }
                else {
                    this.updateModal = true;
                }
            },
            update () {
                this.$refs['updateForm'].validate((valid)=>{
                    if(valid)
                    {
                        fetch({
                            url: '/cargo/invoice',
                            method: 'put',
                            data: this.updateForm
                        }).then((result) => {
                            this.updateModal = false,
                                    this.$Message.success('Success!');
                            this.gopage(this.pageNo);
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
            remove () {
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/cargo/invoice',
                        method: 'delete',
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
            gopage(){
                const pageNo = this.pageNo;
                const pageSize = this.pageSize;
                const keyWord = this.keyWord;
                fetch({
                    url: '/cargo/invoice',
                    method: 'get',
                    params: {pageNo, pageSize,keyWord}
                }).then((result) => {
                    this.data1 = result.data.list;
                    this.pageNo = pageNo;
                    this.pageSize = pageSize;
                    this.totalCount = result.data.totalCount;
                });
            },
            cancel () {
                this.$Message.info('点击了取消');
            },
            finance(){

            	if(this.states.indexOf("1")!=-1){
                	this.$Message.warning('只能操作草稿状态的发票');
                }
                else if (this.groupId != null && this.groupId != ""&& this.groupId.length == 1) {
                    this.addFinanceModal=true;
                }
                else {
                    this.$Message.warning('请至少并只能选择一项开始生成财务报运单');
                }
            },
            //添加财务报运单
            addFinance(){
                this.$refs['addFinanceForm'].validate((valid)=>{
                    if(valid)
                    {
                        const finance = this.addFinanceForm;
                        fetch({
                            url: '/cargo/finance',
                            method: 'post',
                            data: finance,
                            params:{
                            	invoiceIds:this.groupId
                            }
                        }).then((result) => {

                            fetch({
                                url: '/cargo/invoice/updateState',
                                method: 'put',
                                params:{
                                    invoiceIds:this.groupId
                                }
                            }).then(resp=>{
                                this.gopage(this.pageNo);
                                this.$refs['addFinanceForm'].resetFields();
                                this.$Message.success('Success!');
                                this.addFinanceModal = false;
                            })

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
            }
        },
        mounted: function () {
            this.gopage();
        }
    }


</script>