package postgreSqlCon;


import java.time.LocalDate;

public class OrderBuilder {

    private String sku = "SKU-1";
    private int quantity = 1;
    private long totalPaise = 129900;
    private String status = "NEW";
    private LocalDate orderedOn = LocalDate.now();
    private boolean refunded = false;

    private OrderBuilder() {
    }

    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    public OrderBuilder withSku(String sku) {
        this.sku = sku;
        return this;
    }

    public OrderBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder withTotalPaise(long totalPaise) {
        this.totalPaise = totalPaise;
        return this;
    }

    public OrderBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder withOrderedOn(LocalDate orderedOn) {
        this.orderedOn = orderedOn;
        return this;
    }

    public OrderBuilder withRefunded(boolean refunded) {
        this.refunded = refunded;
        return this;
    }

    public Order build() {
        return new Order(
                sku,
                quantity,
                totalPaise,
                status,
                orderedOn,
                refunded
        );
    }
}
