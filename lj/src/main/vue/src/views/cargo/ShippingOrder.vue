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
                    <Button type="primary" icon="plus-round" @click="invoice()">新增发票</Button>
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
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="货主" prop="shipper">
                                <Input type="text" v-model="updateForm.shipper"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="提单抬头" prop="consignee">
                                <Input type="text" v-model="updateForm.consignee"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="正本通知人" prop="notifyParty">
                                <Input type="text" v-model="updateForm.notifyParty"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="信用证" prop="lcNo">
                                <Input type="text" v-model="updateForm.lcNo"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="装运港" prop="portOfLoading">
                                <Input type="text" v-model="updateForm.portOfLoading"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="转船港" prop="portOfTrans">
                                <Input type="text" v-model="updateForm.portOfTrans"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="卸货港" prop="portOfDischarge">
                                <Input type="text" v-model="updateForm.portOfDischarge"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="装期" >

                                <DatePicker type="date" v-model="updateForm.loadingDate" placeholder="Select date" style="width: 200px"></DatePicker>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="效期">

                                <DatePicker type="date" v-model="updateForm.limitDate" placeholder="Select date" style="width: 200px"></DatePicker>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="是否分批" prop="isBatch">
                                <Input type="text" v-model="updateForm.isBatch"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="是否转船" prop="isTrans">
                                <Input type="text" v-model="updateForm.isTrans"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="份数" prop="copyNum">
                                <Input type="text" v-model="updateForm.copyNum"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="扼要说明" prop="remark">
                                <Input type="text" v-model="updateForm.remark"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="运输要求" prop="specialCondition">
                                <Input type="text" v-model="updateForm.specialCondition"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="运费说明" prop="freight">
                                <Input type="text" v-model="updateForm.freight"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="复核人" prop="checkBy">
                                <Input type="text" v-model="updateForm.checkBy"/>
                            </FormItem>
                            </Col>
                    </Row>
                    
                    <Row>
                        <!-- 循环便利row中的两个元素-->
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
                            <FormItem label="海运/空运" prop="orderType">

                                <RadioGroup v-model="updateForm.orderType" type="button">
		                            <Radio label="sea">海运</Radio>
		                            <Radio label="air">空运</Radio>
		                        </RadioGroup>
                            </FormItem>
                            </Col>
                    </Row>

            </Form>
        </Modal>
        
        
        <Modal
                v-model="addInvoiceModal"
                title="添加发票"
                :mask-closable="false"
                :loading="loading"
                @on-ok="addInvoice"
                @on-cancel="cancel"
                width="60%">
            <Form ref="addInvoiceForm" :model="addInvoiceForm" :rules="formRule" :label-width="80">

                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="提货单" prop="blNo">
                                <Input type="text" v-model="addInvoiceForm.blNo"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="贸易条款" prop="tradeTerms">
                                <Input type="text" v-model="addInvoiceForm.tradeTerms"/>
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
                        title: '海运/空运',
                        key: 'orderType'
                    },
                    {
                        title: '货主',
                        key: 'shipper'
                    },
                    {
                        title: '提单抬头',
                        key: 'consignee'
                    },
                    {
                        title: '正本通知人',
                        key: 'notifyParty'
                    },
                    {
                        title: '信用证',
                        key: 'lcNo'
                    },
                    {
                        title: '装运港',
                        key: 'portOfLoading'
                    },
                    {
                        title: '转船港',
                        key: 'portOfTrans'
                    },
                    {
                        title: '卸货港',
                        key: 'portOfDischarge'
                    },
                    {
                        title: '装期',
                        key: 'loadingDate',
                        render: function (h, params) {
                            return h('div', dateFormat(this.row.loadingDate))
                        }
                    },
                    {
                        title: '效期',
                        key: 'limitDate' ,
                        render: function (h, params) {
                            return h('div', dateFormat(this.row.limitDate))
                        }
                    },
                    {
                        title: '是否分批',
                        key: 'isBatch'
                    },
                    {
                        title: '是否转船',
                        key: 'isTrans'
                    },
                    {
                        title: '份数',
                        key: 'copyNum'
                    },
                    {
                        title: '扼要说明',
                        key: 'remark'
                    },
                    {
                        title: '运输要求',
                        key: 'specialCondition'
                    },
                    {
                        title: '运费说明',
                        key: 'freight'
                    },
                    {
                        title: '复核人',
                        key: 'checkBy'
                    },
                    {
                        title: '状态',
                        key: 'state',
                        render: (h, params) => {
							//0-草稿 1-已上报
                            //0-草稿 1-已上报
                            const task_status = parseInt(params.row.state);
                            if (task_status === 0) {
                            	return h('div', [
                                    h('strong', '草稿')
                                ]);

                            }
                            else if (task_status === 1) {

                                return h('div', [
                                    h('strong', '已开发票')
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
                addInvoiceModal: false,
                updateForm: {
                        id:"",
                        orderType:"",
                        shipper:"",
                        consignee:"",
                        notifyParty:"",
                        lcNo:"",
                        portOfLoading:"",
                        portOfTrans:"",
                        portOfDischarge:"",
                        loadingDate:"",
                        limitDate:"",
                        isBatch:"",
                        isTrans:"",
                        copyNum:"",
                        remark:"",
                        specialCondition:"",
                        freight:"",
                        checkBy:"",
                        state:""
                },
                addInvoiceForm: {
                        scNo:"",
                        blNo:"",
                        tradeTerms:"fob",
                        state:"0"
                },
                formRule: {
                    
                    orderType: [
                        {required: true, message:'海运/空运不能为空',trigger:'blur'}
                    ]
                    ,
                    shipper: [
                        {required: true, message:'货主不能为空',trigger:'blur'}
                    ]
                    ,
                    consignee: [
                        {required: true, message:'提单抬头不能为空',trigger:'blur'}
                    ]
                    ,
                    notifyParty: [
                        {required: true, message:'正本通知人不能为空',trigger:'blur'}
                    ]
                    ,
                    lcNo: [
                        {required: true, message:'信用证不能为空',trigger:'blur'}
                    ]
                    ,
                    portOfLoading: [
                        {required: true, message:'装运港不能为空',trigger:'blur'}
                    ]
                    ,
                    portOfTrans: [
                        {required: true, message:'转船港不能为空',trigger:'blur'}
                    ]
                    ,
                    portOfDischarge: [
                        {required: true, message:'卸货港不能为空',trigger:'blur'}
                    ]
                    ,
                    loadingDate: [
                        {required: true, message:'装期不能为空',trigger:'blur'}
                    ]
                    ,
                    limitDate: [
                        {required: true, message:'效期不能为空',trigger:'blur'}
                    ]
                    ,
                    isBatch: [
                        {required: true, message:'是否分批不能为空',trigger:'blur'}
                    ]
                    ,
                    isTrans: [
                        {required: true, message:'是否转船不能为空',trigger:'blur'}
                    ]
                    ,
                    copyNum: [
                        {required: true, message:'份数不能为空',trigger:'blur'}
                    ]
                    ,
                    remark: [
                        {required: true, message:'扼要说明不能为空',trigger:'blur'}
                    ]
                    ,
                    specialCondition: [
                        {required: true, message:'运输要求不能为空',trigger:'blur'}
                    ]
                    ,
                    freight: [
                        {required: true, message:'运费说明不能为空',trigger:'blur'}
                    ]
                    ,
                    checkBy: [
                        {required: true, message:'复核人不能为空',trigger:'blur'}
                    ]
                    ,
                    state: [
                        {required: true, message:'状态不能为空',trigger:'blur'}
                    ]
                },
                //用来记录已经选中的委托单状态
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
                            url: '/cargo/shippingOrder',
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
                        url: '/cargo/shippingOrder',
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
            gopage(){
                const pageNo = this.pageNo;
                const pageSize = this.pageSize;
                const keyWord = this.keyWord;
                fetch({
                    url: '/cargo/shippingOrder',
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
            invoice(){

            	if(this.states.indexOf("1")!=-1){
                	this.$Message.warning('只能操作草稿状态的委托单');
                }
                else if (this.groupId != null && this.groupId != ""&& this.groupId.length == 1) {
                    this.addInvoiceModal=true;
                }
                else {
                    this.$Message.warning('请至少并只能选择一项开始发票');
                }
            },
            //添加发票
            addInvoice(){
                this.$refs['addInvoiceForm'].validate((valid)=>{
                    if(valid)
                    {
                        const invoice = this.addInvoiceForm;
                        fetch({
                            url: '/cargo/invoice',
                            method: 'post',
                            data: invoice,
                            params:{
                            	shippingOrderIds:this.groupId
                            }
                        }).then((result) => {

                            fetch({
                                url: '/cargo/shippingOrder/updateState',
                                method: 'put',
                                params: {
                                    shippingOrderIds: this.groupId
                                }
                            }).then(resp=>{
                                fetch({
                                    url: '/cargo/invoice/setTime',
                                    method: 'put',
                                    params:{
                                        shippingOrderIds:this.groupId
                                    },
                                }).then(resp=>{
                                    this.gopage(this.pageNo);
                                    this.$refs['addInvoiceForm'].resetFields();
                                    this.$Message.success('Success!');
                                    this.addInvoiceModal = false;
                                })


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