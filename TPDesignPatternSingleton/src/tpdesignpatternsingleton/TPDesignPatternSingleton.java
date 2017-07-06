/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdesignpatternsingleton;

import com.sun.xml.internal.fastinfoset.sax.Properties;
import java.sql.SQLException;

/**
 *
 * @author YASSALIE
 */
public class TPDesignPatternSingleton {

    public static void main(String[] args) throws SQLException {
        ConnectionMySQLSingleton mysqlCon0 = ConnectionMySQLSingleton.getInstance();
        
        System.out.println(""+mysqlCon0.GetConnection());
        
        ConnectionMySQLSingleton mysqlCon1 = ConnectionMySQLSingleton.getInstance();
        
        System.out.println(""+mysqlCon1.GetConnection());
        
    }
    
}
