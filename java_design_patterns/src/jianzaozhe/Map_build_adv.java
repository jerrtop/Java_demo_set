package jianzaozhe;
/**
 * 新建高画质builder建造者类
 * 
 */
public class Map_build_adv {
	private Map_interface map_interface;
	public Map_build_adv(Map_interface map_interface) {
		this.map_interface = map_interface;
	}
	
	public void create_map() {
		 System.out.println("创建一个高画质的地图");
        // 创建的顺序很重要 从天空往路面创建
		 map_interface.create_weather();
		 map_interface.create_house();
		 map_interface.create_tree();
		 map_interface.create_way();

	}
}
