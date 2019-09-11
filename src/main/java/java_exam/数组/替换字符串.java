package java_exam.数组;

public class 替换字符串 {
    public static void main(String[] args) {
        String string="we are happy.";
        if(string.equals(""))
            System.exit(0);
        int SpaceCount=0;
        char str[]=new char[20];
        for(int i=0;i<string.length();i++){
            str[i]=string.charAt(i);
        }
        for(int i=0;i<string.length();i++){
            System.out.print(str[i]);
        }
        for(int i=0;i<string.length();i++){
            if(str[i]==' ')
                SpaceCount++;
        }
        System.out.println();
        int end=string.length()+SpaceCount*2;
        int length=end;
        int start=string.length();
        while(end!=start){
            if(str[start]!=' '){
                str[end]=str[start];
                start--;
                end--;
            }
            else{
                str[end--]='0';
                str[end--]='2';
                str[end--]='%';
                start--;
            }
        }
        for(int i=0;i<length;i++){
            System.out.print(str[i]);
        }
    }
}
