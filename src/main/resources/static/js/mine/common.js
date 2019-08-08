var mymap = L.map('mapContainer').setView([40.030401, 116.225003], 14);
L.tileLayer.loadTileLayer(mapTileUrl, {
    attribution: 'Map data &copy;<a href="https://github.com/guqing">guqing</a>',
    minZoom: 11,
    maxZoom: 19,
}).addTo(mymap);