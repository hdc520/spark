package java_spark.spark_project;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;
import scala.math.Ordered;

import java.io.Serializable;


//自定义排序Key
public class secondSort {
    public static class SecondSortKey implements Ordered<SecondSortKey>, Serializable {
        private int first;
        private int second;
        public SecondSortKey(int first,int second){
            this.first=first;
            this.second=second;
        }
        public int compare(SecondSortKey that) {
            if(this.first-that.getFirst()!=0)
                return this.first-that.getFirst();
            else{
                return this.second-that.getSecond();
            }
        }

        public boolean $less(SecondSortKey that) {
            if(this.first<that.first)
                return true;
            else if (this.first==that.first && this.second<that.second){
                return true;
            }
            return false;
        }

        public boolean $greater(SecondSortKey that) {
            if(this.first>that.first)
                return true;
            else if (this.first==that.first && this.second>that.second){
                return true;
            }
            return false;
        }

        public boolean $less$eq(SecondSortKey that) {
            if(this.first<that.first)
                return true;
            else if (this.first==that.first && this.second<that.second){
                return true;
            }
            return false;
        }

        public boolean $greater$eq(SecondSortKey that) {
            if(this.$greater(that))
                return true;
            else if(this.first==that.first&& this.second==this.second)
                return true;
            return false;
        }

        public int compareTo(SecondSortKey that) {
            if(this.first-that.getFirst()!=0)
                return this.first-that.getFirst();
            else{
                return this.second-that.getSecond();
            }
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SecondSortKey that = (SecondSortKey) o;

            if (first != that.first) return false;
            return second == that.second;
        }

        @Override
        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            return result;
        }
    }
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("secondSort").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String>inputRDD=sc.textFile("/home/hdc/data/data1");
        JavaPairRDD<SecondSortKey,String>pairs=inputRDD.mapToPair(new PairFunction<String, SecondSortKey, String>() {
            public Tuple2<SecondSortKey, String> call(String s) throws Exception {
                int first=Integer.parseInt(s.split(" ")[0]);
                int second=Integer.parseInt(s.split(" ")[1]);
                SecondSortKey secondSortKey=new SecondSortKey(first,second);
                return new Tuple2<SecondSortKey, String>(secondSortKey,s);
            }
        });
        JavaPairRDD<SecondSortKey,String>sortRDD=pairs.sortByKey();
        sortRDD.foreach(new VoidFunction<Tuple2<SecondSortKey, String>>() {
            public void call(Tuple2<SecondSortKey, String> s) throws Exception {
                System.out.println(s._2);
            }
        });
    }
}
