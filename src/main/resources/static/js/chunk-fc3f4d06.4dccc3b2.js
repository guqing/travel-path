(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-fc3f4d06","chunk-673a55d2"],{"15c0":function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAPCAYAAADzun+cAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAG7AAABuwBHnU4NQAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAGvSURBVDiNxZVPSBRxFMc/b9shEE+WzbC7ddjWEQy75aJ19mAYXurSJS96CLsoQop1EcKLZw8tnbp0koIgEBIPexNkReuwBLvTuGCXwsD8+zr0O0wzs2vIxv5gDu/33vd93rzfb96IqtKKlWgJtVlgEZkTkTUReSYit0XEOlPTjFaLyDpgGzMN/ATWgBXgg6puhzV131hEbopI5h/Zn4DPqpoBeoCnwBHwHNgUkYGIQlUjD9AHqKl8BrgYFxeInwFqMfsXgBLwMuKrk2gVKALzwC+gDAw3AI+YQjtifFPA93DxcUmGTJI7xs4Cy2bvPeDGaNygJuRLOQmOn7Qxq5CIBZszLwHvYhIMmrM8ABaA9lBLD4DxsM5P0f0lbR3v9Drq2RQi4FyFG9ktKtc+op0vuF+npRYwCfwAfOBhoNWnwIOwpuowWut3dW9p8dCzqUTBHoVHu4MnY9/uneQ8Xp9xmWzglYEVgb24C6SqfE1zybPZ8Gz2qw6PI+DrHhO3/Mua969ol8d0I3CggDx/vtc3gNUwHpJB+68B4vpyV09Jlq/yVvm/Q7wpk+s8q2U/id8yDtr6k3+TGQAAAABJRU5ErkJggg=="},"182f":function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("a-list",{attrs:{"item-layout":"horizontal","data-source":t.checkpoints},scopedSlots:t._u([{key:"renderItem",fn:function(e){return a("a-list-item",{},[a("a-card",{staticStyle:{width:"100%"},attrs:{hoverable:""},on:{click:function(n){return t.handleSelect(e)}}},[a("a-list-item-meta",{attrs:{description:e.description}},[a("a",{attrs:{slot:"title",href:"javascript:void(0)"},slot:"title"},[t._v(t._s(e.name))]),a("div",{staticClass:"editor-listLayer-media u-rSpace--m js-thumbnail",style:{background:e.avatarColor,color:"#fff"},attrs:{slot:"avatar"},slot:"avatar"},[a("p",{staticClass:"CDB-text CDB-size-large is-semibold u-upperCase"},[t._v(" "+t._s(e.avatarText)+" "),a("img",{attrs:{src:n("6169")}})])])])],1)],1)}}])}),a("a-pagination",{attrs:{size:"small",current:t.pagination.current,pageSize:t.pagination.pageSize,total:t.pagination.total,hideOnSinglePage:""},on:{change:t.onPageChange}})],1)},o=[],r=(n("b0c0"),n("4e02")),i=["#f56a00","#7265e6","#ffbf00","#00a2ae"],s={name:"DesignList",data:function(){return{checkpoints:[],pagination:{current:1,pageSize:10,total:0}}},created:function(){this.loadData()},methods:{loadData:function(){var t=this,e={current:this.pagination.current,pageSize:this.pagination.pageSize};r["a"].list(e).then((function(e){var n=e.data,a=n.list,o=n.total;for(var r in a){var s=a[r];s.avatarText=s.name.substring(0,1),s.avatarColor=i[r%a.length]}t.checkpoints=a,t.pagination.total=o}))},onPageChange:function(){this.loadData()},handleSelect:function(t){this.$log.debug("选择方案",t),this.$emit("select",t)},handleView:function(t){this.$emit("view",t)}}},l=s,c=(n("c9b6"),n("2877")),u=Object(c["a"])(l,a,o,!1,null,"d5b9e518",null);e["default"]=u.exports},"260d":function(t,e,n){t.exports=n.p+"assets/end_point.45451854.svg"},"2e27":function(t,e,n){"use strict";n.d(e,"e",(function(){return r})),n.d(e,"d",(function(){return i})),n.d(e,"c",(function(){return s})),n.d(e,"a",(function(){return l})),n.d(e,"b",(function(){return c}));var a=n("e11e"),o=a["Icon"].extend({options:{iconUrl:n("d5bb"),shadowUrl:n("e2b9"),shadowAnchor:[14,41],iconSize:[25,39],iconAnchor:[13,38]}}),r=new o({iconUrl:n("c88e")}),i=new o({iconUrl:n("6a91")}),s=new o({iconUrl:n("260d")}),l=(new o({iconUrl:n("742d")}),new o({iconUrl:n("9e10")})),c=new o({iconUrl:n("d5bb")})},"38f8":function(t,e,n){"use strict";var a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"map-wrapper"},[n("div",{style:{cursor:t.cursor},attrs:{id:"mapContainer"}})])},o=[],r=(n("d81d"),n("6cc5"),n("e11e")),i=(n("ca06"),n("53b7"),"https://api.mapbox.com/styles/v1/guqing/ckis880630pby19ohlzsqtuki/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoiZ3VxaW5nIiwiYSI6ImNqdmtmNGh4bjBxdmg0OXFyMDI0dHc3emQifQ.AAGR_XTCwYALRFQmtAwmHA"),s={name:"LeafletMap",props:{cursor:{type:String,required:!1,default:null},bounds:{type:Array,required:!1,default:function(){return[[32.948,107.708],[37.139,113.161]]}}},data:function(){return{map:{}}},mounted:function(){this.init()},methods:{init:function(){var t=r["latLng"](this.bounds[0][0],this.bounds[0][1]),e=r["latLng"](this.bounds[1][0],this.bounds[1][1]),n=r["latLngBounds"](t,e);this.map=new r["Map"]("mapContainer",{maxBounds:n,center:[34.26099394982405,108.94237697124483],zoom:14,zoomControl:!0,loadingControl:!0}),this.L.tileLayer(i,{foo:"bar",attribution:'Map data &copy; <a href="https://github.com/guqing">guqing</a>',minZoom:3}).addTo(this.map),r["rectangle"](n,{width:2,color:"#000",opacity:.3,fill:!1}).addTo(this.map),this.$emit("onMapInit",this.map)}}},l=s,c=(n("be76"),n("2877")),u=Object(c["a"])(l,a,o,!1,null,"af30a2f4",null);e["a"]=u.exports},"4e02":function(t,e,n){"use strict";var a=n("b775"),o={list:function(t){return Object(a["b"])({url:"/deploy/list",method:"get",params:t})},getById:function(t){return Object(a["b"])({url:"/deploy/".concat(t),method:"get"})},create:function(t){return Object(a["b"])({url:"/deploy",method:"post",data:t})},updateById:function(t,e){return Object(a["b"])({url:"/deploy/".concat(t),method:"put",data:e})}};e["a"]=o},"53b7":function(t,e,n){},"584c":function(t,e,n){"use strict";n.d(e,"a",(function(){return o})),n.d(e,"b",(function(){return r}));var a=n("e11e");a["NumberedDivIcon"]=a["Icon"].extend({options:{iconUrl:n("fb72"),number:"",shadowUrl:null,iconSize:new a["Point"](25,41),iconAnchor:new a["Point"](12,40),popupAnchor:new a["Point"](0,-33),shadowSize:new a["Point"](50,-64),shadowAnchor:new a["Point"](4,-62),className:"leaflet-div-icon"},createIcon:function(){var t=document.createElement("div"),e=this._createImg(this.options["iconUrl"]),n=document.createElement("div");return n.setAttribute("class","number"),n.innerHTML=this.options["number"]||"",t.appendChild(e),t.appendChild(n),this._setIconStyles(t,"icon"),t},createShadow:function(){return null}});n("e70a");function o(t,e){return new a["Marker"](t,{icon:new a["NumberedDivIcon"]({number:e})})}var r=function(t){return new a["NumberedDivIcon"]({number:t})}},6169:function(t,e,n){t.exports=n.p+"assets/route.f7c72195.svg"},6558:function(t,e,n){},"6a91":function(t,e,n){t.exports=n.p+"assets/start_point.9c72ff17.svg"},"742d":function(t,e,n){t.exports=n.p+"assets/blue.1805d0e3.svg"},"80d0":function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container"},[a("a-row",{staticStyle:{height:"100%"},attrs:{gutter:[16,16],type:"flex"}},[a("a-col",{attrs:{xs:24,sm:24,md:6,lg:6,order:0}},[a("div",{staticClass:"editor-wrapper"},[a("div",{staticClass:"editor-content"},[a("a-steps",{attrs:{current:t.stepCurrent,size:"small"}},[a("a-step",{attrs:{title:"选择方案"}}),a("a-step",{attrs:{title:"点选卡口序列"}}),a("a-step",{attrs:{title:"还原轨迹"}})],1),a("div",{staticClass:"step-content"},[0===t.stepCurrent?a("DesignList",{on:{select:t.handlePlanSelect}}):t._e(),1===t.stepCurrent?a("a-timeline",{staticStyle:{"margin-top":"28px"}},[t._l(t.checkpoints,(function(e,n){return a("a-timeline-item",{key:n,attrs:{color:t.color(n)}},[a("span",{staticStyle:{"margin-right":"8px"}},[t._v("序号:"+t._s(n+1))]),a("a-tag",[t._v(t._s(e.lat)+","+t._s(e.lng))])],1)})),0===t.checkpoints.length?a("a-empty",{attrs:{image:t.simpleImage}},[a("span",{attrs:{slot:"description"},slot:"description"},[t._v("请点选车辆卡口序列,至少两个")])]):t._e()],2):t._e(),2===t.stepCurrent?a("div",[a("a-alert",{staticStyle:{"margin-bottom":"8px"},attrs:{message:t.tipsMessage,type:t.tipsType,"show-icon":""}}),a("a-table",{attrs:{columns:t.tracks.columns,pagination:{pageSize:8},rowKey:"id",loading:t.loading.table,currentRow:t.tracks.selectedRow,customRow:t.tracksTableRowClick,"data-source":t.tracks.data,scroll:{x:!0}},scopedSlots:t._u([{key:"serial",fn:function(e,n,o){return a("span",{},[t._v(" "+t._s(o+1)+" ")])}},{key:"action",fn:function(e,n){return[a("a",{attrs:{href:"javascript:void(0)"},on:{click:function(e){return t.handleOnSavePath(n)}}},[t._v(" 转存 ")])]}}],null,!1,3450450121)},[a("span",{attrs:{slot:"icon"},slot:"icon"},[a("img",{attrs:{src:n("15c0"),alt:"轨迹"}})])])],1):t._e()],1),a("div",{staticClass:"editor-footer"},[t.stepCurrent>0?a("a-button",{on:{click:t.handlePrev}},[t._v(" 上一步 ")]):t._e(),t.showNext?a("a-button",{staticStyle:{"margin-left":"8px"},attrs:{type:"primary"},on:{click:t.handleOnNext}},[t._v(" 下一步 ")]):t._e()],1)],1)])]),a("a-col",{attrs:{xs:24,sm:24,md:18,lg:18,order:1}},[a("leaflet-map",{staticStyle:{height:"100%"},on:{onMapInit:t.initMap}})],1)],1),a("a-modal",{attrs:{title:"保存车辆出行轨迹"},on:{ok:t.handleOnModalOk},model:{value:t.modal.visible,callback:function(e){t.$set(t.modal,"visible",e)},expression:"modal.visible"}},[a("a-form-model",{ref:"routeForm",attrs:{model:t.modal.form,rules:t.modal.rules,"label-col":t.modal.labelCol,"wrapper-col":t.modal.wrapperCol}},[a("a-form-model-item",{ref:"carNumber",attrs:{label:"车牌号",prop:"carNumber"}},[a("a-input",{on:{blur:function(){t.$refs.carNumber.onFieldBlur()}},model:{value:t.modal.form.carNumber,callback:function(e){t.$set(t.modal.form,"carNumber",e)},expression:"modal.form.carNumber"}})],1)],1)],1)],1)},o=[],r=(n("4160"),n("c975"),n("d81d"),n("a434"),n("b680"),n("d3b7"),n("159b"),n("06f4"),n("fc25")),i=n("38f8"),s=n("e11e"),l=n("182f"),c=n("4e02"),u=n("584c"),d=n("2e27"),h=n("ade3b"),p=n("88bc"),f=n.n(p),m={color:"darkgrey",weight:4,opacity:1},g={color:"#f5222d",weight:5,opacity:.8},b={name:"RouteList",components:{LeafletMap:i["a"],DesignList:l["default"]},data:function(){return{map:{},routeForm:{},loading:{table:!1,map:!1},modal:{visible:!1,form:{},rules:{carNumber:[{required:!0,message:"请输入车牌号",trigger:"blur"},{min:3,max:10,message:"长度在3-10位之间",trigger:"blur"}]},labelCol:{span:4},wrapperCol:{span:18}},markerLayerGroup:{},tracks:{data:[],columns:[{title:"ID",dataIndex:"id"},{title:"#",scopedSlots:{customRender:"icon"}},{title:"决策评分",dataIndex:"decisionValue"},{title:"距离",dataIndex:"distance",customRender:function(t){return t.toFixed(2)+"米"}},{title:"耗时",dataIndex:"time",customRender:function(t){return(t/1e3).toFixed(2)+"秒"}},{title:"平均速度",dataIndex:"averageSpeed",customRender:function(t){return t.toFixed(2)+"km/h"}},{title:"操作",key:"action",fixed:"right",scopedSlots:{customRender:"action"}}],selectedRow:{},rowKeyLayerMap:[],checkpoints:{},layerGroup:null,markerGroup:null},selectedPlanId:null,checkPointMarker:[],stepCurrent:0}},beforeCreate:function(){this.simpleImage=r["a"].PRESENTED_IMAGE_SIMPLE},computed:{showNext:function(){return this.stepCurrent>0&&this.stepCurrent<2},checkpoints:function(){return this.checkPointMarker.map((function(t){return t.getLatLng()}))},tipsMessage:function(){var t=this.tracks.data.length;return this.tracks.data.length>0?"车辆出行轨迹还原成功,共搜寻到".concat(t,"条路径,最优解ID=1"):"无法完成轨迹还原,请检查卡口序列之间是否可达"},tipsType:function(){return this.tracks.data.length>0?"success":"warning"},color:function(){return function(t){return 0===t?"green":t===this.checkpoints.length-1?"red":"gray"}}},methods:{initMap:function(t){this.map=t,this.markerLayerGroup=s["featureGroup"]().addTo(this.map)},handleDrawMarkers:function(t){var e=this;this.clearMapVisible=!0,0!==t.length&&(t.forEach((function(t){var n=e.drawBaseMarker(t);e.markerLayerGroup.addLayer(n),n.on("click",(function(t){return e.handleOnMarkerClick(t)}))})),this.map.fitBounds(this.markerLayerGroup.getBounds()))},drawBaseMarker:function(t){return s["marker"](t,{icon:d["e"]})},handleOnMarkerClick:function(t){var e=t.target,n=this.checkPointMarker.indexOf(e);if(n>-1)this.checkPointMarker.splice(n,1),t.target.setIcon(d["e"]);else{this.checkPointMarker.push(t.target);var a=this.checkPointMarker.length;t.target.setIcon(Object(u["b"])(a))}},handlePlanSelect:function(t){this.stepCurrent=1,this.selectedPlanId=t.id,this.handleFetchDesignPlan()},handleFetchDesignPlan:function(){var t=this;c["a"].getById(this.selectedPlanId).then((function(e){t.handleDrawMarkers(e.data.checkpoints)}))},handleClearMap:function(){this.markerLayerGroup.clearLayers(),this.tracks.layerGroup&&this.tracks.layerGroup.clearLayers(),this.tracks.markerGroup&&this.tracks.markerGroup.clearLayers(),this.checkPointMarker=[]},handlePrev:function(){this.stepCurrent=this.stepCurrent-1,this.handleClearMap(),1===this.stepCurrent&&this.handleFetchDesignPlan()},handleOnNext:function(){if(1===this.stepCurrent){if(this.checkpoints.length<2)return void this.$message.warning("请至少选择两个点");this.handleRoute()}this.stepCurrent=this.stepCurrent+1},handleRoute:function(){var t=this;this.loading.table=!0,this.tracks.checkpoints=this.checkpoints;for(var e=this.checkpoints.length,n=[],a=1;a<e-1;a++){var o=this.checkpoints[a];n.push(o)}var r={start:this.checkpoints[0],end:this.checkpoints[e-1],waypoints:n};this.tracks.markerPoints=r,h["a"].route(r).then((function(e){t.loading.table=!1,t.$log.debug("生成轨迹",e.data.length),e.data.forEach((function(t,e){t.id=e+1})),t.tracks.data=e.data,t.handleClearMap(),t.handleDrawPoline(e.data),t.drwaPathMarker()})).finally((function(){setTimeout((function(){t.loading.table=!1}),1e3)}))},drwaPathMarker:function(){var t=this.tracks.markerPoints,e=t.start,n=t.end,a=t.waypoints,o=[],r=s["marker"](e,{icon:d["d"]});o.push(r);var i=s["marker"](n,{icon:d["c"]});o.push(i),a.forEach((function(t,e){var n=Object(u["a"])(t,e+1);o.push(n)})),this.tracks.markerGroup=s["layerGroup"](o).addTo(this.map)},handleDrawPoline:function(t){var e=this,n=[],a=null;t.forEach((function(t,o){var r=t.points.map((function(t){return[t.lat,t.lng]})),i=s["polyline"](r,m);0===o&&(a=i,i.setStyle(g)),e.registPolylineClickEvent(i),n.push(i),e.tracks.rowKeyLayerMap.push({rowKey:t.id,layer:i})})),this.tracks.layerGroup=s["featureGroup"](n).addTo(this.map),a&&a.bringToFront(),this.map.fitBounds(this.tracks.layerGroup.getBounds())},registPolylineClickEvent:function(t){var e=this;t.off().on("click",(function(t){e.cancelTrackTableSelect(),e.tracks.layerGroup.eachLayer((function(e){e===t.target?(e.bringToFront(),e.setStyle(g)):e.setStyle(m)}))}))},cancelTrackTableSelect:function(){var t=document.querySelectorAll(".table-row-selection");t&&t.forEach((function(t){t.classList.remove("table-row-selection")}))},tracksTableRowClick:function(t){var e=this;return{on:{click:function(n){e.tracks.selectedRow=t,e.cancelTrackTableSelect();for(var a=n.target.parentNode.children,o=0;o<a.length;o++)a[o].classList.add("table-row-selection");e.handleActivePoline()}}}},handleActivePoline:function(){var t=this,e=this.tracks.selectedRow.id;this.tracks.rowKeyLayerMap.forEach((function(n){var a=n.layer;n.rowKey===e?(a.bringToFront(),t.map.fitBounds(a.getBounds()),a.setStyle(g)):a.setStyle(m)}))},handleOnSavePath:function(t){this.routeForm={},this.routeForm=f()(t,"points","distance","time","averageSpeed","regularTurnCount","sharpTurnCount","uturnCount"),this.routeForm.checkpoints=this.tracks.checkpoints,this.modal.visible=!0},handleOnModalOk:function(){var t=this;this.routeForm.carNumber=this.modal.form.carNumber,h["a"].save(this.routeForm).then((function(e){t.$message.success("转存成功"),t.routeForm={},t.modal.visible=!1}))}}},y=b,v=(n("baa0"),n("2877")),k=Object(v["a"])(y,a,o,!1,null,"d745eb38",null);e["default"]=k.exports},"88bc":function(t,e,n){(function(e){var n=1/0,a=9007199254740991,o="[object Arguments]",r="[object Function]",i="[object GeneratorFunction]",s="[object Symbol]",l="object"==typeof e&&e&&e.Object===Object&&e,c="object"==typeof self&&self&&self.Object===Object&&self,u=l||c||Function("return this")();function d(t,e,n){switch(n.length){case 0:return t.call(e);case 1:return t.call(e,n[0]);case 2:return t.call(e,n[0],n[1]);case 3:return t.call(e,n[0],n[1],n[2])}return t.apply(e,n)}function h(t,e){var n=-1,a=t?t.length:0,o=Array(a);while(++n<a)o[n]=e(t[n],n,t);return o}function p(t,e){var n=-1,a=e.length,o=t.length;while(++n<a)t[o+n]=e[n];return t}var f=Object.prototype,m=f.hasOwnProperty,g=f.toString,b=u.Symbol,y=f.propertyIsEnumerable,v=b?b.isConcatSpreadable:void 0,k=Math.max;function C(t,e,n,a,o){var r=-1,i=t.length;n||(n=A),o||(o=[]);while(++r<i){var s=t[r];e>0&&n(s)?e>1?C(s,e-1,n,a,o):p(o,s):a||(o[o.length]=s)}return o}function w(t,e){return t=Object(t),L(t,e,(function(e,n){return n in t}))}function L(t,e,n){var a=-1,o=e.length,r={};while(++a<o){var i=e[a],s=t[i];n(s,i)&&(r[i]=s)}return r}function _(t,e){return e=k(void 0===e?t.length-1:e,0),function(){var n=arguments,a=-1,o=k(n.length-e,0),r=Array(o);while(++a<o)r[a]=n[e+a];a=-1;var i=Array(e+1);while(++a<e)i[a]=n[a];return i[e]=r,d(t,this,i)}}function A(t){return S(t)||x(t)||!!(v&&t&&t[v])}function I(t){if("string"==typeof t||T(t))return t;var e=t+"";return"0"==e&&1/t==-n?"-0":e}function x(t){return M(t)&&m.call(t,"callee")&&(!y.call(t,"callee")||g.call(t)==o)}var S=Array.isArray;function z(t){return null!=t&&P(t.length)&&!B(t)}function M(t){return O(t)&&z(t)}function B(t){var e=D(t)?g.call(t):"";return e==r||e==i}function P(t){return"number"==typeof t&&t>-1&&t%1==0&&t<=a}function D(t){var e=typeof t;return!!t&&("object"==e||"function"==e)}function O(t){return!!t&&"object"==typeof t}function T(t){return"symbol"==typeof t||O(t)&&g.call(t)==s}var j=_((function(t,e){return null==t?{}:w(t,h(C(e,1),I))}));t.exports=j}).call(this,n("c8ba"))},"9e10":function(t,e,n){t.exports=n.p+"assets/red.71758f32.svg"},ade3b:function(t,e,n){"use strict";var a=n("b775"),o={route:function(t){return Object(a["b"])({url:"/route",method:"post",data:t})},save:function(t){return Object(a["b"])({url:"/route/save",method:"post",data:t})},list:function(t){return Object(a["b"])({url:"/route",method:"get",params:t})},getById:function(t){return Object(a["b"])({url:"/route/".concat(t),method:"get"})},deleteById:function(t){return Object(a["b"])({url:"/route/".concat(t),method:"delete"})}};e["a"]=o},b23e:function(t,e,n){},baa0:function(t,e,n){"use strict";n("cd78")},be76:function(t,e,n){"use strict";n("b23e")},c88e:function(t,e,n){t.exports=n.p+"assets/unchecked.be913992.svg"},c9b6:function(t,e,n){"use strict";n("6558")},ca06:function(t,e,n){var a,o;(function(){var r=window.console||{error:function(){},warn:function(){}};function i(t){t.Control.Loading=t.Control.extend({options:{delayIndicator:null,position:"topleft",separate:!1,zoomControl:null,spinjs:!1,spin:{lines:7,length:3,width:3,radius:5,rotate:13,top:"83%"}},initialize:function(e){t.setOptions(this,e),this._dataLoaders={},null!==this.options.zoomControl&&(this.zoomControl=this.options.zoomControl)},onAdd:function(e){if(this.options.spinjs&&"function"!==typeof Spinner)return r.error("Leaflet.loading cannot load because you didn't load spin.js (http://fgnass.github.io/spin.js/), even though you set it in options.");this._addLayerListeners(e),this._addMapListeners(e),this.options.separate||this.zoomControl||(e.zoomControl?this.zoomControl=e.zoomControl:e.zoomsliderControl&&(this.zoomControl=e.zoomsliderControl));var n,a="leaflet-control-loading";return this.zoomControl&&!this.options.separate?(n=this.zoomControl._container,a+=" leaflet-bar-part-bottom leaflet-bar-part last",t.DomUtil.addClass(this._getLastControlButton(),"leaflet-bar-part-bottom")):n=t.DomUtil.create("div","leaflet-control-zoom leaflet-control-layer-container leaflet-bar"),this._indicatorContainer=n,this._indicator=t.DomUtil.create("a",a,n),this.options.spinjs&&(this._spinner=new Spinner(this.options.spin).spin(),this._indicator.appendChild(this._spinner.el)),n},onRemove:function(t){this._removeLayerListeners(t),this._removeMapListeners(t)},removeFrom:function(e){return this.zoomControl&&!this.options.separate?(this._container.removeChild(this._indicator),this._map=null,this.onRemove(e),this):t.Control.prototype.removeFrom.call(this,e)},addLoader:function(t){if(this._dataLoaders[t]=!0,this.options.delayIndicator&&!this.delayIndicatorTimeout){var e=this;this.delayIndicatorTimeout=setTimeout((function(){e.updateIndicator(),e.delayIndicatorTimeout=null}),this.options.delayIndicator)}else this.updateIndicator()},removeLoader:function(t){delete this._dataLoaders[t],this.updateIndicator(),this.options.delayIndicator&&this.delayIndicatorTimeout&&!this.isLoading()&&(clearTimeout(this.delayIndicatorTimeout),this.delayIndicatorTimeout=null)},updateIndicator:function(){this.isLoading()?this._showIndicator():this._hideIndicator()},isLoading:function(){return this._countLoaders()>0},_countLoaders:function(){var t,e=0;for(t in this._dataLoaders)this._dataLoaders.hasOwnProperty(t)&&e++;return e},_showIndicator:function(){t.DomUtil.addClass(this._indicator,"is-loading"),t.DomUtil.addClass(this._indicatorContainer,"is-loading"),this.options.separate||(this.zoomControl instanceof t.Control.Zoom?t.DomUtil.removeClass(this._getLastControlButton(),"leaflet-bar-part-bottom"):"function"===typeof t.Control.Zoomslider&&this.zoomControl instanceof t.Control.Zoomslider&&t.DomUtil.removeClass(this.zoomControl._ui.zoomOut,"leaflet-bar-part-bottom"))},_hideIndicator:function(){t.DomUtil.removeClass(this._indicator,"is-loading"),t.DomUtil.removeClass(this._indicatorContainer,"is-loading"),this.options.separate||(this.zoomControl instanceof t.Control.Zoom?t.DomUtil.addClass(this._getLastControlButton(),"leaflet-bar-part-bottom"):"function"===typeof t.Control.Zoomslider&&this.zoomControl instanceof t.Control.Zoomslider&&t.DomUtil.addClass(this.zoomControl._ui.zoomOut,"leaflet-bar-part-bottom"))},_getLastControlButton:function(){var t=this.zoomControl._container,e=t.children.length-1;while(e>0){var n=t.children[e];if(this._indicator!==n&&0!==n.offsetWidth&&0!==n.offsetHeight)break;e--}return t.children[e]},_handleLoading:function(t){this.addLoader(this.getEventId(t))},_handleBaseLayerChange:function(e){var n=this;e.layer&&e.layer.eachLayer&&"function"===typeof e.layer.eachLayer?e.layer.eachLayer((function(t){n._handleBaseLayerChange({layer:t})})):t.TileLayer.Canvas&&e.layer instanceof t.TileLayer.Canvas||n._handleLoading(e)},_handleLoad:function(t){this.removeLoader(this.getEventId(t))},getEventId:function(t){return t.id?t.id:t.layer?t.layer._leaflet_id:t.target._leaflet_id},_layerAdd:function(t){if(t.layer&&t.layer.on)try{t.layer.on({loading:this._handleLoading,load:this._handleLoad},this)}catch(e){r.warn("L.Control.Loading: Tried and failed to add  event handlers to layer",t.layer),r.warn("L.Control.Loading: Full details",e)}},_layerRemove:function(t){if(t.layer&&t.layer.off)try{t.layer.off({loading:this._handleLoading,load:this._handleLoad},this)}catch(e){r.warn("L.Control.Loading: Tried and failed to remove event handlers from layer",t.layer),r.warn("L.Control.Loading: Full details",e)}},_addLayerListeners:function(t){t.eachLayer((function(t){t.on&&t.on({loading:this._handleLoading,load:this._handleLoad},this)}),this),t.on("layeradd",this._layerAdd,this),t.on("layerremove",this._layerRemove,this)},_removeLayerListeners:function(t){t.eachLayer((function(t){t.off&&t.off({loading:this._handleLoading,load:this._handleLoad},this)}),this),t.off("layeradd",this._layerAdd,this),t.off("layerremove",this._layerRemove,this)},_addMapListeners:function(t){t.on({baselayerchange:this._handleBaseLayerChange,dataloading:this._handleLoading,dataload:this._handleLoad,layerremove:this._handleLoad},this)},_removeMapListeners:function(t){t.off({baselayerchange:this._handleBaseLayerChange,dataloading:this._handleLoading,dataload:this._handleLoad,layerremove:this._handleLoad},this)}}),t.Map.addInitHook((function(){this.options.loadingControl&&(this.loadingControl=new t.Control.Loading,this.addControl(this.loadingControl))})),t.Control.loading=function(e){return new t.Control.Loading(e)}}a=[n("e11e")],o=function(t){i(t)}.apply(e,a),void 0===o||(t.exports=o)})()},cd78:function(t,e,n){},d5bb:function(t,e,n){t.exports=n.p+"assets/geekblue.ebe8f201.svg"},e70a:function(t,e,n){},fb72:function(t,e,n){t.exports=n.p+"img/marker_hole.e695a4fc.png"}}]);