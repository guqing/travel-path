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

$(".createPresetSchemeBtn").on('click',function() {
    xdialog.open({
        title: '添加卡口预设方案',
        body: createSchemeTemplete,
        effect: 'just_me',
        onok: function (e) {
            var name = $("#schemeName").val();
            var description = $("#schemeDescription").val();
            if(name==='') {
                $.Toast("表单校验失败", "请将表单填写完整", "warning");
                return false;
            }
            //向后台请求添加数据
            var presetpoints = markerHash.getKeys();
            saveScheme(name,description, presetpoints);
        }
    });
});

function saveScheme(name, description, presetpoints) {
    var presetPointList = [];
    presetpoints.forEach(function(data){
        var presetJson = JSON.parse(data);
        presetPointList.push(presetJson);
    });
    $.ajax({
        url: baseUrl+'preset/save',
        type: "post",
        contentType: "application/json;charset=utf-8",
        dataType:'json',
        data: JSON.stringify({"name":name,"description":description,"presetpoints": presetPointList}),
        success: function(response) {
            console.log(response)
            if(response.code === 0) {
                $.Toast("成功", "添加预设卡口方案成功", "success");
            }
        },
        error: function(data) {
            $.Toast("失败", "抱歉，添加预设卡口方案失败", "warning");
        }
    })
}
ajaxPage();

/**
 * Ajax分页
 * @param page 当前页码
 */
function ajaxPage(page){
    var p = page || 1;
    $.ajax({
        type: "get",
        url: baseUrl + "preset/list",
        data: {
            pageNum: p,
            pageSize:10
        },
        dataType: "json",
        success: function(response){
            var data = response.data;
            //数据处理
            data.list.forEach(function(item,index){
                appendSchemeTableDom(item, index);
            });
            // 调用分页插件
            $("#paginator").sPage({
                page:p,//当前页码
                pageSize:10,//每页显示多少条数据，默认10条
                total:data.total,//数据总条数,后台返回
                prevPage:"←",
                nextPage:"→",
                backFun:function(page){
                    //点击分页按钮回调函数，返回当前页码
                    ajaxPage(page);
                }
            });
        },
        error:function(e){
            console.log(e);
            $.Toast("失败", "抱歉，加载预设卡口方案列表失败", "warning");
        }
    });
}

/**
 * 预设卡口方案数据表格dom追加
 * @param item 需要显示dom的数据项
 */
function appendSchemeTableDom(item, index) {
    var $tr = $("<tr></tr>");
    var $idTd = $("<td></td>");
    $idTd.text(index+1);

    var $nameTd = $("<td></td>");
    $nameTd.text(item.name);

    var $descriptionTd = $("<td></td>");
    $descriptionTd.text(item.description);

    var $bayonetCountTd = $("<td></td>");
    $bayonetCountTd.text(item.bayonetCount);

    var $modifyTimeTd = $("<td></td>");
    var modifyTime = dateFormat(item.modifyTime,"yyyy-MM-dd");
    $modifyTimeTd.text(modifyTime);

    // 操作
    var $opsTd = $("<td></td>");
    var showSchemeData = $("<label class='badge badge-info showSchemeData' onclick='showSchemeData("+item.id+")'>查看</label>");
    var editSchemeData = $("<label class='badge badge-primary editSchemeData' onclick='editSchemeData("+item.id+")'>编辑</label>");
    var deleteSchemeData = $("<label class='badge badge-danger deleteSchemeData' onclick='editSchemeData("+item.id+")'>删除</label>");
    $opsTd.append(showSchemeData);
    $opsTd.append(editSchemeData);
    $opsTd.append(deleteSchemeData);

    // 追加到dom
    $("#dataTable tbody").append($tr).append($idTd).append($nameTd)
        .append($descriptionTd).append($bayonetCountTd)
        .append($modifyTimeTd).append($opsTd);
}

var markerGroup = [];
function showSchemeData(id) {
    // 清除标记物集合
    removeLayers(markerGroup);

    $.ajax({
        type: "get",
        url: baseUrl + "preset/getScheme/"+id,
        dataType: "json",
        success: function(response){
            var data = response.data;
            data.list.forEach(function(item) {
                var marker = drawMaker(item.lat, item.lng);
                markerGroup.push(marker);
            });
            // 根据标记点集合自适应地图缩放级别
            var group = new L.featureGroup(markerGroup);
            mymap.fitBounds(group.getBounds());
        },
        error:function(e){
            console.log(e);
            $.Toast("失败", "抱歉，查询预设卡口方案失败", "warning");
        }
    });
}


function deleteSchemeData(id) {
    $.ajax({
        type: "get",
        url: baseUrl + "/get-scheme/"+id,
        dataType: "json",
        success: function(response){

        },
        error:function(e){
            console.log(e);
            $.Toast("失败", "抱歉，查询预设卡口方案失败", "warning");
        }
    });
}



function editSchemeData(id) {
    $.ajax({
        type: "get",
        url: baseUrl + "/get-scheme/"+id,
        dataType: "json",
        success: function(response){
            var data = response.data;
            console.log(data)
        },
        error:function(e){
            console.log(e);
            $.Toast("失败", "抱歉，查询预设卡口方案失败", "warning");
        }
    });
}


var createSchemeTemplete = '<form class="presetPointForm">\n' +
    '\t<div class="form-group">\n' +
    '\t\t<label for="schemeName">方案名称</label>\n' +
    '\t\t<input type="text" id="schemeName" class="form-control" placeholder="name">\n' +
    '\t</div>\n' +
    '\t<div class="form-group">\n' +
    '\t\t<label for="schemeDescription">方案描述</label>\n' +
    '\t\t<input type="text" id="schemeDescription" class="form-control" placeholder="description">\n' +
    '\t</div>\n' +
    '</form>';