package ru.itva.propertyframework.DAO.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;


import org.hibernate.Query;
import org.hibernate.Session;

import ru.itva.propertyframework.DAO.ObjectDAO;
import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.logic.T_Object;
import ru.itva.propertyframework.util.HibernateUtil;

public class ObjectDAOImpl implements ObjectDAO {
	public void addObject(T_Object t_object) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(t_object);
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

	public void updateObject(Long object_id, T_Object t_object)
			throws SQLException{
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t_object);
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

	public T_Object getObjctById(Long object_id) throws SQLException{
		Session session = null;
		T_Object t_object = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t_object = (T_Object) session.get(T_Object.class, object_id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return t_object;
	}

	public Collection getAllObjects() throws SQLException{
		Session session = null;
		List objects = new ArrayList<T_Object>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			objects = session.createCriteria(T_Object.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objects;
	}

	public void deleteObjct(T_Object t_object) throws SQLException{
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(t_object);
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
	public Collection getObjectsByClass(T_Class t_class) throws SQLException{
		Session session = null;
		List objects = new ArrayList<T_Object>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long class_id = t_class.getId();
			Query query = session.createQuery(
					"from T_Object where class_id = :classId ").setLong(
					"classId", class_id);
			objects = (List<T_Object>) query.list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objects;
	}
}