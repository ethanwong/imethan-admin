package cn.imethan.dto;

/**
 * ValueObject.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年10月14日上午10:39:36
 */
public class ValueObject {
	private String id;
	private float value;

	public String getId() {
		return id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ValueObject [id=" + id + ", value=" + value + "]";
	}
}
