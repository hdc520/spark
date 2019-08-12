package java_learn.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class lambda_map {
    private static Map<String,Integer> items=new HashMap<>();
    static {
        items.put("hdc",24);
        items.put("Tom",25);
        items.put("Sam",23);
        items.put("Tony",26);
        items.put("Lisa",30);
        items.put("Mary",34);
    }
    public static void main(String[] args) {
        System.out.println("未使用lambda表达式遍历（key,value）：");
        for(Map.Entry<String,Integer> item:items.entrySet()){
            System.out.println("key:"+item.getKey()+" "+item.getValue());
        }
        System.out.println("未使用lambda表达式遍历Keys：");
        for(String key:items.keySet()){
            System.out.println(key);
        }
        System.out.println("未使用lambda表达式遍历Values：");
        for(Integer value:items.values()){
            System.out.println(value);
        }
        System.out.println("----------使用lambda遍历Map--------");
        System.out.println("lambda遍历(key,value)");
        items.entrySet().forEach(x->System.out.println(x.getKey()+"  "+x.getValue()));
        System.out.println("删选后的Map：");
        items.entrySet().stream().filter(x->x.getKey().contains("T")).forEach(x->
                System.out.println("key:"+x.getKey()+" value:"+x.getValue())
        );

        items.entrySet().stream().collect(Collectors.groupingBy(c->c.getValue())).forEach((x,y)->
                System.out.println(x+" "+y)
                );
        System.out.println(items.entrySet().stream().collect(Collectors.groupingBy(c->c.getValue()))
        .getClass().getSimpleName());
    }
}
