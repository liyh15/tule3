package entity;
import java.io.Serializable;
import java.util.List;
/**
 * 城市的实体类
 * @author 李元浩
 *
 */
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;     //城市编号
	private String name;//城市名称
	private String pinyin;//城市拼音
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", pinyin=" + pinyin + "]";
	}
	
}
