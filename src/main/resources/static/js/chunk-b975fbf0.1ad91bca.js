(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b975fbf0"],{"432b":function(t,e,n){"use strict";n.d(e,"a",(function(){return i}));var o=n("5530"),r=n("5880"),i={computed:Object(o["a"])(Object(o["a"])({},Object(r["mapState"])({layout:function(t){return t.app.layout},navTheme:function(t){return t.app.theme},primaryColor:function(t){return t.app.color},colorWeak:function(t){return t.app.weak},fixedHeader:function(t){return t.app.fixedHeader},fixedSidebar:function(t){return t.app.fixedSidebar},contentWidth:function(t){return t.app.contentWidth},autoHideHeader:function(t){return t.app.autoHideHeader},isMobile:function(t){return t.app.isMobile},sideCollapsed:function(t){return t.app.sideCollapsed},multiTab:function(t){return t.app.multiTab}})),{},{isTopMenu:function(){return"topmenu"===this.layout}}),methods:{isSideMenu:function(){return!this.isTopMenu}}}},"55c3":function(t,e,n){"use strict";n.r(e);var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("a-list",{attrs:{itemLayout:"horizontal"}},[n("a-list-item",{scopedSlots:t._u([{key:"actions",fn:function(){return[n("a-switch",{attrs:{checkedChildren:"暗色",unCheckedChildren:"白色",defaultChecked:"dark"===t.navTheme},on:{change:t.onChange}})]},proxy:!0}])},[n("a-list-item-meta",{scopedSlots:t._u([{key:"title",fn:function(){return[n("a",[t._v("风格配色")])]},proxy:!0},{key:"description",fn:function(){return[n("span",[t._v(" 整体风格配色设置 ")])]},proxy:!0}])})],1),n("a-list-item",[n("a-list-item-meta",{scopedSlots:t._u([{key:"title",fn:function(){return[n("a",[t._v("主题色")])]},proxy:!0},{key:"description",fn:function(){return[n("span",[t._v(" 页面风格配色： "),n("a",[t._v(t._s(t.colorFilter(t.primaryColor)))])])]},proxy:!0}])})],1)],1)},r=[],i=(n("7db0"),n("d3b7"),n("f64c"),n("99af"),n("cb29"),n("a15b"),n("d81d"),n("ac1f"),n("5319"),n("6a71"),n("7746"),[{key:"薄暮",color:"#F5222D"},{key:"火山",color:"#FA541C"},{key:"日暮",color:"#FAAD14"},{key:"明青",color:"#13C2C2"},{key:"极光绿",color:"#52C41A"},{key:"拂晓蓝（默认）",color:"#1890FF"},{key:"极客蓝",color:"#2F54EB"},{key:"酱紫",color:"#722ED1"}]),a=n("432b"),u=n("9fb0"),c={dark:"暗色",light:"白色"},l={mixins:[a["a"]],data:function(){return{}},filters:{themeFilter:function(t){return c[t]}},methods:{colorFilter:function(t){var e=i.find((function(e){return e.color===t}));return e&&e.key},onChange:function(t){t?this.$store.commit(u["m"],u["c"].DARK):this.$store.commit(u["m"],u["c"].LIGHT)}}},p=l,s=n("2877"),f=Object(s["a"])(p,o,r,!1,null,null,null);e["default"]=f.exports}}]);