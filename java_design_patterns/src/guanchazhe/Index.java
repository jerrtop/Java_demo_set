package guanchazhe;

public class Index {
	public static void main(String[] args) {

        Carer_subject_parent carer_subject_parent_ref = new Carer_subject_sub();

        Carer_observer_imple carer_observer_imple_ref1 = new Carer_observer_imple(
                carer_subject_parent_ref);
        Carer_observer_imple carer_observer_imple_ref2 = new Carer_observer_imple(
                carer_subject_parent_ref);
        Carer_observer_imple carer_observer_imple_ref3 = new Carer_observer_imple(
                carer_subject_parent_ref);
        Carer_observer_imple carer_observer_imple_ref4 = new Carer_observer_imple(
                carer_subject_parent_ref);

        carer_subject_parent_ref.add_observer(carer_observer_imple_ref1);
        carer_subject_parent_ref.add_observer(carer_observer_imple_ref2);
        carer_subject_parent_ref.add_observer(carer_observer_imple_ref3);
        carer_subject_parent_ref.add_observer(carer_observer_imple_ref4);

        carer_subject_parent_ref.sub_observer(carer_observer_imple_ref3);

        carer_subject_parent_ref.set_state("到达终点");

        carer_subject_parent_ref.notityAllCarer();
        /*
         *  以一对多的方式进行对一方状态的改变而去通知多方，观察者模式也叫发布/订阅模式，
         *  一方进行发布，而多方进行订阅，和生活中看报纸一样，你喜欢订城市晚报，就会发给你。
         */
    }

}
