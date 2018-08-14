package dao2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao2.base.TblBase;

public class DB {

    public static final int insert(Connection conn, TblBase<?> tbl) throws SQLException {
        return change(conn, tbl.insert(), tbl.getParams1());
    }

    public static final int update(Connection conn, TblBase<?> tbl) throws SQLException {
        return change(conn, tbl.update(), tbl.getParams3());
    }

    public static final int delete(Connection conn, TblBase<?> tbl) throws SQLException {
        return change(conn, tbl.delete(), tbl.getParams2());
    }

    private static final int change(Connection conn, String sql, List<Object> params) throws SQLException {
        System.out.println("■ " + sql);
        // Connection conn = getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.clearBatch();
        ps.clearParameters();
        ps.clearWarnings();
        int index = 1;
        for (Object param : params) {
            ps.setObject(index++, param);
        }
        int updateCount = ps.executeUpdate();
        ps.close();
        conn.commit();
        //conn.close();
        return updateCount;
    }

    public static final void select(Connection conn, String sql, List<Object> params) throws SQLException {
        // Connection conn = getConn();
        System.out.println("▲ " + sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.clearBatch();
        ps.clearParameters();
        ps.clearWarnings();
        int index = 1;
        for (Object param : params) {
            ps.setObject(index++, param);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int colCount = rs.getMetaData().getColumnCount();
            for (int ii = 1; ii <= colCount; ii++) {
                System.out.println(rs.getString(ii));
            }
        }
        rs.close();
        ps.close();
        //conn.close();
    }

    public static final Connection getConn() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        statement.execute("pragma sync_mode=off");
        statement.execute("pragma journal_mode=Persist");
        statement.setQueryTimeout(30); // set timeout to 30 sec.
        // statement.executeUpdate("drop table if exists T_USER");
        statement.executeUpdate("create table if not exists T_USER (USER_ID string PRIMARY KEY, REG_DT string, SCORE number)");
        statement.close();
        conn.commit();
        return conn;
    }

}
