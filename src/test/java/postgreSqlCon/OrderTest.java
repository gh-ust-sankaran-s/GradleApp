package postgreSqlCon;

import org.junit.jupiter.api.Disabled;
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

        assertEquals("SKU-101", order.getSku());
    }

    @Test
    void shouldFailForIncorrectQuantity() {
        Order order = OrderBuilder.anOrder()
                .withQuantity(5)
                .build();

        assertEquals(5, order.getQuantity());
    }

    @Test
    void shouldShowBrokenStatus() {
        throw new RuntimeException(
                "Intentional exception for Allure Broken status");
    }

    @Disabled("Intentional skip for Allure report")
    @Test
    void shouldBeSkipped() {
        assertTrue(true);
    }

    @Disabled("Second skipped test for Allure report")
    @Test
    void shouldAlsoBeSkipped() {
        Order order = OrderFactory.anOrder();
        assertNotNull(order);
    }
}