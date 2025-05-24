package jtm.extra04;

public class Animal {
    private int age;

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            this.age = 0;
        }
    }

    public int getAge() {
        return age;
    }
}
