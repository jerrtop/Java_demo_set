package gongchangfangfa;
/**
 * 新建大众汽车实现类
 * 
 */
public class VWCar_imple extends BaseCar_imple {
	 public void start() {
	        System.out.println(this.getName() + " 车以专利技术起动了　最高速度为："
	                + this.getSpeed());
	    }
	    public void stop() {
	        System.out.println(this.getName() + " 车以专利技术停车了");
	    }

}
