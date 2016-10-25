
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
@ManagedBean ( name="ders" )
@SessionScoped
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
    
    
    private ArrayList<ders> list= new ArrayList<ders>();
    private ResultSet derslergeri;
    private ResultSet dersler;
    //@ManagedProperty(value="#{param.ders_no}")
    private String ders_no;
    private String ogret_no;
    private String ders_isim;
    private String kredi;
    CachedRowSet rowSet=null;
    DataSource dataSource;

    /**
     *
     * @throws SQLException
     */
    @PostConstruct
    public void init() 
    {
        try {
            list= (ArrayList<ders>) derslerGetir();
        } catch (SQLException ex) {
            Logger.getLogger(ders.class.getName()).log(Level.SEVERE, null, ex);
        }
      
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

    public String getDers_no() {
        return ders_no;
    }

    public void setDers_no(String ders_no) {
        this.ders_no = ders_no;
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
	
	
	  public String dersEkle() throws SQLException
      { 
          try
   {
       
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
      
       PreparedStatement addEntry =
       connection.prepareStatement( 
       "INSERT INTO ders" +
       "(ders_isim,kredi,ogret_no)" +
       "VALUES (?,?,?)" );

       addEntry.setString( 1, getDers_isim());
       addEntry.setString( 2, getKredi());
       addEntry.setString( 3, getOgret_no());
       

       addEntry.executeUpdate(); 
       return "ders_ekle.xhtml";
   } 
   catch(Exception e)
   {
       return "ders_gor.xhtml";
   }
  finally 
   {
   
   } 
}  
	
	  public String dersSil() throws SQLException
	  {
	     try
	      {
	          Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
	          PreparedStatement deleteEntry = connection.prepareStatement( "delete from ders where ders_no = ? ");

	          deleteEntry.setInt( 1, Integer.parseInt(getDers_no()) );
	          deleteEntry.executeUpdate(); 
	          return "ders_ekle"; 
	      } 
	      finally
	      {
	          
	      }
	  }        
	
    public  List<ders> derslerGetir() throws SQLException
    {
    try{
    	
    	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
        ArrayList<ders> list1= new ArrayList<ders>();
        String numara= ogretmen.getOgret_no();
    
        PreparedStatement ps = connection.prepareStatement( "SELECT * FROM DERS where ogret_no = ?" );
        ps.setString(1, numara);
        
        rowSet = new com.sun.rowset.CachedRowSetImpl();
        rowSet.populate( ps.executeQuery() );
      
     try 
    {    
       while (rowSet.next())
      {
             ders ders1 = new ders();
             ders1.setDers_isim(rowSet.getString("ders_isim"));
             list1.add(ders1);   
      }  
    }
 
     catch(Exception e)
    {
        
    }
     
     finally{}
     return list1;
    }
    
    finally{}
    
    }
    
    
   
      public ResultSet dersBul() throws SQLException
 {
     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitirme","root","root");

     if ( dataSource == null )
     throw new SQLException( "Unable to obtain DataSource" );

     if ( connection == null )
     throw new SQLException( "Unable to connect to DataSource" );

 try
 {  
     PreparedStatement ps =
     connection.prepareStatement( "select ders_isim FROM ders" );
     rowSet = new com.sun.rowset.CachedRowSetImpl();
     rowSet.populate( ps.executeQuery() );
     dersler=rowSet;
     return dersler;
 } 
 finally
 {
 connection.close(); 
 } 
 } 

    
      
      
      public ResultSet dersGor() throws SQLException
 {
    

     try
     {
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
    
PreparedStatement ps =
     connection.prepareStatement( "SELECT * FROM ders" );


     rowSet = new com.sun.rowset.CachedRowSetImpl();
     rowSet.populate( ps.executeQuery() );
    return rowSet;
     } 
     finally
     {
     
     } 
 } 
    
}
