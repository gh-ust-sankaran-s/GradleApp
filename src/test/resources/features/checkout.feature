Feature: Catalog checkout
  As a shopper
  I wnat to find products, add them to my cart, and complete an order
  So that the retail purchase journey is covered in shared language

  Background:
    Given the catalog is open

    @smoke
    Scenario: Buy a single product end to end
      When I searched for "headphones"
      And I add the first result to the cart
      Then the cart badge shows 1
      When I open the cart
      Then the cart has 1 line item
      When I placed the order
      Then the order is confirmed

      @Regression
      Scenario: Buy "<Product>" end to end
        When I searched for "<Product>"
        And I add the first result to the cart
        Then the cart badge shows <count>
        When I open the cart
        Then the cart has <count> line item
        When I placed the order
        Then the order is confirmed

        Examples:
          | product    | count |
          | headphones | 1     |
          | shoes      | 1     |
          | lamp       | 1     |

          @Exercise
          Scenario: A Fresh cart is empty
            When I open the cart
            Then the cart has 0 line items
