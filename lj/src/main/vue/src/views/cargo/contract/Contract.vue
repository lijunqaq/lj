<template>

    <div class="animated fadeIn">
        <Row >
        <Col span="8">合同号：
            <Input v-model="contractNo" placeholder="请输入..." style="width:200px"/>
        </Col>
            <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button></Col>
        </Row>
        <br>
        <Row >
            <Button type="primary" icon="plus-round" @click="addContract()">新建</Button>
            <Button type="success" icon="wrench" @click="edit()">修改</Button>
            <Button type="error" icon="trash-a" @click="remove()">删除</Button>
            <Button type="warning"  @click="submit()">提交</Button>
            <Button type="info"  @click="cancel()">取消</Button>

        </Row>
        <br>
        <Row>
            <Table :columns="columns1" :data="data1" @on-selection-change="s=>{change(s)}"></Table>
        </Row>
        <br>
        <Row>
            <Page :total="totalCount" :page-size="pageSize" :current="pageNo" @on-change="gopage"
                  align="center"></Page>
        </Row>

        <Modal
                v-model="modal1"
                title="编辑购销合同"
                width="60%"
                @on-ok="update"
                @on-cancel="cancel"
                :loading="loading">
            <Form ref="updateForm" :model="updateForm" :rules="ruleCustom" :label-width="80">
                <Row>
                    <Col span="11">
                    <FormItem label="客户名称" prop="customName">
                        <Input type="text" v-model="updateForm.customName"/>
                    </FormItem>
                    </Col>

                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="打印板式" prop="printStyle">
                        <RadioGroup v-model="updateForm.printStyle" type="button">
                            <Radio label="2">两款</Radio>
                            <Radio label="1">一款</Radio>
                        </RadioGroup>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="合同号" prop="contractNo">
                        <Input type="text" v-model="updateForm.contractNo"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="收购方" prop="offeror">
                        <Input type="text" v-model="updateForm.offeror"/>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="制单人" prop="inputBy">
                        <Input type="text" v-model="updateForm.inputBy"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="审单人" prop="checkBy">
                        <Input type="text" v-model="updateForm.checkBy"/>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="验货员" prop="inspector">
                        <Input type="text" v-model="updateForm.inspector"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="签单日期" prop="signingDate">
                        <DatePicker type="date" v-model="updateForm.signingDate" format="yyyy-MM-dd"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="重要程度" prop="importNum">
                        <RadioGroup v-model="updateForm.importNum" type="button">
                            <Radio label=3>★★★</Radio>
                            <Radio label=2>★★</Radio>
                            <Radio label=1>★</Radio>
                        </RadioGroup>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="船期" prop="shipTime">
                        <DatePicker type="date" v-model="updateForm.shipTime" format="yyyy-MM-dd"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="贸易条款" prop="tradeTerms">
                        <Input type="text" v-model="updateForm.tradeTerms"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="交货日期" prop="deliveryPeriod">
                        <DatePicker type="date" v-model="updateForm.deliveryPeriod" format="yyyy-MM-dd"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="说明" prop="remark">
                        <Input type="textarea" v-model="updateForm.remark"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="要求" prop="crequest">
                        <Input type="textarea" v-model="updateForm.crequest"/>
                    </FormItem>
                    </Col>
                </Row>

            </Form>
        </Modal>
        <Modal
                v-model="modal2"
                title="添加购销合同"
                @on-ok="add"
                @on-cancel="cancel" width="60%"
                :loading="loading"
        >
            <Form ref="addForm" :model="addForm" :rules="ruleCustom" :label-width="80">
                <Row>
                    <Col span="11">
                    <FormItem label="客户名称" prop="customName">
                        <Input type="text" v-model="addForm.customName"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="打印板式" prop="printStyle">
                        <RadioGroup v-model="addForm.printStyle" type="button">
                            <Radio label="2">两款</Radio>
                            <Radio label="1">一款</Radio>
                        </RadioGroup>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="合同号" prop="contractNo">
                        <Input type="text" v-model="addForm.contractNo"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="收购方" prop="offeror">
                        <Input type="text" v-model="addForm.offeror"/>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="制单人" prop="inputBy">
                        <Input type="text" v-model="addForm.inputBy"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="审单人" prop="checkBy">
                        <Input type="text" v-model="addForm.checkBy"/>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="验货员" prop="inspector">
                        <Input type="text" v-model="addForm.inspector"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="签单日期" prop="signingDate">
                        <DatePicker type="date" v-model="addForm.signingDate" format="yyyy-MM-dd"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="重要程度" prop="importNum">
                        <RadioGroup v-model="addForm.importNum" type="button">
                            <Radio label="3">★★★</Radio>
                            <Radio label="2">★★</Radio>
                            <Radio label="1">★</Radio>
                        </RadioGroup>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="船期" prop="shipTime">
                        <DatePicker type="date" v-model="addForm.shipTime" format="yyyy-MM-dd" placeholder="Select date"
                                    style="width: 200px"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="贸易条款" prop="tradeTerms">
                        <Input type="text" v-model="addForm.tradeTerms"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="交货日期" prop="deliveryPeriod">
                        <DatePicker type="date" v-model="addForm.deliveryPeriod" format="yyyy-MM-dd"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="说明" prop="remark">
                        <Input type="textarea" v-model="addForm.remark"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="要求" prop="crequest">
                        <Input type="textarea" v-model="addForm.crequest"/>
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
                loading: true,
                count: 0,
                gourpId: null,
                tempIndex: 0,
                pageSize: 20,
                pageNo: 1,
                totalPage: 0,
                totalCount: 0,
                contractNo:"",
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '客户名字',
                        key: 'customName'
                    },
                    {
                        title: '合同号',
                        key: 'contractNo'
                    },
                    {
                        title: '货物数/附件数',
                        key: 'nums'
                    },
                    {
                        title: '制单人',
                        width: 90,
                        key: 'inputBy'
                    }/*,
                     {
                     title: '审单人',
                     key: 'checkBy'
                     }*/,
                    {
                        title: '验货员',
                        key: 'inspector'
                    },
                    {
                        title: '签单日期',
                        key: 'signingDate',
                        render: function (h, params) {
                            return h('div', dateFormat(this.row.signingDate))
                        }
                    },
                    {
                        title: '交货期限',
                        key: 'deliveryPeriod',
                        render: function (h, params) {
                            return h('div', dateFormat(this.row.deliveryPeriod))
                        }
                    },
                    {
                        title: '船期',
                        key: 'shipTime',
                        render: function (h, params) {
                            return h('div', dateFormat(this.row.shipTime))
                        }
                    }/*,
                     {
                     title: '贸易条款',
                     key: 'tradeTerms'
                     }*/,
                    {
                        title: '总金额',
                        width: 80,
                        key: 'totalAmount'
                    },
                    {
                        title: '状态',
                        key: 'state',
                        width: 80,
                        render: (h, params) => {
                            const task_status = parseInt(params.row.state);
                            if (task_status === 0) {
                            	return h('div', [
                                    h('strong', '草稿')
                                ]);

                            }
                            else if (task_status === 1) {
                            	return h('div', [
                                    h('strong', '已上报待保运')
                                ]);

                            }
                            else if (task_status === 2) {
                            	return h('div', [
                                    h('strong', '已保运')
                                ]);

                            }
                            else {
                            	return h('div', [
                                    h('strong', '未知')
                                ]);

                            }
                            

                            
                            
                        }
                    },
                    {
                        title: '操作',
                        key: 'action',
                        align: 'center',
                        render: (h, params) => {
                        // <div><Button type="primary" size="small" style="" @click=>Info</Button></div>
                        // <Input v-model="value" placeholder="Enter something..." style="width: 300px"></Input>
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.$router.push({path:'/cargo/contractProduct/'+(this.data1[params.index].id) })
                                        }
                                    }
                                }, '货物')
                            ]);
                        }
                    }
                ],
                self: this,
                data1: [],
                modal1: false,
                modal2: false,
                updateForm: {
                    id: '',
                    offeror: '',
                    contractNo: '',
                    signingDate: '',
                    inputBy: '',
                    checkBy: '',
                    inspector: '',
                    totalAmount: '',
                    crequest: '',
                    customName: '',
                    shipTime: '',
                    importNum: '',
                    deliveryPeriod: '',//交货日期
                    oldState: '',
                    outState: '',
                    tradeTerms: '',
                    printStyle: '',
                    remark: '',
                    state: ''
                },
                addForm: {
                    offeror: '1',
                    contractNo: '1',
                    signingDate: '2018-12-12',
                    inputBy: 'cgx',
                    checkBy: 'cgx',
                    inspector: 'cgx',
                    totalAmount: '0',
                    crequest: 'request',
                    customName: '客户名字',
                    shipTime: '2018-12-12',
                    importNum: '1',
                    deliveryPeriod: '2018-12-12',//交货日期
                    oldState: '',
                    outState: '',
                    tradeTerms: 'fob',
                    printStyle: '2',
                    remark: 'remark',
                    state: '0'
                },
                ruleCustom: {
                    customName: [
                        {required: true, message: '不能为空', trigger: 'blur'}
                    ],
                    contractNo: [
                        {required: true, message: '不能为空', trigger: 'blur'}
                    ],
                    offeror: [
                        {required: true, message: '不能为空', trigger: 'blur'}
                    ],
                    inputBy: [
                        {required: true, message: '不能为空', trigger: 'blur'}
                    ],
                    checkBy: [
                        {required: true, message: '不能为空', trigger: 'blur'}
                    ],
                    inspector: [
                        {required: true, message: '不能为空', trigger: 'blur'}
                    ],
                    // signingDate: [
                    //     {required: true, message: '不能为空', trigger: 'blur'}
                    // ],
                    importNum: [
                        {required: true, message: '不能为空', trigger: 'blur'}
                    ],
                    // shipTime: [
                    //     {required: true, message: '不能为空', trigger: 'blur'}
                    // ],
                    tradeTerms: [
                        {required: true, message: '不能为空', trigger: 'blur'}
                    ],
                    // deliveryPeriod: [
                    //     {required: true, message: '不能为空', trigger: 'blur'}
                    // ]
                },
                cansubmit:true,
            }
        },
        methods: {
            reset(form){
                this.$refs[form].resetFields();
            },
            addContract(){
                this.modal2 = true;
            },
            add(){
                this.$refs['addForm'].validate((valid) => {
                        if (valid) {
                            const contract = this.addForm;
                            fetch({
                                url: '/cargo/contract',
                                method: 'post',
                                data: contract
                            }).then((result) => {
//                    this.data1.push(result.data);
                                this.gopage(this.pageNo);
                                this.$refs['addForm'].resetFields();
                                this.$Message.success('Success!');
                                this.modal2 = false;
                            });
                        }  else {
                            this.$Message.error("表单验证失败");
                            setTimeout(() => {
                                this.loading = false;
                                this.$nextTick(() => {
                                    this.loading = true;
                                });
                            }, 1000);
                        }
                })
            },
            show (index) {
                this.$Modal.info({
                    title: '购销合同信息',
                    content: `姓名：${this.data1[index].contractName}`
                })
            },
            change(e){
                if (e.length == 1) {
                    this.updateForm = e[0];
                    //将importNum 转成字符串
                    this.updateForm.importNum = this.updateForm.importNum + "";
                    //手动将日期转成字符串
                    this.updateForm.signingDate = dateFormat(e[0].signingDate);
                    this.updateForm.shipTime = dateFormat(e[0].shipTime);
                    this.updateForm.deliveryPeriod = dateFormat(e[0].deliveryPeriod);

                }
                this.setGroupId(e);
            },
            setGroupId(e)
            {
                //初始化
                this.cansubmit=true;
                this.groupId = [];
                this.count = e.length;
                for (var i = 0; i < e.length; i++) {
                    if(e[i].state!=0){
                        this.cansubmit=false;
                    }

                    this.groupId.push(e[i].id);
                }
            },
            edit () {
                if (this.count != 1) {
                    this.modal1 = false;
                    this.$Message.warning('请至少并只能选择一项');
                }
                else {
                    this.modal1 = true;
                }
            },
            remove () {
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/cargo/contract',
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
            update () {
                this.$refs['updateForm'].validate((valid) => {
                        if (valid) {
                            fetch({
                                url: '/cargo/contract',
                                method: 'put',
                                data: this.updateForm
                            }).then((result) => {
                                this.modal1 = false,
                                        this.$Message.success('Success!');
                                //刷新页面数据
                                this.gopage(this.pageNo);
                            });
                        }
                        else {
                            this.$Message.error("表单验证失败");
                            setTimeout(() => {
                                this.loading = false;
                                this.$nextTick(() => {
                                    this.loading = true;
                                });
                            }, 1000);
                        }
                })
            },
            handleReset (form) {
                this.$refs[form].resetFields();
            },
            gopage(pageNo){
                const pageSize = this.pageSize;
                fetch({
                    url: '/cargo/contract',
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
                   if(this.cansubmit){
                       fetch({
                           url: '/cargo/contract/submit',
                           method: 'put',
                           data: this.groupId
                       }).then((result) => {
                           if (result.data == '1') {
                               this.$Message.success('Success!');
                               this.gopage();
                           }else{
                               this.$Message.error('只能提交草稿状态!');
                               this.gopage();
                           }
                       });
                   }else{
                       this.$Message.error('只能提交草稿状态的合同!');
                   }
                } else {
                    this.$Message.warning('请至少选择一项');
                }
            },
            cancel(){
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/cargo/contract/cancel',
                        method: 'put',
                        data: this.groupId
                    }).then((result) => {
                        if (result.data == '1') {
                            this.$Message.success('Success!');
                            this.gopage();
                        }
                    });
                } else {
                    this.$Message.warning('请至少选择一项');
                }
            },
            search(){
                this.gopage();
            }
        },
        mounted: function () {
            this.gopage(1)
        }
    }


</script>