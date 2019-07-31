package scala_learn.info_class

object anonymous_inner_class {
  class person(protected val name:String){
    def sayHello="Hello, i am "+name
  }

  def greeting(p:person{
    def sayHello:String{}
  }): Unit =
  {
    println(p.sayHello)
  }

  def main(args: Array[String]): Unit = {
    val p=new person("hdc"){
      override def sayHello: String = "hi a am "+name
    }
    greeting(p)
  }
}
