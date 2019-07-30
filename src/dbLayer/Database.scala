package dbLayer

import java.lang.Boolean
import java.sql.Connection
import java.sql.PreparedStatement
import connection.Dbconnection
import java.sql.ResultSet
import scala.util.control.Exception.Finally
import scala.util.control.Exception.Finally
import java.sql.SQLException
import java.sql.Statement
import java.util.Arrays.ArrayList
import pojoClasses.ArticlesDisplaybean
import java.util.ArrayList
import pojoClasses.ArticlesDisplaybean
import scala.collection.mutable.ListBuffer

class Database {

  def isLogin(username: String, password: String): Boolean = {
    var login: Boolean = false
    var conn: Connection = null
    var pstmt: PreparedStatement = null
    try {

      conn = Dbconnection.getConnection();
      var query = "SELECT user_name, password FROM Metro_Shop where user_name =?";
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, username);
      var rs = pstmt.executeQuery();
      while (rs.next()) {
        val user = rs.getString(1);
        val pass = rs.getString(2);

        if (username == user && password == pass) {
          login = true
        }
      }
    } catch {
      case e: ClassNotFoundException => println(e);
      case e: SQLException => println(e);

    } finally {
      pstmt.close()
      conn.close()
    }
    login
  }

  def getArticles() = {

    var conn: Connection = null;
    var stmt: Statement = null;
    var list = ListBuffer[ArticlesDisplaybean]()

    try {
      conn = Dbconnection.getConnection()

      val query = "select * from articles";
      stmt = conn.createStatement();
      var rs = stmt.executeQuery(query);

      while (rs.next()) {

        var db = new ArticlesDisplaybean(rs.getString("article_id"), rs.getString("name"),
          rs.getString("store"), rs.getString("color"));

        list.+=(db);
      }
    } catch {
      case e: ClassNotFoundException => println(e);
      case e: SQLException => println(e);

    } finally {
      stmt.close()
      conn.close()
    }
    list;
  }
  
  def  isRegister(name : String ,  store : String , country :String ,city : String ,area : String ,email : String ,
			username :String ,password : String ,empid : String ):Boolean ={
		var register = false;
		var conn : Connection  = null
		var prepstmt : PreparedStatement = null;

		try {
			
      conn=Dbconnection.getConnection()
			val query = "INSERT INTO Metro_shop(id,name,store,country,user_name,password,mail_id,city,area) values(?,?,?,?,?,?,?,?,?)";
			prepstmt = conn.prepareStatement(query);

			prepstmt.setString(1, empid);
			prepstmt.setString(2, name);
			prepstmt.setString(3, store);
			prepstmt.setString(4, country);
			prepstmt.setString(5, username);
			prepstmt.setString(6, password);
			prepstmt.setString(7, email);
			prepstmt.setString(8, city);
			prepstmt.setString(9, area);

			prepstmt.executeUpdate();
			 register = true;
		}catch{
      case e : ClassNotFoundException => println(e)
      case e : SQLException =>println(e)
    }finally{
      
       prepstmt.close()
      conn.close()
 
    }
		register
  }

  def IsUpdate(id: String, name: String, store: String, color: String): Boolean = {
    var update = false
    var conn: Connection = null
    var pstmt: PreparedStatement = null

    try {
      conn = Dbconnection.getConnection()
      val query = "UPDATE articles SET  name=?, store=?, color=? WHERE article_id =?"
      pstmt = conn.prepareStatement(query)

      pstmt.setString(1, name)
      pstmt.setString(2, store)
      pstmt.setString(3, color)
      pstmt.setString(4, id)
      pstmt.executeUpdate()
      update = true
    } catch {
      case e: ClassNotFoundException => println(e);
      case e: SQLException => println(e);
    } finally {
      pstmt.close()
      conn.close()
    }

    update
  }
  
  def isDelete(id:String): Boolean={
     var delete=true
     var conn: Connection = null
    var pstmt: PreparedStatement = null
    
    try{
      conn=Dbconnection.getConnection()
      val query = "delete from articles where article_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeQuery();
			delete = true;
      
    }catch{
      case e: ClassNotFoundException => println(e);
      case e: SQLException => println(e);
    }finally {
      pstmt.close()
      conn.close()
    }
    delete
  }
  
  def isInsert(id:String,name:String,store:String,color:String):Boolean={
    
    var insert=false
     var conn: Connection = null
    var pstmt: PreparedStatement = null
    
    try{
      
      conn=Dbconnection.getConnection()
      val query="INSERT INTO Articles(Article_id,name,store,color) values(?,?,?,?)"
      pstmt=conn.prepareStatement(query)
      pstmt.setString(1,id)
      pstmt.setString(2,name)
      pstmt.setString(3, store);
			pstmt.setString(4, color);
			pstmt.executeUpdate()
			insert=true
    }catch{
      case e : ClassNotFoundException => println(e)
      case e : SQLException =>println(e)
    }finally{
      
        pstmt.close()
      conn.close()
 
    }
    insert
  }
  
  def isRemove(id:String):Boolean={
    
    var remove=false
     var conn: Connection = null
    var stmt: Statement = null
    try{
      conn=Dbconnection.getConnection()
      val query="delete from articles where article_id is null"
      stmt=conn.createStatement()
      stmt.execute(query)
      remove=true
    }catch{
      case e : ClassNotFoundException => println(e)
      case e : SQLException =>println(e)
    }finally{
      
       stmt.close()
      conn.close()
 
    }
     remove
  }
  
  def isEmailFound(email:String) : Boolean={
     
      var exist=false;
      var conn: Connection = null
      var pstmt: PreparedStatement = null
      var email_id=""
      
      try{
        conn=Dbconnection.getConnection()
      val query="select  mail_id from metro_shop where mail_id=?"
       pstmt=conn.prepareStatement(query)
      pstmt.setString(1,email)
       var rs=pstmt.executeQuery()
       while(rs.next()){
           email_id=rs.getString(1)
         exist=true
       }
     
      }catch{
      case e : ClassNotFoundException => println(e)
      case e : SQLException =>println(e)
    }finally{
      
       pstmt.close()
      conn.close()
 
    }
   return exist
  }
}