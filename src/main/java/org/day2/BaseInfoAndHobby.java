package org.day2;

import java.util.Objects;

/**
 * @author yaocy
 * @date 2024-4-16 15:17
 * @description
 */
public class BaseInfoAndHobby {
    private String name;
    private String nickName;
    private int age;
    private Double salary;
    private Double subsidy;
    private Long time;
    private String hobbyStr;

    public BaseInfoAndHobby() {
    }

    public BaseInfoAndHobby(String name, String nickName, int age, Double salary, Double subsidy, Long time, String hobbyStr) {
        this.name = name;
        this.nickName = nickName;
        this.age = age;
        this.salary = salary;
        this.subsidy = subsidy;
        this.time = time;
        this.hobbyStr = hobbyStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseInfoAndHobby that = (BaseInfoAndHobby) o;
        return Objects.equals(name, that.name);}

    @Override
    public int hashCode() {
        return Objects.hash(name);
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

    public String getHobbyStr() {
        return hobbyStr;
    }

    public void setHobbyStr(String hobbyStr) {
        this.hobbyStr = hobbyStr;
    }

    @Override
    public String toString() {
        return   "'" +name + '\'' +
                ", '" + nickName + '\'' +
                ", " + age +
                ", " + salary +
                ", " + subsidy +
                ", " + time +
                ", '" + hobbyStr + '\'' ;
    }
}
