package bos.pojo;

import java.util.HashSet;
import java.util.Set;


public class Staff {
    private String id;
    private String name;
    private String telephone;
    private String haspda="0";
    private Character deltag='0';
    private String station;
    private String standard;
    private Set<Decidedzone> decidedzones = new HashSet<Decidedzone>();

    public void setDecidedzones(Set<Decidedzone> decidedzones) {
        this.decidedzones = decidedzones;
    }

    public Set<Decidedzone> getDecidedzones() {
        return decidedzones;
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


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getHaspda() {
        return haspda;
    }

    public void setHaspda(String haspda) {
        this.haspda = haspda;
    }

    public Character getDeltag() {
        return deltag;
    }

    public void setDeltag(Character deltag) {
        this.deltag = deltag;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }


    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", haspda='" + haspda + '\'' +
                ", deltag='" + deltag + '\'' +
                ", station='" + station + '\'' +
                ", standard='" + standard + '\'' +
                '}';
    }
}