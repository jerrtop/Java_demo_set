package daili;
/**
 * 
 */
public class People implements BuyCar{
	private int cash;
	private String username;
	
	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void buy_car() {
		System.out.println(username + "买了台新车.");
	}
	
}
