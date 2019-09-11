package java_exam.设计模式;

public class SingleObjectTest {
    public static void main(String[] args) {
        SingleObject object=SingleObject.getInstance();
        SingleObject object1=SingleObject.getInstance();
        object.showMessage();

    }
}
