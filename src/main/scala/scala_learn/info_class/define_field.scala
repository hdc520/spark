package scala_learn.info_class

//field用法
  class HelloWorld{
    val name="hdc"
    var sex="male"
    private var age=24
    private [this] var id=1001
    def sayHello(): Unit ={
      println("Hello,"+id)
    }
    def getName(): String ={
      name
    }
    def getAge=age
  }
//自定义getter和setter方法
  class student{
    private var sex:String=_   //初始化为null
    val age:Int=23    //val变量初始化必须要赋值
    private var myName="hdc"
    def name:String="my name is "+myName
    def name_=(newName:String): Unit ={
      println("my name is "+newName)
    }
  }

object define_field {
  def main(args: Array[String]): Unit = {

    val helloWorld1=new HelloWorld
    val helloWorld2=new HelloWorld()
    println("---------helloWorld1内容---------")
    println("helloWorld1.getAge："+helloWorld1.getAge)
    println("helloWorld1.sayHello()："+helloWorld1.sayHello())
    println("---------helloWorld2内容---------")
    println("helloWorld2.getName："+helloWorld2.getName())
    //helloWorld2.name="lsg"错误，因为name是val的变量
    println(helloWorld2.sex)
    helloWorld2.sex="boy"
    println(helloWorld2.sex)
    println("\n---------------------自定义getter和setter方法---------------------")
    val s=new student
    println(s.name)
    s.name="lsg"
    println(s.name)



  }
}
