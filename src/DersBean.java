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
	
	private int sil_id;
	private String o_id;
	private ders ders;
	private ArrayList<ders> list= new ArrayList<ders>();
	private ArrayList<ders> list1= new ArrayList<ders>();
	private ArrayList<ogrenci> ogrlist= new ArrayList<ogrenci>();
	private HtmlForm form = new HtmlForm();
	private ogrenci ogrenci;
	
	
	@PostConstruct
	private void init() {
		initDersList();
		ogrtDersGetir();
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
	//o_id = ders.ogrt.getOgret_no();
	
	try
	{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		PreparedStatement ps = connection.prepareStatement( "SELECT * FROM DERS where ogret_no=?");
		
		ps.setInt(1, 1004);
		
		
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

	
	
	
	
	
	
	
	
	}


