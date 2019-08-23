package java_spark.spark_book.第9章;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

public class object_to_Dataset {
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
        SparkConf conf=new SparkConf().setAppName("object_to_Dataset").setMaster("local");
        SparkSession sparkSession= SparkSession.builder().config(conf).getOrCreate();
        person p=new person();
        p.setName("hdc");
        p.setAge(24);
        Encoder<person>personEncoder=Encoders.bean(person.class);
        Dataset<person> javaBeanDS = sparkSession.createDataset(
                Collections.singletonList(p),
                personEncoder
        );
//        javaBeanDS.show();

        Encoder<Integer>integerEncoder=Encoders.INT();
        Dataset<Integer>pDS=sparkSession.createDataset(Arrays.asList(1,2,3),integerEncoder);
        Dataset<person>personDataset=sparkSession.read()
                .json("/home/hdc/data/data.txt")
                .as(personEncoder);
        //上述代码：
        //1.Spark读取了JSON文件，并根据定义的结构，创建了一个DataFrame的数据集
        //2.在这个DataFrame的数据集，即Dataset[Row]中，实际是一个个的行对象，因为它并不知道各自的类型
        //3.最后，spark将Dataset[Row]转换为Dataset[DeviceIoTData]，每一行数据被转化为了一个个的实例对象
        pDS.show();
        personDataset.show();
    }
}
