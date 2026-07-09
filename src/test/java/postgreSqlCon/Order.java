package postgreSqlCon;

import java.time.LocalDate;
import java.util.Objects;

public class Order {

    private String sku;
    private int quantity;
    private long totalPaise;
    private String status;
    private LocalDate orderedOn;
    private boolean refunded;

    public Order() {
    }

    public Order(String sku, int quantity, long totalPaise, String status, LocalDate orderedOn, boolean refunded) {
        this.sku = sku;
        this.quantity = quantity;
        this.totalPaise = totalPaise;
        this.status = status;
        this.orderedOn = orderedOn;
        this.refunded = refunded;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTotalPaise() {
        return totalPaise;
    }

    public void setTotalPaise(long totalPaise) {
        this.totalPaise = totalPaise;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(LocalDate orderedOn) {
        this.orderedOn = orderedOn;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return quantity == order.quantity && totalPaise == order.totalPaise && refunded == order.refunded && Objects.equals(sku, order.sku) && Objects.equals(status, order.status) && Objects.equals(orderedOn, order.orderedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, quantity, totalPaise, status, orderedOn, refunded);
    }

    @Override
    public String toString() {
        return "Order{" +
                "sku='" + sku + '\'' +
                ", quantity=" + quantity +
                ", totalPaise=" + totalPaise +
                ", status='" + status + '\'' +
                ", orderedOn=" + orderedOn +
                ", refunded=" + refunded +
                '}';
    }
}
