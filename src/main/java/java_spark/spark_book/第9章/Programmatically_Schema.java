package java_spark.spark_book.第9章;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

public class Programmatically_Schema {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("Programmatically_Schema").setMaster("local");
        SparkSession sparkSession=SparkSession.builder().config(conf).getOrCreate();
        //初始化RDD
        JavaRDD<String>personRDD=sparkSession.sparkContext().textFile("/home/hdc/data/data2",1)
                .toJavaRDD();
        //普通RDD转换成RDD<Row>，必须转换
        JavaRDD<Row>rowJavaRDD=personRDD.map(
                (Function<String,Row>)record->{
                    String[] attributes=record.split(" ");
                    return RowFactory.create(attributes[0],attributes[1].trim());
                }
        );
        //动态构建元数据
        List<StructField>fields=new ArrayList<>();
        String schemaString="name age";
        for(String str:schemaString.split(" ")){
            StructField field= DataTypes.createStructField(str, DataTypes.StringType,true);
            fields.add(field);
        }
        StructType schema=DataTypes.createStructType(fields);
        //将rowRDD变成Dataset
        Dataset<Row>pDF=sparkSession.createDataFrame(rowJavaRDD,schema);
        pDF.createOrReplaceTempView("person");
        Dataset<Row>nameDS=sparkSession.sql("select name from person");
        nameDS.show();
        nameDS.map(
                (MapFunction<Row,String>)row->"name:"+row.getString(0), Encoders.STRING()
        ).show();
        nameDS.write().format("csv").save("/home/hdc/data/csv");
    }
}
