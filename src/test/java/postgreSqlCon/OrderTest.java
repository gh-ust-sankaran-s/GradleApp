package postgreSqlCon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void shouldCreateOrder() {
        Order order = OrderBuilder.anOrder()
                .withQuantity(3)
                .build();

        assertEquals(3, order.getQuantity());
    }

    @Test
    void shouldCreateDefaultOrder() {
        Order order = OrderFactory.anOrder();

        assertEquals(1, order.getQuantity());
        assertEquals("NEW", order.getStatus());
    }
}
