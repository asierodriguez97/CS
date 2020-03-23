import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.util.*;

// Simple example of a session tracking

public class ShowSession extends HttpServlet { 

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    
    response.setContentType("text/html"); 
    PrintWriter out = response.getWriter();
    
    String title = "Session Tracking Example";
    
    HttpSession session = request.getSession(true); 
    
    String heading; 
    
    //Use getAttribute instead of getValue in version 2.2 of the Servlet API (getValue is deprecated)
    Integer accessCount = (Integer)session.getAttribute("accessCount");
    
    if (accessCount == null) { 
	  heading = "Welcome, Newcomer";
      accessCount = new Integer(0);     
    } else { 
      heading = "Welcome Back"; 
      accessCount = new Integer(accessCount.intValue() + 1); 
    } 
    
    //Use putAttribute instead of putValue in version 2.2 of the Servlet API (putValue is deprecated)
    session.setAttribute("accessCount", accessCount);
    
    out.println("<HTML>\n" + 
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" + 
                "<BODY BGCOLOR=\"#FDF5E6\">\n" + 
                "<CENTER>\n" + 
                "<H1>" + heading + "</H1>\n" + 
                "<H2>Information on Your Session:</H2>\n" + 
                "<TABLE BORDER=1>\n" + 
                "<TR BGCOLOR=\"#FFAD00\">\n" + 
                "  <TH>Info Type<TH>Value\n" + 
                "<TR>\n" +
                "  <TD>ID\n" +
                "  <TD>" + session.getId() + "\n" +
                "<TR>\n" +
                "  <TD>Creation Time\n" +
                "  <TD>" + new Date(session.getCreationTime()) + "\n" +
                "<TR>\n" +
                "  <TD>Time of Last Access\n" +
                "  <TD>" + new Date(session.getLastAccessedTime()) + "\n" +
                "<TR>\n" +                
                "  <TD>Number of Previous Accesses\n" +
                "  <TD>" + accessCount + "\n" +
                " </TABLE>\n" +
                "</BODY></HTML>");
  }
  
  //Handle GET and POST request identically
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	  
	  doGet( request, response);
  }
}
                