package scala_learn.match_operation



object match_type {
  def matchExceptionType(E:Exception): Unit ={
    E match {
      case e1:java.io.IOException=>println("IO错误")
      case e2:java.io.FileNotFoundException=>println("文件尚未发现错误")
      case _:Exception=>println("反正就是错误")
    }
  }

  def main(args: Array[String]): Unit = {
    matchExceptionType(new java.io.IOException("IO"))
  }
}