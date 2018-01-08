package constant;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constant {

//    public final static String IP = "192.168.218.153";
//    public final static String IP = "172.168.5.97";
//    public final static String IP = "172.168.5.138";
    public static String IP =null;

    static {
        try {
            IP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //用户图片存储路径
    public final static String IMG_USER_HOME = "E:\\imgs\\user\\";
    //商品图片存储路径
    public final static String IMG_GOOD_HOME = "E:\\imgs\\good\\";

    public final static String STORE_HOME = "http://"+IP+":8080/";



}
