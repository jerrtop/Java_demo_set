package jianzaozhe;

public class Index {
	public static void main(String[] args) {
		Map_cloudy cloudy = new Map_cloudy();
		Map_build_adv adv = new Map_build_adv(cloudy);
		adv.create_map();
		
		System.out.println();
		Map_sun sun = new Map_sun();
		Map_build_low low = new Map_build_low(sun);
		low.create_map();
//		从程序中可以看到，建造者模式将不变的创建过程进行封装，
//		创建的过程与main分法进行分离，这样内部的创建过程就和表示层的代码进行分开，
//		有利于创建过程功能上的修改。
//		另外可以发现，代码的设计和功能有些类似于facade外观模式，
//		区别在于，建造者模式目的在于以相同的构建过程通过不同的建造者得到不同的结果，
//		而外观模式并不需要不同的建造者，也不希望得到不同的结果，只是简单的将几个接口合并成高级的一个接口，
//		不影响原有的结果，目的是使调用变得更加容易。
	}
}
