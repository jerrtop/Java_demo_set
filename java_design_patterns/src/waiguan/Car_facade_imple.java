package waiguan;
/**
 * 新建赛车操作的外观类
 * 
 */
public class Car_facade_imple {
	/**
	 * 将子操作用一个外观接口封装起来，然后调用这个接口就是调用那些非常复杂的微操作了
	 * @param car
	 */
	public void car_go_go(Car car) {
		car.check_box();
		car.check_console();
		car.check_stop();
		car.start();

	}
}
