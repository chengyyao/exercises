package org.day3;

import static org.day3.LevelMonthEnum.*;

/**
 * @author yaocy
 * @date 2024-4-17 10:59
 * @description
 */
public class Rank {
    private String name;
    private String level;
    private String performance;

    private int levelMonth;
    private Double coefficient;

    private Double yearBonus;

    public Rank() {
    }

    public Rank(String[] row) {
        this.name = row[0];
        this.level = row[1];
        this.performance = row[2];
        this.levelMonth = LevelMonthEnum.fromInt(Integer.parseInt(row[1])).getMonth();
        if ("A".equals(row[2])){
            this.coefficient = 2.3;
        } else if ("B".equals(row[2])) {
            this.coefficient = 1.7;
        }else {
            this.coefficient = 0.3;
        }

        this.yearBonus = levelMonth * coefficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public int getLevelMonth() {
        return levelMonth;
    }

    public void setLevelMonth(int levelMonth) {
        this.levelMonth = levelMonth;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public Double getYearBonus() {
        return yearBonus;
    }

    public void setYearBonus(Double yearBonus) {
        this.yearBonus = yearBonus;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", performance='" + performance + '\'' +
                ", levelMonth=" + levelMonth +
                ", coefficient=" + coefficient +
                ", yearBonus=" + yearBonus +
                '}';
    }
}
