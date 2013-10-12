package mobanfangfa;

public class Car_red_imple extends Car_parent {
	@Override
	public void print_speed() {
		super.print_speed();
		System.out.println("将速度" + this.getSpeed() + "用红色的仪表盘显示车的速度");
	}
}
