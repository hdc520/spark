package java_learn.lambda;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class lambda_FlatMap {
    private static Map<Integer,String>m=Maps.newHashMap();
    static {
        m.put(1001,"www.baidu.com");
        m.put(1002,"www.leetcode.com");
        m.put(1003,"bjut.edu.cn");
        m.put(1004,"ahnu.edu.com");
        m.put(1005,"www.bjut.com");
    }

    public static void main(String[] args) {
        String result=null;
        result=m.entrySet().stream().filter(x->x.getKey()==1002).map(x->x.getValue()).collect(Collectors.joining());
        System.out.println(result);
        System.out.println("-----------------------------");
        String [] strs=new String[]{"www.baidu.com","bjut.edu.cn"};
        List<String> list= Arrays.stream(strs)
                .map(x->x.split("\\."))
                .flatMap(Arrays::stream).collect(Collectors.toList());
        for(String str:list)
            System.out.println(str);
        list.forEach(x->System.out.println(x));
    }

}
