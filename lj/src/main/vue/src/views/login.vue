<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login" @keydown.enter="handleSubmit">
        <div class="login-con">
            <Card :bordered="false">
                <p slot="title">
                    <Icon type="log-in"></Icon>
                    欢迎登录
                </p>
                <div class="form-con">
                    <Form ref="loginForm" :model="form" :rules="rules">
                        <FormItem prop="userName">
                            <Input v-model="form.userName" placeholder="请输入用户名">
                                <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="form.password" placeholder="请输入密码">
                                <span slot="prepend">
                                    <Icon :size="14" type="locked"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button @click="handleSubmit" type="primary" long>登录</Button>
                        </FormItem>
                    </Form>
                    <p class="login-tip">输入任意用户名和密码即可</p>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>
import Cookies from 'js-cookie';
import fetch from '../utils/fetch1';
export default {
    data () {
        return {
            form: {
                userName: 'cgx',
                password: '111111'
            },
            rules: {
                userName: [
                    { required: true, message: '账号不能为空', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        handleSubmit () {
            this.$refs.loginForm.validate((valid) => {
                if (valid) {

                 fetch({
                        url:'/oauth/token',
                        //认证必须为post
                        method:'post',
                        //参数
                        params:{
                            username:this.form.userName,
                            password:this.form.password,
                            grant_type:'password',

                        },
                        //头消息
                        auth: {
                            username: 'jk_front',
                            password: '123456'
                        }
                    }).then((resp)=>{

                        this.$Message.success("登陆成功")
                        Cookies.set('access_token', resp.data.access_token);
                        Cookies.set('user', this.form.userName);
                        Cookies.set('password', this.form.password);
                        this.$store.commit('setAvator', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3448484253,3685836170&fm=27&gp=0.jpg');
                        if (this.form.userName === 'iview_admin') {
                            Cookies.set('access', 0);
                        } else {
                            Cookies.set('access', 1);
                        }
                        this.$router.push({
                            name: 'home_index'
                        });
                    }).catch(resp=>{
                        if(resp.response.data.error =='unauthorized'){
                            this.$Message.error("用户名错误")
                        }
                        else if(resp.response.data.error =='invalid_grant'){
                            this.$Message.error("用户名错误")
                        }

                    })





                }
            });
        }
    }
};
</script>

<style>

</style>
