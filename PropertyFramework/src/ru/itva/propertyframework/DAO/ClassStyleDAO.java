package ru.itva.propertyframework.DAO;

import java.sql.SQLException;
import java.util.Collection;

import ru.itva.propertyframework.logic.ClassStyle;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;

public interface ClassStyleDAO {
	  public void addClassStyle(ClassStyle classStyle) throws SQLException;
	  public void updateClassStyle(Long ClassStyle_id, ClassStyle class_Style) throws SQLException;
	  public ClassStyle getClassStyleById(Long ClassStyle_id) throws SQLException;
	  public Collection getAllClassStyles() throws SQLException;
	  public void deleteClassStyle(ClassStyle classStyle) throws SQLException;
	  public Collection getClassStylesByClass(T_Class t_class) throws SQLException;
	  public Collection getClassStylesByStyle(Style style) throws SQLException;
}
