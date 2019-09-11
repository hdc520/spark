package scala_learn.generic_args

//在java中，例如professional是master的子类，card[professional]不是card[master]的子类
//但是在scala中可以通过协变与逆变进行相互转化

object 协变_逆变 {
  class Master
  class Professional extends Master

  //协变标志[+T]
  class Card1[+T](val name:String){
  }

  //逆变标志[-T]
  class Card2[-T](val name:String){}

  def EnterMeet1(card1: Card1[Master]): Unit ={
    println("协变转换Card1[Master]为Card2[Professional]的父类")
  }

  def EnterMeet2(card2: Card2[Professional]): Unit ={
    println("Card2[Professional]经过逆变变成Card1[Master]的父类")
  }

  def main(args: Array[String]): Unit = {
    val leo1=new Card1[Master]("leo1")
    val jack1=new Card1[Professional]("jack1")

    EnterMeet1(jack1)
    EnterMeet1(leo1)

    val leo2=new Card2[Master]("leo2")
    val jack2=new Card2[Professional]("jack2")
    EnterMeet2(jack2)
    EnterMeet2(leo2)
  }
}
