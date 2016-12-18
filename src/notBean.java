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
import javax.faces.context.FacesContext;




@ManagedBean(name="notBean")
@ViewScoped
public class notBean {
	private ArrayList<not> not_list= new ArrayList<not>();
	private not not2;
	private int sil_id;

	@PostConstruct
	private void init() {
		not2 = new not();
		 initnotList();
	}
	
	public notBean() 
    { 
        try 
    {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException e) 
    {
        e.printStackTrace();
    } 
    }
	
	
	public void initnotList(){
	
	
	not not3;
		
		try
		     {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		    
			PreparedStatement ps = connection.prepareStatement( "SELECT * FROM notlar where ogret_no=?" );
			
			ps.setInt(1, 1004 );
		
			ResultSet rs    =    ps.executeQuery();
		
			while (rs.next())
		{
			not3 = new not();	
			not3.setNot_no(rs.getInt("not_no"));
			not3.setNot1(rs.getString("not1"));	
			not_list.add(not3);
		}
		
		
		
		     }
		  
		  catch(Exception e)
		    {
		       
		    }
		    finally
		    {
		         
		    } 
		
	}
	
	
	
	public String notEkle(){
		
		 try
		   {
		       
		       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		      
		       PreparedStatement addEntry =
		       connection.prepareStatement( 
		       "INSERT INTO notlar" +
		       "(not1,ogret_no)" +
		       "VALUES (?,?)" );

		       addEntry.setString( 1, not2.getNot1());
		       addEntry.setInt( 2, 1004); //Burasý sessiondan gelmeli!!!
		      
		       

		       addEntry.executeUpdate(); 
		       return null;
		   } 
		   catch(Exception e)
		   {
		       return null;
		   }
		  finally 
		   {
			  return "ogrt_ana_sayfa?faces-redirect=true";
		   } 
	}
	
	public String IDParametresiniAlNot(FacesContext fc)
    {
        Map<String,String> parametreler = fc.getExternalContext().getRequestParameterMap();
	return parametreler.get("not_no");
    }
	
	 public String notSil() {
			// sil db den
			//faces mesaj
			
			 FacesContext fc = FacesContext.getCurrentInstance();
			 this.sil_id = Integer.parseInt(IDParametresiniAlNot(fc));
			 
			
			 try
			 { 
		      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
		      PreparedStatement deleteEntry = connection.prepareStatement( "delete from notlar where not_no =?");

		      deleteEntry.setInt( 1, sil_id) ;
		      deleteEntry.executeUpdate(); 
		     
		     
		     } 
			 
			 catch(SQLException e)
			 {
				 
			 }
		     finally
		     {
		    	 return "ogrt_ana_sayfa?faces-redirect=true";
		     }
			 
			/* FacesMessage message = new FacesMessage("Silme iþlemi baþarýlý");
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage("formid:sil1", message);
			*/
		}
	

	public ArrayList<not> getNot_list() {
		return not_list;
	}

	public void setNot_list(ArrayList<not> not_list) {
		this.not_list = not_list;
	}

	public not getNot2() {
		return not2;
	}

	public void setNot2(not not2) {
		this.not2 = not2;
	}

	public int getSil_id() {
		return sil_id;
	}

	public void setSil_id(int sil_id) {
		this.sil_id = sil_id;
	}

	
}
