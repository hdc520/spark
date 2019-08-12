package java_learn.lambda;

import java.util.ArrayList;
import java.util.List;

public class lambda_List {
    private static List<String> list=new ArrayList<>();
    static {
        list.add("hdc");
        list.add("Tom");
        list.add("Sam");
        list.add("Mary");
    }

    public static void main(String[] args) {
        //java8之前输出
        for(String str:list)
            System.out.println(str);
        System.out.println("----------------------------------");
        list.forEach(c->System.out.println(c));
        //过滤filter
        System.out.println("过滤filter:");
        list.stream().filter(s->s.contains("m")).forEach(x->System.out.println(x));
        System.out.println("并行处理过滤filter:");
        list.parallelStream().filter(s->s.contains("m")).forEach(x->System.out.println(x));
//        list.stream().coll
        System.out.println("--"+(list.stream().filter(s->s.contains("m"))).getClass().getSimpleName());
    }

}
