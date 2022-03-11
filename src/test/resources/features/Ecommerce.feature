@ecommerce
Feature:  E-commerce Project Website Proper working

 Background: Navigation to the URL
 Given user on home page

@TestCase1
Scenario: Application URL redirection
When  user click User navigated to the home application url
Then user should be on the "redirected_to" page

@TestCase2
Scenario: Application logo visibility
When home page opened logo should display
Then logo should be in height and width

@TestCase3
Scenario: Application product main category list validation
When user fetch product main category
Then count should be 3


@TestCase4
Scenario: Application Search functionality
When user enter the text "T-shirt" 
Then it should contains T-shirt as a text


@TestCase5
Scenario: Application social media handles validation
When user click on twitter logo
Then new tab will open
And verify the account name and opened page url















