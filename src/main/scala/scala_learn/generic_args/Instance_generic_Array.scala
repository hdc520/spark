package scala_learn.generic_args

//泛型数组的使用，如果数组元素类型为泛型，则需要为类或者函数定义[T:Manifest]泛型类型
//这样能够实例化Array[]泛型数组

object Instance_generic_Array {

  class Meat(val name:String)
  class Vegetable(val name:String)

  def packageFood[T:Manifest](food:T*):Array[T]  ={
    val foodpackage=new Array[T](food.length)
    for(i<- 0 until(food.length))
      foodpackage(i)=food(i)
    foodpackage
  }

  def main(args: Array[String]): Unit = {
    val m1=new Meat("m1")
    val m2=new Meat("m2")
    val m3=new Meat("m3")
    val foodpackage=packageFood(m1,m2,m3)
    for(i<-0 until(foodpackage.length))
      println(foodpackage(i))
  }

}
