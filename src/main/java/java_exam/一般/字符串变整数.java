package java_exam.一般;

import java.util.Scanner;

public class 字符串变整数 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int number=0;
        String numStr=in.nextLine();
        if(numStr.equals("")) {
            System.out.println("空串");
            System.exit(0);
        }

        if(numStr.charAt(0)=='-'||numStr.charAt(0)=='+'){
            for(int i=1;i<numStr.length();i++){
                if(numStr.charAt(i)>'9'||numStr.charAt(i)<'0'){
                    System.out.print("字符串格式不对");
                    System.exit(0);
                }
            }
            for(int i=1;i<numStr.length();i++){
//                System.out.println(numStr.charAt(i)-'0');
                number=10*number+(numStr.charAt(i)-'0');
            }
            if(numStr.charAt(0)=='-')
                System.out.println(0-number);
            if(numStr.charAt(0)=='+')
                System.out.println(number);
        }
        else {
            for(int i=0;i<numStr.length();i++){
                if(numStr.charAt(i)>'9'||numStr.charAt(i)<'0'){
                    System.out.print("字符串格式不对");
                    System.exit(0);
                }
            }
            for(int i=0;i<numStr.length();i++){
                number=10*number+(numStr.charAt(i)-'0');
            }
            System.out.println(number);
        }
    }
}
