<style lang="less">
    @import './Image.less';
</style>
<template>
    <div class="title">
    <a href="javascript:;" class="a-upload">
        <input type="file" name="" id="" @change="getFile($event)">点击这里上传文件
    </a>
      <div>
                <img :src="imageAddress"  style="margin-left: 17%;height: 65%;width: 65%" />
      </div>
    </div>

    <!--<div class="rz-picter">-->
        <!--<img :src="avatar" class="img-avatar">-->
        <!--<input type="file" name="avatar" id="uppic" accept="image/gif,image/jpeg,image/jpg,image/png"-->
               <!--@change="changeImage($event)"  class="ivu-btn">-->
    <!--</div>-->
    <!--<div>-->


    <!--</div>-->

</template>

<script type="text/ecmascript-6">
    import fetch from '../../utils/fetch';
    import formatDate from '../../utils/date';
    import Cookies from 'js-cookie';

    export default {

        data() {
            return {
                imageAddress:"https://lijunqaq.oss-cn-shenzhen.aliyuncs.com/wallhaven-6kpko7.jpg",
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
                        this.imageAddress=response.data;
                        this.$Message.success('Success!');
                        console.log(this.imageAddress);

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
        },
        mounted: function () {

        }
    }


</script>

<style scoped>

</style>