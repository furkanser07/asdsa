import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="ogrenciBean")
@ViewScoped
public class OgrenciBean {
	
	private int sil_id;
	private ogrenci ogrenci;
	private List<ogrenci> ogrenciList = new ArrayList<>();
	
	@PostConstruct
	private void init() {
	
		initOgrenciList();
		ogrenci = new ogrenci();

	}
	
	
	
	 public OgrenciBean() 
	    { 
	        try 
	    {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (final ClassNotFoundException e) 
	    {
	        e.printStackTrace();
	    } 
	    }
	 
	 
	 
	 public void initOgrenciList(){
			// ogretmenList : read from db
		
			ogrenci ogrn;
			
			try
			     {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
			    
				PreparedStatement ps = connection.prepareStatement( "SELECT * FROM ogrenci" );
			
				ResultSet rs    =    ps.executeQuery();
			
				while (rs.next())
			{
				ogrn = new ogrenci();
				ogrn.setKart_no(rs.getString("kart_no"));
				ogrn.setOgren_no(rs.getString("ogren_no"));
				ogrn.setTc(rs.getString("tc"));
				ogrn.setIsim(rs.getString("isim"));
				ogrn.setSoyisim(rs.getString("soyisim"));
				ogrn.setGuncellenebilirlik(false);
				ogrenciList.add(ogrn);
			}
			
			
			
			     }
			  
			  catch(Exception e)
			    {
			       
			    }
			    finally
			    {
			         
			    } 
			
		}
	 
	 
	 public String ogrenciEkle(){
			// db ye kaydet
			
			
			try
		     {
		         
		         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		        
		         PreparedStatement addEntry =
		         connection.prepareStatement( 
		         "INSERT INTO ogrenci" +
		         "(kart_no,tc,isim,soyisim)" +
		         "VALUES (?,?,?,?)" );

		         addEntry.setString( 1, ogrenci.getKart_no());
		         addEntry.setString( 2, ogrenci.getTc());
		         addEntry.setString( 3, ogrenci.getIsim());
		         addEntry.setString( 4, ogrenci.getSoyisim());
		        

		         addEntry.executeUpdate(); 
		         
		        
		        return null;
		        
		       
		        
		     } 
		     catch(Exception e)
		     {
		    	 return null;
		     }
		    finally 
		     {
		    	return "ogren_ekle?faces-redirect=true";
		     }
			
		}

	 public String IDParametresiniAl(FacesContext fc)
	    {
	        Map<String,String> parametreler = fc.getExternalContext().getRequestParameterMap();
		return parametreler.get("ogren_no");
	    }
	 
	 public String ogrenciSil() {
			// sil db den
			//faces mesaj
			
			 FacesContext fc = FacesContext.getCurrentInstance();
			 this.sil_id = Integer.parseInt(IDParametresiniAl(fc));
			 
			
			 try
			 { 
		      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		      PreparedStatement deleteEntry = connection.prepareStatement( "delete from ogrenci where ogren_no =?");

		      deleteEntry.setInt( 1, sil_id) ;
		      deleteEntry.executeUpdate(); 
		     
		     
		     } 
			 
			 catch(SQLException e)
			 {
				 
			 }
		     finally
		     {
		    	 return "ogren_ekle?faces-redirect=true";
		     }
			 
			/* FacesMessage message = new FacesMessage("Silme iþlemi baþarýlý");
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage("formid:sil1", message);
			*/
		}
	 
	 
	 
		public String guncellenebilirligiDegistir(ogrenci o)
		{
			o.setGuncellenebilirlik(true);
			return null;
		}
		
		 public String DegisikligiKaydet() {
				for (ogrenci ogrenci1 : ogrenciList){
					ogrenci1.setGuncellenebilirlik(false);
				}
				return null;
		 }
		 
		 public boolean ogrenciGuncelle() throws SQLException
		 {
		   int i= 0;

		    try
		    {
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		       
		        PreparedStatement update = connection.prepareStatement( "UPDATE ogrenci SET kart_no=?, tc=?,isim=?,soyisim=?,"
		                +" WHERE ogren_no=?" );
		      
		        for(ogrenci item:ogrenciList){
		        update.setString( 1, item.getKart_no()); 	
		        update.setString( 2, item.getTc());        
		        update.setString( 3, item.getIsim());       
		        update.setString( 4, item.getSoyisim());        
		        update.setInt( 5, Integer.parseInt(item.getOgren_no()) );
		        
		        i = update.executeUpdate();
		        }
		        
		      
		        
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


	public int getSil_id() {
		return sil_id;
	}



	public void setSil_id(int sil_id) {
		this.sil_id = sil_id;
	}



	public ogrenci getOgrenci() {
		return ogrenci;
	}



	public void setOgrenci(ogrenci ogrenci) {
		this.ogrenci = ogrenci;
	}



	public List<ogrenci> getOgrenciList() {
		return ogrenciList;
	}



	public void setOgrenciList(List<ogrenci> ogrenciList) {
		this.ogrenciList = ogrenciList;
	}
	 
	 

}
