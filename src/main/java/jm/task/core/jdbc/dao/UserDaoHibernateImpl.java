package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;
Transaction transaction = null;
    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = """
                CREATE TABLE IF NOT EXISTS users
                (id SERIAL PRIMARY KEY,
                name VARCHAR(40),
                lastName VARCHAR(40),
                 age INT)
                """;

        session.createSQLQuery(sql).executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User (name, lastName,age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> removeUserById(long id) {
        List<User> userList = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            User del = session.get(User.class, id);
            session.delete(del);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from Users", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DELETE FROM Users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
