package ru.itva.propertyframework.DAO.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;


import org.hibernate.Session;

import ru.itva.propertyframework.DAO.FamilyDAO;
import ru.itva.propertyframework.logic.Family;
import ru.itva.propertyframework.util.HibernateUtil;

public class FamilyDAOImpl implements FamilyDAO {
	public void addFamily(Family family) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(family);
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

	public void updateFamily(Long family_id, Family family) throws SQLException{
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(family);
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

	public Family getFamilyById(Long family_id) throws SQLException{
		Session session = null;
		Family family = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			family = (Family) session.get(Family.class, family_id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return family;
	}

	public Collection getAllFamilies() throws SQLException {
		Session session = null;
		List families = new ArrayList<Family>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			families = session.createCriteria(Family.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return families;
	}

	public void deleteFamily(Family family) throws SQLException{
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(family);
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
}