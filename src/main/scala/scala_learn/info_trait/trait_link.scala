package scala_learn.info_trait
//继承多个trait后，依次调用trait中的同一个方法，只需让多个trait的同一个方法中在最后都执行super方法
//按照从右到左的方法执行


object trait_link {

  trait Handle{
    def handle(data:String){}
  }

  trait DataValidHandle extends Handle{
     override def handle(data: String): Unit = {
      println("DataValidHandle："+data)
      super.handle(data)
    }
  }

  trait signatureValidHandler extends Handle{
    override def handle(data: String): Unit = {
      println("signatureValidHandler："+data)
      super.handle(data)
    }
  }

  class person(val name:String)extends signatureValidHandler with DataValidHandle{
    def sayHello(): Unit ={
      println("hi "+name)
      handle(name)
    }
  }

  def main(args: Array[String]): Unit = {
    val p=new person("hdc")
    p.sayHello()
  }

}
