function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    return window.location.protocol + '//' + window.location.host + '/';
}

/**
 * 创建地图
 */
var mymap = L.map('mapContainer').setView([40.030401, 116.225003], 14);
var myLayer = L.tileLayer.loadTileLayer(mapTileUrl, {
    attribution: 'Map data &copy;<a href="https://github.com/guqing">guqing</a>',
    minZoom: 11,
    maxZoom: 19
}).addTo(mymap);

/**
 * 地图瓦片加载失败时提示
 */
myLayer.on('tileerror', function(error, tile) {
    $.Toast("提示", "地图瓦片加载失败", "warning");
});

/**
 * 地图标记物样式
 */
var bayonetIcon = L.icon({
    iconUrl: getRootPath()+'images/icon/bayonet.png',
    iconSize: [30, 45],
    iconAnchor: [16,44]
});


/**
 * 日期格式化方法
 * @param time 时间毫秒值
 * @param fmt 格式化参数
 * @returns fmt 返回格式化后的日期字符串
 */
function dateFormat(time, fmt) {
    var date = new Date(time);
    var o = {
        "M+" : date.getMonth()+1,//月份
        "d+" : date.getDate(),//日
        "h+" : date.getHours(),//小时
        "m+" : date.getMinutes(),//分
        "s+" : date.getSeconds(),//秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()//毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

// 绘制坐标点
function drawMaker(lat,lng) {
    return L.marker([lat, lng],presetMarkerOption).addTo(mymap);
}

function removeLayers(layers) {
    // 先清除地图上的标记物
    layers.forEach(function(item){
        mymap.removeLayer(item);
    });
    //清空数组
    layers = [];
}