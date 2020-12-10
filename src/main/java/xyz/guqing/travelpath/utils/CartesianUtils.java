package xyz.guqing.travelpath.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 笛卡尔积工具类
 * <p>假设有两个集合A和B:
 * A = {0,1}, B = {2,3,4}
 * 那么笛卡尔积A×B表示如下：
 * A×B = {（0，2），（1，2），（0，3），（1，3），（0，4），（1，4）}；</p>
 *
 * @author guqing
 * @date 2020-10-13
 */
public class CartesianUtils {
    public static<T> List<List<T>> cartesianProduct(List<List<T>> dimensionValue) {
        List<List<T>> result = new LinkedList<>();
        cartesianProduct(dimensionValue, result, 0, new ArrayList<>());
        return result;
    }

    private static<T> void cartesianProduct(List<List<T>> dimensionValue, List<List<T>> result, int layer, List<T> currentList) {
        if (layer < dimensionValue.size() - 1) {
            if (dimensionValue.get(layer).size() == 0) {
                cartesianProduct(dimensionValue, result, layer + 1, currentList);
            } else {
                for (int i = 0; i < dimensionValue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<>(currentList);
                    list.add(dimensionValue.get(layer).get(i));
                    cartesianProduct(dimensionValue, result, layer + 1, list);
                }
            }
        } else if (layer == dimensionValue.size() - 1) {
            if (dimensionValue.get(layer).size() == 0) {
                result.add(currentList);
            } else {
                for (int i = 0; i < dimensionValue.get(layer).size(); i++) {
                    List<T> list = new ArrayList<>(currentList);
                    list.add(dimensionValue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }
}
