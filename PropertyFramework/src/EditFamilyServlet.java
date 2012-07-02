import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itva.propertyframework.MainPack.Factory;
import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.logic.T_Object;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;



/**
 * Servlet implementation class HelloWorld
 */
public class EditFamilyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFamilyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		//Выводит текстовые поля с заданными значениями объекта
		//------------------------------------
		response.setContentType("text/html");
		response.setCharacterEncoding("windows-1251");
		PrintWriter pw = response.getWriter();
		Long fid = Long.parseLong(request.getParameter("familyid"));
		try {
			Family family = Factory.getInstance().getFamilyDAO().getFamilyById(fid);
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/FamiliesServlet\"") +
					">Назад</a>"
					+ "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>family_id </b><b> " + family.getId()
					+ "</b>");
			pw.println("<b>name</b> <input type=\"text\" name=\"name\" value=\"" +
					family.getName() +
					"\">");
			pw.println("<b>description</b> <input type=\"text\" name=\"description\" value=\"" +
					family.getDescription() +
					"\">");
			pw.println("<input type=\"submit\" value=\"Сохранить\">");
			pw.println("</form>");
		} catch (SQLException e) {
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
		// Сохраняет объект и редиректит назад
		response.setContentType("text/html");
		response.setCharacterEncoding("windows-1251");
		PrintWriter pw = response.getWriter();
		try {
			Family family = new Family();
			family.setId(Long.parseLong(request.getParameter("familyid")));
			family.setName(request.getParameter("name"));
			family.setDescription(request.getParameter("description"));
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/FamiliesServlet\"") +
					">Назад</a>"
					+ "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>family_id </b><b> " + family.getId()
					+ "</b>");
			pw.println("<b>name</b> <input type=\"text\" name=\"name\" value=\"" +
					family.getName() +
					"\">");
			pw.println("<b>description</b> <input type=\"text\" name=\"description\" value=\"" +
					family.getDescription() +
					"\">");
			pw.println("<input type=\"submit\" value=\"Сохранить\">");
			pw.println("</form>");
			Factory.getInstance().getFamilyDAO().updateFamily(family.getId(), family);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
