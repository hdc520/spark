package scala_learn.info_class

//若父类中字段是protected修饰则不需要利用super，就可以直接访问父类字段
//但是只能在当前子类对象中访问父类中的字段与方法

object class_protect {
  class person{
    protected var name:String="leo"
  }
  class student extends person{
    println(name)
    def this(name:String){
      this()
      this.name=name
    }
    def makefriends(s:student): Unit ={
      println(name+" "+s.name)
    }
  }

  def main(args: Array[String]): Unit = {
    val s1=new student("hdc")
    val s2=new student
    s1.makefriends(s2)
  }

}
