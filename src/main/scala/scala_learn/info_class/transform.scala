package scala_learn.info_class
//如何将父类类型转换成子类类型变量
//先用isInstanceof判断对象是否是指定类的对象，如果是则用asInstanceof转换

object transform {
  class person{}
  class student extends  person{}

  def main(args: Array[String]): Unit = {
    var s:student=null  //因为实例化为空所以不是任何类的对象。
    val p:person=new student
    println("---------isInstanceof和asInstanceof-------")
    if(s.isInstanceOf[student])
      println("yes")
    if(p.isInstanceOf[student]){
      println("是student对象")
      s=p.asInstanceOf[student]
      println(s.isInstanceOf[student])
    }
    if(p.isInstanceOf[person])
      println("是person对象")

    println("---------getClass和classOf-------")
    println(p.getClass==classOf[person])
    println(p.getClass==classOf[student])

  }
}
