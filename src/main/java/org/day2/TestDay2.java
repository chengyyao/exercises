package org.day2;

/**
 * @author yaocy
 * @date 2024-4-12 17:16
 * @description
 * */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * //     * 1.读取BaseInfo.txt和 Hobby.txt 两个文件;输出到一个新的文件中,文件名:BaseInfo_Hobby.txt
 * //     * 2.按照名字去重  （map的key不能重复特性实现）
 * //     * 3.按照time排序,time为空的排在最后(现在实现不了todo)  （数据都组装好了之后，再把map转成list然后再排序）
 * //     * 4.每个人的爱好用 "-"拼接  （这个实现类的构造器中处理， 字符串拼接就行）
 * //     * 5.在倒数第二行输出爱好出现次数大于1次的爱好 用逗号分隔  （数据组装好之后， bw.write()
 * //     * 6.在最后一行输出爱好里包含球的人,示例: 张三(足球,篮球),李四(排球,棒球)
 *  命名规范：包名全部小写；类名首字母大写，驼峰命名；变量和方法首字母小写，驼峰命名；全局变量全部字母大写或者下划线拼接
 * */


public class TestDay2 {
    private static BaseInfo baseInfo;
    private static Hobby hobby;
    private static BaseInfoAndHobby baseInfoAndHobby;
    public static void main(String[] args) {
        try (BufferedReader brb = new BufferedReader(new FileReader("src\\main\\resources\\BaseInfo.txt"));
             BufferedReader brh = new BufferedReader(new FileReader("src\\main\\resources\\Hobby.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\BaseInfo_Habby.txt"));)
        {  // 1、跳过表头一行, 读取BaseInfo中的数据，并且存入实体类
            brb.readLine();
            String BaseInfoline;
            HashMap<String, BaseInfo> baseInfoMap = new HashMap<>();
            while ((BaseInfoline = brb.readLine()) != null){
                baseInfo = new BaseInfo(BaseInfoline.split(","));
                baseInfoMap.put(baseInfo.getName(), baseInfo);

            }

            // 2、读hobby中的数据，并且存入实体类
            String hobbyLine;
            HashMap<String, Hobby> hobbyMap = new HashMap<>();
            while ((hobbyLine = brh.readLine()) != null){
                hobby = new Hobby(hobbyLine.split(","));
                hobbyMap.put(hobby.getName(), hobby);
            }
            System.out.println(baseInfoMap);
            System.out.println("---------------------------------------------------------");
            System.out.println(hobbyMap);

            // 3、两个实体类的数据进行合并; 遍历map的key，然后把每个key对应的值（即一个对象），
            // 这个对象对应的属性分别存入到新实体类中，然后hobby实体类的中值，通过遍历的方式取到对象后，判断如果跟map中的key一致
            // 则就把爱好存入新实体类中； 最后再把这个实体类装进入集合中
            ArrayList<BaseInfoAndHobby> baseInfoAndHobbies = new ArrayList<>();
            for (String s : baseInfoMap.keySet()) {
                baseInfoAndHobby = new BaseInfoAndHobby();
                baseInfoAndHobby.setName(baseInfoMap.get(s).getName());
                baseInfoAndHobby.setNickName(baseInfoMap.get(s).getNickName());
                baseInfoAndHobby.setAge(baseInfoMap.get(s).getAge());
                baseInfoAndHobby.setSalary(baseInfoMap.get(s).getSalary());
                baseInfoAndHobby.setSubsidy(baseInfoMap.get(s).getSubsidy());
                baseInfoAndHobby.setTime(baseInfoMap.get(s).getTime());
                if (hobbyMap.containsKey(s)){
                    baseInfoAndHobby.setHobbyStr(hobbyMap.get(s).getHobbyStr());
                }else {
                    baseInfoAndHobby.setHobbyStr("");
                }
//                不用list的原因，又多使用了一次循环，时间复杂度会变多
//                for (Hobby hobby1 : hobbies) {
//                    if (s.equals(hobby1.getName())){
//                        baseInfoAndHobby.setHobbyStr(hobby1.getHobbyStr());
//
//                    }
//                }
                baseInfoAndHobbies.add(baseInfoAndHobby);
            }


            // 4、合并之后的集合，按照time排序，time为空的排在最后
            Collections.sort(baseInfoAndHobbies, new Comparator<BaseInfoAndHobby>() {
                @Override
                public int compare(BaseInfoAndHobby o1, BaseInfoAndHobby o2) {
                    return Long.compare(o2.getTime(), o1.getTime());
                }
            });
            System.out.println("---------------------------------------------------------");
            System.out.println(baseInfoAndHobbies);


            //5.在倒数第二行输出爱好出现次数大于1次的爱好 用逗号分隔  （数据组装好之后， bw.write()
            HashSet<String> hashSet = new HashSet<>();
            HashSet<String> secondLine = new HashSet<>();
            for (BaseInfoAndHobby infoAndHobby : baseInfoAndHobbies) {
                if (infoAndHobby.getHobbyStr() != null && !infoAndHobby.getHobbyStr().isEmpty()){
                    String[] strings = infoAndHobby.getHobbyStr().split("-");
                    for (int i = 0; i < strings.length; i++) {
                        // 如果不存在，就给添加到hashSet中，如果hashSet中已经存在这个值，就给添加到输出的hashSet中
                        if (hashSet.contains(strings[i])) {
                            secondLine.add(strings[i]);
                        } else {
                            hashSet.add(strings[i]);
                        }
                    }
                }

            }
            String hobbyStr = secondLine.stream().collect(Collectors.joining(","));


            System.out.println("---------------------");
            System.out.println(secondLine);

            // 6.在最后一行输出爱好里包含球的人,示例: 张三(足球,篮球),李四(排球,棒球)
            String s ="";
            for (Map.Entry<String, Hobby> hobbyEntry : hobbyMap.entrySet()) {
                Hobby value = hobbyEntry.getValue();
                String svalue = value.getHobbyList().stream().filter(a -> a.contains("球")).collect(Collectors.joining(","));
                if (svalue != null && !svalue.isEmpty()){
                    s += hobbyEntry.getKey() + "(" + svalue + ")" + ",";
                }
            }
            if (s !="" && s.length()>0){
                s = s.substring(0,s.length() -1);
            }
            System.out.println(s);


            // 先把已经组装好的数据写出去
            for (BaseInfoAndHobby infoAndHobby : baseInfoAndHobbies) {
                bw.write(infoAndHobby.toString());
                bw.newLine();
            }
            bw.write(hobbyStr);
            bw.newLine();
            bw.write(s);




        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
