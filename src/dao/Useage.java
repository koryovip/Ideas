package dao;

import java.util.Date;

import dao.def.T_BOOK;
import dao.def.T_BOOK_COL;
import dao.def.T_USER;
import dao.def.T_USER_COL;

public class Useage {

    public static void main(String[] args) {
        T_USER tUser = new T_USER();
        tUser
                //                                .where(T_USER_COL.ID) //
                .col(T_USER_COL.ID) //
                .where(T_USER_COL.ID, "userid-0192") //
                .where(T_USER_COL.REG_DT, 100L) //
                .where(T_USER_COL.LAST_UPD, new Date()) //
        //                .where(T_USER_COL.PASS, 0) //
        ;
        System.out.println(tUser.select());
        tUser.showParam();

        new T_BOOK() //
                .where(T_BOOK_COL.ID, "") //
        //                .where(T_USER_COL.ID, "") //
        ;

    }

}
