package celue;
/**
 * strategy implement
 * 	execute()
 * 
 */
public class CartyreLongLineStrategy implements CartyreStrategy {

	@Override
	public void print_line() {
		System.out.println("在路面上显示一个长轮胎痕迹.");
	}

}
