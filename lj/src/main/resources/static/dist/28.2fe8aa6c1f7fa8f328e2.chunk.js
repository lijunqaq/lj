webpackJsonp([28],{256:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a(757),n=a.n(r);for(var o in r)"default"!==o&&function(e){a.d(t,e,function(){return r[e]})}(o);var i=a(843),s=a.n(i),l=a(4),u=l(n.a,s.a,!1,null,null,null);u.options.__file="src/views/system/System.vue",t.default=u.exports},757:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a(57),n=function(e){return e&&e.__esModule?e:{default:e}}(r);t.default={data:function(){return{columns4:[{type:"selection",width:60,align:"center"},{title:"用户名",key:"userName"},{title:"计算机域名",key:"userDomain"},{title:"本地ip地址",key:"ip"},{title:"本地主机名",key:"hostName"},{title:"JVM可以使用的总内存",key:"totalMemory"},{title:"JVM可以使用的处理器个数",key:"availableProcessors"},{title:"操作系统的版本",key:"propertyVersion"},{title:"操作系统的名称",key:"propertyName"}],data1:[],totalCount:0,pageNo:1,keyWord:"",pageSize:9,firstRules:{deptName:[{required:!0,message:"部门名字不能为空",trigger:"blur"}]},deptList:null,loading:!0,groupId:[]}},methods:{gopage:function(e){var t=this;(0,n.default)({url:"system/sys/info",method:"get",params:{}}).then(function(e){t.data1=e.data})}},created:function(){var e=this;(0,n.default)({url:"system/sys/info",method:"get"}).then(function(t){e.data1=t.data,console.log(t.data)})}}},843:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"login"},[a("br"),e._v(" "),a("br"),e._v(" "),a("Row",[a("Table",{ref:"selection",attrs:{border:"",stripe:"stripe",columns:e.columns4,data:e.data1}})],1),e._v(" "),a("br"),e._v(" "),a("br"),e._v(" "),a("Row",[a("Page",{attrs:{total:e.totalCount,"page-size":e.pageSize,current:e.pageNo,align:"center"},on:{"on-change":e.gopage}})],1)],1)},n=[];r._withStripped=!0;var o={render:r,staticRenderFns:n};t.default=o}});