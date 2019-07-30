package connection

import java.sql.DriverManager
import java.sql.SQLException
import scala.util.control.Exception.Catch

object Dbconnection {
  
   def getConnection():java.sql.Connection={
    val driverName="oracle.jdbc.driver.OracleDriver"
      val url="jdbc:oracle:thin:@localhost:1521:xe";
      val userId="hr"
      val password="hr"

        Class.forName(driverName)
      val conn=DriverManager.getConnection(url,userId,password)
     
      conn
  }
}