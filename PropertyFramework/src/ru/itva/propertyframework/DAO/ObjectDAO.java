package ru.itva.propertyframework.DAO;

import java.sql.SQLException;
import java.util.Collection;

import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.logic.T_Object;

public interface ObjectDAO {
	public void addObject(T_Object t_object) throws SQLException;

	public void updateObject(Long object_id, T_Object t_object)
			throws SQLException;

	public T_Object getObjctById(Long objct_id) throws SQLException;

	public Collection getAllObjects() throws SQLException;

	public void deleteObjct(T_Object t_object) throws SQLException;

	public Collection getObjectsByClass(T_Class t_class) throws SQLException;
}
