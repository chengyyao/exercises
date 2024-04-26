package org.day5;

/**
 * @program: exercises
 * @description:
 * @author: yaocy
 * @since: 2024-04-19 17:02
 **/


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.day2.BaseInfo;
import org.day4.BaseInfoSalary;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 1.新增IncrBaseInfo.json文件(文件中既包含新员工信息也包含老员工信息)  birthday.json文件(生日信息)
 * 2.需求:
 * a.将新增员工信息写入到BaseInfo.txt
 * b.更新老员工的信息,并且写入到BaseInfo.txt
 * c.增加一列:员工入职天数,并按照入职天数降序排列,入职天数=当前时间-入职时间,写入到BaseInfo.txt
 * d.找出4月过生日的人,如果没有找到生日信息也当做是4月过生日; 追加到最后行
 */
public class TestDay5 {
    public static void main(String[] args) {
        List<BaseInfo> collect = Collections.emptyList();
        String nameJoin= null;
        try (BufferedReader brBaseInfo = new BufferedReader(new FileReader("src\\main\\resources\\BaseInfo.txt"));
             BufferedReader brI = new BufferedReader(new FileReader("src\\main\\resources\\IncrBaseInfo.json"));
             BufferedReader brb = new BufferedReader(new FileReader("src\\main\\resources\\birthday.json"));
             ) {

            //1、读baseInfo文件，并把数据存入到hashMap中
            HashMap<String, BaseInfo> baseInfoMap = getStringBaseInfoHashMap(brBaseInfo);


            // 2、读IncrBaseInfo.json文件, 全部读取完拼接成字符串，然后转换成json对象；然后遍历json对象，判断是否可hashMap中的值一致
            // 一致就更新，不一致就添加
            ArrayList<BaseInfo> baseInfoList = getBaseInfoList(brI, baseInfoMap);


            // 4、遍历list，每个都增加入职时间，然后再排序
            collect = extracted(baseInfoList);

            // 5、读birthday数据，并把4月过生日的追加到最后
            // 创建birthday的实体类，然后直接把读取的json数据转换成 birthdays的list集合
            nameJoin = getNameJoin(brb, collect);


        } catch (Exception e) {
            e.printStackTrace();
        }
        // 1、因为更新到baseinfo.txt中后，第二次运行时，因为数据都不是初始的数据了，会导致有问题；估更新到其他的文件中
        try (BufferedWriter bwb = new BufferedWriter(new FileWriter("src\\main\\resources\\BaseInfo_new.txt"));
        )
        {
            // 3、先输出到new中
            extracted(bwb,nameJoin, collect);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private static void extracted(BufferedWriter bwb, String nameJoin, List<BaseInfo> collect) throws IOException {
        bwb.write("name,nickname,age,salary,subsidy,time,DaysOnDuty");
        bwb.newLine();
        for (BaseInfo baseInfo : collect) {
            bwb.write(baseInfo.toString());
            bwb.newLine();
        }

        if (nameJoin != null && !nameJoin.isEmpty()){
            bwb.write(nameJoin);
            bwb.newLine();
        }
    }

    private static List<BaseInfo> extracted(ArrayList<BaseInfo> baseInfoList) {
        Instant now = Instant.now(); // 获取当前的时间对象
        for (BaseInfo baseInfo : baseInfoList) {
            if (baseInfo.getTime().equals(0L)){
                baseInfo.setDaysOnDuty(0L);
            }else {
                Instant hireDate = Instant.ofEpochMilli(baseInfo.getTime());
                Duration duration = Duration.between(hireDate, now);
                long days = duration.toDays();
                baseInfo.setDaysOnDuty(days);
            }
        }
        List<BaseInfo> collect = baseInfoList.stream().sorted((o1, o2) ->
                Long.compare(o2.getDaysOnDuty(), o1.getDaysOnDuty())).collect(Collectors.toList());
        return collect;
    }

    private static HashMap<String, BaseInfo> getStringBaseInfoHashMap(BufferedReader brBaseInfo) throws IOException {
        //1、读baseInfo文件，并把数据存入到hashMap中
        brBaseInfo.readLine();
        String BaseInfoline;
        HashMap<String, BaseInfo> baseInfoMap = new HashMap<>();
        while ((BaseInfoline = brBaseInfo.readLine()) != null) {
            BaseInfo baseInfo = new BaseInfo(BaseInfoline.split(","));
            baseInfoMap.put(baseInfo.getName(), baseInfo);

        }
        return baseInfoMap;
    }

    private static ArrayList<BaseInfo> getBaseInfoList(BufferedReader brI, HashMap<String, BaseInfo> baseInfoMap ) throws IOException{
        String IncrBaseInfoLine;
        StringBuilder totalBaseinfo = new StringBuilder();
        while ((IncrBaseInfoLine = brI.readLine()) != null) {
            totalBaseinfo.append(IncrBaseInfoLine);
        }

        JSONArray jsonArray = JSON.parseArray(totalBaseinfo.toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // 更新使用map key是唯一的就可以，就直接把json中的数据，put到baseInfo的map中即可
            baseInfoMap.put(jsonObject.getString("name"),
                    new BaseInfo(
                            jsonObject.getString("name"),
                            jsonObject.getString("nickname"),
                            jsonObject.getInteger("age"),
                            jsonObject.getDouble("salary"),
                            jsonObject.getDouble("subsidy"),
                            jsonObject.getLong("time")
                    )
            );
        }

        ArrayList<BaseInfo> baseInfoList = new ArrayList<>(baseInfoMap.values());
        return baseInfoList;
    }

    private static String getNameJoin(BufferedReader brb, List<BaseInfo> collect) throws IOException{
        String nameJoin = null;
        String brbLine;
        StringBuilder birthdayJsonStr = new StringBuilder();
        while ((brbLine = brb.readLine()) != null){
            birthdayJsonStr.append(brbLine);
        }
        List<Birthday> birthdays = JSON.parseArray(birthdayJsonStr.toString(), Birthday.class);
        ArrayList<String> names = new ArrayList<>();
        for (Birthday birthday : birthdays) {
            String[] split = birthday.getBirthday().split("-");
            if (split[1] != null && !split[1].isEmpty()){
                if ("04".equals(split[1]) || "4".equals(split[1])){
                    names.add(birthday.getName());
                }
            }

        }
        // stream().map就是把所有的某个属性值取出来
        Set<String> stringSet = birthdays.stream().map(Birthday::getName).collect(Collectors.toSet());
        List<String> stringName = collect.stream().map(BaseInfo::getName).
                filter(name -> !stringSet.contains(name)).collect(Collectors.toList());


        names.addAll(stringName);

        // 转换成字符串拼接
        if (!names.isEmpty()){
            nameJoin = String.join(",", names);
        }
        return nameJoin;
    }




}
