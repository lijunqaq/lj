import axios from 'axios';
import Cookies from 'js-cookie';
import env from '../../build/env';

const ajaxUrl = env === 'development'
    ? 'http://localhost:8888/v1'
   // http://23h6852w34.51mypc.cn
    : env === 'production'
        ? 'http://lijunqaq.top:8888/v1'
        : 'https://debug.url.com/v1 ';
// 创建axios实例
const service = axios.create({
    baseURL: ajaxUrl, // api的base_url
    timeout: 10000 // 请求超时时间
});

// request拦截器
service.interceptors.request.use(config => {
    // Do something before request is sent
    let access_token = Cookies.get('access_token');//
    if (access_token) {
        var params = config.params;
        if (!params) {
            config.params =
                {
                    'access_token': access_token // 让每个请求携带参数access_token
                };
        } else {
            params.access_token = access_token;// 追加

        }
        console.log(config.url +":::"+ params)
    }
    return config;
}, error => {
    // Do something with request error
    console.log(error); // for debug
    Promise.reject(error);
});

// respone拦截器
service.interceptors.response.use(
    response => response,
    error => {
        console.log('err' + error);// for debug
        // // alert(error);
        // vue.$Message.error({
        //                 message: error.message,
        //                 duration: 5 * 1000,
        //                 closable: true
        //             });
        return Promise.reject(error);
    }
);

export default service;
