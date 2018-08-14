package dao2;

import static dao2.def.M_COMPANY_COL.COMPANY_ID;
import static dao2.def.M_COMPANY_COL.COMPANY_XXXX;
import static dao2.def.T_USER_COL.REG_DT;
import static dao2.def.T_USER_COL.SCORE;
import static dao2.def.T_USER_COL.USER_ID;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import dao2.def.M_COMPANY;
import dao2.def.T_USER;

public class Useage2 {

    public static void main(String[] args) throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 0, 1, 0, 0, 0);

        USER_ID.getType();
        REG_DT.getType();

        Connection conn = DB.getConn();
        {
            T_USER user = new T_USER();
            USER_ID.set("user-001", user);
            SCORE.set(BigDecimal.TEN, user);
            REG_DT.set(cal.getTime(), user);
            int insertCount = DB.insert(conn, user);
            System.out.println(insertCount);
        }
        {
            T_USER user = new T_USER();
            USER_ID.set("user-002", user);
            SCORE.set(BigDecimal.TEN, user);
            REG_DT.set(cal.getTime(), user);
            int insertCount = DB.insert(conn, user);
            System.out.println(insertCount);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            // USER_ID.where(user, "user-001");
            // SCORE.where(user, BigDecimal.TEN);
            DB.select(conn, user.selectAllColumns(), user.getParams2());
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            // USER_ID.where(user, "user-001");
            SCORE.where(user, BigDecimal.TEN);
            DB.select(conn, user.selectCount(USER_ID), user.getParams2());
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            SCORE.set(BigDecimal.ZERO, user);
            REG_DT.set(new Date(), user);
            USER_ID.where(user, "user-001");
            int updateCount = DB.update(conn, user);
            System.out.println(updateCount);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            USER_ID.where(user, "user-001");
            // SCORE.where(user, BigDecimal.TEN);
            DB.select(conn, user.select(USER_ID, REG_DT), user.getParams2());
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            USER_ID.where(user, "user-001");
            // REG_DT.where(user, new Date());
            int deleteCount = DB.delete(conn, user);
            System.out.println(deleteCount);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            user.selectByPrimaryKey("dsdf", null);
            user.showParam();
        }
        System.out.println("------------------------------------------------------------------------");
        {
            COMPANY_ID.getType();
            COMPANY_XXXX.getType();

            M_COMPANY company = new M_COMPANY();
            // T_USER_COL.COMPANY_ID.col(company);
            COMPANY_ID.where(company, "company-9999999");
            COMPANY_XXXX.where(company, 7777777L);
            System.out.println(company.select(COMPANY_ID));
            company.showParam();
        }
    }

}
