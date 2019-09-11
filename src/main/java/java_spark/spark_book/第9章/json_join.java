//package java_spark.spark_book.第9章;
//
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.RowFactory;
//import org.apache.spark.sql.SparkSession;
//import org.apache.spark.sql.types.DataType;
//import org.apache.spark.sql.types.DataTypes;
//import org.apache.spark.sql.types.StructField;
//import org.apache.spark.sql.types.StructType;
//import scala.Tuple2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class json_join {
//    public static void main(String[] args) {
//        SparkConf conf=new SparkConf().setAppName("read_Parquet").setMaster("local");
//        SparkSession sparkSession= SparkSession.builder().config(conf).getOrCreate();
//        //从文件中获取的数据
//        Dataset<Row> stu1DF=sparkSession.read().json("/home/hdc/data/stu1_json");
//        stu1DF.createOrReplaceTempView("stu1");
//        Dataset<Row> stuNameDF=sparkSession.sql("select name,score from stu1 where score>=80");
//        List<String> stuNameList=stuNameDF.javaRDD().map(new Function<Row, String>() {
//            public String call(Row row) throws Exception {
//                return row.getString(0);
//            }
//        }).collect();
//        //从另外一个文件中读取
//        Dataset<Row> stu2DF=sparkSession.read().json("/home/hdc/data/stu2_json");
//        stu2DF.createOrReplaceTempView("stu2");
//        String str_SQL="select name,age from stu2 where name in (";
//        for(int i=0;i<stuNameList.size();i++){
//            str_SQL+="'"+stuNameList.get(i)+"'";
//            if(i<stuNameList.size()-1){
//                str_SQL+=",";
//            }
//        }
//        str_SQL+=")";
//
//        Dataset<Row> stuInfo=sparkSession.sql(str_SQL);
//        JavaPairRDD<String,Tuple2<Integer,Integer>>stuRDD=
//        stu1DF.javaRDD().mapToPair(new PairFunction<Row, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(Row row) throws Exception {
//                return new Tuple2<String, Integer>(row.getString(1),row.getInt(0));
//            }
//        }).join(stu2DF.javaRDD().mapToPair(new PairFunction<Row, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(Row row) throws Exception {
//                return new Tuple2<String, Integer>
//                        (row.getString(1),row.getInt(0));
//            }
//        }));
//        JavaRDD<Row> stuRowRDD=stuRDD.map(new Function<Tuple2<String, Tuple2<Integer, Integer>>, Row>() {
//            @Override
//            public Row call(Tuple2<String, Tuple2<Integer, Integer>> s) throws Exception {
//                return RowFactory.create(s._1,s._2._1,s._2._2);
//            }
//        });
//        //创建元数据信息
//        List<StructField>strutFields=new ArrayList<>();
//        strutFields.add(DataTypes.createStructField("name",DataTypes.StringType,true));
//        strutFields.add(DataTypes.createStructField("score",DataTypes.IntegerType,true));
//        strutFields.add(DataTypes.createStructField("age",DataTypes.IntegerType,true));
//        StructType structType=DataTypes.createStructType(strutFields);
//        Dataset<Row> stuDF=sparkSession.createDataFrame(stuRowRDD,structType);
//        stu1DF.show();
////        System.out.println("-------------------------------------------------");
//        stu2DF.show();
////        System.out.println("-------------------------------------------------");
//        stuDF.show();
//        //将学生信息保存到文件
////        stuDF.write().json("/home/hdc/data/goodSTU");
//    }
//}
