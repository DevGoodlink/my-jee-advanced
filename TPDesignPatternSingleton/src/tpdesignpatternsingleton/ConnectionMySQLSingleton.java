/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdesignpatternsingleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMySQLSingleton {
    private static ConnectionMySQLSingleton uniqueInstance =null;
    
    private Connection con;
    
    private String driver ="com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/ecole";
    private String password ="root";
    private String login ="root";

    private ConnectionMySQLSingleton() {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }
    public static ConnectionMySQLSingleton getInstance() {
        if (uniqueInstance==null) {
            uniqueInstance=new ConnectionMySQLSingleton();
            
        } 
        return uniqueInstance;
    }
    public Connection GetConnection(){
        if(con==null){
            try {
                con = DriverManager.getConnection(url,login, password);
            } catch (Exception e) {
                System.out.println(""+e.getMessage());
            }
            
        }
        return con;
    }
    
    
    
    
}
