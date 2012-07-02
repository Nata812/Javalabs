package ru.itva.propertyframework.MainPack;

import ru.itva.propertyframework.DAO.ClassDAO;
import ru.itva.propertyframework.DAO.ClassStyleDAO;
import ru.itva.propertyframework.DAO.FamilyDAO;
import ru.itva.propertyframework.DAO.ObjectDAO;
import ru.itva.propertyframework.DAO.ObjectValueDAO;
import ru.itva.propertyframework.DAO.StyleDAO;
import ru.itva.propertyframework.DAO.Impl.ClassDAOImpl;
import ru.itva.propertyframework.DAO.Impl.ClassStyleDAOImpl;
import ru.itva.propertyframework.DAO.Impl.FamilyDAOImpl;
import ru.itva.propertyframework.DAO.Impl.ObjectDAOImpl;
import ru.itva.propertyframework.DAO.Impl.ObjectValueDAOImpl;
import ru.itva.propertyframework.DAO.Impl.StyleDAOImpl;

public class Factory {

	private static ClassStyleDAO classStyleDAO = null;
	private static ClassDAO classDAO = null;
	private static ObjectValueDAO objectValueDAO = null;
	private static FamilyDAO familyDAO = null;
	private static ObjectDAO objectDAO = null;
	private static StyleDAO styleDAO = null;

	private static Factory instance = null;

	public static synchronized Factory getInstance() {
		if (instance == null) {
			instance = new Factory();
		}
		return instance;
	}

	public StyleDAO getStyleDAO() {
		if (styleDAO == null) {
			styleDAO = new StyleDAOImpl();
		}
		return styleDAO;
	}

	public ObjectValueDAO getObjectValueDAO() {
		if (objectValueDAO == null) {
			objectValueDAO = new ObjectValueDAOImpl();
		}
		return objectValueDAO;
	}

	public FamilyDAO getFamilyDAO() {
		if (familyDAO == null) {
			familyDAO = new FamilyDAOImpl();
		}
		return familyDAO;
	}

	public ClassDAO getClassDAO() {
		if (classDAO == null) {
			classDAO = new ClassDAOImpl();
		}
		return classDAO;
	}

	public ObjectDAO getObjectDAO() {
		if (objectDAO == null) {
			objectDAO = new ObjectDAOImpl();
		}
		return objectDAO;
	}
	public ClassStyleDAO getClassStyleDAO() {
		if (classStyleDAO == null) {
			classStyleDAO = new ClassStyleDAOImpl();
		}
		return classStyleDAO;
	}
}