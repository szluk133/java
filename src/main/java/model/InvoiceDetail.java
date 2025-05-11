package model;

import java.math.BigDecimal;

public class InvoiceDetail {
    private int detailId;       // Tương ứng với trường id trong DB
    private int invoiceId;      // invoice_id
    private int itemId;         // item_id trong database
    private int quantity;       // quantity
    private BigDecimal unitPrice; // unit_price
    private BigDecimal lineTotal; // line_total trong database
    
    // Thông tin bổ sung (không lưu trong DB)
    private MenuItem menuItem;  // Thông tin chi tiết về sản phẩm

    // Constructors
    public InvoiceDetail() {
    }

    public InvoiceDetail(int detailId, int invoiceId, int itemId, int quantity, 
                        BigDecimal unitPrice, BigDecimal lineTotal) {
        this.detailId = detailId;
        this.invoiceId = invoiceId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }

    // Getters and Setters
    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    // Để tương thích với code cũ
    public int getProductId() {
        return itemId;
    }

    public void setProductId(int productId) {
        this.itemId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
    
    // Để tương thích với code cũ
    public BigDecimal getSubtotal() {
        return lineTotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.lineTotal = subtotal;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
    
    /**
     * Tính toán thành tiền dựa trên số lượng và đơn giá
     * @return thành tiền = số lượng * đơn giá
     */
    public BigDecimal calculateLineTotal() {
        if (quantity > 0 && unitPrice != null) {
            return unitPrice.multiply(new BigDecimal(quantity));
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Chi tiết hóa đơn [ID: " + detailId +
                ", Hóa đơn: " + invoiceId +
                ", Sản phẩm: " + itemId +
                ", Số lượng: " + quantity +
                ", Đơn giá: " + unitPrice +
                ", Thành tiền: " + lineTotal + "]";
    }
} 