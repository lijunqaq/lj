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
                    <Button type="primary" icon="plus-round" @click="addProduct()">新建</Button>
                    <Button type="success" icon="wrench" @click="edit()">修改</Button>
                    <Button type="error" icon="trash-a" @click="remove()">删除</Button>
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
                v-model="addModal"
                title="添加产品"
                :mask-closable="false"
                :loading="loading"
                @on-ok="add"
                @on-cancel="cancel"
                width="60%">
            <Form ref="addForm" :model="addForm" :rules="formRule" :label-width="80">
                <!--一次性取两个元素放在row集合中 -->
                    <Row>
                        <!-- 循环便利row中的两个元素-->

                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="编号" prop="productNo">
                                <Input type="text" v-model="addForm.productNo"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="照片" prop="productImage">
                                <Input type="text" v-model="addForm.productImage"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="描述" prop="description">
                                <Input type="text" v-model="addForm.description"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->

                        <Col span="11">
                            <FormItem label="生产厂家" >
                                <Select v-model="addForm.factoryId" filterable @on-change="o=>changeFactory(this.addForm,o)" label-in-value=true>
                                    <Option v-for="item in factoryList" :value="item.id" :key="item.id" >{{ item.factoryName }}</Option>
                                </Select>
                            </FormItem>
                        </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="厂家简称" prop="factoryName">
                                <Input type="text" v-model="addForm.factoryName"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="市场价" prop="price">
                                <Input type="text" v-model="addForm.price"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="尺寸长" prop="sizeLenght">
                                <Input type="text" v-model="addForm.sizeLenght"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="尺寸宽" prop="sizeWidth">
                                <Input type="text" v-model="addForm.sizeWidth"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="尺寸高" prop="sizeHeight">
                                <Input type="text" v-model="addForm.sizeHeight"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="颜色" prop="color">
                                <Input type="text" v-model="addForm.color"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="包装" prop="packing">
                                <Input type="text" v-model="addForm.packing"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="PCS/SETS" prop="packingUnit">
                                <Input type="text" v-model="addForm.packingUnit"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>

                    </Row>

                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="数量" prop="qty">
                                <Input type="text" v-model="addForm.qty"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="体积" prop="cbm">
                                <Input type="text" v-model="addForm.cbm"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="大箱尺寸长" prop="mpsizeLenght">
                                <Input type="text" v-model="addForm.mpsizeLenght"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="大箱尺寸宽" prop="mpsizeWidth">
                                <Input type="text" v-model="addForm.mpsizeWidth"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="大箱尺寸高" prop="mpsizeHeight">
                                <Input type="text" v-model="addForm.mpsizeHeight"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="备注" prop="remark">
                                <Input type="text" v-model="addForm.remark"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="录入时间" prop="inputTime">
                                <Input type="text" v-model="addForm.inputTime"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                    </Row>
            </Form>
        </Modal>


        <Modal
                v-model="updateModal"
                title="编辑产品"
                :mask-closable="false"
                :loading="loading"
                @on-ok="update"
                @on-cancel="cancel"
                width="60%">
            <Form ref="updateForm" :model="updateForm" :rules="formRule" :label-width="80">

                    <Row>

                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="编号" prop="productNo">
                                <Input type="text" v-model="updateForm.productNo"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="照片" prop="productImage">
                                <Input type="text" v-model="updateForm.productImage"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="描述" prop="description">
                                <Input type="text" v-model="updateForm.description"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>

                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="厂家简称" prop="factoryName">
                                <Input type="text" v-model="updateForm.factoryName"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="市场价" prop="price">
                                <Input type="text" v-model="updateForm.price"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="尺寸长" prop="sizeLenght">
                                <Input type="text" v-model="updateForm.sizeLenght"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="尺寸宽" prop="sizeWidth">
                                <Input type="text" v-model="updateForm.sizeWidth"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="尺寸高" prop="sizeHeight">
                                <Input type="text" v-model="updateForm.sizeHeight"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="颜色" prop="color">
                                <Input type="text" v-model="updateForm.color"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="包装" prop="packing">
                                <Input type="text" v-model="updateForm.packing"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="PCS/SETS" prop="packingUnit">
                                <Input type="text" v-model="updateForm.packingUnit"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>

                    </Row>

                    <Row>
                            <Col span="11">
                            <FormItem label="数量" prop="qty">
                                <Input type="text" v-model="updateForm.qty"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="体积" prop="cbm">
                                <Input type="text" v-model="updateForm.cbm"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="大箱尺寸长" prop="mpsizeLenght">
                                <Input type="text" v-model="updateForm.mpsizeLenght"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="大箱尺寸宽" prop="mpsizeWidth">
                                <Input type="text" v-model="updateForm.mpsizeWidth"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="大箱尺寸高" prop="mpsizeHeight">
                                <Input type="text" v-model="updateForm.mpsizeHeight"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="备注" prop="remark">
                                <Input type="text" v-model="updateForm.remark"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="录入时间" prop="inputTime">
                                <Input type="text" v-model="updateForm.inputTime"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
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
                        title: '编号',
                        key: 'productNo'
                    },
                    {
                        title: '照片',
                        key: 'productImage'
                    },
                    {
                        title: '描述',
                        key: 'description'
                    },
                    {
                        title: '厂家简称',
                        key: 'factoryName'
                    },
                    {
                        title: '市场价',
                        key: 'price'
                    },
                    {
                        title: '尺寸长',
                        key: 'sizeLenght'
                    },
                    {
                        title: '尺寸宽',
                        key: 'sizeWidth'
                    },
                    {
                        title: '尺寸高',
                        key: 'sizeHeight'
                    },
                    {
                        title: '颜色',
                        key: 'color'
                    },
                    {
                        title: '包装',
                        key: 'packing'
                    },
                    {
                        title: 'PCS/SETS',
                        key: 'packingUnit'
                    },

                    {
                        title: '数量',
                        key: 'qty'
                    },
                    {
                        title: '体积',
                        key: 'cbm'
                    },
                    {
                        title: '大箱尺寸长',
                        key: 'mpsizeLenght'
                    },
                    {
                        title: '大箱尺寸宽',
                        key: 'mpsizeWidth'
                    },
                    {
                        title: '大箱尺寸高',
                        key: 'mpsizeHeight'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                    {
                        title: '录入时间',
                        key: 'inputTime',
                        render: function (h, params) {
                            return h('div', dateFormat(this.row.inputTime))
                        }
                    }
                ],
                self: this,
                data1: [],
                updateModal: false,
                addModal: false,
                updateForm: {
                        id:"",
                        productNo:"",
                        productImage:"",
                        description:"",
                        factoryId:"",
                        factoryName:"",
                        price:"",
                        sizeLenght:"",
                        sizeWidth:"",
                        sizeHeight:"",
                        color:"",
                        packing:"",
                        packingUnit:"",
                        type20:"",
                        type40:"",
                        type40hc:"",
                        qty:"",
                        cbm:"",
                        mpsizeLenght:"",
                        mpsizeWidth:"",
                        mpsizeHeight:"",
                        remark:"",
                        inputTime:""
                },
                addForm: {
                        productNo:"",
                        productImage:"",
                        description:"",
                        factoryId:"",
                        factoryName:"",
                        price:"",
                        sizeLenght:"",
                        sizeWidth:"",
                        sizeHeight:"",
                        color:"",
                        packing:"",
                        packingUnit:"",
                        type20:"",
                        type40:"",
                        type40hc:"",
                        qty:"",
                        cbm:"",
                        mpsizeLenght:"",
                        mpsizeWidth:"",
                        mpsizeHeight:"",
                        remark:"",
                        inputTime:""
                },
                formRule: {
                    productId: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ],
                    price: [
                        {required: true, message:'不能为空',trigger:'blur'}
                    ]
                    ,
                    productNo: [
                        {required: true, message:'编号不能为空',trigger:'blur'}
                    ]
                }
            }
        },
        methods: {
            change(e){
                this.count = e.length;
                if (e.length == 1) {
                    this.updateForm = e[0];
                }
                this.setGroupId(e);
            },
            setGroupId(e)
            {
                this.groupId = [];

                for (var i = 0; i < e.length; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            reset(form){
                this.$refs[form].resetFields();
            },
            addProduct(){
                this.addModal = true;
            },
            add(){
                this.$refs['addForm'].validate((valid)=>{
                    if(valid)
                    {
                        const product = this.addForm;
                        fetch({
                            url: '/cargo/product',
                            method: 'post',
                            data: product
                        }).then((result) => {
                            this.gopage(this.pageNo);
                            this.$refs['addForm'].resetFields();
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
                            url: '/cargo/product',
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
                        url: '/cargo/product',
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
                    url: '/cargo/product',
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
            }
        },
        mounted: function () {
            this.gopage();
            //初始化所有生产厂家
            fetch({
                url:'/cargo/factory/all',
                method:'get',
                params:{cType:"货物"}
            }).then((result)=>{
                this.factoryList = result.data;
            })
        }
    }


</script>
