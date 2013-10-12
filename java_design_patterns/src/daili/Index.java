package daili;


public class Index {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		People p1 = new People();
		p1.setCash(4000);
		p1.setUsername("李明");
		
		People p2 = new People();
		p2.setCash(3000);
		p2.setUsername("王刚");

		ProxyBuyCar proxyBuyCar = new ProxyBuyCar();
		proxyBuyCar.setPeople(p1);
		proxyBuyCar.buy_car();
		
		proxyBuyCar.setPeople(p2);
		proxyBuyCar.buy_car();
	}

}
