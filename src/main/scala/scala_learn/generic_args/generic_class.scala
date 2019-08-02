package scala_learn.generic_args
//泛型的表示：[T]

object generic_class {

  //泛型类
  class student[T](val localId:T){
    def getSchoolId(Id:T): String ={
      "s-"+Id+"-"+localId
    }
  }

  //泛型函数

  def getCard[T](content:T): Unit={
    if(content.isInstanceOf[Int])
      println("card:Int "+content)
    else if (content.isInstanceOf[String])
      println("card:String "+content)
    else
      println("card:"+content)
  }

  def main(args:Array[String]): Unit ={
    //泛型类
    val leo=new student[Int](1001)
    println(leo.getSchoolId(110))

    val hdc=new student[String]("0556")
    println(hdc.getSchoolId("101"))

    val lsg=new student("henan")
    println(lsg.getSchoolId("1100"))  //自动识别参数类型

    //泛型参数
    getCard[Int](1411204020)
    getCard("S201861434")
  }

}
