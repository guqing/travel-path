(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ede661c4"],{"260d":function(t,n,o){t.exports=o.p+"assets/end_point.45451854.svg"},"2e27":function(t,n,o){"use strict";o.d(n,"e",(function(){return i})),o.d(n,"d",(function(){return r})),o.d(n,"c",(function(){return s})),o.d(n,"a",(function(){return d})),o.d(n,"b",(function(){return l}));var e=o("e11e"),a=e["Icon"].extend({options:{iconUrl:o("d5bb"),shadowUrl:o("e2b9"),shadowAnchor:[14,41],iconSize:[25,39],iconAnchor:[13,38]}}),i=new a({iconUrl:o("c88e")}),r=new a({iconUrl:o("6a91")}),s=new a({iconUrl:o("260d")}),d=(new a({iconUrl:o("742d")}),new a({iconUrl:o("9e10")})),l=new a({iconUrl:o("d5bb")})},"38f8":function(t,n,o){"use strict";var e=function(){var t=this,n=t.$createElement,o=t._self._c||n;return o("div",{staticClass:"map-wrapper"},[o("div",{style:{cursor:t.cursor},attrs:{id:"mapContainer"}})])},a=[],i=(o("d81d"),o("6cc5"),o("e11e")),r=(o("ca06"),o("53b7"),"https://api.mapbox.com/styles/v1/guqing/ckis880630pby19ohlzsqtuki/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoiZ3VxaW5nIiwiYSI6ImNqdmtmNGh4bjBxdmg0OXFyMDI0dHc3emQifQ.AAGR_XTCwYALRFQmtAwmHA"),s={name:"LeafletMap",props:{cursor:{type:String,required:!1,default:null},bounds:{type:Array,required:!1,default:function(){return[[32.948,107.708],[37.139,113.161]]}}},data:function(){return{map:{}}},mounted:function(){this.init()},methods:{init:function(){var t=i["latLng"](this.bounds[0][0],this.bounds[0][1]),n=i["latLng"](this.bounds[1][0],this.bounds[1][1]),o=i["latLngBounds"](t,n);this.map=new i["Map"]("mapContainer",{maxBounds:o,center:[34.26099394982405,108.94237697124483],zoom:14,zoomControl:!0,loadingControl:!0}),this.L.tileLayer(r,{foo:"bar",attribution:'Map data &copy; <a href="https://github.com/guqing">guqing</a>',minZoom:3}).addTo(this.map),i["rectangle"](o,{width:2,color:"#000",opacity:.3,fill:!1}).addTo(this.map),this.$emit("onMapInit",this.map)}}},d=s,l=(o("be76"),o("2877")),c=Object(l["a"])(d,e,a,!1,null,"af30a2f4",null);n["a"]=c.exports},"53b7":function(t,n,o){},"584c":function(t,n,o){"use strict";o.d(n,"a",(function(){return a})),o.d(n,"b",(function(){return i}));var e=o("e11e");e["NumberedDivIcon"]=e["Icon"].extend({options:{iconUrl:o("fb72"),number:"",shadowUrl:null,iconSize:new e["Point"](25,41),iconAnchor:new e["Point"](12,40),popupAnchor:new e["Point"](0,-33),shadowSize:new e["Point"](50,-64),shadowAnchor:new e["Point"](4,-62),className:"leaflet-div-icon"},createIcon:function(){var t=document.createElement("div"),n=this._createImg(this.options["iconUrl"]),o=document.createElement("div");return o.setAttribute("class","number"),o.innerHTML=this.options["number"]||"",t.appendChild(n),t.appendChild(o),this._setIconStyles(t,"icon"),t},createShadow:function(){return null}});o("e70a");function a(t,n){return new e["Marker"](t,{icon:new e["NumberedDivIcon"]({number:n})})}var i=function(t){return new e["NumberedDivIcon"]({number:t})}},"6a91":function(t,n,o){t.exports=o.p+"assets/start_point.9c72ff17.svg"},"742d":function(t,n,o){t.exports=o.p+"assets/blue.1805d0e3.svg"},9698:function(t,n,o){"use strict";o.r(n);var e=function(){var t=this,n=t.$createElement,o=t._self._c||n;return o("div",{staticStyle:{height:"100%"}},[o("a-row",{staticStyle:{height:"100%"},attrs:{gutter:[16,16]}},[o("a-col",{staticStyle:{height:"60%"},attrs:{xs:24,sm:24,md:24}},[o("leaflet-map",{staticStyle:{height:"100%"},on:{onMapInit:t.initMap}})],1),o("a-col",{staticStyle:{height:"40%"},attrs:{xs:24,sm:24,md:24}},[o("a-card",{attrs:{bordered:!1}},[o("div",{staticClass:"table-operator"},[o("a-button",{attrs:{type:"primary"},on:{click:t.handleClearMap}},[t._v(" 清空地图 ")])],1),o("a-table",{attrs:{columns:t.columns,"data-source":t.data,rowKey:"id",loading:t.loading,pagination:t.pagination},on:{change:t.handleTableChange},scopedSlots:t._u([{key:"action",fn:function(n,e){return[o("a",{attrs:{href:"javascript:void(0)"},on:{click:function(n){return t.handlePreview(e)}}},[t._v(" 预览 ")]),o("a-divider",{attrs:{type:"vertical"}}),o("a-popconfirm",{attrs:{title:"确定要删除这条轨迹吗?","ok-text":"确定","cancel-text":"取消"},on:{confirm:function(n){return t.handleDelete(e)}}},[o("a",{attrs:{href:"javascript:void(0)"}},[t._v(" 删除 ")])])]}}])})],1)],1)],1)],1)},a=[],i=(o("d81d"),o("b680"),o("d3b7"),o("5530")),r=o("e11e"),s=o("38f8"),d=o("ade3b"),l=o("2e27"),c=o("584c"),h={color:"#f5222d",weight:5,opacity:.8},u={name:"PathList",components:{LeafletMap:s["a"]},data:function(){return{map:{},layerGroup:null,data:[],pagination:{},loading:!1,columns:[{title:"ID",dataIndex:"id"},{title:"车牌号",dataIndex:"carNumber"},{title:"距离",dataIndex:"distance",customRender:function(t){return t.toFixed(2)+"米"}},{title:"通行时间",dataIndex:"time",customRender:function(t){return(t/1e3).toFixed(2)+"秒"}},{title:"平均速度",dataIndex:"averageSpeed",customRender:function(t){return t.toFixed(2)+"km/h"}},{title:"缓转弯",dataIndex:"regularTurnCount"},{title:"急转弯",dataIndex:"sharpTurnCount"},{title:"掉头",dataIndex:"uturnCount"},{title:"创建时间",dataIndex:"createTime"},{title:"操作",key:"action",scopedSlots:{customRender:"action"}}]}},created:function(){this.handleLoadData()},methods:{initMap:function(t){this.map=t},handleClearMap:function(){this.layerGroup&&this.layerGroup.clearLayers()},handleLoadData:function(){var t=this;this.loading=!0,d["a"].list().then((function(n){var o=n.data,e=o.list,a=o.total;t.data=e,t.pagination.total=a,t.loading=!1})).finally((function(){setTimeout((function(){t.loading=!1}),1e3)}))},handleTableChange:function(t){var n=Object(i["a"])({},this.pagination);n.current=t.current,this.pagination=n,this.handleLoadData()},handlePreview:function(t){var n=this;d["a"].getById(t.id).then((function(t){var o=t.data,e=o.points,a=o.checkpoints;n.drwaPath(e,a)}))},drwaPath:function(t,n){this.handleClearMap();for(var o=1,e=[],a=0;a<n.length;a++){var i=n[a];if(0===a){var s=r["marker"](i,{icon:l["d"]});e.push(s)}else if(a===n.length-1){var d=r["marker"](i,{icon:l["c"]});e.push(d)}else{var u=Object(c["a"])(i,o);e.push(u),o++}}var f=r["polyline"](t,h);e.push(f),this.layerGroup=r["featureGroup"](e).addTo(this.map),this.map.fitBounds(this.layerGroup.getBounds())},handleDelete:function(t){var n=this;d["a"].deleteById(t.id).then((function(t){n.$message.success("删除成功"),n.handleLoadData()}))}}},f=u,p=o("2877"),m=Object(p["a"])(f,e,a,!1,null,null,null);n["default"]=m.exports},"9e10":function(t,n,o){t.exports=o.p+"assets/red.71758f32.svg"},ade3b:function(t,n,o){"use strict";var e=o("b775"),a={route:function(t){return Object(e["b"])({url:"/route",method:"post",data:t})},save:function(t){return Object(e["b"])({url:"/route/save",method:"post",data:t})},list:function(t){return Object(e["b"])({url:"/route",method:"get",params:t})},getById:function(t){return Object(e["b"])({url:"/route/".concat(t),method:"get"})},deleteById:function(t){return Object(e["b"])({url:"/route/".concat(t),method:"delete"})}};n["a"]=a},b23e:function(t,n,o){},be76:function(t,n,o){"use strict";o("b23e")},c88e:function(t,n,o){t.exports=o.p+"assets/unchecked.be913992.svg"},ca06:function(t,n,o){var e,a;(function(){var i=window.console||{error:function(){},warn:function(){}};function r(t){t.Control.Loading=t.Control.extend({options:{delayIndicator:null,position:"topleft",separate:!1,zoomControl:null,spinjs:!1,spin:{lines:7,length:3,width:3,radius:5,rotate:13,top:"83%"}},initialize:function(n){t.setOptions(this,n),this._dataLoaders={},null!==this.options.zoomControl&&(this.zoomControl=this.options.zoomControl)},onAdd:function(n){if(this.options.spinjs&&"function"!==typeof Spinner)return i.error("Leaflet.loading cannot load because you didn't load spin.js (http://fgnass.github.io/spin.js/), even though you set it in options.");this._addLayerListeners(n),this._addMapListeners(n),this.options.separate||this.zoomControl||(n.zoomControl?this.zoomControl=n.zoomControl:n.zoomsliderControl&&(this.zoomControl=n.zoomsliderControl));var o,e="leaflet-control-loading";return this.zoomControl&&!this.options.separate?(o=this.zoomControl._container,e+=" leaflet-bar-part-bottom leaflet-bar-part last",t.DomUtil.addClass(this._getLastControlButton(),"leaflet-bar-part-bottom")):o=t.DomUtil.create("div","leaflet-control-zoom leaflet-control-layer-container leaflet-bar"),this._indicatorContainer=o,this._indicator=t.DomUtil.create("a",e,o),this.options.spinjs&&(this._spinner=new Spinner(this.options.spin).spin(),this._indicator.appendChild(this._spinner.el)),o},onRemove:function(t){this._removeLayerListeners(t),this._removeMapListeners(t)},removeFrom:function(n){return this.zoomControl&&!this.options.separate?(this._container.removeChild(this._indicator),this._map=null,this.onRemove(n),this):t.Control.prototype.removeFrom.call(this,n)},addLoader:function(t){if(this._dataLoaders[t]=!0,this.options.delayIndicator&&!this.delayIndicatorTimeout){var n=this;this.delayIndicatorTimeout=setTimeout((function(){n.updateIndicator(),n.delayIndicatorTimeout=null}),this.options.delayIndicator)}else this.updateIndicator()},removeLoader:function(t){delete this._dataLoaders[t],this.updateIndicator(),this.options.delayIndicator&&this.delayIndicatorTimeout&&!this.isLoading()&&(clearTimeout(this.delayIndicatorTimeout),this.delayIndicatorTimeout=null)},updateIndicator:function(){this.isLoading()?this._showIndicator():this._hideIndicator()},isLoading:function(){return this._countLoaders()>0},_countLoaders:function(){var t,n=0;for(t in this._dataLoaders)this._dataLoaders.hasOwnProperty(t)&&n++;return n},_showIndicator:function(){t.DomUtil.addClass(this._indicator,"is-loading"),t.DomUtil.addClass(this._indicatorContainer,"is-loading"),this.options.separate||(this.zoomControl instanceof t.Control.Zoom?t.DomUtil.removeClass(this._getLastControlButton(),"leaflet-bar-part-bottom"):"function"===typeof t.Control.Zoomslider&&this.zoomControl instanceof t.Control.Zoomslider&&t.DomUtil.removeClass(this.zoomControl._ui.zoomOut,"leaflet-bar-part-bottom"))},_hideIndicator:function(){t.DomUtil.removeClass(this._indicator,"is-loading"),t.DomUtil.removeClass(this._indicatorContainer,"is-loading"),this.options.separate||(this.zoomControl instanceof t.Control.Zoom?t.DomUtil.addClass(this._getLastControlButton(),"leaflet-bar-part-bottom"):"function"===typeof t.Control.Zoomslider&&this.zoomControl instanceof t.Control.Zoomslider&&t.DomUtil.addClass(this.zoomControl._ui.zoomOut,"leaflet-bar-part-bottom"))},_getLastControlButton:function(){var t=this.zoomControl._container,n=t.children.length-1;while(n>0){var o=t.children[n];if(this._indicator!==o&&0!==o.offsetWidth&&0!==o.offsetHeight)break;n--}return t.children[n]},_handleLoading:function(t){this.addLoader(this.getEventId(t))},_handleBaseLayerChange:function(n){var o=this;n.layer&&n.layer.eachLayer&&"function"===typeof n.layer.eachLayer?n.layer.eachLayer((function(t){o._handleBaseLayerChange({layer:t})})):t.TileLayer.Canvas&&n.layer instanceof t.TileLayer.Canvas||o._handleLoading(n)},_handleLoad:function(t){this.removeLoader(this.getEventId(t))},getEventId:function(t){return t.id?t.id:t.layer?t.layer._leaflet_id:t.target._leaflet_id},_layerAdd:function(t){if(t.layer&&t.layer.on)try{t.layer.on({loading:this._handleLoading,load:this._handleLoad},this)}catch(n){i.warn("L.Control.Loading: Tried and failed to add  event handlers to layer",t.layer),i.warn("L.Control.Loading: Full details",n)}},_layerRemove:function(t){if(t.layer&&t.layer.off)try{t.layer.off({loading:this._handleLoading,load:this._handleLoad},this)}catch(n){i.warn("L.Control.Loading: Tried and failed to remove event handlers from layer",t.layer),i.warn("L.Control.Loading: Full details",n)}},_addLayerListeners:function(t){t.eachLayer((function(t){t.on&&t.on({loading:this._handleLoading,load:this._handleLoad},this)}),this),t.on("layeradd",this._layerAdd,this),t.on("layerremove",this._layerRemove,this)},_removeLayerListeners:function(t){t.eachLayer((function(t){t.off&&t.off({loading:this._handleLoading,load:this._handleLoad},this)}),this),t.off("layeradd",this._layerAdd,this),t.off("layerremove",this._layerRemove,this)},_addMapListeners:function(t){t.on({baselayerchange:this._handleBaseLayerChange,dataloading:this._handleLoading,dataload:this._handleLoad,layerremove:this._handleLoad},this)},_removeMapListeners:function(t){t.off({baselayerchange:this._handleBaseLayerChange,dataloading:this._handleLoading,dataload:this._handleLoad,layerremove:this._handleLoad},this)}}),t.Map.addInitHook((function(){this.options.loadingControl&&(this.loadingControl=new t.Control.Loading,this.addControl(this.loadingControl))})),t.Control.loading=function(n){return new t.Control.Loading(n)}}e=[o("e11e")],a=function(t){r(t)}.apply(n,e),void 0===a||(t.exports=a)})()},d5bb:function(t,n,o){t.exports=o.p+"assets/geekblue.ebe8f201.svg"},e70a:function(t,n,o){},fb72:function(t,n,o){t.exports=o.p+"img/marker_hole.e695a4fc.png"}}]);