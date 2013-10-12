package yuanxing;

/**
 *  新建奥迪汽车的实现类
 * 
 */
public class Audi_imple implements Car_interface, Cloneable {
	private Car_tyre car_tyre;
	public void start() {
		System.out.println("奥迪A6启动了");

	}

	public void stop() {
		System.out.println("奥迪A6停止了");

	}

	

	public Car_tyre getCar_tyre() {
		return car_tyre;
	}

	public void setCar_tyre(Car_tyre car_tyre) {
		this.car_tyre = car_tyre;
	}

	public Object clone() throws CloneNotSupportedException {
		super.clone();
		Audi_imple audi_imple = new Audi_imple();
		audi_imple.setCar_tyre(new Car_tyre());
		return audi_imple;
	}
}
