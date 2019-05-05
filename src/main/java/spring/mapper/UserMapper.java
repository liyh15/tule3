package spring.mapper;

import org.apache.ibatis.annotations.Param;

import entity.User;
public interface UserMapper {
	
	/**
	 * ��ȡ�û��ֻ���
	 * @param phone
	 * @return
	 */
    public User getUserByPhone(@Param("phone") String phone);
    
    /**
     * �޸��û�����
     * @param phone
     * @param newPass
     * @return
     */
    public Integer changeUserPass(@Param("phone")String phone,@Param("newPass") String newPass);
    
    /**
     * ͨ���û���Ż�ȡ�û�ͷ��·��
     * @param id �û����
     * @return
     */
    public String getUserImageUrlByUserId(@Param("id") Integer id);
    
    /**
	 * �ϴ�ͷ��·�������ݿ�
	 * @param id
	 * @param imageUrl
	 * @return
	 */
	public Integer putHeadImage(@Param("id") Integer id, @Param("image") String imageUrl);
	
	/**
	 * �û��������
	 * @param money ������
	 * @param id �û�id
	 * @return
	 */
	public Integer payForMoney(@Param("money") Integer money,@Param("id") Integer id);
    
}
