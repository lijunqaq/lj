import Cookies from 'js-cookie';
import fetch from '../../utils/fetch';
import fetch1 from '../../utils/fetch1';
const user = {
    state: {},
    mutations: {
        logout (state, vm) {
            fetch1({
                url:'login/logout',
                method:'post'
            }).then((resp)=>{
            })
            Cookies.remove('access_token');
            Cookies.remove('user');
            Cookies.remove('password');
            Cookies.remove('access');
            // 恢复默认样式
            let themeLink = document.querySelector('link[name="theme"]');
            themeLink.setAttribute('href', '');
            // 清空打开的页面等数据，但是保存主题数据
            let theme = '';
            if (localStorage.theme) {
                theme = localStorage.theme;
            }
            localStorage.clear();
            if (theme) {
                localStorage.theme = theme;
            }
        }
    }
};

export default user;
