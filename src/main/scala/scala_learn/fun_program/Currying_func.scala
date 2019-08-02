package scala_learn.fun_program

//currying(柯里化)是把接受多个参数的函数变换成接受一个单一参数（最初函数的第一个参数）的函数并且返回接受余下参数
//且返回结果的新函数的技术
//从数学角度来说，实际上是消元求解的过程

object Currying_func {
  //正常函数
  def sum1(a:Int,b:Int)=a+b
  def sum2(a:Int)=(b:Int)=>a+b
  def sum3(a:Int)(b:Int)=a+b

  def main(args: Array[String]): Unit = {
    println("sum1："+sum1(2,5))
    println("sum2："+sum2(2)(5))
    println("sum3："+sum3(2)(5))
  }
}
