package org.day1;

/**
 * @author yaocy
 * @date 2024-4-12 17:15
 * @description
 */
/**
 * 1.字符串拼接，比较  String类型
 * 2.浮点类型的计算，保证精度  BigDecimal
 * 3.时间格式转换  SimpleDateFormat
 * 4.集合的排序 去重  ArrayList HashSet
 * 5.文件读写
 * 6.uuid的使用
 * 7.有脏数据,需要考虑边界值
 */

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 1.数据在data.txt下
 * 2.读取文件,并按以下规则生成新的文件,文件名:data_new.txt
 * a.输出结果忽略第一行数据
 * b.name列和nickname 合成一列 举例: 张三(小可爱)
 * c.salary和subsidy求和合并成一列
 * d.将time转换成yyyy-MM-dd HH:mm:ss格式
 * e.每行数据要生成一个唯一值
 * f.按照age升序排列
 * g.过滤掉name相同地重复数据,只保留最新的一条
 * 一点一点百度
 * 先百度怎么读取文件
 * 然后是每行的每一列都是逗号分割，再查字符串分割
 * 然后就是放到一个集合里边对吧 集合里边应该是一个对象
 */
public class TestDay1 {
    public static DataInfo dataInfo;
    public static void main(String[] args) throws FileNotFoundException {
        // 1、读取文件 / 创建写入文件
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\data.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\data_new.txt"))
        )
        {
            // 2、按行读取数据，忽略掉第一行
            br.readLine();
            String line;
            HashMap<String, DataInfo> hm = new HashMap<>();
            while ((line = br.readLine()) != null){
                // 读到的是一个用逗号分隔的字符串 "张三,小可爱,20,15666.151,300.666,1693524683000"
                // 字符串再分隔成每个单独的字符串数组，把这个字符串数据传给一个实体类
                DataInfo dataInfo = new DataInfo(line.split(","));
                // 3、按照名字去重，利用集合中key不能重复的特性，让name作为集合的key
                hm.put(dataInfo.getName(), dataInfo);
            }

            // 4、按照年龄升序排列；取map的值转换为 list的 stream流，然后用list的stream流进行排序
            // 为方便输出，需将stream流转换为list
            List<DataInfo> list = hm.values().stream().sorted((o1, o2) -> o1.getAge() - o2.getAge()).collect(Collectors.toList());

            //5、输出到一个新文件中; 遍历集合，然后集合中每个对象 调用其toString方法，转换成string类型后，则可调用文件的write方法写出
            for (DataInfo info : list) {
                bw.write(info.toString());
                bw.newLine();
            }


        } catch (IOException e ) {
            e.printStackTrace();
        }

    }
}
