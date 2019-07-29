package scala_learn

object while_for {
  def main(args: Array[String]): Unit = {
    //while循环与java类似
    var n=10;
    while(n>0){
      println("n："+n)
      n-=1
    }
    println("-------------------for循环--------------------")
    var num=10
    for( i <- 1 to num){
      println("i："+i)
    }

    //高级for循环
    println("-------------------多重循环--------------------")
    for(i<- 1 to 3;j<-3 to 6){    //多重循环
      println("i："+i+" j："+j)
    }

    //for循环之if守卫
    println("-------------------for循环之if守卫--------------------")
    for(i<- 1 to 10 if i%2==0){
      println("i："+i)
    }


  }
}
