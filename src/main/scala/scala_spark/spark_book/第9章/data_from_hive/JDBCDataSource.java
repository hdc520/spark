//package scala_spark.spark_book.第9章.data_from_hive;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//import org.apache.spark.sql.sql.Dataset;
//import org.apache.spark.sql.sql.Row;
//import org.apache.spark.sql.sql.RowFactory;
//import org.apache.spark.sql.sql.SparkSession;
//import org.apache.spark.sql.sql.types.*;
//import scala.Tuple2;
//import java.sql.sql.Connection;
//import java.sql.sql.DriverManager;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

//public class JDBCDataSource {
//    public static void main(String[] args) {
//        SparkConf conf=new SparkConf().setAppName("HiveDataScore").setMaster("local");
//        SparkSession sparkSession= SparkSession.builder().config(conf).getOrCreate();
//        Map<String,String>options=new HashMap<>();
//        //读取第一个表stu_info的信息
//        options.put("url","jdbc:mysql://localhost:3306/testDB");
//        options.put("dbtable","stu_info");
//        Dataset<Row> stuInfoDF=sparkSession.read().format("jdbc").options(options).load();
////        stuInfoDF.show();
//
//        //读取第二个表stu_score的信息
//        options.clear();
//        options.put("url","jdbc:mysql://localhost:3306/testDB");
//        options.put("dbtable","stu_score");
//        Dataset<Row> stuScoreDF=sparkSession.read().format("jdbc").options(options).load();
////        stuScoreDF.show();
//        //将两个DataSet转换为RDD
//        System.out.println(stuInfoDF.javaRDD().getClass().getSimpleName());
//        JavaPairRDD<String,Tuple2<Integer,Integer>> stuDF=
//                stuInfoDF.javaRDD().mapToPair(new PairFunction<Row, String, Integer>() {
//            public Tuple2<String, Integer> call(Row row) throws Exception {
//                return new Tuple2<String, Integer>(String.valueOf(row.get(0)),
//                        Integer.valueOf(String.valueOf(row.get(1))));
//            }
//        }).join(stuScoreDF.javaRDD().mapToPair(new PairFunction<Row, String, Integer>() {
//                    @Override
//                    public Tuple2<String, Integer> call(Row row) throws Exception {
//                        return new Tuple2<String, Integer>(String.valueOf(row.get(0)),
//                                Integer.valueOf(String.valueOf(row.get(1))));
//                    }
//                }));
//        stuDF.foreach(new VoidFunction<Tuple2<String, Tuple2<Integer, Integer>>>() {
//            @Override
//            public void call(Tuple2<String, Tuple2<Integer, Integer>> s) throws Exception {
//                System.out.println("name:"+s._1+"age:"+s._2._1+"score"+s._2._2);
//            }
//        });
//
//        //将javaRDD转化成ROWRDD
//        JavaRDD<Row> stuRowRDD=stuDF.map(new Function<Tuple2<String, Tuple2<Integer, Integer>>, Row>() {
//            @Override
//            public Row call(Tuple2<String, Tuple2<Integer, Integer>> s) throws Exception {
//                return RowFactory.create(s._1+s._2._1+s._2._2);
//            }
//        });
////        JavaRDD<Row>filterRDD=stuRowRDD.filter(new Function<Row, Boolean>() {
////            @Override
////            public Boolean call(Row row) throws Exception {
////                return row.getInt(1)>80;
////            }
////        });
//        //stuRowRDD转换为Dataset
//        List<StructField> structFieldList=new ArrayList<>();
//        structFieldList.add(DataTypes.createStructField("name", DataTypes.StringType,true));
//        structFieldList.add(DataTypes.createStructField("age", DataTypes.IntegerType,true));
//        structFieldList.add(DataTypes.createStructField("score", DataTypes.IntegerType,true));
//        StructType structType=DataTypes.createStructType(structFieldList);
//        Dataset<Row>stuRDF=sparkSession.createDataFrame(stuRowRDD,structType);
//        stuRDF.show();
//        //将stuRDF数据保存到数据库表中
//       stuRDF.javaRDD().foreach(new VoidFunction<Row>() {
//           @Override
//           public void call(Row row) throws Exception {
//               String sql.sql="insert into good_student_table values ("+
//                       "'"+String.valueOf(row.getString(0))+"',"
//                       +Integer.valueOf(String.valueOf(row.get(1)))+"'"
//                       +Integer.valueOf(String.valueOf(row.get(2)))+")";
//               Class.forName("com.mysql.jdbc.Driver");
//               Connection connection= DriverManager.getConnection(
//                       "jdbc:mysql://localhost:3306/testDB","root",""
//               );
//               connection.createStatement().execute(sql.sql);
//           }
//       });
//    }
//}
