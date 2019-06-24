package lt.bit.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lt.bit.data.Address;
import lt.bit.data.Contact;
import lt.bit.data.Person;

public class DB {

    private static final String DB_NAME = "address_book";
    private static final String CONNECTION__STRING = "jdbc:mysql://localhost:3306/";
    private static final String TABLE_CONTACTS = "persons";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_BIRTH_DATE = "birth_date";
    private static final String COLUMN_SALARY = "salary";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_EMAIL = "email";

    private static final List<Person> list = new ArrayList<>();

    static {
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Connection conn = DriverManager.getConnection(CONNECTION__STRING + DB_NAME + "?serverTimezone=UTC", "root", "admin");
            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM persons");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                list.add(new Person(results.getString(COLUMN_FIRST_NAME),
                        results.getString(COLUMN_LAST_NAME),
                        results.getDate(COLUMN_BIRTH_DATE),
                        results.getBigDecimal(COLUMN_SALARY),
                        results.getInt(COLUMN_ID)));
            }
            results.close();
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public static List<Person> getAll() {
        return list;
    }

//    grazina visus adresus is visu zmoniu
    public static List<Address> getAllAddresses() {
        List<Address> all = new ArrayList<>();
        for (Person p : list) {
            all.addAll(p.getAddresses());
        }
        return all;
    }

    //    grazina visus kontaktus is visu zmoniu
    public static List<Contact> getAllContacts() {
        List<Contact> all = new ArrayList<>();
        for (Person p : list) {
            all.addAll(p.getContacts());
        }
        return all;
    }

//    grazina tam tikro zmogaus adresus
    public static List<Address> getPersonAddresses(Integer id) {
        Person p = getById(id);
        if (p != null) {
            return p.getAddresses();
        }
        return new ArrayList<>();
    }

    public static List<Contact> getPersonContacts(Integer id) {
        Person p = getById(id);
        if (p != null) {
            return p.getContacts();
        }
        return new ArrayList<>();
    }

    public static Person getById(Integer id) {
        if (id == null) {
            return null;
        }
        for (Person p : list) {
            if (id.equals(p.getId())) {
                return p;
            }
        }
        return null;
    }

    public static Person getByAddress(Address a) {
        if (a == null || a.getId() == null) {
            return null;
        }
        for (Person p : list) {
            for (Address pa : p.getAddresses()) {
                if (a.getId().equals(pa.getId())) {
                    return p;
                }
            }
        }
        return null;
    }

    public static Person getByContact(Contact c) {
        if (c == null || c.getId() == null) {
            return null;
        }
        for (Person p : list) {
            for (Contact ca : p.getContacts()) {
                if (c.getId().equals(ca.getId())) {
                    return p;
                }
            }
        }
        return null;
    }

    public static Address getAddressById(Integer id) {
        if (id == null) {
            return null;
        }
        List<Address> all = getAllAddresses();
        for (Address p : all) {
            if (id.equals(p.getId())) {
                return p;
            }
        }
        return null;
    }

    public static Contact getContactById(Integer id) {
        if (id == null) {
            return null;
        }
        List<Contact> all = getAllContacts();
        for (Contact p : all) {
            if (id.equals(p.getId())) {
                return p;
            }
        }
        return null;
    }

    public static Person add(Person p) {
        if (!list.contains(p)) {
//            p.createId();
            list.add(p);
            return p;
        }
        return null;
    }

    public static Address addAddress(Integer personId, Address a) {
        if (!getAllAddresses().contains(a)) {
            Person p = getById(personId);
            if (p != null) {
//                a.createId();
                p.getAddresses().add(a);
                return a;
            }
        }
        return null;
    }

    public static Contact addContact(Integer personId, Contact c) {
        if (!getAllContacts().contains(c)) {
            Person p = getById(personId);
            if (p != null) {
//                c.createId();
                p.getContacts().add(c);
                return c;
            }
        }
        return null;
    }

    public static Person update(Person p) {
        if (p == null) {
            return null;
        }
        Person currentPerson = getById(p.getId());
        if (currentPerson != null) {
            currentPerson.setFirstName(p.getFirstName());
            currentPerson.setLastName(p.getLastName());
            currentPerson.setBirthDate(p.getBirthDate());
            currentPerson.setSalary(p.getSalary());
        }
        return currentPerson;
    }

    public static Address updateAddress(Address a) {
        if (a == null) {
            return null;
        }
        Address currentAddress = getAddressById(a.getId());
        if (currentAddress != null) {
            currentAddress.setAddress(a.getAddress());
            currentAddress.setCity(a.getCity());
            currentAddress.setPostalCode(a.getPostalCode());
        }
        return currentAddress;
    }

    public static Contact updateContact(Contact c) {
        if (c == null) {
            return null;
        }
        Contact currentContact = getContactById(c.getId());
        if (currentContact != null) {
            currentContact.setContact(c.getContact());
            currentContact.setType(c.getType());
        }
        return currentContact;
    }

    public static void delete(Integer id) {

        
        try {
            Connection conn = DriverManager.getConnection(CONNECTION__STRING + DB_NAME + "?serverTimezone=UTC", "root", "admin");
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM persons WHERE id=" + id );
  
            statement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        Person p = getById(id);
        
        if (p != null) {
            list.remove(p);
        }
//        return p;
        
        
    }

    public static Address deleteAddress(Integer id) {
        if (id == null) {
            return null;
        }
        for (Person p : list) {
            for (Address a : p.getAddresses()) {
                if (id.equals(a.getId())) {
                    p.getAddresses().remove(a);
                    return a;
                }
            }
        }
        return null;
    }

    public static Contact deleteContact(Integer id) {
        if (id == null) {
            return null;
        }
        for (Person p : list) {
            for (Contact c : p.getContacts()) {
                if (id.equals(c.getId())) {
                    p.getContacts().remove(c);
                    return c;
                }
            }
        }
        return null;
    }

}
