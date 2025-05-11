package model;

import java.math.BigDecimal;

public class MenuItem {
    private int itemId;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal costPrice;
    private String category;
    private String status;
    
    public MenuItem() {
    }
    
    public MenuItem(int itemId, String name, BigDecimal unitPrice, BigDecimal costPrice, 
                   String category, String status) {
        this.itemId = itemId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.costPrice = costPrice;
        this.category = category;
        this.status = status;
    }
    
    // Getters and Setters
    public int getItemId() {
        return itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getItemName() {
        return name;
    }
    
    public void setItemName(String itemName) {
        this.name = itemName;
    }
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public BigDecimal getPrice() {
        return unitPrice;
    }
    
    public void setPrice(BigDecimal price) {
        this.unitPrice = price;
    }
    
    public BigDecimal getCostPrice() {
        return costPrice;
    }
    
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getCategoryId() {
        try {
            return Integer.parseInt(category);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public void setCategoryId(int categoryId) {
        this.category = String.valueOf(categoryId);
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean isAvailable() {
        return "active".equalsIgnoreCase(status);
    }
    
    public void setAvailable(boolean available) {
        this.status = available ? "active" : "inactive";
    }
    
    /**
     * Kiểm tra xem sản phẩm này có đang còn tồn tại và đang hoạt động
     * @return true nếu sản phẩm đang hoạt động
     */
    public boolean isActive() {
        return "active".equalsIgnoreCase(status);
    }
    
    /**
     * Kiểm tra xem sản phẩm có phải là đồ uống không
     * Nếu category có chứa từ khóa "drink", "beverage", "nước" thì đây là đồ uống
     * @return true nếu sản phẩm là đồ uống
     */
    public boolean isDrink() {
        if (category == null) return false;
        String lowerCategory = category.toLowerCase();
        return lowerCategory.contains("drink") || 
               lowerCategory.contains("beverage") || 
               lowerCategory.contains("nước") ||
               lowerCategory.contains("nuoc");
    }
    
    /**
     * Kiểm tra xem sản phẩm có phải là đồ ăn không
     * Nếu category có chứa từ khóa "food", "meal", "dish", "món", "thức ăn" thì đây là đồ ăn
     * @return true nếu sản phẩm là đồ ăn
     */
    public boolean isFood() {
        if (category == null) return false;
        String lowerCategory = category.toLowerCase();
        return lowerCategory.contains("food") || 
               lowerCategory.contains("meal") || 
               lowerCategory.contains("dish") ||
               lowerCategory.contains("món") ||
               lowerCategory.contains("thức ăn") ||
               lowerCategory.contains("thuc an");
    }
    
    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", costPrice=" + costPrice +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
} 