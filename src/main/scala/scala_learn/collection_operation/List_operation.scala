package scala_learn.collection_operation

import scala.collection.mutable

object List_operation {
  def main(args:Array[String]):Unit={
    //不可变list
    var list=List(1,2,3,4)
    println("初始list:"+"长度："+list.length+" 值："+list)
    println("取list集合的第一个元素："+list.head)
    println("将除list第一个元素外组成新的集合："+list.tail)
    list=0::list  //此时将0和最初的list合并，创建一个新的list付给list，
    println("合并后的list:"+"长度："+list.length+" 值："+list)
    DiGui(list)
    val linkList=scala.collection.mutable.LinkedList(1,2,3,4)
    println("linkList："+linkList)
    println("linkList.elem同List的head："+linkList.elem)
    println("linkList.next同List的tail"+linkList.next)
    //增加元素
    linkList.append(mutable.LinkedList(5))
    println("增加元素后linkList："+linkList)
    //减少元素未定
    println(second_LinkList(linkList).next)

  }

  //递归为list每个元素增加标识
  def DiGui(list:List[Int]):Unit={
    if(list!=Nil){
      println(list.head+" num")
      DiGui(list.tail)
    }
  }

  //将LinkedList每个元素×2
  def second_LinkList(linkList:mutable.LinkedList[Int]): mutable.LinkedList[Int] ={
    var link_temp=linkList
    while(link_temp!=Nil){
      link_temp.elem=link_temp.elem
      link_temp=link_temp.next
    }
    return linkList
  }
}
