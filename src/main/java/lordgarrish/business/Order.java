package lordgarrish.business;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderID;

    @ElementCollection
    @CollectionTable(name = "order_items")
    private List<LineItem> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;

    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Instant createdOn;

    public Order() {}

    public Order(String orderID, Cart cart, Customer customer) {
        this.orderID = orderID;
        items = cart.getItems();
        this.customer = customer;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<LineItem> getItems() {
        return new ArrayList<>(items);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isEmptyOrder() {
        return items.isEmpty();
    }

    public String getTotalPrice() {
        double totalPrice = 0.0;
        for(LineItem item : items) {
            totalPrice += item.getTotal();
        }
        DecimalFormat decFormat = new DecimalFormat();
        decFormat.setMaximumFractionDigits(2);
        return decFormat.format(totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customer=" + customer +
                ", createdOn=" + createdOn +
                '}';
    }
}
