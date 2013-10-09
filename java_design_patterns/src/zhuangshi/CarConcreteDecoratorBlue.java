package zhuangshi;
/**
 * Blue Decorator 
 * 	extends Decorator
 */
public class CarConcreteDecoratorBlue extends CarDecorator{
	@Override
	public void print_face() {
		super.print_face();
		System.out.println("汽车默认喷漆颜色：蓝色.");
	}
}
