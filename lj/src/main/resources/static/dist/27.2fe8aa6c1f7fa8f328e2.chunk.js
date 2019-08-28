webpackJsonp([27],{269:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a(770),o=a.n(n);for(var s in n)"default"!==s&&function(t){a.d(e,t,function(){return n[t]})}(s);var i=a(873),r=a.n(i),u=a(4),d=u(o.a,r.a,!1,null,null,null);d.options.__file="src/views/cargo/Finance.vue",e.default=d.exports},342:function(t,e,a){"use strict";function n(t){return new Date(t).Format("yyyy-MM-dd")}Object.defineProperty(e,"__esModule",{value:!0}),Date.prototype.Format=function(t){var e={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),S:this.getMilliseconds()};/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length)));for(var a in e)new RegExp("("+a+")").test(t)&&(t=t.replace(RegExp.$1,1==RegExp.$1.length?e[a]:("00"+e[a]).substr((""+e[a]).length)));return t},e.default=n},770:function(t,e,a){"use strict";function n(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var o=a(57),s=n(o),i=a(342),r=n(i);e.default={data:function(){return{loading:!0,count:0,gourpId:null,pageSize:20,pageNo:1,totalPage:0,totalCount:0,keyWord:"",columns1:[{type:"selection",width:60,align:"center"},{title:"制单日期",key:"inputDate",render:function(t,e){return t("div",(0,r.default)(this.row.inputDate))}},{title:"制单人",key:"inputBy"},{title:"状态",key:"state",render:function(t,e){var a=parseInt(e.row.state);return 0===a?t("div",[t("strong","草稿")]):1===a?t("div",[t("strong","已上报")]):t("div",[t("strong","unkown")])}}],self:this,data1:[],updateModal:!1,addModal:!1,updateForm:{id:"",inputDate:"",inputBy:"",state:""},formRule:{inputBy:[{required:!0,message:"制单人不能为空",trigger:"blur"}]}}},methods:{change:function(t){this.count=t.length,1==t.length&&(this.updateForm=t[0],this.updateForm.state=t[0].state+"",this.updateForm.inputDate=(0,r.default)(t[0].inputDate)),this.setGroupId(t)},setGroupId:function(t){this.groupId=[];for(var e=0;e<t.length;e++)this.groupId.push(t[e].id)},reset:function(t){this.$refs[t].resetFields()},addFinance:function(){this.addModal=!0},add:function(){var t=this;this.$refs.addForm.validate(function(e){if(e){var a=t.addForm;(0,s.default)({url:"/cargo/finance",method:"post",data:a}).then(function(e){t.gopage(t.pageNo),t.$refs.addForm.resetFields(),t.$Message.success("Success!"),t.addModal=!1})}else t.$Message.error("表单验证失败"),setTimeout(function(){t.loading=!1,t.$nextTick(function(){t.loading=!0})},1e3)})},edit:function(){void 0==this.groupId||1!=this.groupId.length?(this.updateModal=!1,this.$Message.warning("请至少并只能选择一项")):this.updateModal=!0},update:function(){var t=this;this.$refs.updateForm.validate(function(e){e?(0,s.default)({url:"/cargo/finance",method:"put",data:t.updateForm}).then(function(e){t.updateModal=!1,t.$Message.success("Success!"),t.gopage(t.pageNo)}):(t.$Message.error("表单验证失败"),setTimeout(function(){t.loading=!1,t.$nextTick(function(){t.loading=!0})},1e3))})},remove:function(){var t=this;null!=this.groupId&&""!=this.groupId?(0,s.default)({url:"/cargo/finance",method:"delete",data:this.groupId}).then(function(e){"1"==e.data&&(t.$Message.success("Success!"),t.gopage(t.pageNo))}):this.$Message.warning("请至少选择一项")},gopage:function(){var t=this,e=this.pageNo,a=this.pageSize,n=this.keyWord;(0,s.default)({url:"/cargo/finance",method:"get",params:{pageNo:e,pageSize:a,keyWord:n}}).then(function(n){t.data1=n.data.list,t.pageNo=e,t.pageSize=a,t.totalCount=n.data.totalCount})},cancel:function(){this.$Message.info("点击了取消")},submit:function(){var t=this;null!=this.groupId&&""!=this.groupId?(0,s.default)({url:"/cargo/finance/submit",method:"put",data:this.groupId}).then(function(e){"ok"==e.data&&(t.$Message.success("Success!"),t.gopage())}):this.$Message.warning("请至少选择一项")}},mounted:function(){this.gopage()}}},873:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"animated fadeIn"},[a("Row",[a("Col",{attrs:{span:"6"}},[t._v("\n            财务报运单："),a("Input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入..."},model:{value:t.keyWord,callback:function(e){t.keyWord=e},expression:"keyWord"}})],1),t._v(" "),a("Col",{attrs:{span:"8"}},[a("Button",{attrs:{type:"primary",shape:"circle",icon:"ios-search"},on:{click:function(e){t.gopage()}}},[t._v("搜索")])],1)],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Button",{attrs:{type:"success",icon:"wrench"},on:{click:function(e){t.edit()}}},[t._v("修改")]),t._v(" "),a("Button",{attrs:{type:"warning"},on:{click:function(e){t.submit()}}},[t._v("提交")])],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Table",{attrs:{border:"",columns:t.columns1,data:t.data1},on:{"on-selection-change":t.change}})],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Page",{attrs:{total:t.totalCount,"page-size":t.pageSize,current:t.pageNo,align:"center"},on:{"on-change":t.gopage}})],1),t._v(" "),a("br"),t._v(" "),a("Modal",{attrs:{title:"编辑财务报运单","mask-closable":!1,loading:t.loading,width:"60%"},on:{"on-ok":t.update,"on-cancel":t.cancel},model:{value:t.updateModal,callback:function(e){t.updateModal=e},expression:"updateModal"}},[a("Form",{ref:"updateForm",attrs:{model:t.updateForm,rules:t.formRule,"label-width":80}},[a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"制单人",prop:"inputBy"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.inputBy,callback:function(e){t.$set(t.updateForm,"inputBy",e)},expression:"updateForm.inputBy"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"制单日期",prop:"inputDate"}},[a("DatePicker",{staticStyle:{width:"200px"},attrs:{type:"date",placeholder:"Select date"},model:{value:t.updateForm.inputDate,callback:function(e){t.$set(t.updateForm,"inputDate",e)},expression:"updateForm.inputDate"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"状态",prop:"state"}},[a("RadioGroup",{attrs:{type:"button"},model:{value:t.updateForm.state,callback:function(e){t.$set(t.updateForm,"state",e)},expression:"updateForm.state"}},[a("Radio",{attrs:{label:"0"}},[t._v("草稿")]),t._v(" "),a("Radio",{attrs:{label:"1"}},[t._v("已上报")])],1)],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}})],1)],1)],1)],1)},o=[];n._withStripped=!0;var s={render:n,staticRenderFns:o};e.default=s}});