import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="ogretmenBean")
@ViewScoped
public class OgretmenBean {

	
	private int sil_id;
	private ogretmen ogretmen;
	private List<ogretmen> ogretmenList = new ArrayList<>();
	private List<ogretmen> guncelleList = new ArrayList<>();
	
	private HtmlForm form = new HtmlForm();

	@PostConstruct
	public void init() {
		initOgretmenList();
		ogretmen = new ogretmen();
	}
	
	 public OgretmenBean() 
	    { 
	        try 
	    {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (final ClassNotFoundException e) 
	    {
	        e.printStackTrace();
	    } 
	    }
	
	public void initOgretmenList(){
		// ogretmenList : read from db
	
		ogretmen ogrt;
		
		try
		     {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		    
			PreparedStatement ps = connection.prepareStatement( "SELECT * FROM ogretmen" );
		
			ResultSet rs    =    ps.executeQuery();
		
			while (rs.next())
		{
			ogrt = new ogretmen();
			ogrt.setOgret_no(rs.getString("ogret_no"));
			ogrt.setTc(rs.getString("tc"));
			ogrt.setIsim(rs.getString("isim"));
			ogrt.setSoyisim(rs.getString("soyisim"));
			ogrt.setEmail(rs.getString("email"));
			ogrt.setSifre(rs.getString("sifre"));
			ogrt.setGuncellenebilirlik(false);
			ogretmenList.add(ogrt);
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
		
	public String IDParametresiniAl(FacesContext fc)
	    {
	        Map<String,String> parametreler = fc.getExternalContext().getRequestParameterMap();
		return parametreler.get("ogret_no");
	    }
	 
	 public String ogretmenSil() {
		// sil db den
		//faces mesaj
		
		 FacesContext fc = FacesContext.getCurrentInstance();
		 this.sil_id = Integer.parseInt(IDParametresiniAl(fc));
		 
		
		 try
		 { 
	      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
	      PreparedStatement deleteEntry = connection.prepareStatement( "delete from ogretmen where ogret_no =?");

	      deleteEntry.setInt( 1, sil_id) ;
	      deleteEntry.executeUpdate(); 
	     
	     
	     } 
		 
		 catch(SQLException e)
		 {
			 
		 }
	     finally
	     {
	    	 return "ogrt_ekle?faces-redirect=true";
	     }
		 
		/* FacesMessage message = new FacesMessage("Silme iþlemi baþarýlý");
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage("formid:sil1", message);
		*/
	}
	
	
	
	public String ogretmenEkle(){
		// db ye kaydet
		
		
		try
	     {
	         
	         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
	        
	         PreparedStatement addEntry =
	         connection.prepareStatement( 
	         "INSERT INTO ogretmen" +
	         "(tc,isim,soyisim,email,sifre)" +
	         "VALUES (?,?,?,?,?)" );

	         addEntry.setString( 1, ogretmen.getTc());
	         addEntry.setString( 2, ogretmen.getIsim());
	         addEntry.setString( 3, ogretmen.getSoyisim());
	         addEntry.setString( 4, ogretmen.getEmail());
	         addEntry.setString( 5, ogretmen.getSifre());

	         addEntry.executeUpdate(); 
	         
	        
	        return null;
	        
	       
	        
	     } 
	     catch(Exception e)
	     {
	    	 return null;
	     }
	    finally 
	     {
	    	return "ogrt_ekle?faces-redirect=true";
	     }
		
	}
	
	public String guncellenebilirligiDegistir(ogretmen o)
	{
		o.setGuncellenebilirlik(true);
		return null;
	}
	
	 public String DegisikligiKaydet() {
			for (ogretmen ogretmen1 : ogretmenList){
				ogretmen1.setGuncellenebilirlik(false);
			}
			return null;
	 }
	 
	 public boolean ogretmenGuncelle() throws SQLException
	 {
	   int i= 0;

	    try
	    {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
	       
	        PreparedStatement update = connection.prepareStatement( "UPDATE ogretmen SET tc=?,isim=?,soyisim=?,"
	                + "email=?,sifre=? WHERE ogret_no=?" );
	        for(ogretmen item:ogretmenList){
	        update.setString( 1, item.getTc());        
	        update.setString( 2, item.getIsim());       
	        update.setString( 3, item.getSoyisim());        
	        update.setString( 4, item.getEmail());        
	        update.setString( 5, item.getSifre());       
	        update.setInt( 6, Integer.parseInt(item.getOgret_no()) );
	        
	        i = update.executeUpdate();
	        }
	        
	        form.processUpdates(FacesContext.getCurrentInstance());
	        
	        if(i>0)
	        return true;
	        else 
	        return false;
	    }
	    catch(Exception e)
	    {
	         return false;
	    }
	    finally
	    {
	         
	    } 

	 }  

	
	
	

	public List<ogretmen> getGuncelleList() {
		return guncelleList;
	}

	public void setGuncelleList(List<ogretmen> guncelleList) {
		this.guncelleList = guncelleList;
	}

	public int getSil_id() {
		return sil_id;
	}

	public void setSil_id(int sil_id) {
		this.sil_id = sil_id;
	}

	public ogretmen getOgretmen() {
		return ogretmen;
	}

	public void setOgretmen(ogretmen ogretmen) {
		this.ogretmen = ogretmen;
	}

	public List<ogretmen> getOgretmenList() {
		return ogretmenList;
	}

	public void setOgretmenList(List<ogretmen> ogretmenList) {
		this.ogretmenList = ogretmenList;
	}

	public HtmlForm getForm() {
		return form;
	}

	public void setForm(HtmlForm form) {
		this.form = form;
	}
}
