
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




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Furkan
 */

@ManagedBean ( name="ogretmen" )
@SessionScoped

public class ogretmen implements Filter {
    
 // @ManagedProperty(value="#{param.ogret_no}")
    private static String ogret_no; 
    private String isim;
    private String soyisim;
    private String tc;
    private String email;
    private String sifre; 
    private String silinecekid;
    CachedRowSet rowSet=null;
    DataSource dataSource;
   
 @PostConstruct
 public void init()
 {
     
 }
    public String getSilinecekid() 
    {
        return silinecekid;
    }

    public void setSilinecekid(String silinecekid) 
    {
        this.silinecekid = silinecekid;
    }

    public CachedRowSet getRowSet() 
    {
        return rowSet;
    }

    public void setRowSet(CachedRowSet rowSet) 
    {
        this.rowSet = rowSet;
    }

    public DataSource getDataSource() 
    {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) 
    {
        this.dataSource = dataSource;
    }
    
  

    public static String getOgret_no() 
    {
        return ogret_no;
    }

    public void setOgret_no(String ogret_no) 
    {
        this.ogret_no = ogret_no;
    }

    public String getIsim() 
    {
        return isim;
    }

    public void setIsim(String isim) 
    {
        this.isim = isim;
    }

    public String getSoyisim() 
    {
        return soyisim;
    }

    public void setSoyisim(String soyisim) 
    {
        this.soyisim = soyisim;
    }

    public String getTc() 
    {
        return tc;
    }

    public void setTc(String tc) 
    {
        this.tc = tc;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getSifre() 
    {
        return sifre;
    }

    public void setSifre(String sifre) 
    {
        this.sifre = sifre;
    }
    
    
   
     public ogretmen() 
    { 
        try 
    {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException e) 
    {
        e.printStackTrace();
    }   
   
    
    
    }
    
    
        public String ogretmenEkle() throws SQLException
        { 
            try
     {
         
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
        
         PreparedStatement addEntry =
         connection.prepareStatement( 
         "INSERT INTO ogretmen" +
         "(tc,isim,soyisim,email,sifre)" +
         "VALUES (?,?,?,?,?)" );

         addEntry.setString( 1, getTc());
         addEntry.setString( 2, getIsim());
         addEntry.setString( 3, getSoyisim());
         addEntry.setString( 4, getEmail());
         addEntry.setString( 5, getSifre());

         addEntry.executeUpdate(); 
         return "ogrt_gor.xhtml";
     } 
     catch(Exception e)
     {
         return "ogrt_gor.xhtml";
     }
    finally 
     {
     
     } 
 }  
    
        public String ogretmenGuncelle() throws SQLException
 {
   

    try
    {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
       
        PreparedStatement update = connection.prepareStatement( "UPDATE ogretmen SET tc=?,isim=?,soyisim=?,"
                + "email=?,sifre=? WHERE ogret_no=?" );

        update.setString( 1, getTc());        
        update.setString( 2, getIsim());       
        update.setString( 3, getSoyisim());        
        update.setString( 4, getEmail());        
        update.setString( 5, getSifre());       
        update.setInt( 6, Integer.parseInt(getOgret_no()) );
        
        update.executeUpdate();
        
        return "ogrt_gun"; 
    }
    catch(Exception e)
    {
         return "admin";
    }
    finally
    {
         
    } 

 }  
     
public String ogretmenSil() throws SQLException
 {
   

     try
     {
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
         PreparedStatement deleteEntry = connection.prepareStatement( "delete from ogretmen where ogret_no =?");

         deleteEntry.setInt( 1, Integer.parseInt(getOgret_no()) );
         deleteEntry.executeUpdate(); 
         return "ogrt_ekle"; 
     } 
     finally
     {
         
     }
 }        
        
        
      public ResultSet ogretmenGor() throws SQLException
 {
    

     try
     {
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
     PreparedStatement ps =
     connection.prepareStatement( "SELECT * FROM ogretmen" );


     rowSet = new com.sun.rowset.CachedRowSetImpl();
     rowSet.populate( ps.executeQuery() );
    return rowSet;
     } 
     finally
     {
     
     } 
 } 
  
 
public boolean girisYap1(String username, String password) throws SQLException 
{

    try
    {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
       
        PreparedStatement ps = connection.prepareStatement( "Select * from ogretmen where"
                + " email = ? and sifre = ?" );

        ps.setString( 1, getEmail());
        ps.setString( 2, getSifre());
        
        
         
        ResultSet rs    =    ps.executeQuery();
      
       
        
    if (rs.next()) 
    {
        	  isim = rs.getString("isim");
        	  soyisim = rs.getString("soyisim"); 
        	  ogret_no = rs.getString("ogret_no"); 
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
		return "ogrt_ana_sayfa";
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
@Override
public void destroy() {
	// TODO Auto-generated method stub
	
}

@Override
public void init(FilterConfig arg0) throws ServletException {
	// TODO Auto-generated method stub
	
}

@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException 
{
	try{
		
	HttpServletRequest reqt = (HttpServletRequest) request;
	HttpServletResponse resp = (HttpServletResponse) response;
	HttpSession ses = reqt.getSession(false);
	
	String reqURI = reqt.getRequestURI();
	
	if (reqURI.indexOf("/login.xhtml") >= 0
			|| (ses != null && ses.getAttribute("email") != null)
			|| reqURI.indexOf("/public/") >= 0
			|| reqURI.contains("javax.faces.resource"))
		chain.doFilter(request, response);
	else
		resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
} 

catch (Exception e) {
	System.out.println(e.getMessage());

}
	
}

     
    
}
