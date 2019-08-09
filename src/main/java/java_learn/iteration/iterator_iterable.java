package java_learn.iteration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class iterator_iterable {
    public static void main(String[] args) {
        List<Integer>list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //第一种遍历方式
        System.out.println("正常循环遍历：");
        for(int i=0;i<list.size();i++)
            System.out.println(list.get(i));
        //第二种循环方式
        System.out.println("迭代器遍历：");
        //对列表list的迭代器进行引用,因为list实现的collection接口，而collection接口中定义的Inerator迭代器
        Iterator iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //for each循环遍历此种遍历循环必须是相应的容器实现了Iterable接口(java基本类库中都可以使用foreach)
        System.out.println("foreach循环遍历：");
        for (int i:list) {
            System.out.println(i);
        }
        //java类库中的foreach循环
        System.out.println("foreach循环遍历：");
        int [] arr=new int[]{1,2,3,4,5};
        for(int i:arr)
            System.out.println(i);
    }

}
