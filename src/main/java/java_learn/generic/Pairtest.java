package java_learn.generic;

public class Pairtest {
    public static void main(String[] args) {
        String words[]=new String[]{"Adc","Tom","Sam","hello","zary","happy"};
        ArrayAlg arrayAlg=new ArrayAlg(words);
        Pair<String> pair=arrayAlg.Min_Max();
        System.out.println("min:"+pair.getFirst()+" max:"+pair.getSecond());
    }
}
