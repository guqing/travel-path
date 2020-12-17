package xyz.guqing.travelpath.model.enums;

/**
 * 多属性决策属性配置
 *
 * @author guqing
 * @date 2020-12-17
 */
public enum TopSisPropertyEnum implements PropertyEnum {
    /**
     * 多属性决策权重配置
     * distance: 距离
     * time: 总耗时
     * average_speed:平均速度
     * regular_turn: 正常转弯
     * sharp_turn: 急转弯
     * u_turn: 掉头
     */
    DISTANCE("topsis_weight_distance", Double.class, "0.2"),
    TIME("topsis_weight_time", Double.class, "0.2"),
    AVERAGE_SPEED("topsis_weight_average_speed", Double.class, "0.2"),
    REGULAR_TURN("topsis_weight_regular_turn", Double.class, "0.1"),
    SHARP_TURN("topsis_weight_sharp_turn", Double.class, "0.2"),
    U_TURN("topsis_weight_u_turn", Double.class, "0.1");

    private final String value;

    private final Class<?> type;

    private final String defaultValue;

    TopSisPropertyEnum(String value, Class<?> type, String defaultValue) {
        this.defaultValue = defaultValue;
        if (!PropertyEnum.isSupportedType(type)) {
            throw new IllegalArgumentException("Unsupported blog property type: " + type);
        }

        this.value = value;
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String defaultValue() {
        return defaultValue;
    }

    @Override
    public String getValue() {
        return value;
    }
}
