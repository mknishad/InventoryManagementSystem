package inventorymanagement;

import java.sql.*;
import dao.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ProductController {

    // save product to the database
    public static boolean saveProduct(String pname) {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean t = true;

        try {
            con = DatabaseHelper.getConnection();
            cstmt = con.prepareCall("{ CALL saveProduct(?) }");
            cstmt.setString(1, pname);
            t = cstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                cstmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return t;
    }
    
    // save purchase info to the database
    public static boolean savePurchase(String pname, String price, String date, String qty) {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean t = true;

        try {
            con = DatabaseHelper.getConnection();
            cstmt = con.prepareCall("{ CALL savePurchase(getProductId(?),?,?,?) }");
            cstmt.setString(1, pname);
            cstmt.setString(2, price);
            cstmt.setString(3, date);
            cstmt.setString(4, qty);
            t = cstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                cstmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return t;
    }
    
    // save sale info to the database
    public static boolean saveSale(String pname, String price, String date, String qty) {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean t = true;

        try {
            con = DatabaseHelper.getConnection();
            cstmt = con.prepareCall("{ CALL saveSale(getProductId(?),?,?,?) }");
            cstmt.setString(1, pname);
            cstmt.setString(2, price);
            cstmt.setString(3, date);
            cstmt.setString(4, qty);
            t = cstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                cstmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return t;
    }

    // load the items of product name combobox
    public static void loadCombo(JComboBox combo) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            con = DatabaseHelper.getConnection();
            cstmt = con.prepareCall("{ CALL listProduct() }");
            cstmt.execute();
            rs = cstmt.getResultSet();
            List pList = new ArrayList();
            while (rs.next()) {
                pList.add(rs.getString(1));
            }
            combo.setModel(new DefaultComboBoxModel(pList.toArray()));
            combo.insertItemAt("Select One", 0);
            combo.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                rs.close();
                cstmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
