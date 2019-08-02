package scala_learn.fun_program.high_Func

//高阶函数可自动推断出参数类型，无需写明类型，而且对于只有一个参数的函数还可省略小括号()
//若仅有的一个参数在右侧的函数体内只使用过一次，则还可以将接受参数省略，并且将参数用_来代替

object infer_type_of_args {

  def greeting(func:(String)=>Unit,name:String): Unit ={
    func(name)
  }

  def triple(func:(Int)=>Int): Int ={
    func(3)
  }

  def main(args: Array[String]): Unit = {
    greeting((name:String)=>println("Hello "+name),"hdc")
    greeting(name=>println("Hello "+name),"lsg")
    greeting(name => println("Hello"+name),"hdc")
    println(triple(2*_))

  }
}
