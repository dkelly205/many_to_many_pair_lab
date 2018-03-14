package db;

import models.Course;
import models.Lesson;
import models.Student;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void saveOrUpdate(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getList(Criteria criteria){
        List<T> results = null;
        try{
            transaction = session.beginTransaction();
            results = criteria.list();
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUniqueResult(Criteria criteria){
        T result  = null;
        try{
            transaction = session.beginTransaction();
            result = (T)criteria.uniqueResult();
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> T find(Class classtype, int id){
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;
        Criteria criteria = session.createCriteria(classtype);
        criteria.add(Restrictions.idEq(id));
        result = getUniqueResult(criteria);
        return result;
    }

    public static <T> List<T> getAll(Class classtype){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        Criteria cr = session.createCriteria(classtype);
        results = getList(cr);
        return results;
    }

    public static List<Student> getStudentsFromCourse(Course course) {
        session = HibernateUtil.getSessionFactory().openSession();
        List <Student> results = null;
        Criteria cr = session.createCriteria(Course.class);
        results = getList(cr);
        return results;
    }

    public static List<Lesson> getLessonsFromCourse(Course course) {
        session = HibernateUtil.getSessionFactory().openSession();
        List <Lesson> results = null;
        Criteria cr = session.createCriteria(Course.class);
        results = getList(cr);
        return results;
    }

    public static void addStudentToLesson(Student student, Lesson lesson) {

        student.addLesson(lesson);
        lesson.addStudent(student);
        saveOrUpdate(lesson);
        saveOrUpdate(student);
    }













}
