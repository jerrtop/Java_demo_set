package celue;
/**
 * Context
 * 
 */
public class CarContext {
	private String address;
	private int speed;
	private int death_year;
	private CartyreStrategy cartyreStrategy;
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
	public int getDeath_year() {
		return death_year;
	}
	public void setDeath_year(int death_year) {
		this.death_year = death_year;
	}
	public CartyreStrategy getCartyreStrategy() {
		return cartyreStrategy;
	}
	public void setCartyreStrategy(CartyreStrategy cartyreStrategy) {
		this.cartyreStrategy = cartyreStrategy;
	}
	
	public void start(){
		System.out.println("车的基本信息为：");
        System.out.println("制造地make_address：" + this.getAddress());
        System.out.println("报废年限death_year：" + this.getDeath_year());
        System.out.println("速度speed：" + this.getSpeed());
        System.out.println("Car 起动了！");
        System.out.println("Car高速行驶，遇到一个大转弯，路面显示：");
        this.cartyreStrategy.print_line();

	}
}
