package chouxianggongchang;
/**
 * 新建大众高级车
 * 
 */
public class Car_3w_imple_adv extends Base_car_imple {
	 public void start() {
	        System.out.println("富华版：" + this.getName() + " 车以专利技术起动了　最高速度为："
	                + this.getSpeed());
	    }

	    public void stop() {
	        System.out.println("富华版：" + this.getName() + " 车以专利技术停车了");
	    }

}
