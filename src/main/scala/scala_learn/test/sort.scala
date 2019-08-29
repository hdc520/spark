package scala_learn.test
object sort {
  def main(args: Array[String]): Unit = {
    print("选择排序(升序)：")
    println(select_sort(num = Array(3,2,1)).mkString("  "))

    print("冒泡排序(降序)：")
    println(bubbling_sort(num = Array(3,1,1,1,1,5,3)).mkString("  "))

  }
  //选择排序从小到大
  def select_sort(num:Array[Int]):Array[Int]={
    for(i<-0 until num.length-1){
      for(j<- i+1  until num.length){
        if(num(i)>num(j)){
          var temp=num(i)
          num(i)=num(j)
          num(j)=temp
        }
      }
    }
    return num;
  }
  //冒泡排序从大到小
  def bubbling_sort(num:Array[Int]):Array[Int]={
    var flag=1
    var count=0
    while(flag==1){
       flag=0
        for(j<-0 until (num.length-1,1)){
          if(num(j)<num(j+1)){
            var temp=num(j)
            num(j)=num(j+1)
            num(j+1)=temp
            flag=1
          }

        }
    }
    return num
  }
}
