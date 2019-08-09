mymap.on('click', onMapClick);

// 标记物marker参数
var presetMarkerOption = {
    icon: bayonetIcon,
    title: '预设卡口',
    draggable: true,
    autoPan: true
};

var markerHash = new HashTable();
var tempPointString;
/**
 * 地图点击事件
 */
function onMapClick(e) {
    //LatLng(40.044957, 116.236961)
    var point = e.latlng;
    var marker = L.marker([point.lat, point.lng],presetMarkerOption).addTo(mymap);

    //将marker添加到hashTable中,先转换为字符串在添加到hash否则会出现精度失真
    var pointJSONString = JSON.stringify(point);
    markerHash.add(pointJSONString, marker);

    // 为marker添加相应事件
    marker.on('click', onMarkerClick);
    marker.on('dragstart', onMarkerDragstart);
    marker.on('moveend', onMarkerMoveEnd);
}

/**
 * marker标记点的点击事件，点击marker时会被删除
 * @param {Object} e 被点击的事件变量
 */
function onMarkerClick(e) {
    var point = e.target.getLatLng();
    if(confirm('确定要删除预设卡口点吗')){
        mymap.removeLayer(markerHash.getValue(point));
    }
}

function onMarkerDragstart(e) {
    //标记点开始被拖拽时记录下被拖拽的标记，用于修改hash中的记录
    var tempPoint = e.target.getLatLng();
    tempPointString = JSON.stringify(tempPoint);
}

function onMarkerMoveEnd(e) {
    var point = e.target.getLatLng();
    var pointJSONString = JSON.stringify(point);
    if(tempPointString) {
        // 删除原来的marker
        markerHash.remove(tempPointString);
        //插入移动后的新marker
        markerHash.add(pointJSONString, e.target);
    }
    //置空，防止产生脏数据
    tempPointString = '';
}