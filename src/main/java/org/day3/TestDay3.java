package org.day3;

/**
 * @author yaocy
 * @date 2024-4-15 19:11
 * @description
 */


import org.day2.BaseInfo;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 1. 新增rank.txt表 列说明: 名字 职级 绩效
 * 2. 读取BaseInfo.txt 和 rank.txt 两个文件;输出到一个新的文件中,文件名:BaseInfo_Rank.txt
 * 3. 输出结果再BaseInfo的基础上新增一列:年终奖; 年终奖计算规则:  salary*月基数*系数
 * 月基数：
 * 职级1:1个月
 * 职级2:3个月
 * 职级3:5个月
 * 职级4:8个月----如果没有这个对应的月份，那就返回月份为0吗？
 * 系数：
 * A:2.3
 * B:1.7
 * C:0.3
 * <p>
 * 考虑边界值
 */
public class TestDay3 {
    private static BaseInfo baseInfo;
    private static Rank rank;
    private static BaseInfoAndRank baseInfoAndRank;
    public static void main(String[] args) {
        // 读两个表数据
        try (BufferedReader brb = new BufferedReader(new FileReader("src\\main\\resources\\BaseInfo.txt"));
             BufferedReader brr = new BufferedReader(new FileReader("src\\main\\resources\\rank.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\BaseInfo_Rank"))
             )
        {
            // 1、读取rank表的数据
            String rankLine;
            HashMap<String, Rank> rankHm = new HashMap<>();
            while ((rankLine = brr.readLine()) != null){
                rank = new Rank(rankLine.split(","));
                rankHm.put(rank.getName(), rank);
            }
            //2、读取baseInfo表的数据
            brb.readLine();
            String baseLine;
            ArrayList<BaseInfoAndRank> baseInfoAndRankList = new ArrayList<>();
            while ((baseLine = brb.readLine()) != null){
                baseInfo = new BaseInfo(baseLine.split(","));
                baseInfoAndRank = new BaseInfoAndRank();
                baseInfoAndRank.setName(baseInfo.getName());
                baseInfoAndRank.setNickName(baseInfo.getNickName());
                baseInfoAndRank.setAge(baseInfo.getAge());
                baseInfoAndRank.setSalary(baseInfo.getSalary());
                baseInfoAndRank.setSubsidy(baseInfo.getSubsidy());
                baseInfoAndRank.setTime(baseInfo.getTime());
                if (rankHm.containsKey(baseInfo.getName())){
                    double v = baseInfo.getSalary() * rankHm.get(baseInfo.getName()).getYearBonus();
                    BigDecimal vb = BigDecimal.valueOf(v);
                    baseInfoAndRank.setYearBonus(vb.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                }else {
                    baseInfoAndRank.setYearBonus(0.0);
                }
                baseInfoAndRankList.add(baseInfoAndRank);


            }

            for (BaseInfoAndRank baseAndRank : baseInfoAndRankList) {
                bw.write(baseAndRank.toString());
                bw.newLine();
            }





        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
