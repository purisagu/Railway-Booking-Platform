package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Train;
import dto.User;

public class TrainDao {
	EntityManagerFactory e = Persistence.createEntityManagerFactory("dev");
	EntityManager m = e.createEntityManager();
	EntityTransaction t = m.getTransaction();

	public void save(Train train) {
		t.begin();
		m.persist(train);
		t.commit();
	}

	public List<Train> fetchall() {
		return m.createQuery("select x from Train x").getResultList();
	}
	public void delete(int tnumber){
		t.begin();
		m.remove(m.find(Train.class, tnumber));
		t.commit();
	}
	public Train fetch(int number){
		return m.find(Train.class, number);
	}

	public void update(Train train) {
		t.begin();
		m.merge(train);
		t.commit();
		
	}

	
}
