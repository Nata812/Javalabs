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
import ru.itva.propertyframework.logic.T_Object;

public class ObjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObjectsServlet() {
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
			pw.println("<h1>Все объекты</h1>");
			pw.println("");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/MainServlet\"")
					+ ">Назад</a>" + "<br>");
			// -------------------------
			Collection objects = Factory.getInstance().getObjectDAO()
					.getAllObjects();
			Iterator iterator = objects.iterator();
			pw.println("<br>");
			pw.println("<table border=\"1\">");
			pw.println("<tr>");
			pw.println("<td>Имя</td>");
			pw.println("<td>Принвдлежит к классу</td>");
			pw.println("<td>Удалить</td>");
			pw.println("</tr>");
			while (iterator.hasNext()) {
				pw.println("<tr>");
				T_Object t_object = (T_Object) iterator.next();
				pw.println("<td>" + t_object.getObject_name() + "</td>");
				pw.println("<td>" + 
						"<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/EditClassServlet?classid="
										+ t_object.getClass_id() + "\"")
				+ ">" +  t_object.getClass_id() + 
				"</a></td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/DeleteObjectServlet?objectid="
										+ t_object.getId() + "\"")
						+ ">Del</a>");
				pw.println("</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/AddObjectServlet")
					+ ">Добавить объект</a>" + "<br>");
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
