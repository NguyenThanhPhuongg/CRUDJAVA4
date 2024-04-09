package repository;

import entity.NXB;
import entity.Sach;
import jakarta.persistence.Query;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.List;

public class SachRepo {
    public Session hSession;

    public SachRepo() {
        hSession = HibernateUtils.getSessionFactory().openSession();
    }

    public List<Sach> getSach() {
        String hql = "Select entity FROM Sach entity";
        Query q = hSession.createQuery(hql, Sach.class);
        return q.getResultList();
    }

    public void insert(Sach sach) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(sach);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Sach sach) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(sach);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sach findById(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "Select c from Sach c where c.id = :id";
            org.hibernate.query.Query<Sach> query = session.createQuery(hql, Sach.class);
            query.setParameter("id", id);
            Sach sach = query.uniqueResult();// trả về 1 bản ghi
            return sach;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NXB> getNXB() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "Select entity from NXB entity";
            Query q = session.createQuery(hql, NXB.class);
            session.getTransaction().commit();
            return q.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

