package zhuangshi;
/**
 * Decorator 
 * 	extends Component
 * 
 */
public abstract class CarDecorator extends CarComponent{
	protected CarComponent carComponent;
	
	public CarComponent getCarComponent() {
		return carComponent;
	}

	public void setCarComponent(CarComponent carComponent) {
		this.carComponent = carComponent;
	}

	@Override
	public void print_face() {
		carComponent.print_face();
	}
}
