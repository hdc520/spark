package java_spark.spark_book.第10章;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

public class ConnectionsPool {

    //静态的connection队列
    private static LinkedList<Connection>connectionsQueue;
    //加载驱动
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接，多线程访问并发控制
    public synchronized static Connection getConnection(){
        try {
            if(connectionsQueue==null){
                for(int i=0;i<10;i++){
                    connectionsQueue=new LinkedList<>();
                    Connection conn= DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/testDB","root",""
                    );
                    connectionsQueue.push(conn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connectionsQueue.poll();
    }

    //还回连接
    public static void returnConnection(Connection conn){
        connectionsQueue.push(conn);
    }
}
