import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.* ; 
@WebServlet(urlPatterns = {"/signup"})
public class signup extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) { 
            String user = request.getParameter("username");              
            String Password = request.getParameter("password");  
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Quora","root","anniesimran");  
            Statement stmt = con.createStatement();      
            String query = "insert into login_credential values('"+user+"','"+Password+"')";             
            String query2 = "select * from login_credential"; 
            ResultSet rs = stmt.executeQuery(query2);
            out.println(user);
            out.println(Password);
           int count =0;
            int flag =0;
            while(rs.next()){
                if(user.equals(rs.getString(1))){
                    count++;                    
                    out.println("username already exists!!");
                    break;
                }
            }            
            if(count==0){
                stmt.executeUpdate(query);
                out.println("Sucessfully inserted");
                flag =1;
            }                        
            if(flag!=1 && count!=1){  
                out.println("success");
                out.println("failed to insert the data!!");
            }  
        }
        catch(Exception e){ System.out.println(e);}  
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        processRequest(request, response);        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        processRequest(request, response);        
    }

}
