package model;

import java.sql.Date;

public class PurchaseOrder {
    private int poId;
    private int supplierId;
    private Date expectedDate;
    private String status;

    public PurchaseOrder() {}

    public PurchaseOrder(int poId, int supplierId, Date expectedDate, String status) {
        this.poId = poId;
        this.supplierId = supplierId;
        this.expectedDate = expectedDate;
        this.status = status;
    }

    // Getters and Setters

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "poId=" + poId +
                ", supplierId=" + supplierId +
                ", expectedDate=" + expectedDate +
                ", status='" + status + '\'' +
                '}';
    }
}
