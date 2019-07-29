package scala_learn

object in_out {
  def main(args: Array[String]): Unit = {
    //判断是否未成年
    val name=scala.io.StdIn.readLine("请输入您的姓名：")
    print("请输入您的年龄：")
    val age=scala.io.StdIn.readInt()
    if(age>=18)
      println("成年人")
    else
      println("未成年")
  }
}
