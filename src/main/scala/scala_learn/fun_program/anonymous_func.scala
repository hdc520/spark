package scala_learn.fun_program

object anonymous_func {

  def main(args:Array[String]):Unit={
    val sayHelloFunc=(name:String)=>println("Hello "+name)
    sayHelloFunc("hdc")
  }
}
