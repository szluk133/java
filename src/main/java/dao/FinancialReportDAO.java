package dao;

import database.JDBCUtil;
import model.MonthlyFinancialSummary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinancialReportDAO {
    
    /**
     * Retrieve all monthly financial summary records ordered by date
     * @return List of MonthlyFinancialSummary objects
     */
    public List<MonthlyFinancialSummary> getAllMonthlyReports() {
        List<MonthlyFinancialSummary> reports = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM monthly_financial_summary_table ORDER BY report_month DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                MonthlyFinancialSummary report = new MonthlyFinancialSummary();
                report.setId(rs.getInt("id"));
                report.setReportMonth(rs.getString("report_month"));
                report.setMonthlyRevenue(rs.getDouble("monthly_revenue"));
                report.setMonthlyPurchaseCost(rs.getDouble("monthly_purchase_cost"));
                report.setMonthlySalaryCost(rs.getDouble("monthly_salary_cost"));
                report.setMonthlyRent(rs.getDouble("monthly_rent"));
                report.setMonthlyProfit(rs.getDouble("monthly_profit"));
                report.setCalculatedAt(rs.getTimestamp("calculated_at"));
                
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                JDBCUtil.closeConnection(conn);
            }
        }
        
        return reports;
    }
    
    /**
     * Get financial summary for a specific month
     * @param month Month in YYYY-MM format
     * @return MonthlyFinancialSummary object for the requested month
     */
    public MonthlyFinancialSummary getMonthlyReport(String month) {
        MonthlyFinancialSummary report = null;
        Connection conn = null;
        
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM monthly_financial_summary_table WHERE report_month = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, month);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                report = new MonthlyFinancialSummary();
                report.setId(rs.getInt("id"));
                report.setReportMonth(rs.getString("report_month"));
                report.setMonthlyRevenue(rs.getDouble("monthly_revenue"));
                report.setMonthlyPurchaseCost(rs.getDouble("monthly_purchase_cost"));
                report.setMonthlySalaryCost(rs.getDouble("monthly_salary_cost"));
                report.setMonthlyRent(rs.getDouble("monthly_rent"));
                report.setMonthlyProfit(rs.getDouble("monthly_profit"));
                report.setCalculatedAt(rs.getTimestamp("calculated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                JDBCUtil.closeConnection(conn);
            }
        }
        
        return report;
    }
    
    /**
     * Get yearly summary data for a specific year
     * @param year The year to get summary for (YYYY format)
     * @return Object array with yearly totals [revenue, purchase, salary, rent, profit]
     */
    public Object[] getYearlySummary(String year) {
        Object[] summary = new Object[5];
        Connection conn = null;
        
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT SUM(monthly_revenue) as total_revenue, " +
                         "SUM(monthly_purchase_cost) as total_purchase, " +
                         "SUM(monthly_salary_cost) as total_salary, " +
                         "SUM(monthly_rent) as total_rent, " +
                         "SUM(monthly_profit) as total_profit " +
                         "FROM monthly_financial_summary_table " +
                         "WHERE report_month LIKE ?";
                         
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, year + "-%");
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                summary[0] = rs.getDouble("total_revenue");
                summary[1] = rs.getDouble("total_purchase");
                summary[2] = rs.getDouble("total_salary");
                summary[3] = rs.getDouble("total_rent");
                summary[4] = rs.getDouble("total_profit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                JDBCUtil.closeConnection(conn);
            }
        }
        
        return summary;
    }
} 