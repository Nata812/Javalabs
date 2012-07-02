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
import ru.itva.propertyframework.logic.Style;

public class StylesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StylesServlet() {
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
			pw.println("<h1>Все стили</h1>");
			pw.println("");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/MainServlet\"")
					+ ">Назад</a>" + "<br>");
			// -------------------------
			Collection styles = Factory.getInstance().getStyleDAO().getAllStyles();
			Iterator iterator = styles.iterator();
			pw.println("<br>");
			pw.println("<table border=\"1\">");
			pw.println("<tr>");
			pw.println("<td>Принадлежит к свойству (family)</td>");
			pw.println("<td>Обязательный</td>");
			pw.println("<td>Множественный</td>");
			pw.println("<td>Редактировать</td>");
			pw.println("<td>Удалить</td>");
			pw.println("</tr>");
			while (iterator.hasNext()) {
				pw.println("<tr>");
				Style style = (Style) iterator.next();
				pw.println("<td>" + 
						"<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/EditFamilyServlet?familyid="
										+ style.getFamily_id() + "\"")
				+ ">" + style.getFamily_id() + 
				"</a></td>");
				pw.println("<td>" + style.isIs_mandatory() + "</td>");
				pw.println("<td>" + style.isIs_multiple() + "</td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/EditStyleServlet?styleid="
										+ style.getId() + "\"")
						+ ">Edit</a>");
				pw.println("</td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/DeleteStyleServlet?styleid="
										+ style.getId() + "\"")
						+ ">Del</a>");
				pw.println("</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/AddStyleServlet")
					+ ">Добавить стиль</a>" + "<br>");
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
