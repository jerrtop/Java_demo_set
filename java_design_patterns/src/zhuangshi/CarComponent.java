package zhuangshi;
/**
 * 
 * Component 
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
