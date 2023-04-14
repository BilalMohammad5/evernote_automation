Feature: Product features

  @smoke @TC_1  @login
  Scenario Outline: ever note invalid login scenario
    Given User clicks on already have account
    When User logs into evernote application using <email> and <password>
    And User clicks login
    And User must see message as <message>
    Examples:
      | email                  | password | message            |
      | bilalroshan5@gmail.com | ######   | Incorrect password |

  @smoke @TC_2  @login
  Scenario Outline: ever note valid user login scenario
    Given User clicks on already have account
    When User logs into evernote application using <email> and <password>
    And User clicks login
    And User must see message as <message>
    Then User clicks logout

    Examples:
      | email                  | password  | message                |
      | bilalroshan5@gmail.com | User@1234 | bilalroshan5@gmail.com |

  @note_operation @smoke @TC3
  Scenario Outline: write a note
    Given User clicks on already have account
    When User logs into evernote application using <email> and <password>
    And User clicks login
    Then User must see message as <message>
    When User clicks new note button
    And User enters data into title as <title>
    Then User clicks logout
    Examples:
      | email                  | password  | message                | title       |
      | bilalroshan5@gmail.com | User@1234 | bilalroshan5@gmail.com | video_slots |

  @existing_note @smoke @note_operation
  Scenario Outline: open  a existing note
    Given User clicks on already have account
    When User logs into evernote application using <email> and <password>
    And User clicks login
    And User must see message as <message>
    Then User searches for existing_note
    Examples:
      | email                  | password  | message                |
      | bilalroshan5@gmail.com | User@1234 | bilalroshan5@gmail.com |
