package daili;
/**
 * Proxy
 * 
 */
public class ProxyBuyCar implements BuyCar {
	private People people;
	
	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

//	代理模式在GOF四人帮的介绍中大体有4种使用情景：
//    （1）远程代理。典型的就是客户端与webservice使用的情况，客户端由于是针对OOP编程，而不是针对webservice中的方法进行编程，所以得在客户端模拟一下webservice的环境，用proxy来对webservice进行包装，这样就可以使用proxy代理类来远程操作webservice了。
//    （2）虚拟代理。比如你要开发一个大文档查看软件，大文档中有大的图片，有可能一个图片有100MB，在打开文件时不可能将所有的图片都显示出来，这样就可以使用代理模式，当需要查看图片时，用proxy来进行大图片的打开。
//    （3）安全代理。其实也就是本例中所举的买车的例子，金钱不够不可以买车！
//    （4）智能指引。比如在访问一个对象时检测其是否被锁定等情况。
	public void buy_car() {
		if(people.getCash() > 3000){
			System.out.println(people.getUsername() + " 花了 "+ people.getCash() + "买了台新车。交易结束!");
		}else{
			  System.out.println(people.getUsername() + "金钱不够，请继续比赛!");
		}
	}

}
