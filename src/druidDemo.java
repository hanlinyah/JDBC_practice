import com.alibaba.druid.pool.DruidDataSourceFactory;
import pojo.Brand;

import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class druidDemo {
    public static  Connection CONNECTION;
    public static Collection<Brand> Brands=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Properties prop=new Properties();
//        prop.load(new FileInputStream("jdbcPractice_1120309/src/druid.properties"));
        prop.load(new FileReader("src/druid.properties"));
//        System.out.println(prop.getProperty("url"));
        DataSource dataSource= DruidDataSourceFactory.createDataSource(prop);
        CONNECTION=dataSource.getConnection();
        System.out.println("----Select ALL----");
        selectAll();
//        System.out.println(System.getProperty("user.dir"));
        System.out.println("----Insert----");
        Brand brand=new Brand(
                5,
                "brandName5",
                "companyName5",
                555,
                "decription5",
                0
        );
        insert(brand);
        System.out.println("----UPDATE----");
            brand.setBrandName("brandName5_revise");
            brand.setCompanyName("companyName5_revise");
            brand.setDescription("decription5_revise");
            brand.setOrdered(55555);
            brand.setStatus(5);
        update(brand);
        System.out.println("----Delete----");
        delete(brand.getId());
        CONNECTION.close();

    }

    private static void delete(int id)throws Exception {
        String sql ="delete from tb_brand where id=?";
        PreparedStatement pstmt=CONNECTION.prepareStatement(sql);
        pstmt.setInt(1,id);
        int rs=pstmt.executeUpdate();
        if(rs>0){
            System.out.println("刪除成功(回傳值："+rs+")");
        }else {
            System.out.println("刪除失敗(回傳值："+rs+")");

        }
        pstmt.close();
        selectAll();
    }

    private static void update(Brand brand)  throws Exception{
        String sql ="update tb_brand set brand_name=?,company_name=?," +
                "ordered=?,description=?,status=? where id=?";
        PreparedStatement pstmt=CONNECTION.prepareStatement(sql);
        pstmt.setInt(6,brand.getId());
        pstmt.setString(1,brand.getBrandName());
        pstmt.setString(2,brand.getCompanyName());
        pstmt.setInt(3,brand.getOrdered());
        pstmt.setString(4,brand.getDescription());
        pstmt.setInt(5,brand.getStatus());
        int rs=pstmt.executeUpdate();
        if(rs>0){
            System.out.println("更新成功(回傳值："+rs+")");
        }else {
            System.out.println("更新失敗(回傳值："+rs+")");

        }
        pstmt.close();
        selectAll();
    }

    private static void insert(Brand brand) throws Exception {
        String sql ="Insert into tb_brand(id,brand_name,company_name," +
                "ordered,description,status) values(?,?,?,?,?,?)";
        PreparedStatement pstmt=CONNECTION.prepareStatement(sql);
        pstmt.setInt(1,brand.getId());
        pstmt.setString(2,brand.getBrandName());
        pstmt.setString(3,brand.getCompanyName());
        pstmt.setInt(4,brand.getOrdered());
        pstmt.setString(5,brand.getDescription());
        pstmt.setInt(6,brand.getStatus());
        int rs=pstmt.executeUpdate();
        if(rs>0){
            System.out.println("新增成功(回傳值："+rs+")");
        }else {
            System.out.println("新增失敗(回傳值："+rs+")");

        }
        pstmt.close();
        selectAll();

    }

    private static void selectAll() throws Exception {
        Brands.clear();
        String sql ="select * from tb_brand";
        PreparedStatement pstmt=CONNECTION.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            Brand brand=new Brand(
                    rs.getInt("id"),
                    rs.getString("brand_name"),
                    rs.getString("company_name"),
                    rs.getInt("ordered"),
                    rs.getString("description"),
                    rs.getInt("status")
            );
            Brands.add(brand);
        }
        rs.close();
        pstmt.close();
        System.out.println(Brands);

    }
}
