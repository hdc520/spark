package scala_learn.info_trait



object achieve_method {

  trait logger{
    def log(message:String) {
      println("logger " + message)
    }
  }

  class person(val name:String)extends logger{
    def MakeFriends(p:person): Unit ={
      println(name+" MakeFriends "+p.name)
      log("log in pesron "+p.name)
    }
  }

  def main(args: Array[String]): Unit = {
    val p=new person("leo")
    val p1=new person("hdc")
    p.MakeFriends(p1)
  }

}
