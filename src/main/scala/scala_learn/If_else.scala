package scala_learn

object If_else {
  def main(args: Array[String]): Unit = {
    val age=28
    if (age>28)
      print(age)
    else
      println("小于")
    val flag=if(age<29) 1 else "hdc"  //运用if和else来赋值
    println("flag:"+flag)

    //scala的块表达式,scala的语句终结符是分号,在块表达式中,最后一个语句的值就是块表达式的返回值.
    val str=if (age>18){var b=age+1;b+2} else "未成年"
    println("str："+str)
  }

}
