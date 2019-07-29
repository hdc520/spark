package scala_learn

object zip_operation {
  def main(args: Array[String]): Unit = {
    val names=Array("lsg","hdc")
    val ages=Array(25,23)
    val names_ages=names.zip(ages)
    println("names_ages："+names_ages.mkString)
  }
}
