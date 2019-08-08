package scala_learn

import scala.collection.mutable.ArrayBuffer


object array_operation {
  def main(args: Array[String]): Unit = {
    //创建数组
    println("-----创建不可变数组使用关键字Array-----")
    val a1=new Array[Int](4)
    val a2=new Array[String](3)

    //创建数组并初始化
    val a3=Array("hdc","lsg")
    println("a3(0)："+a3(0))

    //以上数组都是不可变数组使用关键字array
    println("-----可变数组使用关键字ArrayBuffer-----")

    val b1=ArrayBuffer[Int]()
//    val b11=new ArrayBuffer("hdc","rer")
    b1+=1
    println("b1增加元素按单个:"+b1)
    b1++=Array(2,3,4,5) //数组加
    println("b1增加元素按数组:"+b1)
    //从尾部删除元素
    b1.trimEnd(2) //删除2个元素.
    println("b1尾部删除2个元素:"+b1)
    //在指定位置插入元素
    val index=1
    val number=4
    b1.insert(index,number) //在index处处插入元素number
    println("b1在指定位置插入1个元素:"+b1)
    b1.insert(index,5,6)
    println("b1在指定位置插入5,6元素:"+b1) //在index处处插入两个元素5,6

    val b2=ArrayBuffer(1,2,"hdc") //数组里面元素类型可不同
    b2+=3
    println("b2:"+b2)
    println("--------数组遍历--------")
    for(i<- 0 until(b1.length,1))
      print(b1(i)+" ")
    println()
    //转置数组
    for(i<-(0 until b1.length).reverse)
      print(b1(i)+" ")
    println()
    //数组的计算操作
    val num_arr=Array(1,3,5,4,7,6)
    val sum=num_arr.sum
    val min=num_arr.min
    val max=num_arr.max
    val sort_num=scala.util.Sorting.quickSort(num_arr);
    println("sum："+sum+" min："+min+" max："+max+" sort_num："+sort_num)

    val arr_to_str=num_arr.mkString("")
    println("num_arr.mkString(\"\"):"+arr_to_str)
    val str=arr_to_str.toString
    println("toString："+str)
  }
}
