package utils;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * ���ڶ����Ĺ��߷����볣��
 * @author ��Ԫ��
 *
 */
public class OrderUtil {
   
	public static String DELETE = "0";  // ����ɾ��
	
	public static String DEAL = "1"; // �����ɽ�
	
	public static String CANCEL = "2"; // ����ȡ��
	
	public static String BACK = "3"; // ������Ʊ
	
	public static String NOPAY = "4"; // δ����
	
	public static String HAS_COMMENT = "1"; // �Ѿ�����
	
	public static String NO_COMMENT = "2"; // δ����
	
	/**
	 * ͨ��״̬���ȡ״̬����
	 * @param name
	 * @return
	 */
	public static String getDisByName(String name){
	    if("0".equals(name)){
	    	return "��ɾ��";
	    } else if("1".equals(name)){
	    	return "�ѳɽ�";
	    } else if("2".equals(name)){
	    	return "��ȡ��";
	    } else if("3".equals(name)){
	    	return "����Ʊ";
	    } else if("4".equals(name)){
	    	return "δ����";
	    }
	    return null;
	}
	
	/**
	 * ͨ������״̬��ȡ������������
	 * @return
	 */
	public static String getCommentByName(String name) {
		 if("1".equals(name)){
		    	return "������";
		    } else if("2".equals(name)){
		    	return "δ����";
		    }
	   return null;
	}
}
