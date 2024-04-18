package org.day3;

/**
 * @author yaocy
 * @date 2024-4-17 17:51
 * @description
 */
public class BaseInfoAndRank {
    private String name;
    private String nickName;
    private int age;
    private Double salary;
    private Double subsidy;
    private Long time;
    private Double yearBonus;

    public BaseInfoAndRank() {
    }

    public BaseInfoAndRank(String name, String nickName, int age, Double salary, Double subsidy, Long time, Double yearBonus) {
        this.name = name;
        this.nickName = nickName;
        this.age = age;
        this.salary = salary;
        this.subsidy = subsidy;
        this.time = time;
        this.yearBonus = yearBonus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Double subsidy) {
        this.subsidy = subsidy;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getYearBonus() {
        return yearBonus;
    }

    public void setYearBonus(Double yearBonus) {
        this.yearBonus = yearBonus;
    }

    @Override
    public String toString() {
        return "BaseInfoAndRank{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", subsidy=" + subsidy +
                ", time=" + time +
                ", yearBonus=" + yearBonus +
                '}';
    }
}
