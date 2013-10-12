package waiguan;
/**
 *  【外观模式解释】
    类型：结构模式
    为子系统中的一组接口提供一个一致的interface接口界面。

 * 
 * 新建赛车类
 */
public class Car {
	public void start() {
		System.out.println("车子已经启动。");

	}
	public void check_stop() {
        System.out.println("刹车检查");
    }

    public void check_box() {
        System.out.println("检查油箱");
    }

    public void check_console() {
        System.out.println("检查仪表盘是否异常");
    }

}
