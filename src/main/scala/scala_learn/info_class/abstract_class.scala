package scala_learn.info_class
//抽象类是因为暂时无法实现，等其子类来覆盖，重写实现自己的不同方法来实现
//类中只要有一个抽象方法就需要定义为抽象类，抽象类不可被实例化
//子类覆盖抽象类方法时不需要使用overrode关键字
object abstract_class {
  abstract class person(val name:String){
    def sayHello:Unit //抽象方法
    val age:Int =90
    val sex:String  //抽象字段，没有给出初始值
  }

  class student(name:String)extends person(name){
    val sex="male"
    def sayHello: Unit = {
      println("hello "+age+" 岁 "+name+" 性别 "+sex)
    }
  }

  def main(args: Array[String]): Unit = {
    val s=new student("hdc")
    s.sayHello
  }

}
