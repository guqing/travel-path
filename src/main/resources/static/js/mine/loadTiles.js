L.TileLayer.loadTileLayer = L.TileLayer.extend({
	getTileUrl: function(coords) {
		var data = {
			// r: retina ? '@2x' : '',
			s: this._getSubdomain(coords),
			x: coords.x,
			y: this._globalTileRange.max.y - coords.y,
			z: this._getZoomForUrl()
		};

		return L.Util.template(this._url, L.extend(data, this.options));
	}
});

L.tileLayer.loadTileLayer = function(url, options) {
	return new L.TileLayer.loadTileLayer(url, options);
};
