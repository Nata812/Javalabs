import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itva.propertyframework.MainPack.Factory;
import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.logic.T_Object;

public class AddObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddObjectServlet() {
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
		try {
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/StylesServlet\"")
					+ ">Назад</a>" + "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>name</b> <input type=\"text\" name=\"name\" value=\"" +
					"\">");
			pw.println("<b>Принадлежит классу</b><select name=\"classid\">");
			// --------------------------------------
			// Тут дергаем все family и пихаем в селектор
			Collection classes = Factory.getInstance().getClassDAO()
					.getAllClasses();
			Iterator iterator = classes.iterator();
			while (iterator.hasNext()) {
				T_Class t_class = (T_Class) iterator.next();
				pw.println("<option value=\"" + t_class.getId() + "\">"
						+ t_class.getName() + "</option>");
			}
			// --------------------------------------
			pw.println("</select>");
			pw.println("<br>");
			pw.println("<input type=\"submit\" value=\"Добавить\">");
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
		try {
			T_Object t_object = new T_Object();
			t_object.setObject_name(request.getParameter("name"));
			t_object.setClass_id(Long.valueOf(request.getParameter("classid")));
			pw.println("<h2>Запись добавлена</h2>");
			pw.println("<br>" + "<a href=\""
					+ response.encodeUrl("/PropertyFramework/ObjectsServlet\"")
					+ ">Назад</a>" + "<br>");
			Factory.getInstance().getObjectDAO().addObject(t_object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
