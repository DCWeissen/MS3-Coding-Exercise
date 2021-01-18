package CodingExercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DAO {
    static DAO shared = new DAO();
    private Connection conn = null;
 
    public void connectToDataBase(String fileName) {
        String path = "jdbc:sqlite:.\\" + fileName.replace(".csv", ".db");

        try {  
            conn = DriverManager.getConnection(path);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public void createTable(String[] headers) {
        String table;
        
        table = "CREATE TABLE IF NOT EXISTS Data (\n";  
        
        for (int i= 0; i < headers.length - 1; i++)
            table = table + " " + headers[i] + " TEXT, \n";

        table = table + " " + headers[headers.length -1] + " TEXT\n);";
        
        try {   
            Statement stmt = conn.createStatement();
            stmt.execute(table);
        } 
        catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
    
    public void insertRecord(String[] data, String[] headers) {
        String sql = "INSERT INTO Data(";
        
        for (int j = 0; j < headers.length - 1; j++)
            sql = sql + headers[j] + ", ";
        
        sql = sql + headers[headers.length - 1] + ") VALUES(";
                
        for (int j = 0; j< headers.length - 1; j++) {
            sql = sql + "?,";
        }       
        
        sql = sql + "?)";
        
        System.out.println(sql);
        
        try {   
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            for (int i = 0; i < data.length; i++)
                pstmt.setString(i+1, String.valueOf(data[i]));     
            pstmt.executeUpdate();
        } 
        catch (SQLException e) {  
            e.printStackTrace(); 
        }  
    }
    
    public void close() {
        try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
