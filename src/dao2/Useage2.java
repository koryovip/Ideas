package dao2;

import static dao2.def.M_COMPANY_COL.COMPANY_ID;
import static dao2.def.M_COMPANY_COL.COMPANY_XXXX;
import static dao2.def.T_USER_COL.REG_DT;
import static dao2.def.T_USER_COL.SCORE;
import static dao2.def.T_USER_COL.USER_ID;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao2.def.M_COMPANY;
import dao2.def.T_USER;
import dao2.def.T_USER_DTO;

public class Useage2 {

    public static void main(String[] args) throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 0, 1, 0, 0, 0);

        USER_ID.getType();
        REG_DT.getType();

        // QueryRunner run = new QueryRunner();
        Connection conn = DB.getConn();
        {
            T_USER user = T_USER.$();
            USER_ID.set("user-001", user);
            SCORE.set(10, user);
            REG_DT.set(cal.getTime(), user);
            int insertCount = DB.insert(conn, user);
            System.out.println(insertCount);
        }
        {
            T_USER user = T_USER.$();
            USER_ID.set("user-002", user);
            SCORE.set(10, user);
            REG_DT.set(cal.getTime(), user);
            int insertCount = DB.insert(conn, user);
            System.out.println(insertCount);

            USER_ID.set("user-003", user);
            DB.insert(conn, user);
            USER_ID.set("user-004", user);
            DB.insert(conn, user);
            USER_ID.set("user-005", user);
            DB.insert(conn, user);
            USER_ID.set("user-006", user);
            DB.insert(conn, user);
            USER_ID.set("user-100", user);
            DB.insert(conn, user);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = T_USER.$();
            // USER_ID.where(user, "user-001");
            SCORE.whereDesc(user, 10);
            SCORE.where$01(user, 101);
            SCORE.where$10(user, 102);
            USER_ID.whereIn(user, "1");
            SCORE.whereIn(user, 1, 2, 3);
            SCORE.where$AB(user, 103);
            SCORE.where$0eq1(user, 104);
            SCORE.where$1eq0(user, 105);
            USER_ID.desc(user);
            SCORE.asc(user);
            REG_DT.isNull(user);
            List<T_USER_DTO> list = DB.select(T_USER_DTO.class, conn, user.selectColumnAll(), user);
            for (T_USER_DTO ntt : list) {
                System.out.println(ntt);
            }

            // ResultSetHandler<List<T_USER_DTO>> handler = new BeanListHandler<T_USER_DTO>(T_USER_DTO.class);
            // List<T_USER_DTO> list = run.query(conn, user.selectAllColumns(), handler, user.getParams2().toArray());

        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = T_USER.$();
            USER_ID.whereLike(user, "user-00%");
            SCORE.where(user, 10);
            SCORE.isNOTNull(user);
            long count = DB.count(conn, user.selectCount(USER_ID), user);
            System.out.println(count);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = T_USER.$();
            SCORE.set(0, user);
            REG_DT.set(new Date(), user);
            USER_ID.where(user, "user-001");
            int updateCount = DB.update(conn, user);
            System.out.println(updateCount);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = T_USER.$();
            USER_ID.where(user, "user-001");
            // SCORE.where(user, BigDecimal.TEN);
            DB.select(T_USER_DTO.class, conn, user.select(USER_ID, REG_DT), user);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = T_USER.$();
            USER_ID.where(user, "user-001");
            // REG_DT.where(user, new Date());
            int deleteCount = DB.delete(conn, user);
            System.out.println(deleteCount);
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = T_USER.$();
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
