package scala_learn.info_trait
//trait高级，为实例混入trait，调用最新的trait

object example_in_trait {

  trait logged{
    def log(msg:String): Unit ={
    }
  }

  trait Mylogger extends logged{
    override def log(msg: String): Unit = {
      println(msg)
    }
  }

  class person(val name:String)extends logged{
    def syaHello: Unit ={
      println("person："+name)
      log("person hdc")
    }
  }

  def main(args: Array[String]): Unit = {
    val p1=new person("leo")
    p1.syaHello
    val p2=new person("lsg") with Mylogger  //喜新厌旧
    p2.syaHello
  }

}
