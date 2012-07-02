package ru.itva.propertyframework.DAO.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import ru.itva.propertyframework.DAO.ClassStyleDAO;
import ru.itva.propertyframework.DAO.ObjectValueDAO;
import ru.itva.propertyframework.logic.ClassStyle;
import ru.itva.propertyframework.logic.ObjectValue;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.logic.T_Object;
import ru.itva.propertyframework.util.HibernateUtil;

public class ClassStyleDAOImpl implements ClassStyleDAO {
	public void addClassStyle(ClassStyle classStyle) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(classStyle);
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

	public void updateClassStyle(Long ClassStyle_id, ClassStyle class_Style)
			throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(class_Style);
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

	public ClassStyle getClassStyleById(Long ClassStyle_id) throws SQLException {
		Session session = null;
		ClassStyle classStyle = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			classStyle = (ClassStyle) session.get(ClassStyle.class,
					ClassStyle_id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return classStyle;
	}

	public Collection getAllClassStyles() throws SQLException {
		Session session = null;
		List classStyles = new ArrayList<ClassStyle>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			classStyles = session.createCriteria(ClassStyle.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return classStyles;
	}

	public void deleteClassStyle(ClassStyle classStyle) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(classStyle);
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

	public Collection getClassStylesByClass(T_Class t_class)
			throws SQLException {
		Session session = null;
		List classStyles = new ArrayList<ClassStyle>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long class_id = t_class.getId();
			Query query = session.createQuery(
					"from ClassStyle where class_id = :classId ").setLong(
					"classId", class_id);
			classStyles = (List<ClassStyle>) query.list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return classStyles;
	}

	public Collection getClassStylesByStyle(Style style) throws SQLException {
		Session session = null;
		List classStyles = new ArrayList<ClassStyle>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long style_id = style.getId();
			Query query = session.createQuery(
					"from ClassStyle where style_id = :styleId ").setLong(
					"styleId", style_id);
			classStyles = (List<ClassStyle>) query.list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return classStyles;
	}
}
