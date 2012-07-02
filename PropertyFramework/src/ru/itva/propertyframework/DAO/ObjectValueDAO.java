package ru.itva.propertyframework.DAO;

import java.util.Collection;
import java.sql.SQLException;

import ru.itva.propertyframework.logic.ObjectValue;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Object;

public interface ObjectValueDAO {
  public void addObjectValue(ObjectValue objectValue) throws SQLException;
  public void updateObjectValue(Long ObjectValue_id, ObjectValue objectValue) throws SQLException;
  public ObjectValue getObjectValueById(Long ObjectValue_id) throws SQLException;
  public Collection getAllObjectValues() throws SQLException;
  public void deleteObjectValue(ObjectValue objectValue) throws SQLException;
  public Collection getObjectValuesByObject(T_Object t_object) throws SQLException;
  public Collection getObjectValuesByStyle(Style style) throws SQLException;
}