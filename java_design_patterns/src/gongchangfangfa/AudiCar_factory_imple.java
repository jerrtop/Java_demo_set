package gongchangfangfa;
/**
 * 新建一个创建奥迪车的汽车工厂实现类
 * 
 */
public class AudiCar_factory_imple implements ICar_factory {

	public ICar_interface create_car() {
		AudiCar_imple audiCar_imple = new AudiCar_imple();
		audiCar_imple.setName("奥迪A8");
		audiCar_imple.setSpeed(300);
		return audiCar_imple;
	}

}
