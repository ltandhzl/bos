package bos.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "qp_workordermanage", schema = "bos-pom", catalog = "")
public class Workordermanage {
    private String id;
    private String arrivecity;
    private String product;
    private Integer num;
    private Double weight;
    private String floadreqr;
    private String prodtimelimit;
    private String prodtype;
    private String sendername;
    private String senderphone;
    private String senderaddr;
    private String receivername;
    private String receiverphone;
    private String receiveraddr;
    private Integer feeitemnum;
    private Double actlweit;
    private String vol;
    private String managerCheck;
    private Date updatetime;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "floadreqr", nullable = true, length = 255)
    public String getFloadreqr() {
        return floadreqr;
    }

    public void setFloadreqr(String floadreqr) {
        this.floadreqr = floadreqr;
    }

    @Basic
    @Column(name = "prodtimelimit", nullable = true, length = 40)
    public String getProdtimelimit() {
        return prodtimelimit;
    }

    public void setProdtimelimit(String prodtimelimit) {
        this.prodtimelimit = prodtimelimit;
    }

    @Basic
    @Column(name = "prodtype", nullable = true, length = 40)
    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype;
    }

    @Basic
    @Column(name = "sendername", nullable = true, length = 20)
    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    @Basic
    @Column(name = "senderphone", nullable = true, length = 20)
    public String getSenderphone() {
        return senderphone;
    }

    public void setSenderphone(String senderphone) {
        this.senderphone = senderphone;
    }

    @Basic
    @Column(name = "senderaddr", nullable = true, length = 200)
    public String getSenderaddr() {
        return senderaddr;
    }

    public void setSenderaddr(String senderaddr) {
        this.senderaddr = senderaddr;
    }

    @Basic
    @Column(name = "receivername", nullable = true, length = 20)
    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    @Basic
    @Column(name = "receiverphone", nullable = true, length = 20)
    public String getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone;
    }

    @Basic
    @Column(name = "receiveraddr", nullable = true, length = 200)
    public String getReceiveraddr() {
        return receiveraddr;
    }

    public void setReceiveraddr(String receiveraddr) {
        this.receiveraddr = receiveraddr;
    }

    @Basic
    @Column(name = "feeitemnum", nullable = true)
    public Integer getFeeitemnum() {
        return feeitemnum;
    }

    public void setFeeitemnum(Integer feeitemnum) {
        this.feeitemnum = feeitemnum;
    }

    @Basic
    @Column(name = "actlweit", nullable = true, precision = 0)
    public Double getActlweit() {
        return actlweit;
    }

    public void setActlweit(Double actlweit) {
        this.actlweit = actlweit;
    }

    @Basic
    @Column(name = "vol", nullable = true, length = 20)
    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    @Basic
    @Column(name = "managerCheck", nullable = true, length = 1)
    public String getManagerCheck() {
        return managerCheck;
    }

    public void setManagerCheck(String managerCheck) {
        this.managerCheck = managerCheck;
    }

    @Basic
    @Column(name = "updatetime", nullable = true)
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workordermanage that = (Workordermanage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(arrivecity, that.arrivecity) &&
                Objects.equals(product, that.product) &&
                Objects.equals(num, that.num) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(floadreqr, that.floadreqr) &&
                Objects.equals(prodtimelimit, that.prodtimelimit) &&
                Objects.equals(prodtype, that.prodtype) &&
                Objects.equals(sendername, that.sendername) &&
                Objects.equals(senderphone, that.senderphone) &&
                Objects.equals(senderaddr, that.senderaddr) &&
                Objects.equals(receivername, that.receivername) &&
                Objects.equals(receiverphone, that.receiverphone) &&
                Objects.equals(receiveraddr, that.receiveraddr) &&
                Objects.equals(feeitemnum, that.feeitemnum) &&
                Objects.equals(actlweit, that.actlweit) &&
                Objects.equals(vol, that.vol) &&
                Objects.equals(managerCheck, that.managerCheck) &&
                Objects.equals(updatetime, that.updatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, arrivecity, product, num, weight, floadreqr, prodtimelimit, prodtype, sendername, senderphone, senderaddr, receivername, receiverphone, receiveraddr, feeitemnum, actlweit, vol, managerCheck, updatetime);
    }
}
