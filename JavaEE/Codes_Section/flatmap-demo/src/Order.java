import java.util.List;

public class Order {
    private Integer orderId;
    private List<String> items;

    public Order() {

    }

    public Order(Integer orderId, List<String> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", items=" + items +
                '}';
    }
}
