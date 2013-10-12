package yuanxing;

public class Index {
	public static void main(String[] args) {
		Audi_imple audi_imple = new Audi_imple();
		Car_tyre car_tyre = new Car_tyre();
		audi_imple.setCar_tyre(car_tyre);
		
		System.out.println("我的奥迪参数：" + audi_imple);
		System.out.println("我的奥迪轮胎参数：" + audi_imple.getCar_tyre());
		try{
			Audi_imple audiImple_other = (Audi_imple) audi_imple.clone();
			System.out.println("其它人的奥迪车的参数是：" + audiImple_other);
			System.out.println("其它人的奥迪车的轮胎参数是：" + audiImple_other.getCar_tyre());
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
