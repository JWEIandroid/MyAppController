package utils;

public class DateUtils {


    //判断两个时间戳相差分钟数
    public int getMinutesfrom2TimeStamp(long before, long after) {
        if (after > before) {
            int  distance = (int)(after - before) / 60;
            return distance;
        } else {
            return -1;
        }
    }



}
