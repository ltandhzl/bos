package bos.utils;


import org.hibernate.criterion.DetachedCriteria;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    private int currentPage;
    private int pageSize;
    private int total;
    private List rows=new ArrayList();
    private DetachedCriteria detachedCriteria;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public List getRows() {
        return rows;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }
}
