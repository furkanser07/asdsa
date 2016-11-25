


import javax.annotation.PostConstruct;
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



public class ogretmen{
    

    private String ogret_no; 
    private String isim;
    private String soyisim;
    private String tc;
    private String email;
    private String sifre; 
    private String silinecekid;
    private boolean guncellenebilirlik;
    
    CachedRowSet rowSet=null;
    DataSource dataSource;
   
 @PostConstruct
 public void init()
 {
     
 }
 
 
 
  


	public String getSilinecekid() 
    {
        return silinecekid;
    }

    public void setSilinecekid(String silinecekid) 
    {
        this.silinecekid = silinecekid;
    }

    public CachedRowSet getRowSet() 
    {
        return rowSet;
    }

    public void setRowSet(CachedRowSet rowSet) 
    {
        this.rowSet = rowSet;
    }

    public DataSource getDataSource() 
    {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) 
    {
        this.dataSource = dataSource;
    }
   
    
    
	public boolean isGuncellenebilirlik() {
		return guncellenebilirlik;
	}

	public void setGuncellenebilirlik(boolean guncellenebilirlik) {
		this.guncellenebilirlik = guncellenebilirlik;
	}


	public String getOgret_no() 
    {
        return ogret_no;
    }

    public void setOgret_no(String ogret_no) 
    {
        this.ogret_no = ogret_no;
    }

    public String getIsim() 
    {
        return isim;
    }

    public void setIsim(String isim) 
    {
        this.isim = isim;
    }

    public String getSoyisim() 
    {
        return soyisim;
    }

    public void setSoyisim(String soyisim) 
    {
        this.soyisim = soyisim;
    }

    public String getTc() 
    {
        return tc;
    }

    public void setTc(String tc) 
    {
        this.tc = tc;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getSifre() 
    {
        return sifre;
    }

    public void setSifre(String sifre) 
    {
        this.sifre = sifre;
    }
    
    
   
     public ogretmen() 
    { 
        try 
    {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException e) 
    {
        e.printStackTrace();
    }   
   
    
    
    }
    
    
  
     

        
   /*   public ResultSet ogretmenGor() throws SQLException
 {
    

     try
     {
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yoklama","root","root");
     PreparedStatement ps =
     connection.prepareStatement( "SELECT * FROM ogretmen" );


     rowSet = new com.sun.rowset.CachedRowSetImpl();
     rowSet.populate( ps.executeQuery() );
    return rowSet;
     } 
     finally
     {
     
     } 
 } 
  
 */




//@Override
//public void destroy() {
//	// TODO Auto-generated method stub
//	
//}

//@Override
//public void init(FilterConfig arg0) throws ServletException {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException 
//{
//	try{
//		
//	HttpServletRequest reqt = (HttpServletRequest) request;
//	HttpServletResponse resp = (HttpServletResponse) response;
//	HttpSession ses = reqt.getSession(false);
//	
//	String reqURI = reqt.getRequestURI();
//	
//	if (reqURI.indexOf("/login.xhtml") >= 0
//			|| (ses != null && ses.getAttribute("email") != null)
//			|| reqURI.indexOf("/public/") >= 0
//			|| reqURI.contains("javax.faces.resource"))
//		chain.doFilter(request, response);
//	else
//		resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
//} 
//
//catch (Exception e) {
//	System.out.println(e.getMessage());
//
//}
//	
//}

     
    
}
