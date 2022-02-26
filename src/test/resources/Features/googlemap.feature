Feature: Google map
  @Smoke
  Scenario: User search city on google map
    Given User is on google map
    When  User search city 'Dublin'
    Then  User should see the results




