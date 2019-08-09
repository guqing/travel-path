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
