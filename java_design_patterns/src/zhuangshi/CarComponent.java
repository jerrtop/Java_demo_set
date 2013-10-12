package zhuangshi;

/**
 * 
 * Component 
 * 【装饰模式解释】 
 *  类型：结构模式 
 * 
 *  动态的对一个对象进行功能上的扩展，也可以对其子类进行功能上的扩展。
 */
public abstract class CarComponent {
	private String address;
	private int speed;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public  abstract void print_face();
}
