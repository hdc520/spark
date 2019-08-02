package scala_learn.fun_program.high_Func

object args_is_func {

  def greeting(func:(String)=>Unit,name:String): Unit ={
    func(name)
  }

  def main(args: Array[String]): Unit = {
    val sayHelloFunc=(name:String)=>println("Hello "+name)
    greeting(sayHelloFunc,"hdc")

    val A=Array(1,2,3,4).map((num:Int)=>num*num)
    println(A.mkString(","))
    println(A.mkString.getClass.getSimpleName)
  }

}
