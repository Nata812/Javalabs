import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itva.propertyframework.MainPack.Factory;
import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.logic.T_Object;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Servlet implementation class HelloWorld
 */
public class EditStyleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditStyleServlet() {
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
		response.setContentType("text/html");
		response.setCharacterEncoding("windows-1251");
		PrintWriter pw = response.getWriter();
		Long sid = Long.parseLong(request.getParameter("styleid"));
		try {
			Style style = Factory.getInstance().getStyleDAO().getStyleById(sid);
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/StylesServlet\"")
					+ ">Назад</a>" + "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>style_id </b><b> " + style.getId() + "</b>");
			pw.println("<br>");
			String ismandatory = "";
			String ismultiple = "";
			if (style.isIs_mandatory())
				ismandatory = "CHECKED";
			if (style.isIs_multiple())
				ismultiple = "CHECKED";
			pw.println("<b>Обязательный</b> <input type=\"checkbox\" name=\"mandatory\""
					+ ismandatory + ">");
			pw.println("<br>");
			pw.println("<b>Множественный</b> <input type=\"checkbox\" name=\"multiple\""
					+ ismultiple + ">");
			pw.println("<br>");
			pw.println("<b>Принадлежит свойству</b><select name=\"familyid\">");
			// --------------------------------------
			// Тут дергаем все family и пихаем в селектор
			Collection families = Factory.getInstance().getFamilyDAO()
					.getAllFamilies();
			Iterator iterator = families.iterator();
			String selected = "";
			while (iterator.hasNext()) {
				Family family = (Family) iterator.next();
				if (family.getId() == style.getFamily_id())
					selected = "SELECTED";
				pw.println("<option value=\"" + family.getId() + "\""
						+ selected + ">" + family.getName() + "</option>");
				selected = "";
			}
			// --------------------------------------
			pw.println("</select>");
			pw.println("<br>");
			pw.println("<input type=\"submit\" value=\"Сохранить\">");
			pw.println("</form>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		response.setContentType("text/html");
		response.setCharacterEncoding("windows-1251");
		PrintWriter pw = response.getWriter();
		Long sid = Long.parseLong(request.getParameter("styleid"));
		try {
			Style style = new Style();
			style.setId(sid);
			String s1 = "";
			String s2 = "";
			s1 = request.getParameter("mandatory");// on!!!
			s2 = request.getParameter("multiple");// on!!!
			if (s1 != null) {
				if (s1.equalsIgnoreCase("on"))
					style.setIs_mandatory(true);
				else
					style.setIs_mandatory(false);
			}
			else
				style.setIs_mandatory(false);
			if (s2 != null) {
				if (s2.equalsIgnoreCase("on"))
					style.setIs_multiple(true);
				else
					style.setIs_multiple(false);
			}
			else
				style.setIs_multiple(false);
			style.setFamily_id(Long.valueOf(request.getParameter("familyid")));
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/StylesServlet\"")
					+ ">Назад</a>" + "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>style_id </b><b> " + style.getId() + "</b>");
			pw.println("<br>");
			String ismandatory = "";
			String ismultiple = "";
			if (style.isIs_mandatory())
				ismandatory = "CHECKED";
			if (style.isIs_multiple())
				ismultiple = "CHECKED";
			pw.println("<b>Обязательный</b> <input type=\"checkbox\" name=\"mandatory\""
					+ ismandatory + ">");
			pw.println("<br>");
			pw.println("<b>Множественный</b> <input type=\"checkbox\" name=\"multiple\""
					+ ismultiple + ">");
			pw.println("<br>");
			pw.println("<b>Принадлежит свойству</b><select name=\"familyid\">");
			// --------------------------------------
			// Тут дергаем все family и пихаем в селектор
			Collection families = Factory.getInstance().getFamilyDAO()
					.getAllFamilies();
			Iterator iterator = families.iterator();
			String selected = "";
			while (iterator.hasNext()) {
				Family family = (Family) iterator.next();
				if (family.getId() == style.getFamily_id())
					selected = "SELECTED";
				pw.println("<option value=\"" + family.getId() + "\""
						+ selected + ">" + family.getName() + "</option>");
				selected = "";
			}
			// --------------------------------------
			pw.println("</select>");
			pw.println("<br>");
			pw.println("<input type=\"submit\" value=\"Сохранить\">");
			pw.println("</form>");
			Factory.getInstance().getStyleDAO()
					.updateStyle(style.getId(), style);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
