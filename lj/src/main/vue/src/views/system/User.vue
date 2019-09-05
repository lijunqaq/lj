<template>
    <div class="animated fadeIn">
    <Row>
        <Col span="6">用户名：
            <Input v-model="keyWord" placeholder="请输入..." style="width:200px"/>
        </Col>
        <Col span="8">
            <Button type="primary" shape="circle" icon="ios-search" @click="gopage()">搜索</Button>
        </Col>
    </Row>
    <br>
    <Row>
        <Button type="primary" icon="plus-round" @click="addUser()">新建</Button>
        <Button type="success" icon="wrench" @click="edit()">修改</Button>
        <Button type="error" icon="trash-a" @click="remove()">删除</Button>
        <Button type="success" icon="wrench" @click="role()">修改角色</Button>
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

    <Modal
            v-model="updateModal"
            title="编辑用户"
            width="60%"
            :mask-closable="false"
            :loading="loading"
            @on-ok="update"
            @on-cancel="cancel">
        <Form ref="updateForm" :model="updateForm" :rules="ruleCustom" :label-width="80">
            <Row>
                <Col span="11">
                    <FormItem label="所在部门" prop="deptID">
                        <Select v-model="updateForm.deptID" filterable>
                            <Option v-for="item in deptList" :value="item.keyID" :key="item.keyID">{{ item.deptName }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="直属领导" prop="managerID">
                        <Select v-model="updateForm.managerID">
                            <Option v-for="item in userList" :value="item.keyID" :disabled="item.keyID==updateForm.managerID"
                                    :key="item.keyID">{{ item.userName }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
            </Row>


            <Row>
                <Col span="11">
                    <FormItem label="登录名" prop="userName">
                        <Input type="text" readonly v-model="updateForm.userName"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="姓名" prop="userInfo.name">
                        <Input type="text" v-model="updateForm.userInfo.name"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="入职日期" prop="userInfo.createTime">
                        <DatePicker v-model="updateForm.userInfo.createTime" format="yyyy-MM-dd" type="date"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="薪水" prop="userInfo.salary">
                        <Input type="text" v-model="updateForm.userInfo.salary"/>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="等级" prop="userInfo.degree">
                        <Select v-model="updateForm.userInfo.degree" filterable>
                            <Option v-for="item in degreeList" :value="item.value" :key="item.value">{{ item.label
                                }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="性别" prop="userInfo.sex">
                        <RadioGroup v-model="updateForm.userInfo.sex" type="button">
                            <Radio label="1">男</Radio>
                            <Radio label="0">女</Radio>
                        </RadioGroup>
                    </FormItem>
                </Col>
                <Col span="11">
                    <FormItem label="状态" prop="state">
                        <Radio-group v-model="updateForm.state" type="button">
                            <Radio label="1">启用</Radio>
                            <Radio label="0">停用</Radio>
                        </Radio-group>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="岗位" prop="userInfo.station">
                        <Input type="text" v-model="updateForm.userInfo.station"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="电话" prop="userInfo.telephone">
                        <Input type="text" v-model="updateForm.userInfo.telephone"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="邮箱" prop="userInfo.email">
                        <Input type="text" v-model="updateForm.userInfo.email"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="出生年月" prop="userInfo.birthday">
                        <DatePicker type="date" v-model="updateForm.userInfo.birthday" format="yyyy-MM-dd"
                                    placeholder="Select date" style="width: 200px"></DatePicker>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">

                    <FormItem label="排序号" prop="userInfo.orderNo">
                        <Input type="text" v-model="updateForm.userInfo.orderNo"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="说明" prop="userInfo.remark">
                        <Input type="textarea" v-model="updateForm.userInfo.remark"/>
                    </FormItem>
                </Col>
            </Row>

        </Form>
    </Modal>
    <Modal
            v-model="addModal"
            title="添加用户"
            width="60%"
            @on-ok="add"
            @on-cancel="cancel"
            :loading="loading">
        <Form ref="addForm" :model="addForm" :rules="ruleCustom" :label-width="80">
            <Row>
                <Col span="11">
                    <FormItem label="所在部门" prop="deptID">
                        <Select v-model="addForm.deptID" filterable>
                            <Option v-for="item in deptList" :value="item.keyID" :key="item.keyID">{{ item.deptName }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="直属领导" prop="managerID">
                        <Select v-model="addForm.managerID" filterable>
                            <Option v-for="item in userList" :value="item.keyID" :key="item.keyID">{{ item.userName }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
            </Row>
            <!--<template>-->
                <!--<Upload-->
                        <!--multiple-->
                        <!--action="/img/qiniu">-->
                    <!--<Button type="ghost" icon="ios-cloud-upload-outline">上传头像</Button>-->
                <!--</Upload>-->
            <!--</template>-->
            <Row>
            <!--<div class="pic_upload">-->
                <!--<Upload-->

                        <!--ref="upload"-->
                        <!--:show-upload-list="false"-->
                        <!--type="drag"-->
                        <!--:format="['jpg','jpeg','png']"-->
                        <!--:on-success="handleSuccess"-->
                        <!--:headers="headers"-->
                        <!--action="http://localhost:8888/img/qiniu"-->
                        <!--style="display: inline-block;width:90px;">-->
                    <!--<div style="width: 90px;height:90px;line-height: 90px;">-->
                        <!--<Icon type="camera" size="20"></Icon>-->
                    <!--</div>-->
                <!--</Upload>-->
            <!--</div>-->
                <template>



                        <Col span="4">  <p style="margin-left: 20px">请上传头像</p> </Col>
                        <Col span="1" style="text-align: center"/>
                        <Col span="11">
                        <form>
                            <a href="javascript:;" class="a-upload">
                            <input type="file"
                                   @change="getFile($event)">点击这里上传文件
                            </a>
                            <!--<div>-->
                            <!--<button class="ivu-btn" @click="submit($event)">提交</button>-->
                            <!--</div>-->

                        </form>
                        </Col>
                    <Col span="9">
                        <FormItem label="图片">
                            <img :src="addForm.picture"   style="height: 80px;width: 100px"/>
                        </FormItem>
                    </Col>
                </template>

            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="登录名" prop="userName">
                        <Input type="text" v-model="addForm.userName"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="姓名" prop="name">
                        <Input type="text" v-model="addForm.name"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="入职日期" prop="createTime">
                        <DatePicker v-model="addForm.createTime" type="date" placeholder="Select date"
                                    style="width: 200px"></DatePicker>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="薪水" prop="salary">
                        <Input type="text" v-model="addForm.salary"/>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="等级" prop="degree">
                        <Select v-model="addForm.degree" filterable>
                            <Option v-for="item in degreeList" :value="item.value" :key="item.value">{{ item.label
                                }}
                            </Option>
                        </Select>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="性别" prop="gender">
                        <RadioGroup v-model="addForm.sex" type="button">
                            <Radio label="男">男</Radio>
                            <Radio label="女">女</Radio>
                        </RadioGroup>
                    </FormItem>
                </Col>
                <Col span="11">
                    <FormItem label="状态" prop="state">
                        <Radio-group v-model="addForm.state" type="button">
                            <Radio label="1">启用</Radio>
                            <Radio label="0">停用</Radio>
                        </Radio-group>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <Col span="11">
                    <FormItem label="岗位" prop="station">
                        <Input type="text" v-model="addForm.station"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="电话" prop="telephone">
                        <Input type="text" v-model="addForm.telephone"/>
                    </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="11">
                    <FormItem label="邮箱" prop="email">
                        <Input type="text" v-model="addForm.email"/>
                    </FormItem>
                </Col>
                <Col span="2" style="text-align: center"/>
                <Col span="11">
                    <FormItem label="出生年月" prop="birthday">
                        <DatePicker type="date" v-model="addForm.birthday" placeholder="Select date"
                                    style="width: 200px"></DatePicker>
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
                <Col span="11">
                    <FormItem label="说明" prop="remark">
                        <Input type="textarea" v-model="addForm.remark"/>
                    </FormItem>
                </Col>
            </Row>

        </Form>
    </Modal>

    <Modal
            v-model="roleModal"
            title="分配角色"
            @on-ok="updateRole"
            @on-cancel="cancel" width="60%">
        <Form ref="roleForm" :model="roleForm" :label-width="80">

            <Row>
                <Col span="11">
                    <FormItem label="登录名" prop="userName">
                        <Input type="text" v-model="roleForm.userName"/>
                    </FormItem>
                </Col>
            </Row>

            <Row>
                <FormItem label="角色" prop="roleIds">
                    <CheckboxGroup v-model="roleForm.roleIds">
                        <Checkbox v-for="(role,key) in roleList" :key="role.keyID" :label="role.keyID">{{role.name}}
                        </Checkbox>
                    </CheckboxGroup>
                </FormItem>
            </Row>

        </Form>
    </Modal>
</div>
</template>

<script type="text/ecmascript-6">
    import fetch from '../../utils/fetch';
    import formatDate from '../../utils/date';
    import Cookies from 'js-cookie';

    export default {
        data() {
            const validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('用户名不能为空'));
                } else {
                    fetch({
                            url:'system/user/checkUsername/'+value,
                        method:'get',
                    }).then(resp=>{
                        //用户名已存在
                        if(resp.data=="1"){
                            callback(new Error('用户名不能重复'));
                        }else{
                            //用户名可用
                            callback();
                        }
                    })


                }
            };
            return {
                loading: true,
                access_token:0,
                count: 0,
                file: '',
                gourpId: null,
                pageSize: 5,
                pageNo: 1,
                totalPage: 0,
                totalCount: 0,
                keyWord: "",
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '登录名',
                        key: 'userName'
                    },
                    {
                        title: '用户名',
                        key: 'name'
                    },
                    {
                        title: '电话',
                        key: 'telephone'
                    },

                    {
                        title: '性别',
                        key: 'sex'
                    },
                    {
                        title: '头像',
                        key: 'picture',
                        render: function (h, params) {
                            return h('div', [
                                h('img', {
                                    style: {
                                        width: "30px",
                                        verticalAlign: "middle"
                                    },
                                    attrs: {
                                        src: this.row.picture
                                    }
                                },)
                            ]);
                        }
                     },
                    {
                        title: '生日',
                        key: 'birthday',
                        render: function (h, params) {
                            return h('div', formatDate(this.row.birthday))
                        }
                    },
                    {
                        title: '状态',
                        key: 'state',
                        render: (h, params) => {
                            const state = parseInt(params.row.state);
                            if (state === 0) {
                                return "不可用";
                            }
                            else if (state === 1) {
                                return "可用";
                            }
                            else {
                                return "unkown"
                            }
                        }
                    }
                ],
                self: this,
                data1: [],
                formRule: {
                    userName: [
                        { required: true,message:'cannot be empty', trigger: 'blur' }
                    ]
                },
             headers: {
                 'Access-Control-Allow-Origin' : '*',
                 'Access-Control-Allow-Credentials': true,
             },
                addModal: false,
                updateModal: false,
                roleModal: false,
                addForm: {
                    "managerID": "",
                    "deptName": "",
                    "keyID":"",
                    "deptID": "100",
                    "userName": "",
                    "picture": "",
                    "state": "1",
                        "name": "翩若惊鸿",
                        "createTime": "2018-08-17",
                        "salary": "10000",
                        "degree": "1",
                        "sex": "1",
                        "station": "开发部",
                        "telephone": "1001",
                        "email": "lijunqaq@sina.com",
                        "birthday": "2018-08-17",
                        "orderNo": "12",
                        "remark": "一个人的夜"
                },
                updateForm: {
                    "managerID": "",
                    "deptID": "",
                    "deptName": "",
                    "userName": "",
                    "state": "",
                    "keyID":"",
                    "picture":"",
                    "userInfo": {
                        "name": "",
                        "createTime": "",
                        "salary": "",
                        "degree": "",
                        "sex": "",
                        "station": "",
                        "telephone": "",
                        "email": "",
                        "birthday": "",
                        "orderNo": "",
                        "remark": ""
                    }
                },
                roleForm: {
                    keyID: "",
                    userName: "",
                    roleIds: {}
                },
                deptList: {},
                userList:{},
                roleList:{},
                degreeList:[
                    {
                        value: '0',
                        label: '超级管理员'
                    },
                    {
                        value: '1',
                        label: '跨部门跨人员'
                    },
                    {
                        value: '2',
                        label: '管理所有下属部门和人员'
                    },
                    {
                        value: '3',
                        label: '管理本部门'
                    },
                    {
                        value: '4',
                        label: '普通员工'
                    }
                ],
                ruleCustom: {
                    userName: [
                        { validator: validatePass, trigger: 'blur' }

                    ]
                }
            }
        },
        methods: {
            getFile: function (event) {

                this.file = event.target.files[0];
                console.log(this.file);

                //判断文件大小和文件类型
                if(this.file.size>20 *1024 *1024)
                {
                    alert('上传的图片大于2M,请重新选择');
                    return false;
                }
                // if(!"/image\/\w+/".test(this.file.type)){
                //     alert('上传的不是图片');
                //     return false;
                // }
                var AllImgExt=".jpg|.jpeg|.gif|.bmp|.png|";
                var extName = this.file.name.substring(this.file.name.lastIndexOf(".")).toLowerCase();//（把路径中的所有字母全部转换为小写）
                if(AllImgExt.indexOf(extName+"|")==-1)
                {
                    var ErrMsg="该文件类型不允许上传。请上传 "+AllImgExt+" 类型的文件，当前文件类型为"+extName;
                    alert(ErrMsg);
                    return false;
                }

                //阻止元素发生默认的行为
                event.preventDefault();
                let formData = new FormData();
                formData.append("qiniuimg", this.file);
                let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }
                console.log("====="+formData);
                console.log("====="+this.file);
                //localhost
                //http://lijunqaq.top:8888
                fetch.post('http://localhost:8888/v1/img/qiniu', formData,config)
                    .then((response) =>{
                        this.addForm.picture=response.data;
                        this.$Message.success('Success!');
                        console.log(this.addForm.picture);

                    });
                // fetch({
                //     url: 'http://localhost:8888/img/qiniu',
                //     method: 'post',
                //     params: formData,
                //     config:config
                // }).then((result) => {
                //     this.picture=response.data();
                //     this.$Message.success('Success!');
                //     console.log(this.picture);
                //     this.gopage(this.pageNo);
                // });
            },
            change(e) {
                if (e.length == 1) {

                    var editUser = e[0];


// 手动赋值的原因：userInfo中的manager不会转换成json，单独传了一个manageId过来
//                     if(editUser.manageId!=undefined)
//                     {
//                         this.updateForm.manageId= editUser.manageId;
//                     }
//                     if(editUser.dept!=undefined)
//                     {
//                         this.updateForm.dept.id=editUser.dept.id;
//                     }
//                     if(editUser.userInfo != undefined)
//                     {
                    this.updateForm.managerID = editUser.managerID;
                    this.updateForm.deptID = editUser.deptID;
                    this.updateForm.deptName = editUser.deptName;
                    this.updateForm.userName = editUser.userName;
                    this.updateForm.id = editUser.id;
                    this.updateForm.userInfo.name = editUser.name;
                    this.updateForm.userInfo.salary = editUser.salary;

                    this.updateForm.userInfo.station = editUser.station;
                    this.updateForm.userInfo.telephone = editUser.telephone;
                    this.updateForm.userInfo.email = editUser.email;
                    this.updateForm.userInfo.orderNo = editUser.orderNo;
                    this.updateForm.userInfo.remark = editUser.remark;
                    this.updateForm.roleSet = editUser.roleSet;
//数字转字符
                    this.updateForm.state = editUser.state + "";
                    this.updateForm.userInfo.degree = editUser.degree + "";
                    this.updateForm.userInfo.sex = editUser.sex + "";
//日期转字符
                    this.updateForm.userInfo.createTime = formatDate(editUser.createTime);
                    this.updateForm.userInfo.birthday = formatDate(editUser.birthday);
                    // }
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
//显示新建用户对话框
            addUser() {
                this.addModal = true;
            },
//新建用户
            add() {
                this.$refs['addForm'].validate((valid) => {
                    if (valid) {
                        const user = this.addForm;
                        console.log(user);
                        fetch({
                            url: '/system/user',
                            method: 'post',
                            data: user
                        }).then((result) => {
                            this.gopage(this.pageNo);
                            this.$refs['addForm'].resetFields();
                            this.$Message.success('Success!');
                            this.addModal = false;
                        }).catch((resp) => {
                            this.$Message.error('新建用户失败!');
                        })
                    } else {
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
//显示修改用户对话框
            edit() {
                if (this.count != 1) {
                    this.updateModal = false;
                    this.$Message.warning('请至少并只能选择一项');
                } else {
                    this.updateModal = true;
                }
            },
//更新用户
            update() {
                this.$refs['updateForm'].validate((valid) => {
                    if (valid) {
                        fetch({
                            url: '/system/user',
                            method: 'put',
                            data: this.updateForm,
                            params: {
                                manageId: this.updateForm.userInfo.manager.id
                            }
                        }).then((result) => {
                            this.updateModal = false,
                                this.$Message.success('Success!');
                            this.gopage(this.pageNo);
                        });
                    } else {
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
//删除用户
            remove() {
                if (this.groupId != null && this.groupId != "") {
                    fetch({
                        url: '/system/user',
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
                const keyWord = this.keyWord;
                fetch({
                    url: '/system/user',
                    method: 'get',
                    params: {pageNo, pageSize, keyWord}
                }).then((result) => {
                    this.data1 = result.data.dataList;
                    this.pageNo = result.data.pageNo;
                    this.pageSize = result.data.pageSize;
                    this.totalCount = result.data.totalCount;
                });
            },
            cancel() {
                this.$Message.info('点击了取消');
            },
//显示修改角色对话框
            role() {
                if (this.count != 1) {
                    this.roleModal = false;
                    this.$Message.warning('请至少并只能选择一项');
                } else {
//获取到需要修改的用户信息
                    const editData = this.updateForm;
                    this.roleForm.id = editData.id;
                    this.roleForm.userName = editData.userName;

                    this.roleForm.roleIds = new Array();//clean 之前的数据

//将用户中的userSet 转成 roleIds，用于确定哪些角色已经被选中
                    for (let i = 0; i < editData.roleSet.length; i++) {
                        this.roleForm.roleIds[i] = editData.roleSet[i].id;
                    }
//显示角色对话框
                    this.roleModal = true;
                }

            },
//更新角色
            updateRole() {
                const roleForm = this.roleForm;
                fetch({
                    url: '/system/user/role',
                    method: 'put',
                    params: roleForm
                }).then((result) => {
                    this.$Message.success('Success!');
                    this.gopage(this.pageNo);
                });
            },
            submit: function (event) {
                //阻止元素发生默认的行为
                event.preventDefault();
                let formData = new FormData();
                formData.append("qiniuimg", this.file);
                let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }
                console.log("====="+formData);
                console.log("====="+this.file);
                //localhost
                //http://lijunqaq.top:8888
                fetch.post('http://localhost:8888/v1/img/qiniu', formData,config)
                    .then((response) =>{
                        this.addForm.picture=response.data;
                        this.$Message.success('Success!');
                        console.log(this.addForm.picture);

                    });
                // fetch({
                //     url: 'http://localhost:8888/img/qiniu',
                //     method: 'post',
                //     params: formData,
                //     config:config
                // }).then((result) => {
                //     this.picture=response.data();
                //     this.$Message.success('Success!');
                //     console.log(this.picture);
                //     this.gopage(this.pageNo);
                // });
            }

        },

        mounted: function () {
            this.access_token=Cookies.get('access_token');
            console.log("---------------------"+this.access_token+"---------------------")
            this.gopage(1);

//加载所有部门
            fetch({
                url: '/system/dept/all',
                method: 'get',

            }).then((result) => {
                this.deptList=result.data.data;
            });

//加载所有员工（直属领导）
            fetch({
                url:'/system/user/all',
                method: 'get'
            }).then((result) => {
                this.userList=result.data;
            });
//加载角色列表
            fetch({
                url: '/system/role/all',
                method: 'get'
            }).then((result) => {
                this.roleList = result.data;
            });
        }
    }

</script>
