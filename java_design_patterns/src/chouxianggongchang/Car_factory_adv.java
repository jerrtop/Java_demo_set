package chouxianggongchang;
/**
 * 新建抽象工厂接口的高级车adv工厂实现类
 * 
 */
public class Car_factory_adv implements ICar_factory {

	public Icar_interface create_audi_car() {

        Car_audi_imple_adv car_audi_imple_adv = new Car_audi_imple_adv();
        car_audi_imple_adv.setName("奥迪A6");
        car_audi_imple_adv.setSpeed(300);

        return car_audi_imple_adv;
    }

    public Icar_interface create_threeW_car() {
        
    	Car_3w_imple_adv car_3w_imple_adv_ref = new Car_3w_imple_adv();
        car_3w_imple_adv_ref.setName("大众A6");
        car_3w_imple_adv_ref.setSpeed(300);

        return car_3w_imple_adv_ref;
    }


}
