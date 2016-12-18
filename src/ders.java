
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

public class ders {

    public ders() {
            try 
    {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException e) 
    {
        e.printStackTrace();
    }   
    }
    
    private ArrayList<ders> list1= new ArrayList<ders>();
    private ArrayList<ders> list= new ArrayList<ders>();
    private ResultSet derslergeri;
    private ResultSet dersler;
    private String ders_no;
    private String ogret_no;
    private String ders_isim;
    private String kredi;
    CachedRowSet rowSet=null;
    DataSource dataSource;
    private boolean guncellenebilirlik;
    

    /**
     *
     * @throws SQLException
     */
    ogretmen ogrt;
    @PostConstruct
    public void init() 
    {
    	ogrt = (ogretmen) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginOgrt");
        /*try {
            list= (ArrayList<ders>) derslerGetir();
        } catch (SQLException ex) {
            Logger.getLogger(ders.class.getName()).log(Level.SEVERE, null, ex);
        }
      */
    }
    
    
    public String getOgret_no() {
		return ogret_no;
	}


	public void setOgret_no(String ogret_no) {
		this.ogret_no = ogret_no;
	}


	public ResultSet getDerslergeri() {
        
        return derslergeri;
    }

    public List<ders> getList() {
        return list;
    }

    public void setList(List<ders> list) {
        this.list = (ArrayList<ders>) list;
    }
    
    

    public void setDerslergeri(ResultSet derslergeri) {
        this.derslergeri = derslergeri;
    }
     public ResultSet getDersler() {
        return dersler;
    }

    public void setDersler(ResultSet dersler) {
        this.dersler = dersler;
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

   

   

   

   
    


	public boolean isGuncellenebilirlik() {
		return guncellenebilirlik;
	}


	public void setGuncellenebilirlik(boolean guncellenebilirlik) {
		this.guncellenebilirlik = guncellenebilirlik;
	}


	public ogretmen getOgrt() {
		return ogrt;
	}


	public void setOgrt(ogretmen ogrt) {
		this.ogrt = ogrt;
	}


	public String getKredi() {
		return kredi;
	}

	public void setKredi(String kredi) {
		this.kredi = kredi;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setList(ArrayList<ders> list) {
		this.list = list;
	}


	public String getDers_isim() {
		return ders_isim;
	}

	public void setDers_isim(String ders_isim) {
		this.ders_isim = ders_isim;
	}


	Connection connection = null;
	public ArrayList<ders> getList1() {
		return list1;
	}


	public void setList1(ArrayList<ders> list1) {
		this.list1 = list1;
	}


	public String getDers_no() {
		return ders_no;
	}


	public void setDers_no(String ders_no) {
		this.ders_no = ders_no;
	}
	

      
    
}
