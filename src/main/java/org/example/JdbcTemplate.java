package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    public void executeUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = ConnectionManager.getConnection();
            /*String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?)";*/
            pstmt = con.prepareStatement(sql);
            /*pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());*/
            pss.setter(pstmt);

            pstmt.executeUpdate();
        }finally {
            if(pstmt != null){
                pstmt.close();
            }

            if(con != null){
                con.close();
            }
        }
    }

    public Object executeQuery(String userId, String sql, PreparedStatementSetter pss, RowMapper rm) throws Exception{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try{
            con = ConnectionManager.getConnection();
            /*String sql = "SELECT * FROM USERS WHERE userId = ?";*/
            pstmt = con.prepareStatement(sql);
            /*pstmt.setString(1, userId);*/
            pss.setter(pstmt);

            rs = pstmt.executeQuery();


            if(rs.next()){
                /*user = new User(
                    rs.getString("userId"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"));*/
                user = rm.map(rs);
            }
        }finally {
            if(rs != null){
                rs.close();
            }

            if(pstmt != null){
                pstmt.close();
            }

            if(con != null){
                con.close();
            }
        }
        return user;
    }

}
