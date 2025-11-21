package bean;
import domain.*;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class OrderBean implements Serializable {

    @PersistenceContext(unitName = "orderPU")
    private EntityManager em;

    private List<Order> orders;

    @PostConstruct
    public void init() {
        orders = em.createQuery("SELECT o FROM Order o", Order.class)
                   .getResultList();
    }

    public List<Order> getOrders() {
        return orders;
    }
}