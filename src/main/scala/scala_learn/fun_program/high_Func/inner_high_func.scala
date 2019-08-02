package scala_learn.fun_program.high_Func

//scala默认的内置高阶函数：map,foreach,filter,reduceLeft,sortwith,

object inner_high_func {
  def main(args:Array[String]):Unit={
    //map：对于传入的每个元素都进行映射，返回一个处理后的元素
    val A=Array(1,2,3,4,5)
    val B=A.map(2*_)  //_是指Array中每个元素都只使用一次
    val C=A.map((num:Int)=>{if (num%2==0) num else 1})
    println(B.mkString(","))
    println(C.mkString(","))

    //foreach:对传入的每个元素都处理，但是无返回值
    (1 to 9).map("*"*_).foreach(println(_))

    //reduceLeft:从左开始，进行reduce操作（对元素1和元素2进行处理，然后将结果与元素3处理
    // 再将结果与元素4处理，以此类推）
    println("reduceLeft:\n"+(1 to 9).reduceLeft(_*_)) //求9的阶乘
    println("reduceLeft:\n"+(1 to 9).reduceLeft(_+_))

    //sortwith:对元素进行两两比较，然后排序
    println("利用sortwith进行升序：\n"+Array(2,6,3,8,5,9,0).sortWith(_<_).mkString(","))
    println("利用sortwith进行降序：\n"+Array(2,6,3,8,5,9,0).sortWith(_>_).mkString(","))

    //flatMap
    val lines=List("Hello world","Hello hdc")
    val words=lines.flatMap(_.split(" "))
    println("words："+words)

    //统计list中的单词个数
    val words_num=lines.flatMap(_.split(" ")).map((_,1)).map(_._2).reduce(_+_)
    println("words_num= "+words_num)

  }
}
