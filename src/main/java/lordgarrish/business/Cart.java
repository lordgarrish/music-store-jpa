package lordgarrish.business;

import javax.persistence.*;
import java.io.*;
import java.text.DecimalFormat;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "cart_items")
    private List<LineItem> items = new ArrayList<>();

    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Instant createdOn;

    public Cart() {}

    public Long getId() {
        return id;
    }

    public List<LineItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(LineItem item) {
        String code = item.getAlbum().getCode();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getAlbum().getCode().equals(code)) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(LineItem item) {
        String code = item.getAlbum().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getAlbum().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
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

    public void removeAllItemsFromCart() {
        items.clear();
    }

    public int getCount() {
        return items.size();
    }

    public boolean isEmptyCart() {
        return items.isEmpty();
    }
}
