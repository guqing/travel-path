package xyz.guqing.travelpath.exception;

import com.carrotsearch.hppc.IntArrayList;

/**
 * @author guqing
 * @date 2020-11-19
 */
public class MultiplePointsNotFoundException extends RuntimeException{
    private final IntArrayList pointsNotFound;

    public MultiplePointsNotFoundException(IntArrayList pointsNotFound) {
        this.pointsNotFound = pointsNotFound;
    }

    public IntArrayList getPointsNotFound() {
        return pointsNotFound;
    }
}
