import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itva.propertyframework.MainPack.Factory;
import ru.itva.propertyframework.logic.ClassStyle;
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
public class EditClassStyleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditClassStyleServlet() {
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
		Long csid = Long.parseLong(request.getParameter("classstyleid"));
		try {
			ClassStyle classStyle = Factory.getInstance().getClassStyleDAO().getClassStyleById(csid);
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/ClassStylesServlet\"")
					+ ">Назад</a>" + "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<br>");
			pw.println("<b>Стиль</b><select name=\"styleid\">");
			// --------------------------------------
			// Тут дергаем все family и пихаем в селектор
			Collection styles = Factory.getInstance().getStyleDAO()
					.getAllStyles();
			Iterator iterator = styles.iterator();
			String selected = "";
			while (iterator.hasNext()) {
				Style style = (Style) iterator.next();
				if (style.getId() == classStyle.getStyle_id())
					selected = "SELECTED";
				pw.println("<option value=\"" + style.getId() + "\"" +
					selected + ">"
						+ style.getId() + "</option>");
				selected = "";
			}
			// --------------------------------------
			pw.println("</select>");
			pw.println("<br>");
			pw.println("<b>Класс</b><select name=\"classid\">");
			// --------------------------------------
			// Тут дергаем все family и пихаем в селектор
			Collection classes = Factory.getInstance().getClassDAO()
					.getAllClasses();
			Iterator iterator2 = classes.iterator();
			String selected2 = "";
			while (iterator2.hasNext()) {
				T_Class t_class = (T_Class) iterator2.next();
				if(t_class.getId()==classStyle.getClass_id()) 
					selected2 = "SELECTED";
				pw.println("<option value=\"" + t_class.getId() + "\"" +
						selected2 + ">"
						+ t_class.getName() + "</option>");
				selected2 = "";
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
		Long csid = Long.parseLong(request.getParameter("classstyleid"));
		try {
			ClassStyle classStyle = new ClassStyle();
			classStyle.setId(csid);
			classStyle.setClass_id(Long.valueOf(request.getParameter("classid")));
			classStyle.setStyle_id(Long.valueOf(request.getParameter("styleid")));
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/ClassStylesServlet\"")
					+ ">Назад</a>" + "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>Стиль</b><select name=\"styleid\">");
			// --------------------------------------
			// Тут дергаем все family и пихаем в селектор
			Collection styles = Factory.getInstance().getStyleDAO()
					.getAllStyles();
			Iterator iterator = styles.iterator();
			String selected = "";
			while (iterator.hasNext()) {
				Style style = (Style) iterator.next();
				Long x = classStyle.getStyle_id();
				Long y = style.getId();
				if (x.longValue()==y.longValue())
					selected = "SELECTED";
				pw.println("<option value=\"" + style.getId() + "\"" +
					selected + ">"
						+ style.getId() + "</option>");
				selected = "";
			}
			// --------------------------------------
			pw.println("</select>");
			pw.println("<br>");
			pw.println("<b>Класс</b><select name=\"classid\">");
			// --------------------------------------
			// Тут дергаем все family и пихаем в селектор
			Collection classes = Factory.getInstance().getClassDAO()
					.getAllClasses();
			Iterator iterator2 = classes.iterator();
			String selected2 = "";
			while (iterator2.hasNext()) {
				T_Class t_class = (T_Class) iterator2.next();
				Long x = classStyle.getClass_id();
				Long z = t_class.getId();
				if(x.longValue()==z.longValue()) selected2 = "SELECTED";
				pw.println("<option value=\"" + t_class.getId() + "\"" +
						selected2 + ">"
						+ t_class.getName() + "</option>");
				selected2 = "";
			}
			// --------------------------------------
			pw.println("</select>");
			pw.println("<br>");
			pw.println("<input type=\"submit\" value=\"Сохранить\">");
			pw.println("</form>");
			Factory.getInstance().getClassStyleDAO().updateClassStyle(classStyle.getId(), classStyle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
