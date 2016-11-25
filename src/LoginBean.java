import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped

public class LoginBean implements Filter {

	ogretmen ogrt1 = new ogretmen();
	
	public boolean girisYap1(String username, String password) throws SQLException 
	{
		
	    try
	    {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
	       
	        
	        PreparedStatement ps = connection.prepareStatement( "Select * from ogretmen where"
	                + " email = ? and sifre = ?" );

	        ps.setString( 1, ogrt1.getEmail());
	        ps.setString( 2, ogrt1.getSifre());
	        
	        
	         
	        ResultSet rs    =    ps.executeQuery();
	      
	       
	        
	    while (rs.next()) 
	    {
	    	
	    	ogrt1.setIsim(rs.getString("isim"));
	        ogrt1.setSoyisim(rs.getString("soyisim"));
	        ogrt1.setTc(rs.getString("tc"));
	        ogrt1.setEmail("email");
	        ogrt1.setOgret_no(rs.getString("ogret_no"));
	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginOgrt", ogrt1);
	        	  
		return true;
		}
    }
	    
	    catch(Exception e)
	    {
	        return false;
	    }
	    finally
	    {
	         
	    } 
	   

	 
	return false;
			
	     
	}  
	
	
	public String girisYap() throws SQLException
	{
		
		
		boolean kabul = girisYap1(ogrt1.getEmail(), ogrt1.getSifre());
		
		if(kabul)
		{
			HttpSession hs = util.getSession();
			hs.setAttribute("email", ogrt1.getEmail());
			return "ogrt_ana_sayfa";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_WARN,
	                                "Incorrect Username and Passowrd",
	                                "Please enter correct username and Password"));
	return "login";
		}
	}
	        
	  
	public String cikisYap()
	{
		HttpSession hs = util.getSession();
		hs.invalidate();
		return "login";
		
	}
	
	
	
	
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException 
	{
	try{
		
	HttpServletRequest reqt = (HttpServletRequest) request;
	HttpServletResponse resp = (HttpServletResponse) response;
	HttpSession ses = reqt.getSession(false);
	
		String reqURI = reqt.getRequestURI();
		
	if (reqURI.indexOf("/login.xhtml") >= 0
			|| (ses != null && ses.getAttribute("email") != null)
			|| reqURI.indexOf("/public/") >= 0
			|| reqURI.contains("javax.faces.resource"))
			chain.doFilter(request, response);
		else
			resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
	} 
	
	catch (Exception e) {
		System.out.println(e.getMessage());
	
	}
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}