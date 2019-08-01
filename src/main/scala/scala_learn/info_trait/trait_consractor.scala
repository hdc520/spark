package scala_learn.info_trait
//trait是没有接受参数的构造函数的


object trait_consractor {
  class person{
    println("--person--")
  }

  trait Logger{
    println("--Logger--")
  }

  trait ALogger extends Logger{
    println("--ALogger")
  }

  trait BLogger extends Logger{
    println("--BLogger--")
  }

  class student extends person with ALogger with BLogger{
    println("--student--")
  }

  def main(args: Array[String]): Unit = {
    println("父类构造器先行")
    println("trait的构造代码先（从左到右）")
    println("构造trait时会先构造父trait，若多个trait继承同一个trait则父trait只会构造一次")
    println("所有trait构造完后，子类的构造函数先执行")

    var s=new student
  }
}
