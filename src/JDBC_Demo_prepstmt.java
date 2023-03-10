import pojo.Account;

import java.sql.*;

public class JDBC_Demo_prepstmt {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://127.0.0.1:3306/jdbctest";
        String username="root";
        String password="root123";
        Connection conn = DriverManager.getConnection(url,username,password);
        String sql ="update account set money = ?,name=? where id =?";
        double moneyrev=20;
        String namerev="mark";
        int idrev=1;
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setDouble(1,moneyrev);
        pstmt.setString(2,namerev);
        pstmt.setInt(3,idrev);
        int count = pstmt.executeUpdate();
        System.out.println(count);
        sql="select * from account";
        pstmt=conn.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            Account account = new Account(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("money")
                );
            System.out.println(account);

        }
        pstmt.close();
        conn.close();

    }

}
