package scala_learn.info_class

object Enmu_info extends Enumeration {
  val spring,summer,autumn,winter=Value("季节")
  val SPRING=Value("春天")
  val SUMMER=Value("夏天")
  val AUTUMN=Value("秋天")
  val WINTER=Value("冬天")

  def main(args: Array[String]): Unit = {
    println(Enmu_info.spring)
    println(Enmu_info.SPRING.toString)
  }
}
