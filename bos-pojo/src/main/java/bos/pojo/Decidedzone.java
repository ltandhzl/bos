package bos.pojo;

import java.util.HashSet;
import java.util.Set;


public class Decidedzone {
    private String id;
    private String name;
    private Staff staff;
    private Set<Subarea> subareas=new HashSet<Subarea>();
    private Character delflag='0';

    public void setDelflag(Character delflag) {
        this.delflag = delflag;
    }

    public Character getDelflag() {
        return delflag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setSubareas(Set<Subarea> subareas) {
        this.subareas = subareas;
    }

    public Set<Subarea> getSubareas() {
        return subareas;
    }

}
