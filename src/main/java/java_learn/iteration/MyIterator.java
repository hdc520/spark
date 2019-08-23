package java_learn.iteration;

import java.util.Iterator;

public class MyIterator implements Iterator<String> {
    private String []elem={"a","b","c","d","e"};
    private int size=elem.length;
    private int curror=-1;

    public boolean hasNext() {
        return curror+1<size;
    }

    public String next() {
        curror++;
        return elem[curror];
    }
    public void remove() {
    }

    public static void main(String[] args) {
        MyIterator myIterator=new MyIterator();
        while(myIterator.hasNext()){
            System.out.println(myIterator.next());
        }
        for(String str:myIterator.elem){
            System.out.println(str);
//            报错是因为实现foreach循环必须要实现Iterable接口
        }
    }
}
