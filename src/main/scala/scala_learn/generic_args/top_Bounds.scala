package scala_learn.generic_args

//泛型类型是某个类的子类T(T为子类)<:T_father(父类)  上边界
object top_Bounds {

  class person(val name:String){
    def sayHello: Unit ={
      println("Hello,"+name)
    }
    def makeFriends(p:person): Unit ={
      sayHello
      p.sayHello
    }
  }

  class student(name:String)extends person(name){
  }

  class party[T<:person](p1:T,p2:T){
    def play: Unit ={
      p1.makeFriends(p2)
    }
  }

  def main(args: Array[String]): Unit = {
    val p1=new student("hdc")
    val p2=new student("lsg")
    val p=new party(p1,p2)
    p.play
  }
}
