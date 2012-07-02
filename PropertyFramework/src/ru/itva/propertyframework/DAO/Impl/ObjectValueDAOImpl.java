package ru.itva.propertyframework.DAO.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import ru.itva.propertyframework.DAO.ObjectValueDAO;
import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.logic.ObjectValue;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Object;
import ru.itva.propertyframework.util.HibernateUtil;

public class ObjectValueDAOImpl implements ObjectValueDAO {
	public void addObjectValue(ObjectValue objectValue) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(objectValue);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при вставке", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}
	}

	public void updateObjectValue(Long ObjectValue_id, ObjectValue objectValue)
			throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(objectValue);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при вставке", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public ObjectValue getObjectValueById(Long ObjectValue_id)
			throws SQLException {
		Session session = null;
		ObjectValue objectValue = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			objectValue = (ObjectValue) session.get(ObjectValue.class,
					ObjectValue_id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objectValue;
	}

	public Collection getAllObjectValues() throws SQLException {
		Session session = null;
		List objValues = new ArrayList<ObjectValue>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			objValues = session.createCriteria(ObjectValue.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objValues;
	}

	public void deleteObjectValue(ObjectValue objectValue) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(objectValue);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка при удалении", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public Collection getObjectValuesByObject(T_Object t_object)
			throws SQLException {
		Session session = null;
		List objectValues = new ArrayList<ObjectValue>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long object_id = t_object.getId();
			Query query = session.createQuery(
					"from ObjectValue where object_id = :objectId ").setLong(
					"objectId", object_id);
			objectValues = (List<ObjectValue>) query.list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objectValues;
	}

	public Collection getObjectValuesByStyle(Style style) throws SQLException {
		Session session = null;
		List objectValues = new ArrayList<ObjectValue>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long style_id = style.getId();
			Query query = session.createQuery(
					"from ObjectValue where style_id = :styleId ").setLong(
					"styleId", style_id);
			objectValues = (List<ObjectValue>) query.list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objectValues;
	}
}
