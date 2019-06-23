package lt.bit.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lt.bit.data.Address;
import lt.bit.data.Contact;
import lt.bit.data.Person;

public class DB {

    private static final List<Person> list = new ArrayList<>();

    static {
        Person p = new Person("PirmasVardas", "PirmaPavarde", new Date(), new BigDecimal("154.76"));
        list.add(p);
        Person p2 = new Person("AntrasVardas", "AntraPavarde", new Date(), new BigDecimal("987.76"));
        list.add(p2);
        Person p3 = new Person("Jonis", "AntraPavarde", new Date(), new BigDecimal("987.76"));
        list.add(p3);
        Person p4 = new Person("AntrasVardas", "AntraPavarde", new Date(), new BigDecimal("987.76"));
        list.add(p4);
        
        
         addAddress(1, new Address("Liepklanio 79-2","Vilnius", "02120"));
         addAddress(1, new Address("kalnio 79-2","e", "02120"));
         addAddress(1, new Address("a 79-2","s", "02120"));
         addAddress(2, new Address("l 79-2","Kaunas", "02120"));
        
         addContact(1, new Contact("Tomas", "mob"));
         addContact(1, new Contact("Domas", "mob"));
         addContact(1, new Contact("Fonas", "tel"));
         addContact(2, new Contact("Tomas", "Mail"));
         addContact(2, new Contact("Tomas2", "mo"));
         
         System.out.println(p.getAddresses());
         System.out.println("hit");
         System.out.println(p2.getAddresses());
//     
//        list.get(0).getAddresses().add(new Address("Liepklanio 79-2","Vilnius", "02120"));
//        list.get(0).getAddresses().add(new Address("Liepklanio 79-2","Vilnius", "02120"));
//        
        
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

    public static Person delete(Integer id) {
        Person p = getById(id);
        if (p != null) {
            list.remove(p);
        }
        return p;
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
