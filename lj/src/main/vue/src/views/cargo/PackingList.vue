<template>
    <div class="animated fadeIn">
        <Row>
        <Col span="6">
            装箱单：<Input v-model="keyWord" placeholder="请输入..." style="width:200px"/>
        </Col>
        <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button></Col>
        </Row>
        <br>
        <Row>

                    <Button type="success" icon="wrench" @click="edit()">修改</Button>
                    <Button type="error" icon="trash-a" @click="remove()">删除</Button>
                    <Button type="primary" icon="trash-a" @click="shipping()">委托</Button>
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
                title="编辑装箱单"
                :mask-closable="false"
                :loading="loading"
                @on-ok="update"
                @on-cancel="cancel"
                width="60%">
            <Form ref="updateForm" :model="updateForm" :rules="formRule" :label-width="80">

                    <Row>
                            <Col span="11">
                            <FormItem label="卖方" prop="seller">
                                <Input type="text" v-model="updateForm.seller"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="买方" prop="buyer">
                                <Input type="text" v-model="updateForm.buyer"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="发票号" prop="invoiceNo">
                                <Input type="text" v-model="updateForm.invoiceNo"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="发票日期" prop="invoiceDate">
                                <Input type="text" v-model="updateForm.invoiceDate"/>

                            </FormItem>

                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="唛头" prop="marks">
                                <Input type="text" v-model="updateForm.marks"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="描述" prop="descriptions">
                                <Input type="text" v-model="updateForm.descriptions"/>
                            </FormItem>
                            </Col>
                    </Row>
                    
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
                    </Row>

            </Form>
        </Modal>
                <Modal
                v-model="shippingModal"
                title="添加委托单"
                :mask-closable="false"
                :loading="loading"
                @on-ok="addShippingOrder"
                @on-cancel="cancel"
                width="60%">
            <Form ref="addShippingOrderForm" :model="addShippingOrderForm" :rules="formRule" :label-width="80">
                    
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="货主" prop="shipper">
                                <Input type="text" v-model="addShippingOrderForm.shipper"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="提单抬头" prop="consignee">
                                <Input type="text" v-model="addShippingOrderForm.consignee"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="正本通知人" prop="notifyParty">
                                <Input type="text" v-model="addShippingOrderForm.notifyParty"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="信用证" prop="lcNo">
                                <Input type="text" v-model="addShippingOrderForm.lcNo"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="装运港" prop="portOfLoading">
                                <Input type="text" v-model="addShippingOrderForm.portOfLoading"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="转船港" prop="portOfTrans">
                                <Input type="text" v-model="addShippingOrderForm.portOfTrans"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="卸货港" prop="portOfDischarge">
                                <Input type="text" v-model="addShippingOrderForm.portOfDischarge"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="装期" prop="loadingDate">

                                <DatePicker type="date" v-model="addShippingOrderForm.loadingDate" placeholder="Select date" style="width: 200px"></DatePicker>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="效期" prop="limitDate">

                                <DatePicker type="date" v-model="addShippingOrderForm.limitDate" placeholder="Select date" style="width: 200px"></DatePicker>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="是否分批" prop="isBatch">
                                <Input type="text" v-model="addShippingOrderForm.isBatch"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="是否转船" prop="isTrans">
                                <Input type="text" v-model="addShippingOrderForm.isTrans"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="份数" prop="copyNum">
                                <Input type="text" v-model="addShippingOrderForm.copyNum"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="扼要说明" prop="remark">
                                <Input type="text" v-model="addShippingOrderForm.remark"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="运输要求" prop="specialCondition">
                                <Input type="text" v-model="addShippingOrderForm.specialCondition"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="运费说明" prop="freight">
                                <Input type="text" v-model="addShippingOrderForm.freight"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="复核人" prop="checkBy">
                                <Input type="text" v-model="addShippingOrderForm.checkBy"/>
                            </FormItem>
                            </Col>
                    </Row>
                    
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="状态" prop="state">
                                <RadioGroup v-model="addShippingOrderForm.state" type="button">
		                            <Radio label="0">草稿</Radio>
		                            <Radio label="1">已上报</Radio>
		                        </RadioGroup>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="海运/空运" prop="orderType">

                                <RadioGroup v-model="addShippingOrderForm.orderType" type="button">
		                            <Radio label="sea">海运</Radio>
		                            <Radio label="air">空运</Radio>
		                        </RadioGroup>
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
                        title: '卖方',
                        key: 'seller'
                    },
                    {
                        title: '买方',
                        key: 'buyer'
                    },
                    {
                        title: '发票',
                        key: 'invoiceNo'
                    },
                    {
                        title: '发票日期',
                        key: 'invoiceDate',
                        render: function (h, params) {

                            return h('div', dateFormat(this.row.invoiceDate))
                        }
                    },
                    {
                        title: '报运NO集合',
                        key: 'exportNos'
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
                                    h('strong', '已委托')
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
                shippingModal: false,
                updateForm: {
                        seller:"",
                        buyer:"",
                        invoiceNo:"",
                        invoiceDate:"",
                        marks:"",
                        descriptions:"",
                        exportIds:"",
                        exportNos:"",
                        state:""
                },
                addShippingOrderForm: {
                        orderType:"sea",
                        shipper:"shipper",
                        consignee:"consignee",
                        notifyParty:"notifyParty",
                        lcNo:"1234567888",
                        portOfLoading:"大连",
                        portOfTrans:"韩国",
                        portOfDischarge:"卸货港",
                        loadingDate:"2018-01-01",
                        limitDate:"2018-03-03",
                        isBatch:"0",
                        isTrans:"0",
                        copyNum:"2",
                        remark:"说明",
                        specialCondition:"易爆",
                        freight:"fob",
                        checkBy:"andy",
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
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    descriptions: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ],
                    state: [
                        {required: true, message:'0草稿 1已上报不能为空',trigger:'blur'}
                    ]
                },
                //用来记录已经选中的报运单状态
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
                            url: '/cargo/packingList',
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
                        url: '/cargo/packingList',
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
            gopage(pageNo){

                const pageSize = this.pageSize;
                const keyWord = this.keyWord;
                fetch({
                    url: '/cargo/packingList',
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
            shipping(){
            	//判断是否有选中已委托的装箱单
            	if(this.states.indexOf("1")!=-1){
                	this.$Message.warning('不能再次委托已经委托的装箱单');
                }
                else if (this.groupId != null && this.groupId != ""&& this.groupId.length == 1) {
                    this.shippingModal=true;
                }
                else {
                    this.$Message.warning('请至少并只能选择一项开始委托');
                }
            },
            //添加委托单
            addShippingOrder(){
                this.$refs['addShippingOrderForm'].validate((valid)=>{
                    if(valid)
                    {
                        const shippingOrder = this.addShippingOrderForm;
                        fetch({
                            url: '/cargo/shippingOrder',
                            method: 'post',
                            data: shippingOrder,
                            params:{
                            	packingListIds:this.groupId
                            }
                        }).then((result) => {
                            //修改装箱单状态为  1 已委托
                            fetch({
                                url: '/cargo/packingList/updateState',
                                method: 'put',
                                params: {
                                    packingListIds: this.groupId
                                }
                            }).then((resp)=>{
                                this.gopage(this.pageNo);
                                this.$refs['addShippingOrderForm'].resetFields();
                                this.$Message.success('Success!');
                                this.shippingModal = false;
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
            this.gopage(1);
        }
    }


</script>