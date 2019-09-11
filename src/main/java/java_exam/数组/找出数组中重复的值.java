package java_exam.数组;

import java.util.ArrayList;
import java.util.List;

public class 找出数组中重复的值 {
    public static void main(String []args){
      int a[]={2,3,1,0,2,5,3},flag=1;
      List<Integer> l=new ArrayList();
      for(int i=0;i<a.length;i++){
          int tmp=a[i];
          for(int j=i+1;j<a.length;j++){
              if(tmp==a[j]){
//                  System.out.println("tmp="+tmp);
                  for(int x:l){
//                      System.out.println("x="+x);
                      if(tmp==x){

                          flag=0;
                          break;
                      }
                  }
                  if(flag!=0)
                      l.add(tmp);
                  break;
              }
          }
//          System.out.println("flag="+flag);
      }
      for(int i:l)
          System.out.print(i+" ");
    }

}
