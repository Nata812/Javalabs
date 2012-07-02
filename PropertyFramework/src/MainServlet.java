import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * Servlet implementation class HelloWorld
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("windows-1251");
			PrintWriter pw = response.getWriter();
			pw.println("");
			pw.println("");
			pw.println("");
			pw.println("<h1>Hello World</h1>");
			pw.println("");
			pw.println("<br>"
					+ "<a href=\""
					+ response
							.encodeUrl("/PropertyFramework/FamiliesServlet\"")
					+ ">Свойства (family)</a>" + "<br>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/StylesServlet\"")
					+ ">Стили (style)</a>" + "<br>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/ClassesServlet\"")
					+ ">Классы (class)</a>" + "<br>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/ClassStylesServlet\"")
					+ ">Ассоциации классов и стилей</a>" + "<br>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/ObjectsServlet\"")
					+ ">Объекты</a>" + "<br>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/ObjectsByClassServlet\"")
					+ ">Объекты заданного класса</a>" + "<br>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/ObjectsByNameServlet\"")
					+ ">Поиск обектов по имени</a>" + "<br>");
			// -------------------------
		} catch (Exception e) {
		}
		// --------------------------------------------
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
