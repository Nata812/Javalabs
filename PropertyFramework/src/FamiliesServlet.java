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

public class FamiliesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FamiliesServlet() {
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
			pw.println("<h1>Все свойства</h1>");
			pw.println("");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/MainServlet\"")
					+ ">Назад</a>" + "<br>");
			// -------------------------
			Collection families = Factory.getInstance().getFamilyDAO()
					.getAllFamilies();
			Iterator iterator = families.iterator();
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
				Family family = (Family) iterator.next();
				pw.println("<td>" + family.getName() + "</td>");
				pw.println("<td>" + family.getDescription() + "</td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/EditFamilyServlet?familyid="
										+ family.getId() + "\"")
						+ ">Edit</a>");;
				pw.println("</td>");
				pw.println("<td>");
				pw.println("<a href=\""
						+ response
								.encodeUrl("/PropertyFramework/DeleteFamilyServlet?familyid="
										+ family.getId() + "\"")
						+ ">Del</a>");
				pw.println("</td>");
				// pw.println("<br>");
				/*
				 * Collection styles =
				 * Factory.getInstance().getStyleDAO().getStylesByFamily
				 * (family); Iterator iterator2 = styles.iterator(); while
				 * (iterator2.hasNext()) { Style style = (Style)
				 * iterator2.next(); pw.println("Mandatory: " +
				 * style.isIs_mandatory() + "Multiple: " +
				 * style.isIs_multiple()); pw.println("<br>"); }
				 */
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/AddFamilyServlet")
					+ ">Добавить свойство</a>" + "<br>");
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
