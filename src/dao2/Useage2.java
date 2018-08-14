package dao2;

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

            T_USER uesr = new T_USER();
            T_USER_COL.USER_ID.where(uesr, "user001");
            T_USER_COL.REG_DT.where(uesr, new Date());

        }
        System.out.println("------------------------");
        {
            M_COMPANY_COL.COMPANY_ID.getType();
            M_COMPANY_COL.COMPANY_XXXX.getType();

            M_COMPANY company = new M_COMPANY();
            M_COMPANY_COL.COMPANY_ID.where(company, "company-9999999");
            M_COMPANY_COL.COMPANY_XXXX.where(company, 7777777L);
        }
    }

}
