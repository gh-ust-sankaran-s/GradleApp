package postgreSqlCon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldSetSkuUsingBuilder() {
        Order order = OrderBuilder.anOrder()
                .withSku("SKU-100")
                .build();

        assertEquals("SKU-100", order.getSku());
    }

    @Test
    void shouldCreateOrderWithCustomStatus() {
        Order order = OrderBuilder.anOrder()
                .withStatus("COMPLETED")
                .build();

        assertEquals("COMPLETED", order.getStatus());
    }

    @Test
    void shouldMarkOrderAsRefunded() {
        Order order = OrderBuilder.anOrder()
                .withRefunded(true)
                .build();

        assertTrue(order.isRefunded());
    }

    @Test
    void shouldCreateOrderWithQuantityFromFactory() {
        Order order = OrderFactory.anOrderWithQuantity(5);

        assertEquals(5, order.getQuantity());
    }

    @Test
    void shouldBeEqualForSameValues() {
        Order order1 = OrderBuilder.anOrder()
                .withSku("SKU-1")
                .withQuantity(2)
                .build();

        Order order2 = OrderBuilder.anOrder()
                .withSku("SKU-1")
                .withQuantity(2)
                .build();

        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());
    }

    @Test
    void shouldNotBeEqualForDifferentValues() {
        Order order1 = OrderBuilder.anOrder()
                .withQuantity(1)
                .build();

        Order order2 = OrderBuilder.anOrder()
                .withQuantity(2)
                .build();

        assertNotEquals(order1, order2);
    }
}