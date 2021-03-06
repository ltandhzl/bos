package bos.pojo;

import java.util.HashSet;
import java.util.Set;


public class Region {
    private String id;
    private String province;
    private String city;
    private String district;
    private String postcode;
    private String shortcode;
    private String citycode;
    private Character delflag='0';

    public String getName(){
        return province+city+district;
    }
    private Set<Subarea> subareas=new HashSet<Subarea>();

    public void setSubareas(Set<Subarea> subareas) {
        this.subareas = subareas;
    }

    public Set<Subarea> getSubareas() {
        return subareas;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }


    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }
    public void setDelflag(Character delflag) {
        this.delflag = delflag;
    }

    public Character getDelflag() {
        return delflag;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", postcode='" + postcode + '\'' +
                ", shortcode='" + shortcode + '\'' +
                ", citycode='" + citycode + '\'' +
                ", delflag=" + delflag +
                ", subareas=" + subareas +
                '}';
    }
}
