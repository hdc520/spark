package java_learn.lambda;

import scala.actors.threadpool.Arrays;

import java.util.Comparator;

public class old_compare {
    public static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static class UserCompare implements Comparator<User>{
        public int compare(User o1, User o2) {
            return o1.age-o2.age;
        }
    }

    public static void main(String[] args) {
        User u[]=new User[]{
                new User("hdc",23),
                new User("lisi",25),
                new User("Tom",30)
        };
        System.out.println("未排序之前：");
        for(User i:u){
            System.out.println("name:"+i.getName()+" age:"+i.getAge());
        }
        System.out.println("排序之后：");
        Arrays.sort(u,new UserCompare());
        for(User i:u){
            System.out.println("name:"+i.getName()+" age:"+i.getAge());
        }
    }

}
