package DBDAO;

import CLS.ConnectionPool;
import CLS.DBManger;
import CLS.DBtools;
import DAO.CouponsDAO;
import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CouponsDBDAO implements CouponsDAO {
    ConnectionPool connectionPool;

    @Override
    public void addCoupon(Coupon coupon) {
        String ADD_COUPON = "INSERT INTO `jb_project`.`coupons` (`id`, `COMPANY_ID`, `CATEGORY_ID`, `TITLE`, `DESCRIPTION`, `START_DATE`, `END_DATE`, `AMOUNT`, `PRICE`) VALUES (?,?,?,?,?,?,?,?,?);";
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getId());
        params.put(2, coupon.getCompanyID());
        params.put(3, coupon.getCategory().getCategoryNumber());
        params.put(4, coupon.getTitle());
        params.put(5, coupon.getDescription());
        params.put(6, coupon.getStartDate());
        params.put(7, coupon.getEndDate());
        params.put(8, coupon.getAmount());
        params.put(9, coupon.getPrice());
        boolean flag = DBtools.runQuery(ADD_COUPON, params);
        if (flag)
            System.out.println("The Coupon has been added successfully.");
        else
            System.out.println("ERROR :The Coupon not added.");

    }

    @Override
    public void updateCoupon(Coupon coupon) {
        final String id = String.valueOf(coupon.getId());
        final String UPDATE = "UPDATE `jb_project`.`coupons` SET" +
                " `id`=?, `COMPANY_ID`=?, `CATEGORY_ID`=?, `TITLE`=?, `DESCRIPTION`=?, `START_DATE`=?, `END_DATE`=?, `AMOUNT`=?, `PRICE`=? WHERE (`id` = " + id + ");";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getId());
        params.put(2, coupon.getCompanyID());
        params.put(3, coupon.getCategory().getCategoryNumber());
        params.put(4, coupon.getTitle());
        params.put(5, coupon.getDescription());
        params.put(6, coupon.getStartDate());
        params.put(7, coupon.getEndDate());
        params.put(8, coupon.getAmount());
        params.put(9, coupon.getPrice());
        boolean flag = DBtools.runQuery(UPDATE, params);
        if (flag)
            System.out.println("The Coupon has been Updated successfully.");
        else
            System.out.println("The Coupon not exists.");

    }

    @Override
    public void deleteCoupon(int couponID) {
        final String DELETE = "DELETE FROM `jb_project`.`coupons` WHERE id = " + couponID + ";";
        boolean flag = DBtools.runQuery(DELETE);
        if (flag)
            System.out.println("The Coupon deleted successfully.");
        else
            System.out.println("ERROR ,the Coupon not deleted.");

    }

    @Override
    public ArrayList<Coupon> getAllCoupons() throws SQLException {

        final String selectAll = "SELECT * FROM `jb_project`.`coupons`;";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet resultSet = DBtools.runQueryForResult(selectAll, params);
        ArrayList<Coupon> coupons = new ArrayList<>();
        while (resultSet.next()) {
            int ID = resultSet.getInt(1);
            int companyID = resultSet.getInt(2);
            int categoryID = resultSet.getInt(3);
            String title = resultSet.getString(4);
            String desc = resultSet.getString(5);
            Date startDate = resultSet.getDate(6);
            Date endDate = resultSet.getDate(7);
            int amount = resultSet.getInt(8);
            Double price = resultSet.getDouble(9);
//            String img = resultSet.getString(10);
            Category category = Category.getCategoryByNumber(categoryID);
            Coupon coupon = new Coupon(ID, companyID, category, title, desc, startDate, endDate, amount, price, "");

            coupons.add(coupon);
        }
        return coupons;
    }

    @Override
    public Coupon getOneCoupon(int couponID) throws SQLException {
        final String ONE_COUPONS = "SELECT * FROM `jb_project`.`coupons` WHERE id = " + couponID + ";";
        Map<Integer, Object> params = new HashMap<>();
        ResultSet resultSet = DBtools.runQueryForResult(ONE_COUPONS, params);
        while (resultSet.next()) {

            int ID = resultSet.getInt(1);
            int companyID = resultSet.getInt(2);
            int categoryID = resultSet.getInt(3);
            String title = resultSet.getString(4);
            String desc = resultSet.getString(5);
            Date startDate = resultSet.getDate(6);
            Date endDate = resultSet.getDate(7);
            int amount = resultSet.getInt(8);
            Double price = resultSet.getDouble(9);
//            String img = resultSet.getString(10);
            Category category = Category.getCategoryByNumber(categoryID);
            Coupon coupon = new Coupon(ID, companyID, category, title, desc, startDate, endDate, amount, price, "");
            return coupon;

        }
        return null;
    }


    @Override
    public void addCouponPurchase(int customerlD, int couponID) {

    }

    @Override
    public void deleteCouponPurchase(int customerlD, int couponID) {


    }
}



