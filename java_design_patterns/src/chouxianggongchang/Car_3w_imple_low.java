package chouxianggongchang;
/**
 * 新建大众普通车
 * 
 */
public class Car_3w_imple_low extends Base_car_imple {
	public void start() {
        System.out.println("普通版：" + this.getName() + " 车以专利技术起动了　最高速度为："
                + this.getSpeed());
    }

    public void stop() {
        System.out.println("普通版：" + this.getName() + " 车以专利技术停车了");
    }

}
