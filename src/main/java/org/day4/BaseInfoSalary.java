package org.day4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * @author yaocy
 * @date 2024-4-18 18:41
 * @description
 */
public class BaseInfoSalary {
    private String name;
    private String nickName;
    private Double salary;
    private Double subsidy;
    private String time;
    private ArrayList<String> hobbyList = new ArrayList<>();

    public BaseInfoSalary() {
    }

    public BaseInfoSalary(String name, String nickName, Double salary, Double subsidy, String time, ArrayList<String> hobbyList) {
        this.name = name;
        this.nickName = nickName;
        this.salary = salary;
        this.subsidy = subsidy;
        this.time = time;
        this.hobbyList = hobbyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseInfoSalary that = (BaseInfoSalary) o;
        return name.equals(that.name);
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(ArrayList<String> hobbyList) {
        this.hobbyList = hobbyList;
    }

    @Override
    public String toString() {
        return '\''+ name + '\'' +
                ", " + '\''+ nickName + '\'' +
                ", " + '\''+  salary + '\'' +
                ", " + '\''+  subsidy +'\'' +
                ", " + '\''+ time + '\'' ;
    }
}
