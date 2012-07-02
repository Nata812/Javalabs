import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itva.propertyframework.MainPack.Factory;
import ru.itva.propertyframework.logic.ClassStyle;

public class ClassStylesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassStylesServlet() {
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
			pw.println("<h1>Все ассоциации класс-стиль</h1>");
			pw.println("");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/MainServlet\"")
					+ ">Назад</a>" + "<br>");
			// -------------------------
			Collection classStyles = Factory.getInstance().getClassStyleDAO()
					.getAllClassStyles();
			Iterator iterator = classStyles.iterator();
			pw.println("<br>");
			pw.println("<table border=\"1\">");
			pw.println("<tr>");
			pw.println("<td>Класс</td>");
			pw.println("<td>Стиль</td>");
			pw.println("<td>Редактировать</td>");
			pw.println("<td>Удалить</td>");
			pw.println("</tr>");
			while (iterator.hasNext()) {
				pw.println("<tr>");
				ClassStyle classStyle = (ClassStyle) iterator.next();
				pw.println("<td>" + 
						"<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/EditClassServlet?classid="
										+ classStyle.getClass_id() + "\"") +
				">"+classStyle.getClass_id() + 
				"</td>");
				pw.println("<td>" + 
						"<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/EditStyleServlet?styleid="
										+ classStyle.getStyle_id() + "\"")
				+ ">" + classStyle.getStyle_id() + 
				"</td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/EditClassStyleServlet?classstyleid="
										+ classStyle.getId() + "\"")
						+ ">Edit</a>");;
				pw.println("</td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/DeleteClassStyleServlet?classstyleid="
										+ classStyle.getId() + "\"")
						+ ">Del</a>");
				pw.println("</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/AddClassStyleServlet")
					+ ">Добавить ассоциацию</a>" + "<br>");
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
