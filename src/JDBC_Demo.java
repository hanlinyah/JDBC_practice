import pojo.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_Demo {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://127.0.0.1:3306/jdbctest";
        String username="root";
        String password="root123";
        Connection conn = DriverManager.getConnection(url,username,password);
        String sql ="update account set money = 2000,name='小華' where id =1";
        Statement stmt=conn.createStatement();
        int count = stmt.executeUpdate(sql);
        System.out.println(count);
        sql="select * from account";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
            Account account = new Account(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("money")
                );
            System.out.println(account);

        }
        stmt.close();
        conn.close();

    }

}
