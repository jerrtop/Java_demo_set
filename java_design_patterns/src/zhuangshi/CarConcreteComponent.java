package zhuangshi;
/**
 * ConcreteComponent
 * extends Component
 */
public class CarConcreteComponent extends CarComponent{

	@Override
	public void print_face() {
		System.out.println("汽车默认喷漆颜色：黑色.");
	}

}
