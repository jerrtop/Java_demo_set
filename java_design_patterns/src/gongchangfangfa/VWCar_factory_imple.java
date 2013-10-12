package gongchangfangfa;
/**
 * 新建一个创建大众车的汽车工厂实现类
 * 
 * 
 */
public class VWCar_factory_imple implements ICar_factory {

	public ICar_interface create_car() {
		VWCar_imple vwCar_imple = new VWCar_imple();
		vwCar_imple.setName("大众途观");
		vwCar_imple.setSpeed(200);
		return vwCar_imple;
	}

}
