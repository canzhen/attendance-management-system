package dbentity;

// Generated 2015-12-11 11:02:07 by Hibernate Tools 4.0.0

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Sc.
 * @see dbentity.Sc
 * @author Hibernate Tools
 */
public class ScHome {

	private static final Log log = LogFactory.getLog(ScHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
	
	public Transaction createTransaction() {
		return sessionFactory.getCurrentSession().beginTransaction();
	}

	public void persist(Sc transientInstance) {
		log.debug("persisting Sc instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Sc instance) {
		log.debug("attaching dirty Sc instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sc instance) {
		log.debug("attaching clean Sc instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Sc persistentInstance) {
		log.debug("deleting Sc instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sc merge(Sc detachedInstance) {
		log.debug("merging Sc instance");
		try {
			Sc result = (Sc) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sc findById(dbentity.ScId id) {
		log.debug("getting Sc instance with id: " + id);
		try {
			Sc instance = (Sc) sessionFactory.getCurrentSession().get(
					"dbentity.Sc", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Sc instance) {
		log.debug("finding Sc instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("dbentity.Sc")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findBySno(String sno){
		Configuration config=new Configuration().configure();
        SessionFactory sessionFactory= config.buildSessionFactory();
        Session session=null;
        Transaction tr=null;
        List list = null;
        
        try{
            session= sessionFactory.openSession();
            tr=session.beginTransaction();
            String hql="from Sc sc where sc.id.sno=:sno";                          
            Query query= session.createQuery(hql);
            query.setParameter("sno", sno);
            list = query.list();
            tr.commit();
        }catch(Exception e){
            tr.rollback();
        }finally{
            if(session!=null){
                session.close();
            }
            if(sessionFactory!=null){
                sessionFactory.close();
            }
        }
        return list;
	}
	
	
	public List findByCno(String cno){
		Configuration config=new Configuration().configure();
        SessionFactory sessionFactory= config.buildSessionFactory();
        Session session=null;
        Transaction tr=null;
        List list = null;
        
        try{
            session= sessionFactory.openSession();
            tr=session.beginTransaction();
            String hql="from Sc sc where sc.id.cno=:cno";                          
            Query query= session.createQuery(hql);
            query.setParameter("cno", cno);
            list = query.list();                                                                               
            tr.commit();
        }catch(Exception e){
            tr.rollback();
        }finally{
            if(session!=null){
                session.close();
            }
            if(sessionFactory!=null){
                sessionFactory.close();
            }
        }
        return list;
	}
}
