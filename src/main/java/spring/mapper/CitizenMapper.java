package spring.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * ��������ݿ�־ò�mapper
 * @author ��Ԫ��
 */
public interface CitizenMapper {
    
	/**
	 * ��ѯ�û��Ƿ����
	 * @param name �û�����
	 * @param type �û�����
	 * @param code ֤������
	 * @return
	 */
	public Integer queryIsExist(@Param("name") String name,
			@Param("type") String type,@Param("code") String code);
}
