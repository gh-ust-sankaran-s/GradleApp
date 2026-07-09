package postgreSqlCon;

public class OrderFactory {

    public static Order anOrder() {
        return OrderBuilder.anOrder().build();
    }

    public static Order anOrderWithQuantity(int quantity) {
        return OrderBuilder.anOrder()
                .withQuantity(quantity)
                .build();
    }
}
