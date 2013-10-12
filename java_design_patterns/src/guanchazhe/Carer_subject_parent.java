package guanchazhe;
/**
 * 新建抽象发布通知父类
 * 
 */
public interface Carer_subject_parent {
	 	public void set_state(String state_string);

	    public String get_state();

	    public void add_observer(Carer_observer carer_observer_ref);

	    public void sub_observer(Carer_observer carer_observer_ref);

	    public void notityAllCarer();

}
