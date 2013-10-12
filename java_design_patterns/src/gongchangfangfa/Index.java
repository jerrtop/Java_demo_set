package gongchangfangfa;

public class Index {
	public static void main(String[] args) {
		ICar_factory iCar_factory = new AudiCar_factory_imple();
		ICar_interface car_interface = iCar_factory.create_car();
		car_interface.start();
		car_interface.stop();
		
		ICar_factory car_factory = new VWCar_factory_imple();
		ICar_interface car_interface2 = car_factory.create_car();
		car_interface2.start();
		car_interface2.stop();
		
	}
}
