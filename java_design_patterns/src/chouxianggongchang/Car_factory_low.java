package chouxianggongchang;
/**
 * 新建抽象工厂接口的普通车low工厂实现类
 * 
 */
public class Car_factory_low implements ICar_factory {
	 public Icar_interface create_audi_car() {

	        Car_audi_imple_low car_audi_imple_low_ref = new Car_audi_imple_low();
	        car_audi_imple_low_ref.setName("奥迪A6");
	        car_audi_imple_low_ref.setSpeed(300);

	        return car_audi_imple_low_ref;
	    }

	    public Icar_interface create_threeW_car() {
	        
	        Car_3w_imple_low car_3w_imple_low_ref = new Car_3w_imple_low();
	        car_3w_imple_low_ref.setName("大众A6");
	        car_3w_imple_low_ref.setSpeed(300);

	        return car_3w_imple_low_ref;
	    }

}
