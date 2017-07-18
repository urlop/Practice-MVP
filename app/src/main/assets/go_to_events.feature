Feature: Go to Events

    Scenario: Available Events
        Given I see characters' view
         When I go to the navigation drawer
          And I press the events option
         Then I see events' view

