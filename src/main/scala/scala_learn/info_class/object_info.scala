package scala_learn.info_class
/*
 object只有无参构造器，其构造器只会在第一次被调用执行，之后再也不会被调用。
 伴生对象和伴生类
 calss A    object A
 其中前者是后者的伴生类，后者是前者的伴生对象
 伴生类和伴生对象必须存放在同一个.scala文件中
 伴生类和伴生对象可以相互访问private field
*/
class object_info(val name:String){
  def info(): Unit ={
    print("伴生类的标志为："+name+"\n伴生对象的标志为："+object_info.c_info)
  }
}

object object_info {
  private val c_info="object"
  def getO_info:String=c_info
  def main(args: Array[String]): Unit = {
    val c=new object_info("class")
    c.info()
  }


}
