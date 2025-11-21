package bean;

import domain.*;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ViewScoped
public class CustomerBean implements Serializable {

    @PersistenceContext(unitName = "orderPU")
    private EntityManager em;

    private List<Customer> customers;

    private Customer selected = new Customer();

    @PostConstruct
    public void init() {
        loadCustomers();
    }

    public void loadCustomers() {
        customers = em.createQuery("SELECT c FROM Customer c", Customer.class)
                      .getResultList();
    }

    @Transactional
    public void save() {
        if (selected.getId() == null) {
            em.persist(selected);
        } else {
            em.merge(selected);
        }
        selected = new Customer();
        loadCustomers();
    }

    @Transactional
    public void edit(Customer c) {
        this.selected = c;
    }

    // getters/setters
    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getSelected() {
        return selected;
    }

    public void setSelected(Customer selected) {
        this.selected = selected;
    }
}