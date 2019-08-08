package scala_learn.collection_operation

import scala.collection.mutable

//set代表一个都没有重复元素的集合

object Set_operation {
  def main(args: Array[String]): Unit = {
    //set
    var s=Set(1,2,3)
    println("初始Set："+s)
    s+=4
    s++=Set(5,6,7)
    println("添加元素后"+s)
    //linkedHashSet
    val hashset=new mutable.LinkedHashSet[Int]()
    println("初始linkedHashSet："+hashset)
    hashset+=1
    hashset+=2
    hashset+=3
    hashset+=3
    println("添加元素后linkedHashSet："+hashset)
    //sortedSet
    val sortedSet=mutable.SortedSet("orange","apple","banana")
    println("初始sortedSet："+sortedSet)
    sortedSet+="pear"
    println("添加元素后sortedSet："+sortedSet)


  }
}
