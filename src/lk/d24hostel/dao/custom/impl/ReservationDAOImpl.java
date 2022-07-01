package lk.d24hostel.dao.custom.impl;

import lk.d24hostel.dao.custom.ReservationDAO;
import lk.d24hostel.entity.Reserved;
import lk.d24hostel.entity.Room;
import lk.d24hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {


    @Override
    public ArrayList<Reserved> getAll() throws IOException {
        return null;
    }

    @Override
    public boolean save(Reserved entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserved entity) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String s) throws IOException {
        return false;
    }

    @Override
    public String generateNewId() {
        return null;
    }

    @Override
    public Reserved search(String id) {
        return null;
    }

    @Override
    public List<Reserved> searchReservedRoomById(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reserved WHERE room = :roomTypeId";
        Query query = session.createQuery(hql);

        Room room = new Room();
        room.setRoomTypeId(id);

        query.setParameter("roomTypeId",room);
        List<Reserved> r = query.list();

        transaction.commit();
        session.close();

        return r;
    }
}
