package org.day1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author yaocy
 * @date 2024-4-12 17:32
 * @description
 */
public class DataInfo {
    private String name;
    private String nickName;
    private String nameNew;
    private int age;
    private Double salary;
    private Double subsidy;
    private Double salaryAndSubsidy;
    private String time;
    private String uuid;

    public DataInfo() {
    }

    public DataInfo(String[] row) {
        this.name = (row[0] == null || row[0].isEmpty()) ? "" : row[0];
        this.nickName = (row[1] == null || row[1].isEmpty()) ? "" : row[1];
        this.nameNew = name + "(" + nickName + ")";
        this.age = (row[2] == null || row[2].isEmpty()) ? 0 : Integer.parseInt(row[2]);
        this.salary = (row[3] == null || row[3].isEmpty())? 0.0 : Double.parseDouble(row[3]);
        this.subsidy = (row[4] == null || row[4].isEmpty())? 0.0 : Double.parseDouble(row[4]);
        this.salaryAndSubsidy = salary + subsidy;
        if (row.length == 6){
            Long dataTime = (row[5] == null || row[5].isEmpty()) ? 0L : Long.parseLong(row[5]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(dataTime);
            String str = sdf.format(date);
            this.time = str;
        }else {
            this.time = "";
        }
        this.uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();

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

    public String getNameNew(){
        return nameNew;
    }

    public void setNameNew(String nameNew){
        this.nameNew = nameNew;
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

    public Double getSalaryAndSubsidy(){
        return salaryAndSubsidy;
    }

    public void setSalaryAndSubsidy(Double salaryAndSubsidy){
        this.salaryAndSubsidy = salaryAndSubsidy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return  nameNew + '\'' +
                ", " + age +
                ", " + salaryAndSubsidy +
                ", " + time +
                ", " + uuid +
                '}';
    }
}
