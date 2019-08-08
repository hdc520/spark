package scala_learn.info_trait

object abstract_field {

  trait sayHello{
    val message1:String="hi"
    val message2:String
    val message3:String="hey"
    def sayHello(name:String): Unit ={
      println(message1+" "+name)
    }
  }

  class person(val name:String)extends sayHello {
    override val message1:String="Hello" //实体字段重写必须使用override
    override val message2: String="heihei"
    def makefriends(p:person): Unit ={
      sayHello(p.name)
      println("message2："+message2)
      println("message3："+message3)
    }
  }

  def main(args: Array[String]): Unit = {
    val p1=new person("hdc")
    val p2=new person("lsg")
    p1.sayHello("hdc")
    p2.makefriends(p1)
  }

}
