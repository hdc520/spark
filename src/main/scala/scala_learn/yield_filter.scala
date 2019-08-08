package scala_learn

object yield_filter {
  def main(args: Array[String]): Unit = {

    //yield运行到yield处就结束,若yield函数一次也没有执行过，则会先执行一次yield。
    val num_arr=Array(1,2,3,4)
    val nums=for(i<-num_arr) yield i*i
    println("nums："+nums.getClass.getSimpleName)
    for(i<-nums){
      print(i+" ")
    }
    println()
    //利用if守卫来寻找自己的操作
    val nums1=for(i<-num_arr if i%2==0) yield i*i

    //使用函数式编程转换数组
    val num_yield=num_arr.filter(_%2==0).map(_*2)
    println("num_yield："+num_yield)

  }
}
