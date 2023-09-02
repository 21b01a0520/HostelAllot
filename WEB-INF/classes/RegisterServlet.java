import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/
public class RegisterServlet extends HttpServlet {
       public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String hno = request.getParameter("hno");
        String srank =request.getParameter("srank");

    String branch = request.getParameter("branch");
 String[] branchOptions = request.getParameterValues("branch");
    if (branchOptions != null && branchOptions.length > 0) {
        branch = branchOptions[0];
    }

String hostel= request.getParameter("hostel");
    String[] hostelOptions = request.getParameterValues("hostel");
    if (hostelOptions != null && hostelOptions.length > 0) {
          hostel = hostelOptions[0];
    }

    String roomno = request.getParameter("roomno");
 String[] roomnoOptions = request.getParameterValues("roomno");
    if (roomnoOptions != null && roomnoOptions.length > 0) {
        roomno = roomnoOptions[0];
    }
    String bed = request.getParameter("bed");
    String[] bedOptions = request.getParameterValues("bed");
    if (bedOptions != null && bedOptions.length > 0) {
        bed = bedOptions[0];
    }
      Connection con = null;
       PreparedStatement pstmt = null;
         try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useSSL=false","root","1234");
            String query = "insert into student (name, hno, srank,branch, hostel, roomno, bed) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, hno);
pstmt.setString(3, srank);
pstmt.setString(4, branch);
pstmt.setString(5, hostel);
pstmt.setString(6, roomno);
pstmt.setString(7, bed);
            int x = pstmt.executeUpdate();
            System.out.println("Data Updated Successfully..."+x);
            response.sendRedirect("success.html");
        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
