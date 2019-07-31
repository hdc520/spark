package scala_learn.info_class


class method_of_apply(val name:String){
}

object method_of_apply {
  def apply(name:String): method_of_apply ={
    new method_of_apply(name)
  }

  def main(args: Array[String]): Unit = {
    val m=new method_of_apply("class")
    val m1=method_of_apply.apply("object")
    //若无视参数，其实m和m1是等价的
  }

}
