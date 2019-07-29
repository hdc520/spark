package scala_learn

object def_fun {
  def main(args: Array[String]): Unit = {
    println("------------无返回值函数---------")
    fun_void()
    println("-------------返回值函数----------")
    var num=fun_int(24)
    println(num)
    var flag=fun_str(24)
    println(flag)
    println("-------------递归函数-----------")
    var index_10=fun_DiGui(10)
    println("index_10："+index_10)
    println("-------------带名函数-----------")
    println("正常顺序：")
    fun_DaiName("hdc",24,"男")
    println("可以不按照函数中参数顺序来传递参数，而是按照参数名称来调用参数")
    fun_DaiName(age=24,name="hdc",sex = "男")
  }

  //无返回值函数
  def fun_void():Unit={
    println("hdc")
  }
  //返回值函数
  def fun_int(n:Int):Int={
    if(n>18)
      return n
    else
      return 18
  }

  def fun_str(n:Int):String={
    if(n>18)
      "成年人"
    else
      "未成年人"
  }

  //递归函数
  def fun_DiGui(n:Int):Int={
    if(n<=2)
      return 1
    else
      return fun_DiGui(n-1)+fun_DiGui(n-2)
  }

  //带名参数函数（函数定义与正常函数一样）
  def fun_DaiName(name:String,age:Int,sex:String): Unit ={
    println("姓名："+name+" 年龄："+age+" 性别："+sex)
  }

}
