@android @iOS @Technician @Test
Feature: Technician - Order Items

  Scenario: Gemini - 793 Technician- View order items
    Given Open the Application and click on Get Started button
    And fill the login details and click on login button
    Then Verify that Order listing displayed in the order summery tab
    And Open Order from order listing
    When Navigate to items page
    Then Verify that Order Items are displayed

  Scenario: Gemini - 794 Technician- add order items
    Given Open the Application and click on Get Started button
    And fill the login details and click on login button
    Then Verify that Order listing displayed in the order summery tab
    And Open Order from order listing
    When Navigate to items page
    And Tap on add icon of order items
    And Enter product details and tap on add button
    Then Verify the order is displayed in order item list

  Scenario: Gemini - 795 Technician - Edit Order Items
    Given Open the Application and click on Get Started button
    And fill the login details and click on login button
    Then Verify that Order listing displayed in the order summery tab
    And Open Order from order listing
    When Navigate to items page
    And Open "Tabletops" order item
    When Change the quantity to "100" for that order item
    And Tap on Update Button
    Then Verify "Item Updated Successfully" toast message is displayed and "Tabletops" Order Item quantity updated to "100"

  Scenario: Gemini - 796 Technician - Remove Order Items
    Given Open the Application and click on Get Started button
    And fill the login details and click on login button
    Then Verify that Order listing displayed in the order summery tab
    And Open Order from order listing
    When Navigate to items page
    And Open "Tabletops" order item
    When Tap on Remove Button
    And Tap on Remove Button from Confirmation popUp
    Then Verify that "Tabletops" order item is not present