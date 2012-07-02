package ru.itva.propertyframework.DAO.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import ru.itva.propertyframework.DAO.ClassDAO;
import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.util.HibernateUtil;

public class ClassDAOImpl implements ClassDAO {
	public void addClass(T_Class t_class) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(t_class);
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

	public void updateClass(Long class_id, T_Class t_class) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t_class);
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

	public T_Class getClassById(Long class_id) throws SQLException{
		Session session = null;
		T_Class t_class = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t_class = (T_Class) session.get(T_Class.class, class_id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return t_class;
	}

	public Collection getAllClasses() throws SQLException{
		Session session = null;
		List classes = new ArrayList<T_Class>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			classes = session.createCriteria(T_Class.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return classes;
	}

	public void deleteClass(T_Class t_class) throws SQLException{
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(t_class);
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
	public Collection getClassesByStyle(Style style) throws SQLException{
		Session session = null;
		List classes = new ArrayList<T_Class>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long style_id = style.getId();
			Query query = session.createQuery(
					" select b " + " from T_Class b INNER JOIN b.styles style"
							+ " where style.id = :styleId ").setLong(
					"styleId", style_id);
			classes = (List<T_Class>) query.list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return classes;
	}
}
