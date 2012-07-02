import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itva.propertyframework.MainPack.Factory;
import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.logic.T_Object;


public class AddFamilyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFamilyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("windows-1251");
		PrintWriter pw = response.getWriter();
		try {
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/FamiliesServlet\"") +
					">Назад</a>"
					+ "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>name</b> <input type=\"text\" name=\"name\" value=\"" +
					"\">");
			pw.println("<b>description</b> <input type=\"text\" name=\"description\" value=\"" +
					"\">");
			pw.println("<input type=\"submit\" value=\"Добавить\">");
			pw.println("</form>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //--------------------------------------------
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("windows-1251");
		PrintWriter pw = response.getWriter();
		try {
			Family family = new Family();
			family.setName(request.getParameter("name"));
			family.setDescription(request.getParameter("description"));
			pw.println("<h2>Запись добавлена</h2>");
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/FamiliesServlet\"") +
					">Назад</a>"
					+ "<br>");
			Factory.getInstance().getFamilyDAO().addFamily(family);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
