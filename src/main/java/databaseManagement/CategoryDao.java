package databaseManagement;

import org.json.simple.JSONArray;
import java.sql.*;

public class CategoryDao {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/export_library_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "password";

    public JSONArray categories() {
        Connection conn = null;
        Statement stmt = null;
        JSONArray categories = new JSONArray();
        try{
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT name FROM categories";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString("name");
                categories.add(name);
                System.out.println("cat: " + name);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se){
            se.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2){ }
            try{
                if(conn!=null)
                    conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return categories;
    }

}
