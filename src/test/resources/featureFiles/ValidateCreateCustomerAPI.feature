@CreateCustomerAPI
Feature: Validate Create Customer API

Scenario Outline: Validating Create Customer API with Valid details
Given I set the authentication using the secret key
And I set "<customerEmail>" as email of the customer
And I set "<description>" as the description of the customer
When I send a Post request to the url 
Then I should get <expectedStatusCode> as the response status code
And I should get id field in the response
And the email in the response should be "<expectedEmail>"
And  the description in the response should be "<expectedDescription>"

Examples:
|customerEmail|description|expectedStatusCode|expectedEmail|expectedDescription|
|TesterAPI@gmail.com|This is a Test Customer|200|TesterAPI@gmail.com|This is a Test Customer|

Scenario Outline: Validating Create Customer API with InValid auth token
Given I set the authentication using the invalid secret key
And I set "<customerEmail>" as email of the customer
And I set "<description>" as the description of the customer
When I send a Post request to the url 
Then I should get <expectedStatusCode> as the response status code
And I should get id field in the response
And the email in the response should be "<expectedEmail>"
And  the description in the response should be "<expectedDescription>"

Examples:
|customerEmail|description|expectedStatusCode|expectedEmail|expectedDescription|
|TesterAPI@gmail.com|This is a Test Customer|200|TesterAPI@gmail.com|This is a Test Customer|