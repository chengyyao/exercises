package org.day2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yaocy
 * @date 2024-4-15 19:11
 * @description
 */
public class BaseInfo {
    private String name;
    private String nickName;
    private int age;
    private Double salary;
    private Double subsidy;
    private Long time;
    private String dateTime;

    public BaseInfo() {
    }

    public BaseInfo(String[] row) {
        this.name = (row[0] == null || row[0].isEmpty()) ? "" : row[0];
        this.nickName = (row[1] == null || row[1].isEmpty()) ? "" : row[1];
        this.age = (row[2] == null || row[2].isEmpty()) ? 0 : Integer.parseInt(row[2]);
        this.salary = (row[3] == null || row[3].isEmpty())? 0.0 : Double.parseDouble(row[3]);
        this.subsidy = (row[4] == null || row[4].isEmpty())? 0.0 : Double.parseDouble(row[4]);
        if (row.length == 6){
            this.time = (row[5] == null || row[5].isEmpty()) ? 0L : Long.parseLong(row[5]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            Date date = new Date((row[5] == null || row[5].isEmpty()) ? 0L : Long.parseLong(row[5]));
            this.dateTime = sdf.format(date);
        }else {
            this.time = 0L;
            this.dateTime = "";
        }
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", subsidy=" + subsidy +
                ", time=" + time +
                '}';
    }
}
