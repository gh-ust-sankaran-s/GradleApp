Feature: Login
  @smokeS
  Scenario: Successful login with valid credentials
    Given the login page is open
    When I login with "customer@example.com" and "Password@123"
    Then I should be redirected to the home page
