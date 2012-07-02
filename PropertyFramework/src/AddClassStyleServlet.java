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
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;


public class AddClassStyleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClassStyleServlet() {
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
			pw.println("<b>Стиль</b><select name=\"styleid\">");
			//--------------------------------------
			//Тут дергаем все family и пихаем в селектор
			Collection styles = Factory.getInstance().getStyleDAO()
					.getAllStyles();
			Iterator iterator = styles.iterator();
			while (iterator.hasNext()) {
				Style style = (Style) iterator.next();
				pw.println("<option value=\"" +
						style.getId() +
						"\">" +
						style.getId() +
						"</option>");
			}
			//--------------------------------------
			pw.println("</select>");
			pw.println("<br>");
			pw.println("<b>Класс</b><select name=\"classid\">");
			//--------------------------------------
			//Тут дергаем все family и пихаем в селектор
			Collection classes = Factory.getInstance().getClassDAO()
					.getAllClasses();
			Iterator iterator2 = classes.iterator();
			while (iterator2.hasNext()) {
				T_Class t_class = (T_Class) iterator2.next();
				pw.println("<option value=\"" +
						t_class.getId() +
						"\">" +
						t_class.getName() +
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
			ClassStyle classStyle = new ClassStyle();
			classStyle.setClass_id(Long.valueOf(request.getParameter("classid")));
			classStyle.setStyle_id(Long.valueOf(request.getParameter("styleid")));
			pw.println("<h2>Запись добавлена</h2>");
			pw.println("<br>" +
					"<a href=\"" +
					response.encodeUrl("/PropertyFramework/ClassStylesServlet\"") +
					">Назад</a>"
					+ "<br>");
			Factory.getInstance().getClassStyleDAO().addClassStyle(classStyle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
