<template>


    <div class="animated fadeIn">
        <Row >
            <Col span="8">合同号：
            <Input v-model="contractNo" placeholder="请输入..." style="width:200px"/>
            </Col>
            <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button></Col>
        </Row>
        <br>
        <Row>
            <Button type="success" icon="wrench" @click="edit()">打印</Button>
            <Button type="error" icon="trash-a" @click="addExport()">保运</Button>
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
                v-model="modal2"
                title="新增出口保运"
                @on-ok="add"
                @on-cancel="cancel" width="60%">
            <Form ref="addForm" :model="addForm" :rules="ruleCustom" :label-width="80">
                <Row>
                    <Col span="11">
                    <FormItem label="信用证号" prop="lcno">
                        <Input type="text" v-model="addForm.lcno"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="收货人及地址" prop="consignee">
                        <Input type="text" v-model="addForm.consignee"/>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="唛头" prop="marks">
                        <Input type="text" v-model="addForm.marks"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="装运港" prop="shipmentPort">
                        <Input type="text" v-model="addForm.shipmentPort"/>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="目的港" prop="destinationPort">
                        <Input type="text" v-model="addForm.destinationPort"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="运输方式" prop="transportMode">
                        <Input type="text" v-model="addForm.transportMode"/>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="11">
                    <FormItem label="价格条件" prop="priceCondition">
                        <Input type="text" v-model="addForm.priceCondition"/>
                    </FormItem>
                    </Col>
                    <Col span="2" style="text-align: center"/>
                    <Col span="11">
                    <FormItem label="备注" prop="remark">
                        <Input type="textarea" v-model="addForm.remark"/>
                    </FormItem>
                    </Col>
                </Row>

                <Row>
                    <Col span="24">
                    <FormItem>
                        <Button type="primary" @click="add">保存</Button>
                        <Button type="ghost" @click="reset('addForm')">清空</Button>
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
            const validateUser = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入购销合同名'));
                }
            };
            const validateAddr = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入地址'));
                }
            };
            const validateid = (rule, value, callback) => {
                if (value === '') {
                    return callback(new Error('年龄不能为空'));
                }
                // 模拟异步验证效果
                setTimeout(() => {
                    if (!Number.isInteger(value)) {
                        callback(new Error('请输入整数'));
                    } else {
                        if (value < 18) {
                            callback(new Error('必须年满18！'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };
            return {
                count: 0,
                gourpId: null,
                customerContract:null,
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
                    },
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
                                    h('strong', 'unkown')
                                ]);

                            }
                        }
                    }
                ],
                self: this,
                data1: [],
                modal1: false,
                modal2: false,
                addForm: {
                    lcno: '1',
                    contractIds: '',
                    customerContract: '',
                    consignee: 'consignee',
                    marks: 'marks',
                    shipmentPort: 'shipmentPort',
                    destinationPort: 'destinationPort',
                    transportMode: 'sea',
                    priceCondition: 'price',
                    remark: '1',
                    boxNums: '12',
                    state: '0'
                },
                ruleCustom: {
                    contractName: [
                        {validator: validateUser, trigger: 'blur'}
                    ],
                    id: [
                        {validator: validateid, trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            reset(form){
                this.$refs[form].resetFields();
            },
            addExport(){

                if (this.groupId != null && this.groupId != "") {
                    this.modal2 = true;
                } else {
                    this.$Message.warning('请至少选择一项');
                }
            },
            add(){
                this.addForm.contractIds = "";
                this.addForm.customerContract = "";
                this.groupId.forEach((id,index)=>{
                    this.addForm.contractIds+=id;
                    if(index+1<this.groupId.length){this.addForm.contractIds+=","}
                });

                this.customerContract.forEach((id,index)=>{
                    this.addForm.customerContract+=id;
                    if(index+1<this.customerContract.length){this.addForm.customerContract+=","}
                });
                const contract = this.addForm;

                fetch({
                    url: '/cargo/export',
                    method: 'post',
                    data: contract
                }).then((result) => {
//                    this.data1.push(result.data);
                    this.gopage(this.pageNo);
                    this.$refs['addForm'].resetFields();
                    this.$Message.success('Success!');
                    this.modal2 = false;
                }).catch((e)=>{
                    this.$Message.error(e.response.data);
                });
            },
            change(e){
                if (e.length == 1) {
                    this.updateForm = e[0];
                    //将importNum 转成字符串
                    this.updateForm.importNum = this.updateForm.importNum + "";
                }
                this.setGroupId(e);
                this.setCustomerContract(e);
            },
            setGroupId(e)
            {
                this.groupId = [];
                this.count = e.length;
                for (var i = 0; i < e.length; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            setCustomerContract(e)
            {
                this.customerContract = [];
                this.count = e.length;
                for (var i = 0; i < e.length; i++) {
                    this.customerContract.push(e[i].contractNo);
                }
            },
            handleReset (form) {
                this.$refs[form].resetFields();
            },
            gopage(){
                const pageNo = this.pageNo;
                const pageSize = this.pageSize;
                const contractNo = this.contractNo;
                fetch({
                    url: '/cargo/contract/1',
                    method: 'get',
                    params: {pageNo, pageSize,contractNo}
                }).then((result) => {
                    this.data1 = result.data.list;
                    this.pageNo = pageNo;
                    this.pageSize = pageSize;
                    this.totalCount = result.data.totalCount;
                });
            },
            search(){
                this.gopage();
            }
        },
        mounted: function () {
            this.gopage()
        }
    }


</script>