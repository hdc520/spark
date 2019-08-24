package java_spark.spark_book.第9章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;

import java.io.Serializable;

public class flection_class {
    //javaBean
    public static class person implements Serializable {
        private String name;
        private  int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("flection_class").setMaster("local");
        SparkSession sparkSession=SparkSession.builder().config(conf).getOrCreate();
        JavaRDD<person>personJavaRDD=sparkSession.read()
                .textFile("/home/hdc/data/data2")
                .javaRDD()
                .map(line->{
                        String[] parts=line.split(" ");
                        person p=new person();
                        p.setName(parts[0]);
                        p.setAge(Integer.parseInt(parts[1].trim()));
                        return p;
                    }
                );
        //使用反射机制，将RDD转换为Dataset
        //即将person.class传入进去，其实就是用反射机制来创建Dataset
        Dataset<Row> pDF=sparkSession.createDataFrame(personJavaRDD,person.class);
        //拿到dataset就可以将其注册为一个临时表（临时视图）
        pDF.createOrReplaceTempView("student");
        Dataset<Row> sDF=sparkSession.sql(
                "select name,age from student where age between 25 and 30"
        );
        //将Dataset转换成RDD<Row>
        JavaRDD<Row>pRDD=sDF.javaRDD();
        //将RDD中的数据映射为person
        JavaRDD<person> personJavaRDD1=pRDD.map(new Function<Row, person>() {
            @Override
            public person call(Row row) throws Exception {
                person p=new person();
                p.setName(row.getString(0));
                p.setAge(row.getInt(1));
                return p;
            }
        });
        System.out.println("---------"+personJavaRDD1.collect().toString());





        Encoder<String>stringEncoder= Encoders.STRING();
        Dataset<String>pIndex=sDF.map(
                (MapFunction<Row,String>)row->"name:"+row.getString(0),stringEncoder
        );
        pIndex.show();

        Dataset<String>fieldDF=sDF.map(
                (MapFunction<Row,String>)row->"name: "+row.<String>getAs("name"),stringEncoder
        );
        System.out.println("-----"+fieldDF.getClass().getSimpleName());
        fieldDF.show();
    }
}
