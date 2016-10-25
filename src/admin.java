
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Furkan
 */

@ManagedBean ( name="admin" )
@SessionScoped
public class admin {
  
    private String silinecekid;
    private String isim;
    private String soyisim;
    private String tc;
    private String email;
    private String sifre;
    CachedRowSet rowSet=null;
    DataSource dataSource;

    public String getSilinecekid() {
        return silinecekid;
    }

    public void setSilinecekid(String silinecekid) {
        this.silinecekid = silinecekid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    
   
     public admin() 
     {
         
    	 try 
    	 {
    		 Class.forName("com.mysql.jdbc.Driver");
    	 }	
    	 catch (final ClassNotFoundException e) 
    	 {
    		 e.printStackTrace();
    	 }
       
     }
     
     public boolean girisYap1(String username, String password) throws SQLException 
     {

         try
         {
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
            
             PreparedStatement ps = connection.prepareStatement( "Select * from admin where"
                     + " email = ? and sifre = ?" );

             ps.setString( 1, getEmail());
             ps.setString( 2, getSifre());
             
             
              
             ResultSet rs    =    ps.executeQuery();
           
            
             
         if (rs.next()) 
         {
             	  isim = rs.getString("isim");
             	  soyisim = rs.getString("soyisim"); 
     	return true;
     	}

             
            
             
                 }
         
         catch(Exception e)
         {
             return false;
         }
         finally
         {
              
         } 
        

      
     return false;
     		
          
     }  


     public String girisYap() throws SQLException
     {
     	boolean kabul=girisYap1(email, sifre);
     	
     	if(kabul)
     	{
     		HttpSession hs = util.getSession();
     		hs.setAttribute("email", email);
     		return "admin_ana";
     	}
     	else{
     		FacesContext.getCurrentInstance().addMessage(
                     null,
                     new FacesMessage(FacesMessage.SEVERITY_WARN,
                                     "Incorrect Username and Passowrd",
                                     "Please enter correct username and Password"));
     return "login";
     	}
     }
             
       
     public String cikisYap()
     {
     	HttpSession hs = util.getSession();
     	hs.invalidate();
     	return "login";
     	
     }

 
     
    
    
     
     
        
        
     
     
}
