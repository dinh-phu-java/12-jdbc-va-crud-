import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HomeServlet",urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username=request.getParameter("user_name");
//        String password=request.getParameter("password");
//        PrintWriter writer=response.getWriter();
//
//        writer.println("User Name la: "+username);
//        writer.println("Password la: "+password);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("user_name");
        String password=request.getParameter("password");
        PrintWriter writer=response.getWriter();

        writer.println("User Name la: "+username);
        writer.println("Password la: "+password);
    }
    @Override
    public void destroy(){
        System.out.println("Huy serlet");
    }
}
