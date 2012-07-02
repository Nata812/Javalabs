import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itva.propertyframework.MainPack.Factory;
import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.logic.T_Class;

public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassesServlet() {
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
			pw.println("<h1>Все классы</h1>");
			pw.println("");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/MainServlet\"")
					+ ">Назад</a>" + "<br>");
			// -------------------------
			Collection classes = Factory.getInstance().getClassDAO()
					.getAllClasses();
			Iterator iterator = classes.iterator();
			pw.println("<br>");
			pw.println("<table border=\"1\">");
			pw.println("<tr>");
			pw.println("<td>Имя</td>");
			pw.println("<td>Пояснение</td>");
			pw.println("<td>Редактировать</td>");
			pw.println("<td>Удалить</td>");
			pw.println("</tr>");
			while (iterator.hasNext()) {
				pw.println("<tr>");
				T_Class t_class = (T_Class) iterator.next();
				pw.println("<td>" + t_class.getName() + "</td>");
				pw.println("<td>" + t_class.getDescription() + "</td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/EditClassServlet?classid="
										+ t_class.getId() + "\"")
						+ ">Edit</a>");;
				pw.println("</td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/DeleteClassServlet?classid="
										+ t_class.getId() + "\"")
						+ ">Del</a>");
				pw.println("</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/AddClassServlet")
					+ ">Добавить класс</a>" + "<br>");
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
