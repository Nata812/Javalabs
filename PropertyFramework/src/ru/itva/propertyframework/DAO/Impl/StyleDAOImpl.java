package ru.itva.propertyframework.DAO.Impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Query;

import ru.itva.propertyframework.DAO.StyleDAO;
import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.logic.Style;
import ru.itva.propertyframework.logic.T_Class;
import ru.itva.propertyframework.util.HibernateUtil;

public class StyleDAOImpl implements StyleDAO {

	public void addStyle(Style style) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(style);
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

	public void updateStyle(Long Style_id, Style style) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(style);
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

	public Style getStyleById(Long Style_id) throws SQLException {
		Session session = null;
		Style style = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			style = (Style) session.get(Style.class, Style_id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return style;
	}

	public Collection getAllStyles() throws SQLException {
		Session session = null;
		List styles = new ArrayList<Style>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(Style.class);
			styles = cr.list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return styles;
	}

	public void deleteStyle(Style style) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(style);
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

	public Collection getStylesByFamily(Family family) throws SQLException {
		Session session = null;
		List styles = new ArrayList<Style>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long family_id = family.getId();
			Query query = session.createQuery(
					"from Style where family_id = :familyId ").setLong(
					"familyId", family_id);
			styles = (List<Style>) query.list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return styles;
	}

	public Collection getStylesByClass(T_Class t_class) throws SQLException{
		Session session = null;
		List styles = new ArrayList<Style>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Long class_id = t_class.getId();
			Query query = session.createQuery(
					" select b " + " from Style b INNER JOIN b.t_classes class"
							+ " where class.id = :classId ").setLong(
					"classId", class_id);
			styles = (List<Style>) query.list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return styles;
	}
}