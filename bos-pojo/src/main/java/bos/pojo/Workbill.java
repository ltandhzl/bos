package bos.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "qp_workbill", schema = "bos-pom", catalog = "")
public class Workbill {
    //工单
    private String id;
    private Noticebill noticebill;
    private String type;//工单类型:新、追、改、销
    private String pickstate;//取件状态:未取件、取件中、已取件
    private Timestamp buildtime;
    private Integer attachbilltimes;
    private String remark;
    private Staff staff;

    public static final String  TYPE_1 = "新单";
    public static final String  TYPE_2 = "追单";
    public static final String  TYPE_3 = "改单";
    public static final String  TYPE_4 = "销单";

    public static final String  PICKSTATE_NO = "未取件";
    public static final String  PICKSTATE_RUNNING = "取件中";
    public static final String  PICKSTATE_YES = "已取件";

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "pickstate", nullable = true, length = 20)
    public String getPickstate() {
        return pickstate;
    }

    public void setPickstate(String pickstate) {
        this.pickstate = pickstate;
    }

    @Basic
    @Column(name = "buildtime", nullable = false)
    public Timestamp getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Timestamp buildtime) {
        this.buildtime = buildtime;
    }

    @Basic
    @Column(name = "attachbilltimes", nullable = true)
    public Integer getAttachbilltimes() {
        return attachbilltimes;
    }

    public void setAttachbilltimes(Integer attachbilltimes) {
        this.attachbilltimes = attachbilltimes;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Noticebill getNoticebill() {
        return noticebill;
    }

    public void setNoticebill(Noticebill noticebill) {
        this.noticebill = noticebill;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Workbill{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", pickstate='" + pickstate + '\'' +
                ", buildtime=" + buildtime +
                ", attachbilltimes=" + attachbilltimes +
                ", remark='" + remark + '\'' +
                ", staff=" + staff +
                '}';
    }
}
