package gongchangfangfa;
/**
 * 新建奥迪汽车实现类
 * 
 */
public class AudiCar_imple extends BaseCar_imple {
	   public void start() {
	        System.out.println(this.getName() + " 车以专利技术起动了　最高速度为："
	                + this.getSpeed());
	    }
	   
	    public void stop() {
	        System.out.println(this.getName() + " 车以专利技术停车了");
	    }

}
