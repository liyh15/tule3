package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 火车方面工具类
 * @author 李元浩
 *
 */
public class TrainUtil {
	
    private static Map<String, String> typeMap = new HashMap<String, String>();
    
    static {
    	typeMap.put("动车", "D");
    	typeMap.put("高铁", "G");
    	typeMap.put("快车", "K");
    	typeMap.put("特快", "T");
    	typeMap.put("直达", "Z");
    }
    
    /**
     * 通过火车类型描述获得火车类型编号
     * @param name
     * @return
     */
    public static String getTypeByName(String name) {
    	
    	return typeMap.get(name);
    }
}
