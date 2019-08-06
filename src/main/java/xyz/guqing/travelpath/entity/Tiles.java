package xyz.guqing.travelpath.entity;

import java.util.Arrays;

public class Tiles {
    private Long id;

    private Integer zoomLevel;

    private Integer tileColumn;

    private Integer tileRow;

    private byte[] tileData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(Integer zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public Integer getTileColumn() {
        return tileColumn;
    }

    public void setTileColumn(Integer tileColumn) {
        this.tileColumn = tileColumn;
    }

    public Integer getTileRow() {
        return tileRow;
    }

    public void setTileRow(Integer tileRow) {
        this.tileRow = tileRow;
    }

    public byte[] getTileData() {
        return tileData;
    }

    public void setTileData(byte[] tileData) {
        this.tileData = tileData;
    }

    @Override
    public String toString() {
        return "Tiles{" +
                "id=" + id +
                ", zoomLevel=" + zoomLevel +
                ", tileColumn=" + tileColumn +
                ", tileRow=" + tileRow +
                ", tileData=" + Arrays.toString(tileData) +
                '}';
    }
}