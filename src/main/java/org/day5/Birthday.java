package org.day5;

/**
 * @author yaocy
 * @date 2024-4-24 8:56
 * @description
 */
public class Birthday {
    private String name;
    private String birthday;

    public Birthday() {
    }

    public Birthday(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
