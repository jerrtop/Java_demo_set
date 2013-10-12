package celue;
/**
 * strategy implement
 * 	execute()
 * 
 */
public class CartyreShortLineStrategy implements CartyreStrategy {

	@Override
	public void print_line() {
		System.out.println("在路面上显示一个短轮胎痕迹.");
	}

}
