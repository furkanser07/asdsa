import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;


@ManagedBean(name="dersBean")
@ViewScoped
public class DersBean {
	
	
	private String text;
	private String hafta_adi;
	private int sil_id;
	private String o_id;
	private ders ders;
	private ArrayList<ders> list= new ArrayList<ders>();
	private ArrayList<ders> list1= new ArrayList<ders>();
	private ArrayList<ders> list2= new ArrayList<ders>();
	private ArrayList<ogrenci> ogrlist= new ArrayList<ogrenci>();
	private ArrayList<ogrenci> ogrlist1= new ArrayList<ogrenci>();
	private HtmlForm form = new HtmlForm();
	private ogrenci ogrenci;
	
	
	@PostConstruct
	private void init() {
		initDersList();
		ogrtDersGetir();
		ogrDersGetir();
		ders = new ders();
		ogrenci = new ogrenci();
		
	}
	
	public DersBean() 
    { 
        try 
    {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException e) 
    {
        e.printStackTrace();
    } 
    }
	
	public String ogrYoklamaGetir()
	{
		
		//Bu kod çalýþtýðýnda tekrar getirmek isteyince eskisiyle beraber getiriyor.
		ogrenci ogr = new ogrenci();
		
	try
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		PreparedStatement ps = connection.prepareStatement( "SELECT * FROM ders_ogrenci INNER JOIN ogrenci"
				+ " ON ogrenci.ogren_no=ders_ogrenci.ogren_no where ders_ogrenci.ders_no=?");
		
		ps.setString(1,ders.getDers_no());
		
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		 {
		 ogr = new ogrenci();
		 ogr.setOgren_no(rs.getString("ogren_no"));
		 ogr.setIsim(rs.getString("isim"));
		 ogr.setSoyisim(rs.getString("soyisim"));
		 ogr.setHafta1(rs.getInt("hafta1"));
		 ogr.setHafta2(rs.getInt("hafta2"));
		 ogr.setHafta3(rs.getInt("hafta3"));
		 ogr.setHafta4(rs.getInt("hafta4"));
		 ogr.setToplam(rs.getInt("hafta1")+rs.getInt("hafta2")+rs.getInt("hafta3")+ rs.getInt("hafta4"));
		 ogrlist.add(ogr);
		 }
		
		return null;
		
	}
	catch(Exception e)
    {
		return null;
    }
    
