package mobanfangfa;

/**
 * 这就是模板方法模式，一个最常用，最容易理解的一个模式，将相同的功能抽象出来成一个父类，然后用子类做不同功能的实现。
 * 
 */
public class Index {
	public static void main(String[] args) {
		Car_parent redCar = new Car_red_imple();
		redCar.setSpeed(300);
		redCar.print_speed();
		
		Car_parent blueCar = new Car_blue_imple();
		blueCar.setSpeed(500);
		blueCar.print_speed();
	}
}
