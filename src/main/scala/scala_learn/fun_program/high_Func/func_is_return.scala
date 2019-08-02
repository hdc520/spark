package scala_learn.fun_program.high_Func

//高阶函数还可以将函数作为返回值与将函数赋值给变量是不一样的
object func_is_return {

  def getGreetingFunc(msg:String)={
    (name:String)=>println(msg+","+name)
  }

  def main(args:Array[String]):Unit={
    val greetFunc=getGreetingFunc("Hello")
    greetFunc("hdc")

  }
}
