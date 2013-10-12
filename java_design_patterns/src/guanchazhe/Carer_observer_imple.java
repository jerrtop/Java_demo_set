package guanchazhe;
/**
 * 新建观察者实现类
 * 
 */
public class Carer_observer_imple implements Carer_observer {
	private Carer_subject_parent carer_subject_parent_ref;

    public Carer_subject_parent getCarer_subject_parent_ref() {
        return carer_subject_parent_ref;
    }

    public void setCarer_subject_parent_ref(
    		Carer_subject_parent carer_subject_parent_ref) {
        this.carer_subject_parent_ref = carer_subject_parent_ref;
    }

    public Carer_observer_imple(Carer_subject_parent carer_subject_parent_ref) {
        super();
        this.carer_subject_parent_ref = carer_subject_parent_ref;
    }

    public void show_info() {
        System.out.println(carer_subject_parent_ref.get_state());

    }


}
