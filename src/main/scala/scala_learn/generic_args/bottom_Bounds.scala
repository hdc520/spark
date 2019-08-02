package scala_learn.generic_args

//泛型类型是某个类的子类T(T为父类)>:T_clid(字类)  下边界
object bottom_Bounds {
  class father(val name:String)
  class child(name:String)extends father(name){
    def getLostIdCard[T>:child](p:T): Unit ={
      if(p.getClass==classOf[child])
        println("child")
      else if(p.getClass==classOf[father])
        println("father")
      else
        println("NO")
    }
  }

  def main(args: Array[String]): Unit = {
    val f=new father("ZhangSan")
    val c=new child("LiSi")
    c.getLostIdCard(f)
    c.getLostIdCard(c)
  }
}
