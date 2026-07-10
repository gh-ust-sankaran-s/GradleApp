package postgreSqlCon;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    // PASSED
    @Test
    void shouldCreateOrder() {
        Order order = OrderBuilder.anOrder()
                .withQuantity(3)
                .build();

        assertEquals(4, order.getQuantity());
    }

    // PASSED
    @Test
    void shouldCreateDefaultOrder() {
        Order order = OrderFactory.anOrder();

        assertEquals(1, order.getQuantity());
        assertEquals("NEW", order.getStatus());
    }

    // PASSED
    @Test
    void shouldSetSkuUsingBuilder() {
        Order order = OrderBuilder.anOrder()
                .withSku("SKU-100")
                .build();

        assertEquals("SKU-100", order.getSku());
    }

    // FAILED
    @Test
    void shouldFailForIncorrectQuantity() {
        Order order = OrderBuilder.anOrder()
                .withQuantity(5)
                .build();

        assertEquals(10, order.getQuantity(),
                "Intentional failure for Allure Failed status");
    }

    // BROKEN
    @Test
    void shouldShowBrokenStatus() {
        throw new RuntimeException(
                "Intentional exception for Allure Broken status");
    }

    // SKIPPED
    @Disabled("Intentional skip for Allure report")
    @Test
    void shouldBeSkipped() {
        assertTrue(true);
    }
}