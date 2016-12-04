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

@ManagedBean(name = "ogrenciBean")
@ViewScoped
public class OgrenciBean {

	private int sil_id;
	private ogrenci ogrenci;
	private ders ders;
	private List<ogrenci> ogrenciList = new ArrayList<>();
	private List<ders> dersList = new ArrayList<>();

	@PostConstruct
	private void init() {
		initDersList();
		initOgrenciList();
		ogrenci = new ogrenci();
		ders= new ders();
	}

	public OgrenciBean() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (final ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}

	public void initOgrenciList() {
	

		ogrenci ogrn;

		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yoklama", "root", "root");

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM ogrenci");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ogrn = new ogrenci();
				ogrn.setKart_no(rs.getString("kart_no"));
				ogrn.setOgren_no(rs.getString("ogren_no"));
				ogrn.setTc(rs.getString("tc"));
				ogrn.setIsim(rs.getString("isim"));
				ogrn.setSoyisim(rs.getString("soyisim"));
				ogrn.setEmail(rs.getString("email"));
				ogrn.setSifre(rs.getString("sifre"));
				ogrn.setGuncellenebilirlik(false);
				ogrenciList.add(ogrn);
			}

		}

		catch (Exception e) {

		} finally {

		}

	}
	
	public void initDersList() {
		
		ders drs;
		
		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yoklama", "root", "root");

			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM ders_ogrenci INNER JOIN ders "
							+ "ON ders.ders_no=ders_ogrenci.ders_no where ders_ogrenci.ogren_no=?");
			
			ps.setInt(1, 20003 );

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				drs = new ders();
				drs.setDers_isim(rs.getString("ders_isim"));
				drs.setDers_no(rs.getString("ders_no"));
				drs.setKredi(rs.getString("kredi"));
				dersList.add(drs);
			}

		}

		catch (Exception e) {

		} finally {

		}
		
		
		
		
	}
	
	
	public String ogrDersEkle()
	{
		
		try {

			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yoklama", "root", "root");
			
			PreparedStatement addEntry = connection.prepareStatement("INSERT INTO ders_ogrenci"
					+ " (ders_no,ogren_no,hafta1,hafta2,hafta3,hafta4,hafta5,hafta6,hafta7,hafta8,hafta9,hafta10,hafta11,hafta12) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			addEntry.setString(1, ders.getDers_no());
			addEntry.setInt(2, 20002);
			addEntry.setInt(3, 0);
			addEntry.setInt(4, 1);
			addEntry.setInt(5, 1);
			addEntry.setInt(6, 1);
			addEntry.setInt(7, 0);
			addEntry.setInt(8, 0);
			addEntry.setInt(9, 0);
			addEntry.setInt(10, 0);
			addEntry.setInt(11, 0);
			addEntry.setInt(12, 0);
			addEntry.setInt(13, 0);
			addEntry.setInt(14, 0);
			
			
			
			
			addEntry.executeUpdate();
			return null;
			
		}
		
		catch (Exception e) {
			return null;
		} finally {
			return "ogr_ders_sec?faces-redirect=true";
		}
		
	}

	public String ogrenciEkle() {
		// db ye kaydet

		try {

			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yoklama", "root", "root");

			PreparedStatement addEntry = connection
					.prepareStatement("INSERT INTO ogrenci"
							+ "(kart_no,tc,isim,soyisim,email,sifre)" + "VALUES (?,?,?,?,?,?)");

			addEntry.setString(1, ogrenci.getKart_no());
			addEntry.setString(2, ogrenci.getTc());
			addEntry.setString(3, ogrenci.getIsim());
			addEntry.setString(4, ogrenci.getSoyisim());
			addEntry.setString(5, ogrenci.getEmail());
			addEntry.setString(6, ogrenci.getSifre());
			

			addEntry.executeUpdate();

			return null;

		} catch (Exception e) {
			return null;
		} finally {
			return "ogren_ekle?faces-redirect=true";
		}

	}

	public String ogrDersParam(FacesContext fc) {
		Map<String, String> parametreler = fc.getExternalContext()
				.getRequestParameterMap();
		return parametreler.get("ders_no");
	}
	
	public String IDParametresiniAl(FacesContext fc) {
		Map<String, String> parametreler = fc.getExternalContext()
				.getRequestParameterMap();
		return parametreler.get("ogren_no");
	}

	
	public String ogrDersSil() {
		// sil db den
		// faces mesaj

		FacesContext fc = FacesContext.getCurrentInstance();
		this.sil_id = Integer.parseInt(ogrDersParam(fc));

		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yoklama", "root", "root");
			PreparedStatement deleteEntry = connection
					.prepareStatement("delete from ders_ogrenci where ders_no =?");

			deleteEntry.setInt(1, sil_id);
			deleteEntry.executeUpdate();

		}

		catch (SQLException e) {

		} finally {
			return "ogr_ders_sec?faces-redirect=true";
		}

		/*
		 * FacesMessage message = new FacesMessage("Silme iþlemi baþarýlý");
		 * FacesContext context = FacesContext.getCurrentInstance();
		 * context.addMessage("formid:sil1", message);
		 */
	}
	
	public String ogrenciSil() {
		// sil db den
		// faces mesaj

		FacesContext fc = FacesContext.getCurrentInstance();
		this.sil_id = Integer.parseInt(IDParametresiniAl(fc));

		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yoklama", "root", "root");
			PreparedStatement deleteEntry = connection
					.prepareStatement("delete from ogrenci where ogren_no =?");

			deleteEntry.setInt(1, sil_id);
			deleteEntry.executeUpdate();

		}

		catch (SQLException e) {

		} finally {
			return "ogren_ekle?faces-redirect=true";
		}

		/*
		 * FacesMessage message = new FacesMessage("Silme iþlemi baþarýlý");
		 * FacesContext context = FacesContext.getCurrentInstance();
		 * context.addMessage("formid:sil1", message);
		 */
	}

	public String guncellenebilirligiDegistir(ogrenci o) {
		o.setGuncellenebilirlik(true);
		return null;
	}

	public String DegisikligiKaydet() {
		for (ogrenci ogrenci1 : ogrenciList) {
			ogrenci1.setGuncellenebilirlik(false);
		}
		return null;
	}

	public boolean ogrenciGuncelle() throws SQLException {
		int i = 0;

		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yoklama", "root", "root");

			PreparedStatement update = connection
					.prepareStatement("UPDATE ogrenci SET kart_no=?, tc=?,isim=?,soyisim=?,email=?,sifre=?"
							+ " WHERE ogren_no=?");

			for (ogrenci item : ogrenciList) {
				update.setString(1, item.getKart_no());
				update.setInt(2,Integer.parseInt( item.getTc()));
				update.setString(3, item.getIsim());
				update.setString(4, item.getSoyisim());
				update.setString(5, item.getEmail());
				update.setString(6, item.getSifre());
				
				update.setInt(7, Integer.parseInt(item.getOgren_no()));

				i = update.executeUpdate();
			}

			if (i > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {

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

	public ders getDers() {
		return ders;
	}

	public void setDers(ders ders) {
		this.ders = ders;
	}

	public List<ders> getDersList() {
		return dersList;
	}

	public void setDersList(List<ders> dersList) {
		this.dersList = dersList;
	}

}
