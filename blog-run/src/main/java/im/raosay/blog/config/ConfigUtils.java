package im.raosay.blog.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author ron
 * @version ConfigUtils, v 0.1 2019/2/11 16:22 Administrator Exp $
 * @contact raosay92@gmail.com
 */
public class ConfigUtils {

    public static Map<String,String> configMap = new HashMap<String, String>();


    public static String get(String configName){
        if (configMap.isEmpty())
            initConfig();


        return configMap.containsKey(configName) ? configMap.get(configName):"";
    }


    public static void initConfig(){
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Set<String> keys = bundle.keySet();
        keys.stream().forEach(s -> {
            configMap.put(s,bundle.getString(s));
            System.out.println(s+":"+bundle.getString(s));
        });

    }

}
