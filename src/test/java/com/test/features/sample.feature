#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @Scenario1
  Scenario: Testing github login..
    Given user launching "chrome" broswer and open "https://github.com/" app
    Then user click on "loginlink" button on "login" screen
    When user type "mayankshri84@gmail.com" on "username" in "login" screen
    Then user type "Roli_2018" on "password" in "login" screen
    And user click on "signin" button on "login" screen
    And user close browser

  #And user select "testText" on "element" in "home" screen
  #Then user hover on "element1" and click on "element2" on "home" screen
  @Scenario2
  Scenario: Title of your scenario
    Given user launching "chrome" broswer and open "https://github.com/" app
    Then user click on "loginlink" button on "login" screen
    When user type "mayankshri84@gmail.com" on "username" in "login" screen
    Then user type "Roli_2018" on "password" in "login" screen
    And user click on "signin" button on "login" screen
    And user close browser
