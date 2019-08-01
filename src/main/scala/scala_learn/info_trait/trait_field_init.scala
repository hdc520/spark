package scala_learn.info_trait
//若要让trait对为赋值的field进行初始化，则需要提前定义或者在子类中定义成lazy


object trait_field_init {
  trait syaHello{
    val msg:String
    println(msg.toString)
  }

  class person extends syaHello{
    override lazy val msg: String = "init"
  }

  def main(args: Array[String]): Unit = {
    val p=new person
  }

}
