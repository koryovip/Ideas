package dao2;

import java.math.BigDecimal;
import java.util.Date;

import dao2.def.M_COMPANY;
import dao2.def.M_COMPANY_COL;
import dao2.def.T_USER;
import dao2.def.T_USER_COL;

public class Useage2 {

    public static void main(String[] args) {
        {
            T_USER_COL.USER_ID.getType();
            T_USER_COL.REG_DT.getType();

            T_USER user = new T_USER();
            //T_USER_COL.USER_ID.col(user);
            //T_USER_COL.SCORE.col(user);
            T_USER_COL.SCORE.set(user, new BigDecimal(1000));

            T_USER_COL.USER_ID.where(user, "ssssss");
            T_USER_COL.REG_DT.where(user, new Date());
            // T_USER_COL.SCORE.where(uesr, new Date()); // 型の不一致コンパイルエラー
            // M_COMPANY_COL.COMPANY_ID.where(uesr, ""); // tbl
            System.out.println(user.select(T_USER_COL.USER_ID, T_USER_COL.REG_DT, T_USER_COL.USER_ID, M_COMPANY_COL.COMPANY_ID));
            user.showParam();
        }
        System.out.println("------------------------------------------------------------------------");
        {
            T_USER user = new T_USER();
            T_USER_COL.USER_ID.where(user, "user001");
            T_USER_COL.REG_DT.where(user, new Date());
            System.out.println(user.delete());
            user.showParam();
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
            M_COMPANY_COL.COMPANY_ID.col(company);
            M_COMPANY_COL.COMPANY_ID.where(company, "company-9999999");
            M_COMPANY_COL.COMPANY_XXXX.where(company, 7777777L);
            System.out.println(company.select());
            company.showParam();
        }
    }

}
