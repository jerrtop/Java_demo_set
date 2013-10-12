package chouxianggongchang;

public class Index {
	public static void main(String[] args) {

		ICar_factory icar_factory_ref = new Car_factory_adv();

		Icar_interface icar_interface_ref = icar_factory_ref.create_threeW_car();
		icar_interface_ref.start();
		icar_interface_ref.stop();

	}

}
