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

public class ObjectsByClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObjectsByClassServlet() {
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
			pw.println("");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/MainServlet\"")
					+ ">Назад</a>" + "<br>");
			// --------------------------------------
			// Тут селектор с классами
			Collection classes = Factory.getInstance().getClassDAO()
					.getAllClasses();
			Iterator iterator2 = classes.iterator();
			pw.println("<form method=\"POST\">");
			pw.println("<b>Принадлежит классу</b><select name=\"classid\">");
			while (iterator2.hasNext()) {
				T_Class t_class = (T_Class) iterator2.next();
				pw.println("<option value=\"" + t_class.getId() + "\">"
						+ t_class.getName() + "</option>");
			}
			// --------------------------------------
			pw.println("</select>");
			pw.println("<input type=\"submit\" value=\"Искать\">");
			pw.println("<br>");
			pw.println("</form>");
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
		try {
			Long classid = Long.valueOf(request.getParameter("classid"));
			response.setContentType("text/html");
			response.setCharacterEncoding("windows-1251");
			PrintWriter pw = response.getWriter();
			pw.println("");
			pw.println("");
			pw.println("");
			pw.println("");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/MainServlet\"")
					+ ">Назад</a>" + "<br>");
			// --------------------------------------
			// Тут селектор с классами
			Collection classes = Factory.getInstance().getClassDAO()
					.getAllClasses();
			Iterator iterator2 = classes.iterator();
			pw.println("<form method=\"POST\">");
			pw.println("<b>Принадлежит классу</b><select name=\"classid\">");
			String selected = "";
			while (iterator2.hasNext()) {
				T_Class t_class = (T_Class) iterator2.next();
				if(t_class.getId().longValue() == classid.longValue())selected = "SELECTED";
				pw.println("<option value=\"" + t_class.getId() + "\"" +
						selected + ">"
						+ t_class.getName() + "</option>");
				selected = "";
			}
			// --------------------------------------
			pw.println("</select>");
			pw.println("<input type=\"submit\" value=\"Искать\">");
			pw.println("<br>");
			pw.println("</form>");
			// -------------------------
			Collection objects = Factory.getInstance().getObjectDAO()
					.getAllObjects();
			Iterator iterator = objects.iterator();
			pw.println("<br>");
			pw.println("<table border=\"1\">");
			pw.println("<tr>");
			pw.println("<td>Имя</td>");
			pw.println("<td>Принвдлежит к классу</td>");
			pw.println("</tr>");
			while (iterator.hasNext()) {
				T_Object t_object = (T_Object) iterator.next();
				if (t_object.getClass_id().longValue() == classid.longValue()) {
					pw.println("<tr>");
					pw.println("<td>" + t_object.getObject_name() + "</td>");
					pw.println("<td>"
							+ "<a href=\""
							+ response
									.encodeUrl("/PropertyFramework/EditClassServlet?classid="
											+ t_object.getClass_id() + "\"")
							+ ">" + t_object.getClass_id() + "</a></td>");
					pw.println("</tr>");
				}
			}
			pw.println("</table>");
		} catch (Exception e) {
		}
		// --------------------------------------------
	}
}
