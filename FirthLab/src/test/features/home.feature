Feature: Home page
  Scenario: Profile transition
    Given Open home page
    When Attempt to go to profile
    Then Successfully profile transition
  Scenario:
    Given Open home page
    When Attempt to leave home page
    Then Check success
