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
                    <Button type="primary" icon="plus-round" @click="addFactory()">新建</Button>
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
                title="添加生产厂家"
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
                            <FormItem label="货物/附件" prop="ctype">

                                    <select v-model="addForm.ctype">
                                        <option >货物</option>
                                        <option >附件</option>
                                    </select>


                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="厂家全称" prop="fullName">
                                <Input type="text" v-model="addForm.fullName"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="名称缩写" prop="factoryName">
                                <Input type="text" v-model="addForm.factoryName"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="联系人" prop="contacts">
                                <Input type="text" v-model="addForm.contacts"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="电话号" prop="phone">
                                <Input type="text" v-model="addForm.phone"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="手机号" prop="mobile">
                                <Input type="text" v-model="addForm.mobile"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="传真" prop="fax">
                                <Input type="text" v-model="addForm.fax"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="地址" prop="address">
                                <Input type="text" v-model="addForm.address"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="验货员" prop="inspector">
                                <Input type="text" v-model="addForm.inspector"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="说明" prop="remark">
                                <Input type="text" v-model="addForm.remark"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="排序号" prop="orderNo">
                                <Input type="text" v-model="addForm.orderNo"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                        <!-- 循环便利row中的两个元素-->
                            <Col span="11">
                            <FormItem label="1正常0停用" prop="state">
                                <Input type="text" v-model="addForm.state"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                    </Row>
            </Form>
        </Modal>


        <Modal
                v-model="updateModal"
                title="编辑生产厂家"
                :mask-closable="false"
                :loading="loading"
                @on-ok="update"
                @on-cancel="cancel"
                width="60%">
            <Form ref="updateForm" :model="updateForm" :rules="formRule" :label-width="80">

                    <Row>

                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="货物/附件" prop="ctype">
                                <Input type="text" v-model="updateForm.ctype"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="厂家全称" prop="fullName">
                                <Input type="text" v-model="updateForm.fullName"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="名称缩写" prop="factoryName">
                                <Input type="text" v-model="updateForm.factoryName"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="联系人" prop="contacts">
                                <Input type="text" v-model="updateForm.contacts"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="电话号" prop="phone">
                                <Input type="text" v-model="updateForm.phone"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="手机号" prop="mobile">
                                <Input type="text" v-model="updateForm.mobile"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="传真" prop="fax">
                                <Input type="text" v-model="updateForm.fax"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="地址" prop="address">
                                <Input type="text" v-model="updateForm.address"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="验货员" prop="inspector">
                                <Input type="text" v-model="updateForm.inspector"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="说明" prop="remark">
                                <Input type="text" v-model="updateForm.remark"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
                            <FormItem label="排序号" prop="orderNo">
                                <Input type="text" v-model="updateForm.orderNo"/>
                            </FormItem>
                            </Col>
                    </Row>
                    <Row>
                            <Col span="11">
                            <FormItem label="1正常0停用" prop="state">
                                <Input type="text" v-model="updateForm.state"/>
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
    import {dateFormat} from '../../utils/date';

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
                        title: '货物/附件',
                        key: 'ctype'
                    },
                    {
                        title: '厂家全称',
                        key: 'fullName'
                    },
                    {
                        title: '名称缩写',
                        key: 'factoryName'
                    },
                    {
                        title: '联系人',
                        key: 'contacts'
                    },
                    {
                        title: '电话号',
                        key: 'phone'
                    },
                    {
                        title: '手机号',
                        key: 'mobile'
                    },
                    {
                        title: '传真',
                        key: 'fax'
                    },
                    {
                        title: '地址',
                        key: 'address'
                    },
                    {
                        title: '验货员',
                        key: 'inspector'
                    },
                    {
                        title: '说明',
                        key: 'remark'
                    },
                    {
                        title: '排序号',
                        key: 'orderNo'
                    },
                    {
                        title: '1正常0停用',
                        key: 'state'
                    }
                ],
                self: this,
                data1: [],
                updateModal: false,
                addModal: false,
                updateForm: {
                        id:"",
                        ctype:"",
                        fullName:"",
                        factoryName:"",
                        contacts:"",
                        phone:"",
                        mobile:"",
                        fax:"",
                        address:"",
                        inspector:"",
                        remark:"",
                        orderNo:"",
                        state:""
                },
                addForm: {
                        ctype:"",
                        fullName:"",
                        factoryName:"",
                        contacts:"",
                        phone:"",
                        mobile:"",
                        fax:"",
                        address:"",
                        inspector:"",
                        remark:"",
                        orderNo:"",
                        state:""
                },
                formRule: {
                    ctype: [
                        {required: true, message:'货物/附件不能为空',trigger:'blur'}
                    ],
                    state: [
                        {required: true, message:'1正常0停用不能为空',trigger:'blur'}
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
            addFactory(){
                this.addModal = true;
            },
            add(){
                this.$refs['addForm'].validate((valid)=>{
                    if(valid)
                    {
                        const factory = this.addForm;
                        fetch({
                            url: '/cargo/factory',
                            method: 'post',
                            data: factory
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
                            url: '/cargo/factory',
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
                        url: '/cargo/factory',
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
                    url: '/cargo/factory',
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
            this.gopage(1);
        }
    }


</script>
