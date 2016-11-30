
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
import javax.faces.bean.ViewScoped;
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



public class ogrenci {
	
	
	 private String ogren_no; 
	 private String kart_no; 
	 private boolean guncellenebilirlik;
	 private String isim;
	 private String soyisim;
	 private String tc;
	    
	    
	    
	    @PostConstruct
	    public void init()
	    {
	        
	    }
	    
	    
		public boolean isGuncellenebilirlik() {
			return guncellenebilirlik;
		}


		public void setGuncellenebilirlik(boolean guncellenebilirlik) {
			this.guncellenebilirlik = guncellenebilirlik;
		}


		public String getKart_no() {
			return kart_no;
		}


		public void setKart_no(String kart_no) {
			this.kart_no = kart_no;
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
	
	
		
		
		public ogrenci() 
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
		 
		 
		 
		   
}

