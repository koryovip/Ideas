package dao2;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public static final <D> List<D> select(Class<D> dtoClass, Connection conn, String sql, TblBase<?> tbl/*,List<Object> params*/) throws SQLException {
        // Connection conn = getConn();
        System.out.println("▲ " + sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.clearBatch();
        ps.clearParameters();
        ps.clearWarnings();
        int index = 1;
        for (Object param : tbl.getParams2()) {
            ps.setObject(index++, param);
        }
        ResultSet rs = ps.executeQuery();
        List<D> list = putResult(rs, dtoClass);
        /*while (rs.next()) {
            int colCount = rs.getMetaData().getColumnCount();
            for (int ii = 1; ii <= colCount; ii++) {
                System.out.println(rs.getString(ii));
            }
        }*/
        rs.close();
        ps.close();
        //conn.close();
        return list;
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

    private static final <D> List<D> putResult(ResultSet rs, Class<D> dtoClass) {
        List<D> result = new ArrayList<D>(0);
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                D dto = dtoClass.newInstance();
                for (int ii = 1; ii <= columnCount; ii++) {
                    String columnName = metaData.getColumnName(ii);
                    Field field = getField(dtoClass, columnName);
                    if (field == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    try {
                        field.set(dto, rs.getObject(ii));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        System.err.println(e.getMessage());
                    }
                }
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return result;
    }

    private static final Field getField(Class<?> dtoClass, String columnName) {
        try {
            return dtoClass.getDeclaredField(columnName);
        } catch (NoSuchFieldException | SecurityException e1) {
            try {
                return dtoClass.getDeclaredField(underlineToHump(columnName, true));
            } catch (NoSuchFieldException | SecurityException e2) {
                // NoSuchField. is ok.
                return null;
            }
        }
    }

    private static final String underlineToHump(String para, boolean up) {
        StringBuilder result = new StringBuilder();
        String a[] = para.toUpperCase().split("_");
        for (String s : a) {
            if (up && result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
