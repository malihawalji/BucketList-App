# The Bucket List Journal

## *An app to keep track of all your goals!*

>This app is meant to help you keep track of your various bucket list items.
You can add several bucket list items, detail each with why it is important, 
who you would like to do it with and whatever else you would like to document. 
Everyone has passions and goals, whether it is a country you would like to visit, 
a career goal you are aspiring towards or a habit you would like to break. 
As an avid traveller and dreamer, this app definitely is something I would 
use frequently, keeping me inspired. 

##**User Stories**:
____
- As a user, I want to be able to add as many goals as I would like to my bucket list
- As a user, I want to be able to check off a completed goal with the date completed
- As a user, I want to be able to remove items from my bucket list 
- As a user, I want to be able to view my list 
- As a user, I would like to add notes to each item, whether it is why I want to do it, with who, where etc.
- As a user, I would like to get suggestions for what to add to my Bucket List
- As a user, I would like to be able to save the information I have input
- As a user, I would like to load previously saved information

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking the add item button
and entering in the text fields required which will then be displayed
- You can generate the second required event related to adding Xs to a Y by
selecting the button to remove goals (also can select check off button to 
make a goal complete, which the check off panel only shows a list of 
goals not already completed to select from)
- You can locate my visual component when you start the program, immediately there will be an image 
that displays the name of the app on the welcome screen 
- You can save the state of my application by pressing the save list button in main menu or by 
exiting the application and when prompted before closing you can say yes to whether you want the list saved
- You can reload the state of my application by clicking load existing list, but automatically in the beginning 
if the name entered at the start matches the name of an existing bucket list user it will be loaded up
  (test this by entering in "maliha" at the beginning, which will load an existing bucket list)

# Phase 4: Task 2:
- An example of how the event log would be displayed in the console when exiting the program:

Fri Dec 02 16:33:18 PST 2022
goal added to bucket list


Fri Dec 02 16:33:18 PST 2022
goal added to bucket list


Fri Dec 02 16:33:18 PST 2022
goal added to bucket list


Fri Dec 02 16:33:18 PST 2022
goal added to bucket list


Fri Dec 02 16:33:18 PST 2022
goal added to bucket list


Fri Dec 02 16:33:25 PST 2022
goal added to bucket list


Fri Dec 02 16:33:29 PST 2022
goal checked off!


Fri Dec 02 16:33:34 PST 2022
goal removed from bucket list


Fri Dec 02 16:33:40 PST 2022
saved bucket list





- above the log shows that when the program is first loaded off a saved
bucketlist it will add previously added goals back to display what was saved before to the user. 
Then any subsequent additional goals also are shown as 'goal added to bucket list' by event log as well.
Checking off, removing goals and saving the list are also logged. 

# Phase 4: Task 3:
- I would improve my design by refactoring the way the Suggestion class is used in 
the overall structure. I think that either making Suggestion extend BucketList would help
make the functions happen more in the background so that many calls don't need to be made in the 
ui package and can be handled behind the scenes which can also allow the Suggestion class to be more complex.
- The BucketListAppGui is also not super efficient, splitting it up 
into different classes to split up responsibilities would help so that the app's graphic features can become 
more complex without becoming unreadable 