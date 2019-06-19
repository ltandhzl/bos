package bos.pojo;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "qp_noticebill", schema = "bos-pom", catalog = "")
public class Noticebill {
    //业务通知单
    private String id;
    private Staff staff;
    private String customerId;
    private String customerName;
    private String delegater;
    private String telephone;
    private String pickaddress;
    private String arrivecity;
    private String product;
    private Date pickdate;
    private Integer num;
    private Double weight;
    private String volume;
    private String remark;
    private String ordertype;//分单类型：自动分单、人工分单
    private User user;
    private Set workbills = new HashSet(0);

    public static final String  ORDERTYPE_AUTO = "自动分单";
    public static final String  ORDERTYPE_MAN = "人工分单";

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Basic
    @Column(name = "customer_id", nullable = true, length = 32)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "customer_name", nullable = true, length = 20)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "delegater", nullable = true, length = 20)
    public String getDelegater() {
        return delegater;
    }

    public void setDelegater(String delegater) {
        this.delegater = delegater;
    }

    @Basic
    @Column(name = "telephone", nullable = true, length = 20)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "pickaddress", nullable = true, length = 200)
    public String getPickaddress() {
        return pickaddress;
    }

    public void setPickaddress(String pickaddress) {
        this.pickaddress = pickaddress;
    }

    @Basic
    @Column(name = "arrivecity", nullable = true, length = 20)
    public String getArrivecity() {
        return arrivecity;
    }

    public void setArrivecity(String arrivecity) {
        this.arrivecity = arrivecity;
    }

    @Basic
    @Column(name = "product", nullable = true, length = 20)
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Basic
    @Column(name = "pickdate", nullable = true)
    public Date getPickdate() {
        return pickdate;
    }

    public void setPickdate(Date pickdate) {
        this.pickdate = pickdate;
    }

    @Basic
    @Column(name = "num", nullable = true)
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Basic
    @Column(name = "weight", nullable = true, precision = 0)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "volume", nullable = true, length = 20)
    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "ordertype", nullable = true, length = 20)
    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set getWorkbills() {
        return workbills;
    }

    public void setWorkbills(Set workbills) {
        this.workbills = workbills;
    }

    @Override
    public String toString() {
        return "Noticebill{" +
                "id='" + id + '\'' +
                ", staff=" + staff +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", delegater='" + delegater + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pickaddress='" + pickaddress + '\'' +
                ", arrivecity='" + arrivecity + '\'' +
                ", product='" + product + '\'' +
                ", pickdate=" + pickdate +
                ", num=" + num +
                ", weight=" + weight +
                ", volume='" + volume + '\'' +
                ", remark='" + remark + '\'' +
                ", ordertype='" + ordertype + '\'' +
                ", user=" + user +
                ", workbills=" + workbills +
                '}';
    }
}
