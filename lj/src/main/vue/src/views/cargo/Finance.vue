<template>
    <div class="animated fadeIn">
        <Row>
        <Col span="6">
            财务报运单：<Input v-model="keyWord" placeholder="请输入..." style="width:200px"/>
        </Col>
        <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button></Col>
        </Row>
        <br>
        <Row>

                    <Button type="success" icon="wrench" @click="edit()">修改</Button>
                    <Button type="warning"  @click="submit()">提交</Button>

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
                title="编辑财务报运单"
                :mask-closable="false"
                :loading="loading"
                @on-ok="update"
                @on-cancel="cancel"
                width="60%">
            <Form ref="updateForm" :model="updateForm" :rules="formRule" :label-width="80">

                    <Row>
                           <Col span="11">
	                           
	                            <FormItem label="制单人" prop="inputBy">
                                <Input type="text" v-model="updateForm.inputBy"/>
                            </FormItem>
                            </Col>
                                <Col span="2" style="text-align: center"/>
                            <Col span="11">
	                            <FormItem label="制单日期" prop="inputDate">
	                            	<DatePicker type="date" v-model="updateForm.inputDate" placeholder="Select date" style="width: 200px"></DatePicker>
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
                        title: '制单日期',
                        key: 'inputDate' ,
                        render: function (h, params) {
                            return h('div', dateFormat(this.row.inputDate))
                        }
                    },
                    {
                        title: '制单人',
                        key: 'inputBy'
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
                                    h('strong', '已上报')
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
                addModal: false,
                updateForm: {
                        id:"",
                        inputDate:"",
                        inputBy:"",
                        state:""
                },
                formRule: {
                   
                    inputBy: [
                        {required: true, message:'制单人不能为空',trigger:'blur'}
                    ]
                    
                }
            }
        },
        methods: {
            change(e){
                this.count = e.length;
                if (e.length == 1) {
                    this.updateForm = e[0];
                    this.updateForm.state = e[0].state+"";
                    this.updateForm.inputDate = dateFormat(e[0].inputDate);
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
            addFinance(){
                this.addModal = true;
            },
            add(){
                this.$refs['addForm'].validate((valid)=>{
                    if(valid)
                    {
                        const finance = this.addForm;
                        fetch({
                            url: '/cargo/finance',
                            method: 'post',
                            data: finance
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
                if (this.groupId==undefined||this.groupId.length != 1) {
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
                            url: '/cargo/finance',
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
                        url: '/cargo/finance',
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
                    url: '/cargo/finance',
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
            submit(){
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/cargo/finance/submit',
                        method: 'put',
                        data: this.groupId
                    }).then((result) => {
                        if (result.data == 'ok') {
                            this.$Message.success('Success!');
                            this.gopage();
                        }
                    });
                } else {
                    this.$Message.warning('请至少选择一项');
                }
            }
        },
        mounted: function () {
            this.gopage();
        }
    }


</script>