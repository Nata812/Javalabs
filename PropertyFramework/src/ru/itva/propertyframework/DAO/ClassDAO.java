package ru.itva.propertyframework.DAO;

import java.sql.SQLException;
import java.util.Collection;

import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;

public interface ClassDAO {
	  public void addClass(T_Class t_class) throws SQLException;
	  public void updateClass(Long class_id, T_Class t_class) throws SQLException;
	  public T_Class getClassById(Long class_id) throws SQLException;
	  public Collection getAllClasses() throws SQLException;
	  public void deleteClass(T_Class t_class) throws SQLException;
	  public Collection getClassesByStyle(Style style) throws SQLException;
}
