package xyz.guqing.travelpath.route;

import lombok.Data;

import java.util.Objects;

/**
 * @author guqing
 * @date 2020-10-08
 */
@Data
public class Point {
    double lat;
    double lng;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.lat, lat) == 0 &&
                Double.compare(point.lng, lng) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
