import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itva.propertyframework.MainPack.Factory;
import ru.itva.propertyframework.logic.T_Class;


public class AddClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClassServlet() {
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
					response.encodeUrl("/PropertyFramework/ClassesServlet\"") +
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
			T_Class t_class = new T_Class();
			t_class.setName(request.getParameter("name"));
			t_class.setDescription(request.getParameter("description"));
			pw.println("<h2>Запись добавлена</h2>");
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/ClassesServlet\"") +
					">Назад</a>"
					+ "<br>");
			Factory.getInstance().getClassDAO().addClass(t_class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
