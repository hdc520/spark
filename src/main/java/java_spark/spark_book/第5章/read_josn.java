package java_spark.spark_book.第5章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.ArrayList;
import java.util.Iterator;
public class read_josn {
    public static class person{
        public String firstName,lastName;
//        public List<Address> list;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }


        @Override
        public String toString() {
            return "person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    public static class ParseJson implements FlatMapFunction<Iterator<String>,person>{
        public Iterator<person> call(Iterator<String>lines)throws Exception{
            ArrayList<person>p=new ArrayList<person>();
            ObjectMapper mapper=new ObjectMapper();
            while(lines.hasNext()){
                String line=lines.next();
//                System.out.println("line:"+line);
                try{
//                    System.out.println("mapper.readValue(line,person.class):"+mapper.readValue(line,person.class));
                    p.add(mapper.readValue(line,person.class));
//                    System.out.println("p:"+p.get(0).toString());
                }catch (Exception e){}
            }
            return p.iterator();
        }
    }
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("read_josn").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String>inputRDD=sc.textFile("/home/hdc/data/json");
        inputRDD.foreach(new VoidFunction<String>() {
            public void call(String s) throws Exception {
                System.out.println("inputRDD:"+s);
            }
        });
        JavaRDD<person>resultRDD=inputRDD.mapPartitions(new ParseJson());
        resultRDD.foreach(new VoidFunction<person>() {
            public void call(person p) throws Exception {
                System.out.println(p.toString());
            }
        });
    }
}
