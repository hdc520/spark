package scala_learn

object map_Tuple {
  def main(args: Array[String]): Unit = {
    //创建不可变map
    println("---------创建不可变map--------------------------------------")
    val names_ages=Map("hdc"->23,"lsg"->25)
    val names_ages1=Map(("hdc",23),("lsg",25))
    //names_ages("hdc")=24,此种写法错误，只能对map进行无修改操作.
    println("names_ages："+names_ages)
    println("names_ages1"+names_ages1)
    println("names_ages(\"hdc\")："+names_ages("hdc")+"  names_ages(\"lsg\")："+names_ages1("lsg"))

    //创建可变map
    println("------------------------------创建可变map-------------------")
    val Modify_names_ages=scala.collection.mutable.Map("Tom"->26,"Sam"->27)
    println("Modify_names_ages："+Modify_names_ages)

    //修改value
    Modify_names_ages("Tom")=22
    println("Modify_names_ages(\"Tom\")："+Modify_names_ages("Tom"))

    //增加一个键值对
    Modify_names_ages+=("Tony"->30)
    println("Modify_names_ages："+Modify_names_ages)

    //删除一个键值对
    Modify_names_ages-="Tony"
    println("Modify_names_ages："+Modify_names_ages)

    //对于可变与不可变的map来说通过.getOrElse("",返回值)来判断是否存在其中返回值可以为任意类型
    val flag_names_ages=names_ages.getOrElse("hd3","no")
    println("flag_names_ages："+flag_names_ages)//此时不存在返回no

    val flag_modify_names_ages=Modify_names_ages.getOrElse("Tom","yes")
    println("flag_modify_names_ages："+flag_modify_names_ages)

    println("-----------map的遍历--------------------------------------")
    //遍历键值对
    println("-----------遍历键值对----------------")
    for((key,value)<-names_ages){
      println("key："+key+" value："+value)
    }

    //遍历key
    println("--遍历key--")
    for(key<-names_ages.keySet){
      println("key："+key)
    }

    //遍历value
    println("--遍历value--")
    for(value<-names_ages.values){
      println("value："+value)
    }

    //利用yield交换键值对
    val ages_names=for((key,value)<-names_ages) yield (value,key)
    println("交换键值对："+ages_names)

    //map的排序与插入(按键)
    println("-----不可变map的排序-----")
    val sort_names_ages=scala.collection.immutable.SortedMap("zhangsan"->30,"lisi"->40)
    println("sort_names_ages："+sort_names_ages)

    println("-----可变map按插入顺序返回-----")
    val linkedHashMap=scala.collection.mutable.LinkedHashMap("wangwu"->50,"hdc"->23)
    linkedHashMap+=("lsg"->25)
    println("linkedHashMap："+linkedHashMap)

  }
}
