package scala_learn.fun_program

object closure_func {

  def main(args:Array[String]):Unit={
    //val multi=(i:Int)=>i*factor //此时报错
    //解析：multi有两个变量i和factor，此时函数multi被调用时，i被赋予一个新的值，但是factor无定义
    //若接下来定义一个factor
    var factor=3
    for(i<- 0 to 4){
    val muti=(i:Int)=>i*factor
    println(muti(i))}
    //此时这样定义的函数变量muti成为一个闭包，因为muti引用类函数外面定义的变量。
    //定义这个函数的过程是将这个自由变量捕获而构成一个封闭的函数。
  }

}
