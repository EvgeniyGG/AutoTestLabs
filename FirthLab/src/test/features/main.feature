Feature: Main page
  Scenario: Successfully authorization test
    Given Open main page
    When Successfully authorization
    Then Authorization has been successfully
  Scenario: Wrong authorization test
    Given Open main page
    When Wrong authorization
    Then Authorization has been wrong
