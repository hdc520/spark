package scala_learn.info_class

object class_inherit {
  class person{
    val name="hdc"
    private val Class="一班"
    def getName=name
  }
  class student extends person{
    override  val name="lsg"  //重写字段只能用val修饰而不能用var，并且private字段不能被重写(不知道对不对)
    private var score="A"
    def getScore=score
    override def getName: String = super.getName+" hi"
  }
  def main(args: Array[String]): Unit = {
    val p=new person
    println(p.getName)
    val s=new student
    println(s.getName)

  }
}
