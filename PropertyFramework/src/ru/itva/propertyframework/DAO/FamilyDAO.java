package ru.itva.propertyframework.DAO;

import java.util.Collection;
import java.sql.SQLException;

import ru.itva.propertyframework.logic.Family;

public interface FamilyDAO {
  public void addFamily(Family family) throws SQLException;
  public void updateFamily(Long family_id, Family family) throws SQLException;
  public Family getFamilyById(Long family_id) throws SQLException;
  public Collection getAllFamilies() throws SQLException;
  public void deleteFamily(Family family) throws SQLException;
}