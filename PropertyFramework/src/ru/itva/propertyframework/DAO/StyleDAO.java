package ru.itva.propertyframework.DAO;

import java.util.Collection;
import java.sql.SQLException;

import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;

public interface StyleDAO {
  public void addStyle(Style style) throws SQLException;
  public void updateStyle(Long Style_id, Style style) throws SQLException;
  public Style getStyleById(Long Style_id) throws SQLException;
  public Collection getAllStyles() throws SQLException;
  public void deleteStyle(Style style) throws SQLException;
  public Collection getStylesByFamily(Family family) throws SQLException;
  public Collection getStylesByClass(T_Class t_class) throws SQLException;
}