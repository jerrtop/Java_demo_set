package zhuangshi;

public class Index {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CarConcreteComponent carConcreteComponent = new CarConcreteComponent();
		carConcreteComponent.setAddress("极品飞车");
		carConcreteComponent.setSpeed(500);

		CarConcreteDecoratorBlue blue = new CarConcreteDecoratorBlue();
		blue.setCarComponent(carConcreteComponent);
		CarConcreteDecoratorRed red = new CarConcreteDecoratorRed();
		red.setCarComponent(blue);
		
		red.print_face();
		
	}

}
