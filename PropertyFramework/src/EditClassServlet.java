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
public class EditClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditClassServlet() {
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
		Long cid = Long.parseLong(request.getParameter("classid"));
		try {
			T_Class t_class = Factory.getInstance().getClassDAO().getClassById(cid);
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/ClassesServlet\"") +
					">Назад</a>"
					+ "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>class_id </b><b> " + t_class.getId()
					+ "</b>");
			pw.println("<b>name</b> <input type=\"text\" name=\"name\" value=\"" +
					t_class.getName() +
					"\">");
			pw.println("<b>description</b> <input type=\"text\" name=\"description\" value=\"" +
					t_class.getDescription() +
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
			T_Class t_class = new T_Class();
			t_class.setId(Long.parseLong(request.getParameter("classid")));
			t_class.setName(request.getParameter("name"));
			t_class.setDescription(request.getParameter("description"));
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/ClassesServlet\"") +
					">Назад</a>"
					+ "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>class_id </b><b> " + t_class.getId()
					+ "</b>");
			pw.println("<b>name</b> <input type=\"text\" name=\"name\" value=\"" +
					t_class.getName() +
					"\">");
			pw.println("<b>description</b> <input type=\"text\" name=\"description\" value=\"" +
					t_class.getDescription() +
					"\">");
			pw.println("<input type=\"submit\" value=\"Сохранить\">");
			pw.println("</form>");
			Factory.getInstance().getClassDAO().updateClass(t_class.getId(), t_class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
