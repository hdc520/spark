package scala_learn.info_class

//调用构造器必须放在object的里面
//主构造器是类名之后(参数列表这里为空)+{非field和method}
object class_constractor {
  class student {
    println("主构造器开始:")
    private var name=""
    private var age=0
    //辅助构造器1
    def this(name:String){
      this()
      this.name=name
      println("name："+this.name)
    }
    //辅助构造器3
    def this(age:Int){
      this()
      this.age=age
      println("age："+this.age)
    }
    //辅助构造器2
    def this(name:String,age:Int){
      this(name)
      this.age=age
      println("name："+this.name+"age："+this.age)
    }
    println("主构造器结束:")
  }
  def main(args: Array[String]): Unit = {
    println("主构造器实例：")
    val s=new student
    println("辅助构造器1：")
    val s1=new student("hdc")
    println("辅助构造器2：")
    val s2=new student(23)
    println("辅助构造器3：")
    val s3=new student("lsg",25)


  }
}
