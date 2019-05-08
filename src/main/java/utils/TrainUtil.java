package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * �𳵷��湤����
 * @author ��Ԫ��
 *
 */
public class TrainUtil {
	
    private static Map<String, String> typeMap = new HashMap<String, String>();
    
    static {
    	typeMap.put("����", "D");
    	typeMap.put("����", "G");
    	typeMap.put("�쳵", "K");
    	typeMap.put("�ؿ�", "T");
    	typeMap.put("ֱ��", "Z");
    }
    
    /**
     * ͨ��������������û����ͱ��
     * @param name
     * @return
     */
    public static String getTypeByName(String name) {
    	
    	return typeMap.get(name);
    }
}
