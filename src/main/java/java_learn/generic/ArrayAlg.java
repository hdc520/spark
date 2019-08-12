package java_learn.generic;

public class ArrayAlg {
    private String str[];
    public ArrayAlg(String str[]){
        this.str=str;
    }
    public Pair<String>Min_Max(){
        if( str== null||str.length==0)
            return null;
        String min=str[0];
        String max=str[1];
        for(int i=0;i<str.length;i++){
            if(min.compareTo(str[i])>0)
                min=str[i];
            if(max.compareTo(str[i])<0)
                max=str[i];
        }
        return new Pair<String>(min,max);
    }
}
