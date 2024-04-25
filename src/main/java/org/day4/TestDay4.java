package org.day4;

/**
 * @author yaocy
 * @date 2024-4-15 19:11
 * @description
 */


import org.day2.BaseInfo;
import org.day2.BaseInfoAndHobby;
import org.day2.Hobby;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * baseInfo 和 hobby表
 * 1.如果工资低于平均工资将获得涨薪的机会,规则如下:
 * a,入职时间超过3个月,涨薪百分之2,
 * b.如果入职时间超过6个月的涨薪百分之5
 * 2.如果有人的爱好是打游戏或打麻将,补贴(subsidy)增加1000
 * <p>
 * 3.输出最新工资情况,列说明: name,nickname,salary,subsidy,time(yyyy/MM/dd HH:mm:ss)
 */
public class TestDay4 {
    public static void main(String[] args) {
        try (BufferedReader brb = new BufferedReader(new FileReader("src\\main\\resources\\BaseInfo.txt"));
             BufferedReader brh = new BufferedReader(new FileReader("src\\main\\resources\\Hobby.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\BaseInfo_Habby_day4.txt"));) {
            // 1、读hobby中的数据，并且存入实体类
            HashMap<String, Hobby> hobbyMap = getStringHobbyHashMap(brh);

            // 2、跳过表头一行, 读取BaseInfo中的数据，并且存入实体类
            ArrayList<BaseInfoSalary> andHobbyArrayList = getBaseInfoSalaries(brb, hobbyMap);

            // 3、计算平均工资
            Double avgSalary = getaDouble(andHobbyArrayList);

            // 4、判断是否涨薪，涨薪后的值存入arrayList
            ArrayList<BaseInfoSalary> BaseInfoSalaryList = combBaseInfoSalaries(andHobbyArrayList, avgSalary);

            // 5、把已经组装好的数据写出去
            for (BaseInfoSalary infoAndHobby : BaseInfoSalaryList) {
                bw.write(infoAndHobby.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<BaseInfoSalary> combBaseInfoSalaries(ArrayList<BaseInfoSalary> andHobbyArrayList, Double avgSalary) {
        LocalDate currentDate = LocalDate.now();
        ArrayList<BaseInfoSalary> BaseInfoSalaryList = new ArrayList<>();
        for (BaseInfoSalary infoSalary : andHobbyArrayList) {
            if (infoSalary.getSalary() < avgSalary && infoSalary.getTime() != null && !infoSalary.getTime().isEmpty()) {
                System.out.println(infoSalary.getName() + "工资为：" + infoSalary.getSalary() + "工资低于平均工资，符合涨薪条件");
                String[] times = infoSalary.getTime().split("-");
                // '2023-09-05 07:22:03'   2023, 09, 05 07:22:03
                int targetYear = Integer.parseInt(times[0]);
                int targetMonth = Integer.parseInt(times[1]);
                int targetDay = Integer.parseInt(times[2].split(" ")[0]);
                Period period = Period.between(LocalDate.of(targetYear, targetMonth, targetDay), currentDate);
                int totalMonths = period.getYears() * 12 + period.getMonths();
                if (totalMonths > 3 && totalMonths <= 6) {
                    System.out.println(infoSalary.getName() + "涨薪前工资为" + infoSalary.getSalary());
                    infoSalary.setSalary(infoSalary.getSalary() + infoSalary.getSalary() * 0.02);
                    System.out.println(infoSalary.getName() + "涨薪2%" + ", 涨薪后工资为：" + infoSalary.getSalary());
                } else if (totalMonths > 6) {
                    System.out.println(infoSalary.getName() + "涨薪前工资为" + infoSalary.getSalary());
                    infoSalary.setSalary(infoSalary.getSalary() + infoSalary.getSalary() * 0.05);
                    System.out.println(infoSalary.getName() + "涨薪5%" + ", 涨薪后工资为：" + infoSalary.getSalary());
                }
            }
            if (infoSalary.getHobbyList() != null) {
                for (String s : infoSalary.getHobbyList()) {
                    if ("打麻将".equals(s) || "打游戏".equals(s)) {
                        System.out.println(infoSalary.getName() + "补贴前为" + infoSalary.getSubsidy());
                        infoSalary.setSubsidy(infoSalary.getSubsidy() + 1000);
                        System.out.println(infoSalary.getName() + "补贴1000" + ", 补贴后为：" + infoSalary.getSubsidy());
                    }
                }
            }
            BaseInfoSalaryList.add(infoSalary);
        }
        return BaseInfoSalaryList;
    }

    private static Double getaDouble(ArrayList<BaseInfoSalary> andHobbyArrayList) {
        Double totalSalary = 0.0;
        Double avgSalary;
        for (BaseInfoSalary infoAndHobby : andHobbyArrayList) {
            totalSalary += infoAndHobby.getSalary();
        }
        avgSalary = totalSalary / andHobbyArrayList.size();
        System.out.println("平均工资为：" + avgSalary);
        return avgSalary;
    }

    private static ArrayList<BaseInfoSalary> getBaseInfoSalaries(BufferedReader brb, HashMap<String, Hobby> hobbyMap) throws IOException {
        brb.readLine();
        String BaseInfoline;
        HashSet<BaseInfoSalary> baseInfoAndHobbies = new HashSet<>();
        while ((BaseInfoline = brb.readLine()) != null) {
            BaseInfo baseInfo = new BaseInfo(BaseInfoline.split(","));
            BaseInfoSalary baseInfoSalary = new BaseInfoSalary();
            baseInfoSalary.setName(baseInfo.getName());
            baseInfoSalary.setNickName(baseInfo.getNickName());
            baseInfoSalary.setSalary(baseInfo.getSalary());
            baseInfoSalary.setSubsidy(baseInfo.getSubsidy());
            baseInfoSalary.setTime(baseInfo.getDateTime());
            if (hobbyMap.get(baseInfo.getName()) != null) {
                baseInfoSalary.setHobbyList(hobbyMap.get(baseInfo.getName()).getHobbyList());
            } else {
                baseInfoSalary.setHobbyList(null);
            }
            // BaseInfoAndHobby 实体类中重写了 hashCode和equals方法，就会使对象中某个值一样，就认为是重复了
            if (baseInfoAndHobbies.contains(baseInfoSalary)) {
                baseInfoAndHobbies.remove(baseInfoSalary);
            }
            baseInfoAndHobbies.add(baseInfoSalary);
        }
        ArrayList<BaseInfoSalary> andHobbyArrayList = new ArrayList<>();
        andHobbyArrayList.addAll(baseInfoAndHobbies);
        return andHobbyArrayList;
    }

    private static HashMap<String, Hobby> getStringHobbyHashMap(BufferedReader brh) throws IOException {
        String hobbyLine;
        HashMap<String, Hobby> hobbyMap = new HashMap<>();
        while ((hobbyLine = brh.readLine()) != null) {
            Hobby hobby = new Hobby(hobbyLine.split(","));
            hobbyMap.put(hobby.getName(), hobby);
        }
        return hobbyMap;
    }
}
