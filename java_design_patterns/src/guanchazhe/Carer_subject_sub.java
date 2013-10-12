package guanchazhe;

import java.util.ArrayList;
import java.util.List;

/**
 * 新建发布通知实现类
 * 
 */
public class Carer_subject_sub implements Carer_subject_parent {
	List<Carer_observer> list_observer = new ArrayList<Carer_observer>();

    private String state;

    public void set_state(String state_string) {
        this.state = state_string;
    }

    public String get_state() {
        return state;
    }

    public void add_observer(Carer_observer carer_observer_ref) {
        list_observer.add(carer_observer_ref);
    }

    public void sub_observer(Carer_observer carer_observer_ref) {
        list_observer.remove(carer_observer_ref);

    }

    public void notityAllCarer() {
        state = "到达终点";
        for (int i = 0; i < list_observer.size(); i++) {
            list_observer.get(i).show_info();
        }

    }

}
