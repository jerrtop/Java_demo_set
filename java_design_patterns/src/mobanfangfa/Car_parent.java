package mobanfangfa;
/**
 * 【模板方法模式解释】
    类型：行为模式
    模板方法模式定义一个操作中算法的骨架，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

 * 
 * 新建赛车的父类
 */
public class Car_parent {
	private int speed;
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void print_speed() {
        System.out.println("将速度" + this.getSpeed() + "取出来！");
    }

}
