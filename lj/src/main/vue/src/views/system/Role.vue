<template>
    <div class="animated fadeIn">
    <Row>
        <Col span="6">角色：
            <Input v-model="name" placeholder="请输入..." style="width:200px"/>
        </Col>
        <Col span="8">
            <Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button>
        </Col>
    </Row>
    <br>
    <Row>
        <Button type="primary" icon="plus-round" @click="addRole()">新建</Button>
        <Button type="success" icon="wrench" @click="edit()">修改</Button>
        <Button type="error" icon="trash-a" @click="remove()">删除</Button>
    </Row>
    <br>
    <Row>
        <Table border :columns="columns1" :data="data1" @on-selection-change="s=>{change(s)}"></Table>
    </Row>
    <br>
    <Row>
        <Page :total="totalCount" :page-size="pageSize" :current="pageNo" @on-change="gopage"
              align="center"></Page>
    </Row>

    <Modal
            v-model="addModal"
            title="添加角色"
            :mask-closable="false"
            :loading="loading"
            @on-ok="add"
            @on-cancel="cancel"
            width="60%">
        <Form ref="addForm" :model="addForm" :rules="ruleCustom" :label-width="80">
            <Row>
                <Col span="11">
                    <FormItem label="角色名字" prop="name">
                        <Input type="text" v-model="addForm.name"/>
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
                <Col span="11">
                    <FormItem label="排序号" prop="orderNo">
                        <Input type="text" v-model="addForm.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
            </Row>
            <Row>
                <Col span="24">
                    <FormItem label="选择模块" >
                        <ul id="addTree" class="ztree"> </ul>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
            </Row>

        </Form>
    </Modal>


    <Modal
            v-model="updateModal"
            title="编辑角色"
            :mask-closable="false"
            :loading="loading"
            @on-ok="update"
            @on-cancel="cancel"
            width="60%">
        <Form ref="updateForm" :model="updateForm" :rules="ruleCustom" :label-width="80">

            <Row>
                <Col span="11">
                    <FormItem label="角色名字" prop="name">
                        <Input type="text" v-model="updateForm.name"/>
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
                    <FormItem label="排序号" >
                        <Input type="text" v-model="updateForm.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
            </Row>
            <Row>
                <Col span="24">
                    <FormItem label="选择模块" >
                        <ul id="editTree" class="ztree"> </ul>
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
    import "ztree/css/demo.css";
    import "ztree/css/zTreeStyle/zTreeStyle.css";
    import "ztree/js/jquery-1.4.4.min.js"
    import "ztree/js/jquery.ztree.core.min.js"
    import "ztree/js/jquery.ztree.excheck.min.js"
    export default {
        data() {
            return {
                loading: true,
                count: 0,
                gourpId: null,
                pageSize: 8,
                pageNo: '',
                totalPage: 0,
                totalCount: 0,
                name: "",
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '角色名字',
                        key: 'name'
                    },
                    {
                        title: '备注',
                        key: 'remark'
                    },
                    {
                        title: '排序号',
                        key: 'orderNo'
                    }
                ],
                self: this,
                data1: [],
                updateModal: false,
                addModal: false,
                updateForm: {
                    name: "",
                    remark: "",
                    orderNo: "",
                    modualIds:''

                },
                addForm: {
                    name: "",
                    remark: "",
                    orderNo: "",
                    modualIds:''
                },
                ruleCustom: {
                    name: [
                        {required: true, message: '角色名字不能为空', trigger: 'blur'}
                    ]
                    ,
                    remark: [
                        {required: true, message: '备注不能为空', trigger: 'blur'}
                    ]
                    ,
                    orderNo: [
                        {required: true, message: '排序号不能为空', trigger: 'blur'}
                    ]
                },
                setting:{
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                },
               addNodes:[
                        { id:1, pId:0, name:"随意勾选 1", open:true},

                ],
                editNode:[
                    { id:1, pId:0, name:"随意勾选 1", open:true},

                ],
                addTreeNodes:'',
                editTreeNodes:'',
            }
        },
        methods: {
            change(e) {
                if (e.length == 1) {
                    this.updateForm = e[0];

                }
                this.setGroupId(e);
            },
            setGroupId(e) {
                this.groupId = [];
                this.count = e.length;
                for (var i = 0; i < e.length; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            reset(form) {
                this.$refs[form].resetFields();
            },
            addRole() {
                this.addModal = true;
            },
            add() {
                this.$refs['addForm'].validate((valid) => {
                    if (valid) {
                        const role = this.addForm;
                        //获取当前的选中的ztree节点
                        let checkedNodes=  this.addTreeNodes.getCheckedNodes(true);
                        //清空moduleIds
                        this.addForm.modualIds=[];
                        //循环放入ids
                        for(let i=0;i<checkedNodes.length;i++){
                            let moduleId=checkedNodes[i].id;
                            this.addForm.modualIds.push(moduleId);
                        }
                        fetch({
                            url: '/system/role',
                            method: 'post',
                          data:role,
                        }).then((result) => {
                            this.gopage(this.pageNo);
                            this.$refs['addForm'].resetFields();
                            this.$Message.success('Success!');
                            this.addModal = false;
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
            edit() {
                if (this.count != 1) {
                    this.updateModal = false;
                    this.$Message.warning('请至少并只能选择一项');
                }
                else {
                    //初始化编辑树
                    fetch({
                        url:'system/module/CheckZtree/'+this.updateForm.id,
                        method:'get',
                    }).then(resp=>{
                        this.editNode=resp.data;
                        this.editTreeNodes=$.fn.zTree.init($("#editTree"), this.setting,this.editNode);
                    })

                    this.updateModal = true;


                }
            },
            update() {
                this.$refs['updateForm'].validate((valid) => {
                    if (valid) {

                        //获取当前的选中的ztree节点
                        let checkedNodes=  this.editTreeNodes.getCheckedNodes(true);
                        //清空moduleIds
                        this.updateForm.modualIds=[];
                        //循环放入ids
                        for(let i=0;i<checkedNodes.length;i++) {
                            let moduleId = checkedNodes[i].id;
                            this.updateForm.modualIds.push(moduleId);

                        }

                        fetch({
                            url: '/system/role',
                            method: 'put',
                            data: this.updateForm
                        }).then((result) => {
                            this.updateModal = false,
                                this.$Message.success('Success!');
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
            remove() {
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/system/role',
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
            gopage(pageNo) {

                const pageSize = this.pageSize;
                const name = this.name;
                fetch({
                    url: '/system/role',
                    method: 'get',
                    params: {pageNo, pageSize, name}
                }).then((result) => {
                    this.data1 = result.data.list;
                    this.pageNo = pageNo;
                    this.pageSize = pageSize;
                    this.totalCount = result.data.totalCount;
                });
            },
            cancel() {
                this.$Message.info('点击了取消');
            }
        },
        mounted: function () {
            this.gopage(1);
            //初始化新建树
            fetch({
                url:'system/module/allZtree',
                method:'get'

            }).then((resp)=>{
                this.addNodes=resp.data;
                this.addTreeNodes=$.fn.zTree.init($("#addTree"), this.setting,this.addNodes);

            })
        }
    }


</script>
