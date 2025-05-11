package model;

import java.sql.Date;

public class PurchaseOrderDetail {
    private int podId;
    private int poId;
    private int itemId;
    private int quantity;
    private double unitPrice;
    private double lineTotal;
    private Date receivedDate;

    public PurchaseOrderDetail() {}

    public PurchaseOrderDetail(int podId, int poId, int itemId, int quantity, double unitPrice, Date receivedDate) {
        this.podId = podId;
        this.poId = poId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = quantity * unitPrice;
        this.receivedDate = receivedDate;
    }

    public int getPodId() {
        return podId;
    }

    public void setPodId(int podId) {
        this.podId = podId;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.lineTotal = quantity * unitPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.lineTotal = quantity * unitPrice;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetail{" +
                "podId=" + podId +
                ", poId=" + poId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineTotal=" + lineTotal +
                ", receivedDate=" + receivedDate +
                '}';
    }
}
