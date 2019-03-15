package utils;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Json的基础类
 * @author 李元浩
 *
 */
public class JsonUtil {
   
	
	/**
	 * 获得json集合对象字符串
	 * @param object
	 * @return
	 */
	public static String getJsonArrayStrByObject(Object object){
		return JSONArray.fromObject(object).toString();
	}
	
	/**
	 * 获得json普通对象字符串
	 * @param object
	 * @return
	 */
	public static String getJsonObjectStrByObject(Object object){
		return JSONObject.fromObject(object).toString();
	}
	
	/**
	 * 通过字符串获得jsonArray对象
	 * @param str
	 * @return
	 */
	public static JSONArray getJsonArrayByStr(String str){
		return JSONArray.fromObject(str);
	}
	
	/**
	 * 通过对象字符串获得jsonObject对象
	 * @param str
	 * @return
	 */
	public static JSONObject getJsonObjectByStr(String str){
		return JSONObject.fromObject(str);
	}
	
	/**
	 * 通过不同对象的json对象获取对象实例
	 * @param obj json对象
	 * @param cls 类对象
	 * @return
	 */
	public static Object getObjectByJsonObject(JSONObject obj,Class cls){
		return JSONObject.toBean(obj,cls);
	}
	
	/**
	 * 通过json集合对象获得集合对象实例
	 * @param array
	 * @param cls
	 * @return
	 */
	public static List getListByJsonArray(JSONArray array,Class cls){
		return JSONArray.toList(array, cls);
	}
}
