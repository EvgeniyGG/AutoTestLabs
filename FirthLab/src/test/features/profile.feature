Feature: Profile page
  Scenario: Changing profile name
    Given Open profile page
    When Change profile name first time
    Then Change profile name again
      Then Check profile name
  Scenario: Changing profile hometown
    Given Open profile page
    When Changing profile hometown first time
    Then Changing profile hometown again
      Then Check profile hometown