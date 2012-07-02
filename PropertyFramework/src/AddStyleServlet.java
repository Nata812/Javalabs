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


public class AddStyleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStyleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("windows-1251");
		PrintWriter pw = response.getWriter();
		try {
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/StylesServlet\"") +
					">Назад</a>"
					+ "<br>");
			pw.println("<form method=\"POST\">");
			pw.println("<b>Обязательный</b> <input type=\"checkbox\" name=\"mandatory\">");
			pw.println("<br>");
			pw.println("<b>Множественный</b> <input type=\"checkbox\" name=\"multiple\">");
			pw.println("<br>");
			pw.println("<b>Принадлежит свойству</b><select name=\"familyid\">");
			//--------------------------------------
			//Тут дергаем все family и пихаем в селектор
			Collection families = Factory.getInstance().getFamilyDAO()
					.getAllFamilies();
			Iterator iterator = families.iterator();
			while (iterator.hasNext()) {
				Family family = (Family) iterator.next();
				pw.println("<option value=\"" +
						family.getId() +
						"\">" +
						family.getName() +
						"</option>");
			}
			//--------------------------------------
			pw.println("</select>");
			pw.println("<br>");
			pw.println("<input type=\"submit\" value=\"Добавить\">");
			pw.println("</form>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //--------------------------------------------
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("windows-1251");
		PrintWriter pw = response.getWriter();
		try {
			Style style = new Style();
			style.setFamily_id(Long.valueOf(request.getParameter("familyid")));
			String s1 = "";
			String s2 = "";
			s1 = request.getParameter("mandatory");//on!!!
			s2 = request.getParameter("multiple");//on!!!
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
			pw.println("<h2>Запись добавлена</h2>");
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/StylesServlet\"") +
					">Назад</a>"
					+ "<br>");
			Factory.getInstance().getStyleDAO().addStyle(style);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
