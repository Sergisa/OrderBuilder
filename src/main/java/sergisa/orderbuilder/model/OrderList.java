package sergisa.orderbuilder.model;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    List<OrderItem> orders;

    public OrderList() {
        this.orders = new ArrayList<>();
    }

    public OrderList(List<OrderItem> orders) {
        this.orders = orders;
    }

    public OrderList addOrderItem(OrderItem orderItem) {
        orders.add(orderItem);
        return this;
    }

    public List<OrderItem> getOrderList() {
        return orders;
    }

    public OrderList setAddress(String address) {
        for (OrderItem orderItem : orders) {
            orderItem.setAddress(address);
        }
        return this;
    }

    /*public OrderItem setAmount(int amount) {
        this.amount = amount;
        return this;
    }*/

    public OrderList setContactPerson(String contactPerson) {
        for (OrderItem orderItem : orders) {
            orderItem.setContactPerson(contactPerson);
        }
        return this;
    }

}
