package model;

public class Inventory {
    private int itemId;
    private int reorderLevel;
    private int soldQuantity;
    private int totalQuantity;
    private int stockOnHand;
    private String itemName;

    public Inventory(int itemId, int reorderLevel, int soldQuantity, int totalQuantity, int stockOnHand) {
        this.itemId = itemId;
        this.reorderLevel = reorderLevel;
        this.soldQuantity = soldQuantity;
        this.totalQuantity = totalQuantity;
        this.stockOnHand = stockOnHand;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getStockOnHand() {
        return stockOnHand;
    }

    public void setStockOnHand(int stockOnHand) {
        this.stockOnHand = stockOnHand;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "Mã sản phẩm: " + itemId +
                ", Tên sản phẩm: " + (itemName != null ? itemName : "Chưa xác định") +
                ", Ngưỡng đặt hàng lại: " + reorderLevel +
                ", Đã bán: " + soldQuantity +
                ", Tổng số lượng: " + totalQuantity +
                ", Tồn kho: " + stockOnHand;
    }
}
