
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.mysql.jdbc.Util;

@ManagedBean ( name="ogrenci" )
@SessionScoped

public class ogrenci {
	
	
	 private String ogren_no; 
	    private String isim;
	    private String soyisim;
	    private String tc;
	    
	    CachedRowSet rowSet=null;
	    DataSource dataSource;
	    
	    
	    @PostConstruct
	    public void init()
	    {
	        
	    }
	    
		public String getOgren_no() {
			return ogren_no;
		}
		public void setOgren_no(String ogren_no) {
			this.ogren_no = ogren_no;
		}
		public String getIsim() {
			return isim;
		}
		public void setIsim(String isim) {
			this.isim = isim;
		}
		public String getSoyisim() {
			return soyisim;
		}
		public void setSoyisim(String soyisim) {
			this.soyisim = soyisim;
		}
		public String getTc() {
			return tc;
		}
		public void setTc(String tc) {
			this.tc = tc;
		}
		

		
		 public CachedRowSet getRowSet() {
			return rowSet;
		}
		public void setRowSet(CachedRowSet rowSet) {
			this.rowSet = rowSet;
		}
		public DataSource getDataSource() {
			return dataSource;
		}
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		public ogrenci() 
		    { 
		        try 
		    {
		        Class.forName("com.mysql.jdbc.Driver");
		    } catch (final ClassNotFoundException e) 
		    {
		        e.printStackTrace();
		    }   
		        
		        
		        

		    }
		 
		 
		 public String ogrenciEkle() throws SQLException
	        { 
	            try
	     {
	         
	         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
	        
	         PreparedStatement addEntry =
	         connection.prepareStatement( 
	         "INSERT INTO ogrenci" +
	         "(tc,isim,soyisim)" +
	         "VALUES (?,?,?)" );

	         addEntry.setString( 1, getTc());
	         addEntry.setString( 2, getIsim());
	         addEntry.setString( 3, getSoyisim());
	        

	         addEntry.executeUpdate(); 
	         return "admin_ana.xhtml";
	     } 
	     catch(Exception e)
	     {
	         return "admin_ana.xhtml";
	     }
	    finally 
	     {
	     
	     } 
	 }  
		 
		 
		 public String ogrenciGuncelle() throws SQLException
		 {
		   

		    try
		    {
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		       
		        PreparedStatement update = connection.prepareStatement( "UPDATE ogrenci SET tc=?,isim=?,soyisim=?,"
		                + " WHERE ogren_no=?" );

		        update.setString( 1, getTc());        
		        update.setString( 2, getIsim());       
		        update.setString( 3, getSoyisim());        
		            
		        update.setInt( 4, Integer.parseInt(getOgren_no()) );
		        
		        update.executeUpdate();
		        
		        return "admin_ana.xhtml"; 
		    }
		    catch(Exception e)
		    {
		         return "admin_ana.xhtml";
		    }
		    finally
		    {
		         
		    } 

		 }  

		 
		 public String ogrenciSil() throws SQLException
		 {
		   

		     try
		     {
		         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		         PreparedStatement deleteEntry = connection.prepareStatement( "delete from ogrenci where ogren_no =?");

		         deleteEntry.setInt( 1, Integer.parseInt(getOgren_no()) );
		         deleteEntry.executeUpdate(); 
		         return "ogren_ekle"; 
		     } 
		     finally
		     {
		         
		     }
		 }        
		        
		  public ResultSet ogrenciGor() throws SQLException
		  {
		     

		      try
		      {
		 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		      PreparedStatement ps =
		      connection.prepareStatement( "SELECT * FROM ogrenci" );


		      rowSet = new com.sun.rowset.CachedRowSetImpl();
		      rowSet.populate( ps.executeQuery() );
		     return rowSet;
		      } 
		      finally
		      {
		      
		      } 
		  } 
		   
		   
}

