package java_exam.设计模式;

public class SingleObject {
    private static SingleObject instance=new SingleObject();
    private SingleObject(){}
    public static SingleObject getInstance(){
        return instance;
    }
    public void showMessage(){
        System.out.println("hello world");
    }
}
