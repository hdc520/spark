package java_spark.spark_project.com;

import java.io.Serializable;

public class userINFO implements Serializable {
    String userID;
    String locID;
    long time;
    int time_delay;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLocID() {
        return locID;
    }

    public void setLocID(String locID) {
        this.locID = locID;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTime_delay() {
        return time_delay;
    }

    public void setTime_delay(int time_delay) {
        this.time_delay = time_delay;
    }

    @Override
    public String toString() {
        return "userINFO{" +
                "userID='" + userID + '\'' +
                ", locID='" + locID + '\'' +
                ", time=" + time +
                ", time_delay=" + time_delay +
                '}';
    }
}
