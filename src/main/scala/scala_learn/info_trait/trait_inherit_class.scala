package scala_learn.info_trait

//trait继承class，此时这个class会成为所有继承该trait的类的父类
object trait_inherit_class {

  class MyUtil{
    def printMsg(msg:String): Unit ={
      println("MyUtil中:"+msg)
    }
  }

  trait logger extends MyUtil{
    def Log(msg:String):Unit={
      printMsg("logger中的："+msg)
    }
  }

  class person(val name:String) extends logger{
    def sayHello: Unit ={
      Log("Log in person "+name)
      printMsg("printMsg in person "+name)
    }
  }

  def main(args: Array[String]): Unit = {
    val p=new person("hdc")
    p.sayHello
  }
}
