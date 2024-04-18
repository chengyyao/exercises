package org.day3;

/**
 * @author yaocy
 * @date 2024-4-18 13:49
 * @description
 */
public enum LevelMonthEnum {
    LEVEL_ONE(1), // 就相当于 根据构造器传入的值返回了 一个枚举类对象 LevelMonthEnum LEVEL_ONE
    // LevelMonthEnum LEVEL_ONE = new LevelMonthEnum(1)
    LEVEL_TWO(3),
    LEVEL_THREE(5),
    LEVEL_FOUR(0);

    private final int month;

    LevelMonthEnum(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    // 提供一个传入int类型的方法，来通过枚举类标识信息
    public static LevelMonthEnum fromInt(int month){
        switch (month){
            case 1:
                return LEVEL_ONE;
            case 2:
                return LEVEL_TWO;
            case 3:
                return LEVEL_THREE;
            default:
                return LEVEL_FOUR;
        }
    }
}
