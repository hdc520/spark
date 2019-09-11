package java_spark.spark_project.com;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import scala.Tuple2;

import java.util.*;

public class uesr_delay {
    static final String FORMAT="yyyy-MM-dd HH:mm:ss";
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("uesr_delay").setMaster("local[2]");
        SparkSession sparkSession=SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row>inputRow=sparkSession.read().option("timestampFormat",FORMAT).csv(
                "hdfs://localhost:9000/myhdfs/input.csv"
        ).toDF("userID","locID","time","delay_time");
        inputRow.show();
        JavaPairRDD<String,userINFO> pairRDD=inputRow.toJavaRDD()
                .mapPartitionsToPair(new PairFlatMapFunction<Iterator<Row>, String, userINFO>() {
                    @Override
                    public Iterator<Tuple2<String, userINFO>> call(Iterator<Row> rowIterator) throws Exception {
                        System.out.println();
                        List<Tuple2<String,userINFO>> list=new ArrayList<>();
                        Row row=null;
                        userINFO uInfo=null;
                        DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT);

                        while (rowIterator.hasNext()){
                            row=rowIterator.next();
                            uInfo=new userINFO();
                            uInfo.setUserID(row.getAs("userID"));
                            uInfo.setLocID(row.getAs("locID"));
                            DateTime datetime = formatter.parseDateTime(row.getAs("time"));
                            uInfo.setTime(datetime.getMillis());
                            uInfo.setTime_delay(Integer.parseInt(row.getAs("delay_time")));
//                            System.out.println(uInfo.toString());
                            list.add(new Tuple2<>(row.getAs("userID")+"-"+row.getAs("locID"),uInfo));
                        }

                        return list.iterator();
                    }
                });

        JavaRDD<String>finalRDD=pairRDD.groupByKey()
                .mapPartitions(new FlatMapFunction<Iterator<Tuple2<String, Iterable<userINFO>>>, String>() {
                    @Override
                    public Iterator<String> call(Iterator<Tuple2<String, Iterable<userINFO>>> t) throws Exception {
                        List<String>list=new ArrayList<>();
                        while (t.hasNext()){
                            Iterable<userINFO> infos=t.next()._2();
                            Iterator<userINFO>iterator=infos.iterator();
                            List<userINFO>tmplist=new ArrayList<>();
                            userINFO tmpUser=null;
                            System.out.println("------------开始------------");
                            while (iterator.hasNext()){
                                tmpUser=iterator.next();
                                System.out.println(tmpUser.toString());
                                tmplist.add(tmpUser);
                            }
                            System.out.println("------------结束------------");
                            tmplist.sort(new Comparator<userINFO>() {
                                @Override
                                public int compare(userINFO o1, userINFO o2) {
                                    if(o1.getTime()>o2.getTime())
                                        return 1;
                                    else if(o1.getTime()<o2.getTime())
                                        return -1;
                                    return 0;
                                }
                            });
                            userINFO tmpU=null;
                            if(tmplist.size()>0){
                                tmpU=tmplist.get(0);
                                userINFO preInfo=tmpU;
                                long pretime=tmpU.getTime()+tmpU.getTime_delay()*60*1000;
                                int delay=tmpU.getTime_delay();
                                for(int i=1;i<tmplist.size();i++){
                                    tmpU=tmplist.get(i);
                                    if(pretime==tmpU.getTime()){
                                        delay+=tmpU.getTime_delay();
                                        pretime+=tmpU.getTime_delay()*60*1000;
                                        System.out.println("-"+pretime+"--");
                                    }
                                    else {
                                        StringBuffer testBuffer=new StringBuffer();
                                        testBuffer.append(preInfo.getUserID()).append(",")
                                                .append(preInfo.getLocID()).append(",")
                                                .append(new DateTime(preInfo.getTime()).toString(FORMAT)).append(",")
                                                .append(delay);
                                        list.add(testBuffer.toString());

                                        preInfo=tmpU;
                                        pretime=tmpU.getTime()+tmpU.getTime_delay()*60*1000;
                                        delay=tmpU.getTime_delay();
                                    }
                                }
                                StringBuffer testBuffer=new StringBuffer();
                                testBuffer.append(preInfo.getUserID()).append(",")
                                        .append(preInfo.getLocID()).append(",")
                                        .append(new DateTime(preInfo.getTime()).toString(FORMAT)).append(",").append(delay);
                                list.add(testBuffer.toString());
                            }

                        }

                        return list.iterator();
                    }
                });
        finalRDD.saveAsTextFile("/home/hdc/data/t1");
    }
}
