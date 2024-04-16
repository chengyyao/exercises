package org.day2;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yaocy
 * @date 2024-4-15 19:35
 * @description
 */
public class Hobby {
    private String name;
    private ArrayList<String> hobbyList = new ArrayList<>();
    private String hobbyStr;

    public Hobby() {
    }

    public Hobby(String[] row) {
        this.name = (row[0] == null || row[0].isEmpty()) ? " " : row[0];
        String[] res = Arrays.copyOfRange(row, 1, row.length);
        for (int i = 0; i < res.length; i++) {
            hobbyList.add(res[i]);
        }
        this.hobbyStr = StringUtils.join(hobbyList, "-");


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(ArrayList<String> hobbyList) {
        this.hobbyList = hobbyList;
    }

    public String getHobbyStr() {
        return hobbyStr;
    }

    public void setHobbyStr(String hobbyStr) {
        this.hobbyStr = hobbyStr;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "name='" + name + '\'' +
                ", hobbyList=" + hobbyList +
                ", hobbyStr='" + hobbyStr + '\'' +
                '}';
    }
}
