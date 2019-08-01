package scala_learn.info_trait
//trait可以混合使用具体和抽象方法，将抽象方法放到继承trait的类中去实现

object mix_method_trait {

  trait Valid{
    def getName:String
    def valid:Boolean={
      getName=="leo"
    }
  }

  class person(val name:String)extends Valid{
    println(valid)
    override def getName: String = name
  }

  def main(args: Array[String]): Unit = {
    val p=new person("leo")
    p.valid
    p.getName
  }
}
