package dao2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import dao2.def.M_COMPANY;
import dao2.def.M_COMPANY_COL;
import dao2.def.T_USER;
import dao2.def.T_USER_COL;

public class Useage2 {

    public static void main(String[] args) throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 0, 1, 0, 0, 0);
        T_USER_COL.USER_ID.getType();
        T_USER_COL.REG_DT.getType();
        Connection conn = DB.getConn();
        {
            T_USER user = new T_USER();
            T_USER_COL.USER_ID.set("user-001", user);
            T_USER_COL.SCORE.set(BigDecimal.TEN, user);
            T_USER_COL.REG_DT.set(cal.getTime(), user);
            int insertCount = DB.insert(conn, user);
            System.out.println(insertCount);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            T_USER_COL.USER_ID.where(user, "user-001");
            T_USER_COL.SCORE.where(user, BigDecimal.TEN);
            // T_USER_COL.SCORE.where(uesr, new Date()); // 型の不一致コンパイルエラー
            // M_COMPANY_COL.COMPANY_ID.where(uesr, ""); // tbl
            DB.select(conn, user.select(T_USER_COL.USER_ID, T_USER_COL.REG_DT, T_USER_COL.SCORE), user.getParams2());
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            T_USER_COL.SCORE.set(BigDecimal.ZERO, user);
            T_USER_COL.REG_DT.set(new Date(), user);
            T_USER_COL.USER_ID.where(user, "user-001");
            int updateCount = DB.update(conn, user);
            System.out.println(updateCount);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            T_USER_COL.USER_ID.where(user, "user-001");
            // T_USER_COL.SCORE.where(user, BigDecimal.TEN);
            DB.select(conn, user.select(T_USER_COL.USER_ID, T_USER_COL.REG_DT, T_USER_COL.SCORE), user.getParams2());
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            T_USER_COL.USER_ID.where(user, "user-001");
            // T_USER_COL.REG_DT.where(user, new Date());
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
            M_COMPANY_COL.COMPANY_ID.getType();
            M_COMPANY_COL.COMPANY_XXXX.getType();

            M_COMPANY company = new M_COMPANY();
            // M_COMPANY_COL.COMPANY_ID.col(company);
            M_COMPANY_COL.COMPANY_ID.where(company, "company-9999999");
            M_COMPANY_COL.COMPANY_XXXX.where(company, 7777777L);
            System.out.println(company.select(M_COMPANY_COL.COMPANY_ID));
            company.showParam();
        }
    }

}
