webpackJsonp([26],{268:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a(769),n=a.n(o);for(var r in o)"default"!==r&&function(t){a.d(e,t,function(){return o[t]})}(r);var s=a(872),i=a.n(s),d=a(4),l=d(n.a,i.a,!1,null,null,null);l.options.__file="src/views/cargo/Invoice.vue",e.default=l.exports},342:function(t,e,a){"use strict";function o(t){return new Date(t).Format("yyyy-MM-dd")}Object.defineProperty(e,"__esModule",{value:!0}),Date.prototype.Format=function(t){var e={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),S:this.getMilliseconds()};/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length)));for(var a in e)new RegExp("("+a+")").test(t)&&(t=t.replace(RegExp.$1,1==RegExp.$1.length?e[a]:("00"+e[a]).substr((""+e[a]).length)));return t},e.default=o},769:function(t,e,a){"use strict";function o(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var n=a(57),r=o(n),s=a(342);o(s);e.default={data:function(){return{loading:!0,count:0,gourpId:null,pageSize:20,pageNo:1,totalPage:0,totalCount:0,keyWord:"",columns1:[{type:"selection",width:60,align:"center"},{title:"报运合同号",key:"scNo"},{title:"提货单号",key:"blNo"},{title:"贸易条款",key:"tradeTerms"},{title:"状态",key:"state",render:function(t,e){var a=parseInt(e.row.state);return 0===a?t("div",[t("strong","草稿")]):1===a?t("div",[t("strong","已开财务单")]):t("div",[t("strong","unkown")])}}],self:this,data1:[],updateModal:!1,addFinanceModal:!1,updateForm:{id:"",scNo:"",blNo:"",tradeTerms:"",state:""},addFinanceForm:{inputDate:"",inputBy:"",state:"0"},formRule:{invoiceId:[{required:!0,message:"PACKING_LIST_ID值不能为空",trigger:"blur"}],scNo:[{required:!0,message:"packing.getExportNos S/C就是报运的合同号不能为空",trigger:"blur"}],blNo:[{required:!0,message:"不能为空",trigger:"blur"}],tradeTerms:[{required:!0,message:"不能为空",trigger:"blur"}],state:[{required:!0,message:"0草稿 1已上报不能为空",trigger:"blur"}]},states:""}},methods:{change:function(t){this.count=t.length,1==t.length&&(this.updateForm=t[0],this.updateForm.state=t[0].state+""),this.setGroupId(t)},setGroupId:function(t){this.groupId=[],this.states="";for(var e=0;e<t.length;e++)this.groupId.push(t[e].id),this.states+=t[e].state},reset:function(t){this.$refs[t].resetFields()},addInvoice:function(){this.addFinanceModal=!0},edit:function(){1!=this.count?(this.updateModal=!1,this.$Message.warning("请至少并只能选择一项")):this.updateModal=!0},update:function(){var t=this;this.$refs.updateForm.validate(function(e){e?(0,r.default)({url:"/cargo/invoice",method:"put",data:t.updateForm}).then(function(e){t.updateModal=!1,t.$Message.success("Success!"),t.gopage(t.pageNo)}):(t.$Message.error("表单验证失败"),setTimeout(function(){t.loading=!1,t.$nextTick(function(){t.loading=!0})},1e3))})},remove:function(){var t=this;null!=this.groupId&&""!=this.groupId?(0,r.default)({url:"/cargo/invoice",method:"delete",data:this.groupId}).then(function(e){"1"==e.data&&(t.$Message.success("Success!"),t.gopage(t.pageNo))}):this.$Message.warning("请至少选择一项")},gopage:function(){var t=this,e=this.pageNo,a=this.pageSize,o=this.keyWord;(0,r.default)({url:"/cargo/invoice",method:"get",params:{pageNo:e,pageSize:a,keyWord:o}}).then(function(o){t.data1=o.data.list,t.pageNo=e,t.pageSize=a,t.totalCount=o.data.totalCount})},cancel:function(){this.$Message.info("点击了取消")},finance:function(){-1!=this.states.indexOf("1")?this.$Message.warning("只能操作草稿状态的发票"):null!=this.groupId&&""!=this.groupId&&1==this.groupId.length?this.addFinanceModal=!0:this.$Message.warning("请至少并只能选择一项开始生成财务报运单")},addFinance:function(){var t=this;this.$refs.addFinanceForm.validate(function(e){if(e){var a=t.addFinanceForm;(0,r.default)({url:"/cargo/finance",method:"post",data:a,params:{invoiceIds:t.groupId}}).then(function(e){(0,r.default)({url:"/cargo/invoice/updateState",method:"put",params:{invoiceIds:t.groupId}}).then(function(e){t.gopage(t.pageNo),t.$refs.addFinanceForm.resetFields(),t.$Message.success("Success!"),t.addFinanceModal=!1})})}else t.$Message.error("表单验证失败"),setTimeout(function(){t.loading=!1,t.$nextTick(function(){t.loading=!0})},1e3)})}},mounted:function(){this.gopage()}}},872:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"animated fadeIn"},[a("Row",[a("Col",{attrs:{span:"6"}},[t._v("\n            委托单："),a("Input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入..."},model:{value:t.keyWord,callback:function(e){t.keyWord=e},expression:"keyWord"}})],1),t._v(" "),a("Col",{attrs:{span:"8"}},[a("Button",{attrs:{type:"primary",shape:"circle",icon:"ios-search"},on:{click:function(e){t.gopage()}}},[t._v("搜索")])],1)],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Button",{attrs:{type:"success",icon:"wrench"},on:{click:function(e){t.edit()}}},[t._v("修改")]),t._v(" "),a("Button",{attrs:{type:"error",icon:"trash-a"},on:{click:function(e){t.remove()}}},[t._v("删除")]),t._v(" "),a("Button",{attrs:{type:"primary",icon:"plus-round"},on:{click:function(e){t.finance()}}},[t._v("新建财务报运单")])],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Table",{attrs:{border:"",columns:t.columns1,data:t.data1},on:{"on-selection-change":t.change}})],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Page",{attrs:{total:t.totalCount,"page-size":t.pageSize,current:t.pageNo,align:"center"},on:{"on-change":t.gopage}})],1),t._v(" "),a("br"),t._v(" "),a("Modal",{attrs:{title:"编辑委托单","mask-closable":!1,loading:t.loading,width:"60%"},on:{"on-ok":t.update,"on-cancel":t.cancel},model:{value:t.updateModal,callback:function(e){t.updateModal=e},expression:"updateModal"}},[a("Form",{ref:"updateForm",attrs:{model:t.updateForm,rules:t.formRule,"label-width":80}},[a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"状态",prop:"state"}},[a("RadioGroup",{attrs:{type:"button"},model:{value:t.updateForm.state,callback:function(e){t.$set(t.updateForm,"state",e)},expression:"updateForm.state"}},[a("Radio",{attrs:{label:"0"}},[t._v("草稿")]),t._v(" "),a("Radio",{attrs:{label:"1"}},[t._v("已上报")])],1)],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"报运合同号",prop:"scNo"}},[a("Input",{attrs:{type:"text",readonly:"readonly"},model:{value:t.updateForm.scNo,callback:function(e){t.$set(t.updateForm,"scNo",e)},expression:"updateForm.scNo"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"提货单号",prop:"blNo"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.blNo,callback:function(e){t.$set(t.updateForm,"blNo",e)},expression:"updateForm.blNo"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"贸易条款",prop:"tradeTerms"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.tradeTerms,callback:function(e){t.$set(t.updateForm,"tradeTerms",e)},expression:"updateForm.tradeTerms"}})],1)],1)],1)],1)],1),t._v(" "),a("Modal",{attrs:{title:"添加财务报运单","mask-closable":!1,loading:t.loading,width:"60%"},on:{"on-ok":t.addFinance,"on-cancel":t.cancel},model:{value:t.addFinanceModal,callback:function(e){t.addFinanceModal=e},expression:"addFinanceModal"}},[a("Form",{ref:"addFinanceForm",attrs:{model:t.addFinanceForm,rules:t.formRule,"label-width":80}},[a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"制单日期",prop:"inputDate"}},[a("DatePicker",{staticStyle:{width:"200px"},attrs:{type:"date",placeholder:"Select date"},model:{value:t.addFinanceForm.inputDate,callback:function(e){t.$set(t.addFinanceForm,"inputDate",e)},expression:"addFinanceForm.inputDate"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"制单人",prop:"inputBy"}},[a("Input",{attrs:{type:"text"},model:{value:t.addFinanceForm.inputBy,callback:function(e){t.$set(t.addFinanceForm,"inputBy",e)},expression:"addFinanceForm.inputBy"}})],1)],1)],1)],1)],1)],1)},n=[];o._withStripped=!0;var r={render:o,staticRenderFns:n};e.default=r}});