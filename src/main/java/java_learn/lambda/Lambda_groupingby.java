package java_learn.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Lambda_groupingby {
    public static void main(String[] args) {
        List<Person> personList=new ArrayList<>();
        //Java Builder模式主要是用一个内部类去实例化一个对象，避免一个类出现过多构造函数，
        // 而且构造函数如果出现默认参数的话，很容易出错。
        //stream(不带by,(collect(带by)))
        personList.add(new Person.Builder().id(1001).address("BJUT").name("hdc").build());
        personList.add(new Person.Builder().id(1002).address("AJRT").name("Tom").build());
        personList.add(new Person.Builder().id(1003).address("WZXD").name("Sam").build());
        personList.add(new Person.Builder().id(1002).address("BJUT").name("Mary").build());
        //lambda表达式输出
        Map<String,List<Person>> collect= personList.stream().collect(Collectors.groupingBy(c->c.getAddress()));
        System.out.println(collect);

        Map<Integer,List<Person>> collect1= personList.stream().collect(Collectors.groupingBy(c->c.getId()));
        System.out.println(collect1);

    }
}
