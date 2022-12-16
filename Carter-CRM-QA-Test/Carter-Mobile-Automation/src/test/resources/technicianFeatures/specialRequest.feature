@Technician
Feature: Technician - Special Request

  Scenario: Gemini - 797  Technician - Add order special request items
    Given Open the Application and click on Get Started button
    And fill the login details and click on login button
    Then Verify that Order listing displayed in the order summery tab
    And Open Order from order listing
    When Navigate to items page
    And Tap on add button of special request
    And Enter special request details and tap on add button
    Then Verify special request is displayed in special request list

  Scenario: Gemini - 798 Technician - View order special request items
    Given Open the Application and click on Get Started button
    And fill the login details and click on login button
    Then Verify that Order listing displayed in the order summery tab
    And Open Order from order listing
    When Navigate to items page
    Then Verify that special request Items are displayed

  Scenario: Gemini - 795 Technician - Edit Special request Items
    Given Open the Application and click on Get Started button
    And fill the login details and click on login button
    Then Verify that Order listing displayed in the order summery tab
    And Open Order from order listing
    When Navigate to items page
    And Open special request item
    When Change the Description to "Test Special Request" for that special request
    And Tap on Update Button
    Then Verify "Special Req Updated Successfully" toast message is displayed and special request updated to "Test Special Request"

  Scenario: Gemini - 796 Technician - Remove special request
    Given Open the Application and click on Get Started button
    And fill the login details and click on login button
    Then Verify that Order listing displayed in the order summery tab
    And Open Order from order listing
    When Navigate to items page
    And Open special request item
    When Tap on Remove Button
    Then Verify "Special Req delete Successfully" toast message is displayed and special request is not present


