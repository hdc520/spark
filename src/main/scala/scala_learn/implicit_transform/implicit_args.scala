package scala_learn.implicit_transform

//在函数和方法中定义一个用implicit修饰的参数

object implicit_args {

  class SignPen{
    def write(content:String): Unit ={
      println(content)
    }
  }

  def sign(name:String)(implicit signPen: SignPen): Unit ={
    signPen.write(name+"come on")
  }

  def main(args: Array[String]): Unit = {
    implicit val signPen=new SignPen()
    signPen.write("hdc")
  }
}