 finally
    {
	 
    } 
	}
	
	public String tekogrYoklamaGetir()
	{
		
		//Bu kod çalýþtýðýnda tekrar getirmek isteyince eskisiyle beraber getiriyor.
		ogrenci ogr3 = new ogrenci();
		
	try
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		PreparedStatement ps = connection.prepareStatement( "SELECT * FROM ders_ogrenci INNER JOIN ogrenci"
				+ " ON ogrenci.ogren_no=ders_ogrenci.ogren_no where ders_ogrenci.ders_no=? and ders_ogrenci.ogren_no=?");
		
		ps.setString(1,ders.getDers_no());
		ps.setInt(2,20001); // Buraya giriþ yapan öðrencinin numarasý gelecek.
		
		
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		 {
		 ogr3 = new ogrenci();
		 ogr3.setOgren_no(rs.getString("ogren_no"));
		 ogr3.setIsim(rs.getString("isim"));
		 ogr3.setSoyisim(rs.getString("soyisim"));
		 ogr3.setHafta1(rs.getInt("hafta1"));
		 ogr3.setHafta2(rs.getInt("hafta2"));
		 ogr3.setHafta3(rs.getInt("hafta3"));
		 ogr3.setHafta4(rs.getInt("hafta4"));
		 ogr3.setToplam(rs.getInt("hafta1")+rs.getInt("hafta2")+rs.getInt("hafta3")+ rs.getInt("hafta4"));
		 ogrlist1.add(ogr3);
		 }
		
		return null;
		
	}
	catch(Exception e)
    {
		return null;
    }
    
 finally
    {
	 
    } 
	}
	
	
	public String ogrYoklamaAl()
	{
		
		
	
	try
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		
		if(hafta_adi.equals("hafta1") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta1 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE  ogrenci.kart_no=? and ders_ogrenci.ders_no=?  ");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta2") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta2 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
			ps.setString(1,ogrenci.getKart_no());
			ps.setString(2, ders.getDers_no());
			ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta3") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta3 = 0"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ders_ogrenci.kart_no=? and ders_ogrenci.ders_no=? ");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		
		
		}
		
		if(hafta_adi.equals("hafta4") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta4 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta5") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta5 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta6") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta6 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta7") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta7 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta8") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta8 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta9") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta9 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta10") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta10 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta11") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta11 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
		if(hafta_adi.equals("hafta12") )
		{
		
			PreparedStatement ps = connection.prepareStatement( "UPDATE ders_ogrenci SET ders_ogrenci.hafta12 = 1"
				+ "FROM ders_ogrenci INNER JOIN ogrenci ON ders_ogrenci.kart_no = ogrenci.kart_no"
				+ " WHERE ogrenci.kart_no=? and ders_ogrenci.ders_no=?");
		
		ps.setString(1,ogrenci.getKart_no());
		ps.setString(2, ders.getDers_no());
		ps.executeUpdate();
		}
		
	
		return null;
		
	}
	catch(Exception e)
    {
		return null;
    }
    
 finally
    {
	 
    } 
	}
	
	public String ogr1YoklamaGetir()
	{
		ogrenci ogr = new ogrenci();
		
	
	try
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		PreparedStatement ps = connection.prepareStatement( "SELECT * FROM ders_ogrenci INNER JOIN ogrenci"
				+ " ON ogrenci.ogren_no=ders_ogrenci.ogren_no where ders_ogrenci.ders_no=? and ogrenci.isim LIKE ?");
		
		ps.setString(1,ders.getDers_no());
		ps.setString(2, "%"+text+"%");
		
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		 {
		 ogr = new ogrenci();
		 ogr.setOgren_no(rs.getString("ogren_no"));
		 ogr.setIsim(rs.getString("isim"));
		 ogr.setSoyisim(rs.getString("soyisim"));
		 ogr.setHafta1(rs.getInt("hafta1"));
		 ogr.setHafta2(rs.getInt("hafta2"));
		 ogr.setHafta3(rs.getInt("hafta3"));
		 ogr.setHafta4(rs.getInt("hafta4"));
		 ogr.setToplam(rs.getInt("hafta1")+rs.getInt("hafta2")+rs.getInt("hafta3")+ rs.getInt("hafta4"));
		 ogrlist.add(ogr);
		 }
		
		return null;
		
	}
	catch(Exception e)
    {
		return null;
    }
    
 finally
    {
	 
    } 
	}
	
	public void ogrtDersGetir()
	{
		ders ders2;
		ogretmen ogrt1 = new ogretmen();
	try
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		PreparedStatement ps = connection.prepareStatement( "SELECT * FROM DERS where ogret_no=?");
		
		ps.setInt(1, 1004 ); //Buraya bakýlacak.
		
	
		ResultSet rs    =    ps.executeQuery();
		
		while (rs.next())
		 {
		 ders2 = new ders();
		 ders2.setDers_no(rs.getString("ders_no"));
		 ders2.setDers_isim(rs.getString("ders_isim"));
		 ders2.setKredi(rs.getString("kredi"));
		 ders2.setOgret_no(rs.getString("ogret_no"));
		 list1.add(ders2);
		 }
		
		
		
	}
	catch(Exception e)
    {
       
    }
    
 finally
    {
         
    } 
	}
	
	
	public void ogrDersGetir()
	{
		ders ders3;
		ogrenci ogr1 = new ogrenci();
	try
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		PreparedStatement ps = connection.prepareStatement( "SELECT * FROM ders INNER JOIN ders_ogrenci"
				+  " ON ders.ders_no=ders_ogrenci.ders_no where ders_ogrenci.ogren_no=?");
		
		ps.setInt(1, 20001 ); //Buraya bakýlacak.
		
	
		ResultSet rs    =    ps.executeQuery();
		
		while (rs.next())
		 {
		 ders3 = new ders();
		 ders3.setDers_no(rs.getString("ders_no"));
		 ders3.setDers_isim(rs.getString("ders_isim"));
		 ders3.setKredi(rs.getString("kredi"));
		 ders3.setOgret_no(rs.getString("ogret_no"));
		 list2.add(ders3);
		 }
		
		
		
	}
	catch(Exception e)
    {
       
    }
    
 finally
    {
         
    } 
	}
	public void initDersList()
	{
		ders ders1;
		
		try{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		
		 PreparedStatement ps = connection.prepareStatement( "SELECT * FROM DERS");
		 
		 ResultSet rs    =    ps.executeQuery();
		 
		 while (rs.next())
		 {
		 ders1 = new ders();
		 ders1.setDers_no(rs.getString("ders_no"));
		 ders1.setDers_isim(rs.getString("ders_isim"));
		 ders1.setKredi(rs.getString("kredi"));
		 ders1.setOgret_no(rs.getString("ogret_no"));
		 list.add(ders1);
		 }
		
		 form.processUpdates(FacesContext.getCurrentInstance());
		
	}
		 catch(Exception e)
		    {
		       
		    }
		    
		 finally
		    {
		         
		    } 
		}
	
	public String dersEkle(){
		 try
		   {
		       
		       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		      
		       PreparedStatement addEntry =
		       connection.prepareStatement( 
		       "INSERT INTO ders" +
		       "(ders_isim,kredi,ogret_no)" +
		       "VALUES (?,?,?)" );

		       addEntry.setString( 1, ders.getDers_isim());
		       addEntry.setString( 2, ders.getKredi());
		       addEntry.setString( 3, ders.getOgret_no());
		       

		       addEntry.executeUpdate(); 
		       return null;
		   } 
		   catch(Exception e)
		   {
		       return null;
		   }
		  finally 
		   {
			  return "ders_ekle?faces-redirect=true";
		   } 
	}
	
	public String IDParametresiniAl(FacesContext fc)
    {
        Map<String,String> parametreler = fc.getExternalContext().getRequestParameterMap();
	return parametreler.get("ders_no");
    }
	
	 public String dersSil() {
	 
	FacesContext fc = FacesContext.getCurrentInstance();
	 this.sil_id = Integer.parseInt(IDParametresiniAl(fc));
	  
	 
	 try
	 { 
     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
     PreparedStatement deleteEntry = connection.prepareStatement( "delete from ders where ders_no =?");

     deleteEntry.setInt( 1, sil_id) ;
     deleteEntry.executeUpdate(); 
    
    
    } 
	 
	 catch(SQLException e)
	 {
		 
	 }
    finally
    {
   	 return "ders_ekle?faces-redirect=true";
    }
	 
	/* FacesMessage message = new FacesMessage("Silme iþlemi baþarýlý");
	 FacesContext context = FacesContext.getCurrentInstance();
	 context.addMessage("formid:sil1", message);
	*/
}

	 
	 public String guncellenebilirlikDegistir(ders d)
		{
			d.setGuncellenebilirlik(true);
			return null;
		}
	 
	 public String DegisiklikKaydet() {
			for (ders ders1 : list){
			ders1.setGuncellenebilirlik(false);
			}
			return null;
	 }
	
	 
	 public boolean dersGuncelle() throws SQLException
	 {
	   int i= 0;

	    try
	    {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
	       
	        PreparedStatement update = connection.prepareStatement( "UPDATE ders SET ders_isim=?,kredi=?,"
	                + "ogret_no=? WHERE ders_no=?" );
	        for(ders item:list){
	        update.setString( 1, item.getDers_isim());        
	        update.setString( 2, item.getKredi());       
	        update.setString( 3, item.getOgret_no());          
	        update.setInt( 4, Integer.parseInt(item.getDers_no()) );
	        
	        update.executeUpdate();
	        }
	        
	        form.processUpdates(FacesContext.getCurrentInstance());
	        
	        return true;
	      /*  if(i>0)
	        return true;
	        else 
	        return false;
	        */
	    }
	    catch(Exception e)
	    {
	         return false;
	    }
	    finally
	    {
	         
	    } 

	 }  

	
	
	public ders getDers() {
		return ders;
	}

	public void setDers(ders ders) {
		this.ders = ders;
	}

	public ArrayList<ders> getList() {
		return list;
	}

	public void setList(ArrayList<ders> list) {
		this.list = list;
	}

	public HtmlForm getForm() {
		return form;
	}

	public void setForm(HtmlForm form) {
		this.form = form;
	}

	public int getSil_id() {
		return sil_id;
	}

	public void setSil_id(int sil_id) {
		this.sil_id = sil_id;
	}

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public ArrayList<ders> getList1() {
		return list1;
	}

	public void setList1(ArrayList<ders> list1) {
		this.list1 = list1;
	}

	public ArrayList<ogrenci> getOgrlist() {
		return ogrlist;
	}

	public void setOgrlist(ArrayList<ogrenci> ogrlist) {
		this.ogrlist = ogrlist;
	}

	public ogrenci getOgrenci() {
		return ogrenci;
	}

	public void setOgrenci(ogrenci ogrenci) {
		this.ogrenci = ogrenci;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHafta_adi() {
		return hafta_adi;
	}

	public void setHafta_adi(String hafta_adi) {
		this.hafta_adi = hafta_adi;
	}

	public ArrayList<ders> getList2() {
		return list2;
	}

	public void setList2(ArrayList<ders> list2) {
		this.list2 = list2;
	}

	public ArrayList<ogrenci> getOgrlist1() {
		return ogrlist1;
	}

	public void setOgrlist1(ArrayList<ogrenci> ogrlist1) {
		this.ogrlist1 = ogrlist1;
	}

	
	
	
	
	
	
	
	
	}


