package celue;

public class Index {
	public static void main(String[] args) {
		CartyreLongLineStrategy longline = new CartyreLongLineStrategy();
		CartyreShortLineStrategy shortline = new CartyreShortLineStrategy();
		
        CarContext car = new CarContext();
        car.setDeath_year(8);
        car.setAddress("北京朝阳区");
        car.setSpeed(200);
        // execute1
        car.setCartyreStrategy(longline);
        //execute2
        //car.setCartyreStrategy(shortline);
        car.start();
	}
}
