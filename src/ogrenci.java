
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
	 private int hafta1;
	 private int hafta2;
	 private int hafta3;
	 private int hafta4;
	 private int hafta5;
	 private int hafta6;
	 private int hafta7;
	 private int hafta8;
	 private int hafta9;
	 private int hafta10;
	 private int hafta11;
	 private int hafta12;
	 private int toplam;
	 private String isim;
	 private String soyisim;
	 private String tc;
	 private String email;
	 private String sifre;
	 
	    
	    
	    
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


		public int getHafta1() {
			return hafta1;
		}


		public void setHafta1(int hafta1) {
			this.hafta1 = hafta1;
		}


		public int getHafta2() {
			return hafta2;
		}


		public void setHafta2(int hafta2) {
			this.hafta2 = hafta2;
		}


		public int getHafta3() {
			return hafta3;
		}


		public void setHafta3(int hafta3) {
			this.hafta3 = hafta3;
		}


		public int getHafta4() {
			return hafta4;
		}


		public void setHafta4(int hafta4) {
			this.hafta4 = hafta4;
		}


		public int getToplam() {
			return toplam;
		}


		public void setToplam(int toplam) {
			this.toplam = toplam;
		}


		public int getHafta5() {
			return hafta5;
		}


		public void setHafta5(int hafta5) {
			this.hafta5 = hafta5;
		}


		public int getHafta6() {
			return hafta6;
		}


		public void setHafta6(int hafta6) {
			this.hafta6 = hafta6;
		}


		public int getHafta7() {
			return hafta7;
		}


		public void setHafta7(int hafta7) {
			this.hafta7 = hafta7;
		}


		public int getHafta8() {
			return hafta8;
		}


		public void setHafta8(int hafta8) {
			this.hafta8 = hafta8;
		}


		public int getHafta9() {
			return hafta9;
		}


		public void setHafta9(int hafta9) {
			this.hafta9 = hafta9;
		}


		public int getHafta10() {
			return hafta10;
		}


		public void setHafta10(int hafta10) {
			this.hafta10 = hafta10;
		}


		public int getHafta11() {
			return hafta11;
		}


		public void setHafta11(int hafta11) {
			this.hafta11 = hafta11;
		}


		public int getHafta12() {
			return hafta12;
		}


		public void setHafta12(int hafta12) {
			this.hafta12 = hafta12;
		}


	
		 
		 
		 
		   
}

