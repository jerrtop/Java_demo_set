package jianzaozhe;
/**
 * 新建低画质builder建造者类
 * 
 */
public class Map_build_low {
	private Map_interface map_interface;
	public Map_build_low(Map_interface map_interface) {
		this.map_interface = map_interface;
	}
	
	public void create_map() {
		 System.out.println("创建一个低画质的地图");
	        // 创建的顺序很重要 从天空往路面创建
		 map_interface.create_weather();
		 map_interface.create_house();
	     // map_interface_ref.create_tree();将创建树的过程去掉
		 map_interface.create_way();
	}
}
