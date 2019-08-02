package scala_learn.fun_program
//scala中函数可以独立定义和存在，而且还可以直接将函数作为值付给变量
//将函数赋值给变量，必须在函数后面加上空格和下划线_

object define {
  def sayHello(name:String): Unit ={
    println("Hello "+name)
  }

  def main(args:Array[String]):Unit={
    val sayHelloFunc=sayHello _     //将sayHello函数赋值给变量，在我认为其实是换个名字。
    sayHelloFunc("hdc")
  }
}
