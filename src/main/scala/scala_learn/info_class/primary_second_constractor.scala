package scala_learn.info_class

//scala中只有主构造器才能构造父类的构造器
object primary_second_constractor {
  class person(var name:String,var age:Int)
  {
  }

  class student(name:String,age:Int,val score:Double)extends person(name,age){
    def this(name:String){
      this(name,23,99)
    }
    def this(age:Int){
      this("hdc",age,100)
    }
    def this(score:Double){
      this("hdc",38,score)
    }
  }

  def main(args: Array[String]): Unit = {
    val p=new person("leo",30)
    val s1=new student("lsg")
    val s2=new student(24)
    val s3=new student(99.9)
    println("s1："+s1.name+" "+s1.age+" "+s1.score)
    println("s2："+s2.name+" "+s2.age+" "+s2.score)
    println("s3："+s3.name+" "+s3.age+" "+s3.score)
  }


}
