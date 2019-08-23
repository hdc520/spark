package java_learn.iteration;
import java.lang.Iterable;
import java.util.Iterator;

public class MyItearble implements Iterable<Integer>{
    private int []elem={1,2,3,4,5,6,7};
    private int size=elem.length;

    //匿名内部类，实现Iterable接口里的方法

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int curror=-1;
            public boolean hasNext() {
                return curror+1<size;
            }
            public Integer next() {
                curror++;
                return elem[curror];
            }

            public void remove() {

            }
        };
    }

    public static void main(String[] args) {
        MyItearble myItearble=new MyItearble();
        Iterator<Integer> iterator=myItearble.iterator();
        System.out.println("迭代器循环：");
        while(iterator.hasNext()){
            System.out.print(iterator.next()+",");
        }
        System.out.println();
        System.out.println("foreach循环：");
        //实现此种循环必须要实现iterable接口，重写Iterator<Integer> iterator()方法
        for(int i:myItearble)
        {
            System.out.print(i+" ");
        }
    }
}
