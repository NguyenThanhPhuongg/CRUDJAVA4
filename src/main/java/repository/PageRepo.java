package repository;

import entity.Sach;
import jakarta.persistence.Query;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.List;

public class PageRepo {
    public List<Sach> getAllSach(int pageNumber, int pageSize) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            String sql = "select u from Sach u";
            Query query = session.createQuery(sql, Sach.class);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            List<Sach> list = query.getResultList();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public long getTutolPage(int pageSize){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            String hql = "SELECT COUNT(u) from Sach u";
            Query query = session.createQuery(hql,Sach.class);
            long tutolCount = (long) query.getSingleResult();
            return (tutolCount + pageSize - 1)/pageSize;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
