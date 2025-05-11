package model;

import java.sql.Timestamp;

public class MonthlyFinancialSummary {
    private int id;
    private String reportMonth;
    private double monthlyRevenue;
    private double monthlyPurchaseCost;
    private double monthlySalaryCost;
    private double monthlyRent;
    private double monthlyProfit;
    private Timestamp calculatedAt;
    
    public MonthlyFinancialSummary() {
    }
    
    public MonthlyFinancialSummary(int id, String reportMonth, double monthlyRevenue, double monthlyPurchaseCost,
                                  double monthlySalaryCost, double monthlyRent, double monthlyProfit, Timestamp calculatedAt) {
        this.id = id;
        this.reportMonth = reportMonth;
        this.monthlyRevenue = monthlyRevenue;
        this.monthlyPurchaseCost = monthlyPurchaseCost;
        this.monthlySalaryCost = monthlySalaryCost;
        this.monthlyRent = monthlyRent;
        this.monthlyProfit = monthlyProfit;
        this.calculatedAt = calculatedAt;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getReportMonth() {
        return reportMonth;
    }
    
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }
    
    public double getMonthlyRevenue() {
        return monthlyRevenue;
    }
    
    public void setMonthlyRevenue(double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }
    
    public double getMonthlyPurchaseCost() {
        return monthlyPurchaseCost;
    }
    
    public void setMonthlyPurchaseCost(double monthlyPurchaseCost) {
        this.monthlyPurchaseCost = monthlyPurchaseCost;
    }
    
    public double getMonthlySalaryCost() {
        return monthlySalaryCost;
    }
    
    public void setMonthlySalaryCost(double monthlySalaryCost) {
        this.monthlySalaryCost = monthlySalaryCost;
    }
    
    public double getMonthlyRent() {
        return monthlyRent;
    }
    
    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }
    
    public double getMonthlyProfit() {
        return monthlyProfit;
    }
    
    public void setMonthlyProfit(double monthlyProfit) {
        this.monthlyProfit = monthlyProfit;
    }
    
    public Timestamp getCalculatedAt() {
        return calculatedAt;
    }
    
    public void setCalculatedAt(Timestamp calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
    
    @Override
    public String toString() {
        return "MonthlyFinancialSummary{" +
                "id=" + id +
                ", reportMonth='" + reportMonth + '\'' +
                ", monthlyRevenue=" + monthlyRevenue +
                ", monthlyPurchaseCost=" + monthlyPurchaseCost +
                ", monthlySalaryCost=" + monthlySalaryCost +
                ", monthlyRent=" + monthlyRent +
                ", monthlyProfit=" + monthlyProfit +
                ", calculatedAt=" + calculatedAt +
                '}';
    }
}
