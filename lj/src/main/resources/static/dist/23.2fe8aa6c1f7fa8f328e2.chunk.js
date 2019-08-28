webpackJsonp([23],{267:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=a(768),o=a.n(r);for(var s in r)"default"!==s&&function(t){a.d(e,t,function(){return r[t]})}(s);var i=a(871),n=a.n(i),l=a(4),p=l(o.a,n.a,!1,null,null,null);p.options.__file="src/views/cargo/ShippingOrder.vue",e.default=p.exports},342:function(t,e,a){"use strict";function r(t){return new Date(t).Format("yyyy-MM-dd")}Object.defineProperty(e,"__esModule",{value:!0}),Date.prototype.Format=function(t){var e={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),S:this.getMilliseconds()};/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length)));for(var a in e)new RegExp("("+a+")").test(t)&&(t=t.replace(RegExp.$1,1==RegExp.$1.length?e[a]:("00"+e[a]).substr((""+e[a]).length)));return t},e.default=r},768:function(t,e,a){"use strict";function r(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var o=a(57),s=r(o),i=a(342),n=r(i);e.default={data:function(){return{loading:!0,count:0,gourpId:null,pageSize:20,pageNo:1,totalPage:0,totalCount:0,keyWord:"",columns1:[{type:"selection",width:60,align:"center"},{title:"海运/空运",key:"orderType"},{title:"货主",key:"shipper"},{title:"提单抬头",key:"consignee"},{title:"正本通知人",key:"notifyParty"},{title:"信用证",key:"lcNo"},{title:"装运港",key:"portOfLoading"},{title:"转船港",key:"portOfTrans"},{title:"卸货港",key:"portOfDischarge"},{title:"装期",key:"loadingDate",render:function(t,e){return t("div",(0,n.default)(this.row.loadingDate))}},{title:"效期",key:"limitDate",render:function(t,e){return t("div",(0,n.default)(this.row.limitDate))}},{title:"是否分批",key:"isBatch"},{title:"是否转船",key:"isTrans"},{title:"份数",key:"copyNum"},{title:"扼要说明",key:"remark"},{title:"运输要求",key:"specialCondition"},{title:"运费说明",key:"freight"},{title:"复核人",key:"checkBy"},{title:"状态",key:"state",render:function(t,e){var a=parseInt(e.row.state);return 0===a?t("div",[t("strong","草稿")]):1===a?t("div",[t("strong","已开发票")]):t("div",[t("strong","unkown")])}}],self:this,data1:[],updateModal:!1,addInvoiceModal:!1,updateForm:{id:"",orderType:"",shipper:"",consignee:"",notifyParty:"",lcNo:"",portOfLoading:"",portOfTrans:"",portOfDischarge:"",loadingDate:"",limitDate:"",isBatch:"",isTrans:"",copyNum:"",remark:"",specialCondition:"",freight:"",checkBy:"",state:""},addInvoiceForm:{scNo:"",blNo:"",tradeTerms:"fob",state:"0"},formRule:{orderType:[{required:!0,message:"海运/空运不能为空",trigger:"blur"}],shipper:[{required:!0,message:"货主不能为空",trigger:"blur"}],consignee:[{required:!0,message:"提单抬头不能为空",trigger:"blur"}],notifyParty:[{required:!0,message:"正本通知人不能为空",trigger:"blur"}],lcNo:[{required:!0,message:"信用证不能为空",trigger:"blur"}],portOfLoading:[{required:!0,message:"装运港不能为空",trigger:"blur"}],portOfTrans:[{required:!0,message:"转船港不能为空",trigger:"blur"}],portOfDischarge:[{required:!0,message:"卸货港不能为空",trigger:"blur"}],loadingDate:[{required:!0,message:"装期不能为空",trigger:"blur"}],limitDate:[{required:!0,message:"效期不能为空",trigger:"blur"}],isBatch:[{required:!0,message:"是否分批不能为空",trigger:"blur"}],isTrans:[{required:!0,message:"是否转船不能为空",trigger:"blur"}],copyNum:[{required:!0,message:"份数不能为空",trigger:"blur"}],remark:[{required:!0,message:"扼要说明不能为空",trigger:"blur"}],specialCondition:[{required:!0,message:"运输要求不能为空",trigger:"blur"}],freight:[{required:!0,message:"运费说明不能为空",trigger:"blur"}],checkBy:[{required:!0,message:"复核人不能为空",trigger:"blur"}],state:[{required:!0,message:"状态不能为空",trigger:"blur"}]},states:""}},methods:{change:function(t){this.count=t.length,1==t.length&&(this.updateForm=t[0],this.updateForm.state=t[0].state+""),this.setGroupId(t)},setGroupId:function(t){this.groupId=[],this.states="";for(var e=0;e<t.length;e++)this.groupId.push(t[e].id),this.states+=t[e].state},reset:function(t){this.$refs[t].resetFields()},edit:function(){1!=this.count?(this.updateModal=!1,this.$Message.warning("请至少并只能选择一项")):this.updateModal=!0},update:function(){var t=this;this.$refs.updateForm.validate(function(e){e?(0,s.default)({url:"/cargo/shippingOrder",method:"put",data:t.updateForm}).then(function(e){t.updateModal=!1,t.$Message.success("Success!"),t.gopage(t.pageNo)}):(t.$Message.error("表单验证失败"),setTimeout(function(){t.loading=!1,t.$nextTick(function(){t.loading=!0})},1e3))})},remove:function(){var t=this;null!=this.groupId&&""!=this.groupId?(0,s.default)({url:"/cargo/shippingOrder",method:"delete",data:this.groupId}).then(function(e){"ok"==e.data&&(t.$Message.success("Success!"),t.gopage(t.pageNo))}):this.$Message.warning("请至少选择一项")},gopage:function(){var t=this,e=this.pageNo,a=this.pageSize,r=this.keyWord;(0,s.default)({url:"/cargo/shippingOrder",method:"get",params:{pageNo:e,pageSize:a,keyWord:r}}).then(function(r){t.data1=r.data.list,t.pageNo=e,t.pageSize=a,t.totalCount=r.data.totalCount})},cancel:function(){this.$Message.info("点击了取消")},invoice:function(){-1!=this.states.indexOf("1")?this.$Message.warning("只能操作草稿状态的委托单"):null!=this.groupId&&""!=this.groupId&&1==this.groupId.length?this.addInvoiceModal=!0:this.$Message.warning("请至少并只能选择一项开始发票")},addInvoice:function(){var t=this;this.$refs.addInvoiceForm.validate(function(e){if(e){var a=t.addInvoiceForm;(0,s.default)({url:"/cargo/invoice",method:"post",data:a,params:{shippingOrderIds:t.groupId}}).then(function(e){(0,s.default)({url:"/cargo/shippingOrder/updateState",method:"put",params:{shippingOrderIds:t.groupId}}).then(function(e){(0,s.default)({url:"/cargo/invoice/setTime",method:"put",params:{shippingOrderIds:t.groupId}}).then(function(e){t.gopage(t.pageNo),t.$refs.addInvoiceForm.resetFields(),t.$Message.success("Success!"),t.addInvoiceModal=!1})})})}else t.$Message.error("表单验证失败"),setTimeout(function(){t.loading=!1,t.$nextTick(function(){t.loading=!0})},1e3)})}},mounted:function(){this.gopage()}}},871:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"animated fadeIn"},[a("Row",[a("Col",{attrs:{span:"6"}},[t._v("\n            委托单："),a("Input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入..."},model:{value:t.keyWord,callback:function(e){t.keyWord=e},expression:"keyWord"}})],1),t._v(" "),a("Col",{attrs:{span:"8"}},[a("Button",{attrs:{type:"primary",shape:"circle",icon:"ios-search"},on:{click:function(e){t.gopage()}}},[t._v("搜索")])],1)],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Button",{attrs:{type:"success",icon:"wrench"},on:{click:function(e){t.edit()}}},[t._v("修改")]),t._v(" "),a("Button",{attrs:{type:"error",icon:"trash-a"},on:{click:function(e){t.remove()}}},[t._v("删除")]),t._v(" "),a("Button",{attrs:{type:"primary",icon:"plus-round"},on:{click:function(e){t.invoice()}}},[t._v("新增发票")])],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Table",{attrs:{border:"",columns:t.columns1,data:t.data1},on:{"on-selection-change":t.change}})],1),t._v(" "),a("br"),t._v(" "),a("Row",[a("Page",{attrs:{total:t.totalCount,"page-size":t.pageSize,current:t.pageNo,align:"center"},on:{"on-change":t.gopage}})],1),t._v(" "),a("br"),t._v(" "),a("Modal",{attrs:{title:"编辑委托单","mask-closable":!1,loading:t.loading,width:"60%"},on:{"on-ok":t.update,"on-cancel":t.cancel},model:{value:t.updateModal,callback:function(e){t.updateModal=e},expression:"updateModal"}},[a("Form",{ref:"updateForm",attrs:{model:t.updateForm,rules:t.formRule,"label-width":80}},[a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"货主",prop:"shipper"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.shipper,callback:function(e){t.$set(t.updateForm,"shipper",e)},expression:"updateForm.shipper"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"提单抬头",prop:"consignee"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.consignee,callback:function(e){t.$set(t.updateForm,"consignee",e)},expression:"updateForm.consignee"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"正本通知人",prop:"notifyParty"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.notifyParty,callback:function(e){t.$set(t.updateForm,"notifyParty",e)},expression:"updateForm.notifyParty"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"信用证",prop:"lcNo"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.lcNo,callback:function(e){t.$set(t.updateForm,"lcNo",e)},expression:"updateForm.lcNo"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"装运港",prop:"portOfLoading"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.portOfLoading,callback:function(e){t.$set(t.updateForm,"portOfLoading",e)},expression:"updateForm.portOfLoading"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"转船港",prop:"portOfTrans"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.portOfTrans,callback:function(e){t.$set(t.updateForm,"portOfTrans",e)},expression:"updateForm.portOfTrans"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"卸货港",prop:"portOfDischarge"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.portOfDischarge,callback:function(e){t.$set(t.updateForm,"portOfDischarge",e)},expression:"updateForm.portOfDischarge"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"装期"}},[a("DatePicker",{staticStyle:{width:"200px"},attrs:{type:"date",placeholder:"Select date"},model:{value:t.updateForm.loadingDate,callback:function(e){t.$set(t.updateForm,"loadingDate",e)},expression:"updateForm.loadingDate"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"效期"}},[a("DatePicker",{staticStyle:{width:"200px"},attrs:{type:"date",placeholder:"Select date"},model:{value:t.updateForm.limitDate,callback:function(e){t.$set(t.updateForm,"limitDate",e)},expression:"updateForm.limitDate"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"是否分批",prop:"isBatch"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.isBatch,callback:function(e){t.$set(t.updateForm,"isBatch",e)},expression:"updateForm.isBatch"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"是否转船",prop:"isTrans"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.isTrans,callback:function(e){t.$set(t.updateForm,"isTrans",e)},expression:"updateForm.isTrans"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"份数",prop:"copyNum"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.copyNum,callback:function(e){t.$set(t.updateForm,"copyNum",e)},expression:"updateForm.copyNum"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"扼要说明",prop:"remark"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.remark,callback:function(e){t.$set(t.updateForm,"remark",e)},expression:"updateForm.remark"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"运输要求",prop:"specialCondition"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.specialCondition,callback:function(e){t.$set(t.updateForm,"specialCondition",e)},expression:"updateForm.specialCondition"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"运费说明",prop:"freight"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.freight,callback:function(e){t.$set(t.updateForm,"freight",e)},expression:"updateForm.freight"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"复核人",prop:"checkBy"}},[a("Input",{attrs:{type:"text"},model:{value:t.updateForm.checkBy,callback:function(e){t.$set(t.updateForm,"checkBy",e)},expression:"updateForm.checkBy"}})],1)],1)],1),t._v(" "),a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"状态",prop:"state"}},[a("RadioGroup",{attrs:{type:"button"},model:{value:t.updateForm.state,callback:function(e){t.$set(t.updateForm,"state",e)},expression:"updateForm.state"}},[a("Radio",{attrs:{label:"0"}},[t._v("草稿")]),t._v(" "),a("Radio",{attrs:{label:"1"}},[t._v("已上报")])],1)],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"海运/空运",prop:"orderType"}},[a("RadioGroup",{attrs:{type:"button"},model:{value:t.updateForm.orderType,callback:function(e){t.$set(t.updateForm,"orderType",e)},expression:"updateForm.orderType"}},[a("Radio",{attrs:{label:"sea"}},[t._v("海运")]),t._v(" "),a("Radio",{attrs:{label:"air"}},[t._v("空运")])],1)],1)],1)],1)],1)],1),t._v(" "),a("Modal",{attrs:{title:"添加发票","mask-closable":!1,loading:t.loading,width:"60%"},on:{"on-ok":t.addInvoice,"on-cancel":t.cancel},model:{value:t.addInvoiceModal,callback:function(e){t.addInvoiceModal=e},expression:"addInvoiceModal"}},[a("Form",{ref:"addInvoiceForm",attrs:{model:t.addInvoiceForm,rules:t.formRule,"label-width":80}},[a("Row",[a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"提货单",prop:"blNo"}},[a("Input",{attrs:{type:"text"},model:{value:t.addInvoiceForm.blNo,callback:function(e){t.$set(t.addInvoiceForm,"blNo",e)},expression:"addInvoiceForm.blNo"}})],1)],1),t._v(" "),a("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}}),t._v(" "),a("Col",{attrs:{span:"11"}},[a("FormItem",{attrs:{label:"贸易条款",prop:"tradeTerms"}},[a("Input",{attrs:{type:"text"},model:{value:t.addInvoiceForm.tradeTerms,callback:function(e){t.$set(t.addInvoiceForm,"tradeTerms",e)},expression:"addInvoiceForm.tradeTerms"}})],1)],1)],1)],1)],1)],1)},o=[];r._withStripped=!0;var s={render:r,staticRenderFns:o};e.default=s}});