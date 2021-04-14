# COMP3025Assignment

Author: Alex Blom (867295)
Updated: 04/13/2021

The purpose of this app is for the user to create sets of data (so lets say one titled Books) and then add 
books to it (so they end up with a list of books). Then they can hit the Randomize button with the "Books" 
set selected and it will give them a random book to read. The class they are creating is just the set, 
and the strings that the user inputs into that set is what is randomly returned. 

To put items into a Set, select a Set and then click the Edit Set button. On the Edit Set screen, 
input a new SetItem to add to the list of SetItems that can be randomly selected by the Randomizer.

The program has two model classes: Set and SetItem. SetItems belong to the Sets (so Lord of the Rings belongs to Books). 
Sets and SetItems are displayed as a vertical linear list using an adapter.

Updates from Assignment 2 include:
New navigation icon, navigation buttons, tying data to individual users using a new userID field in the 
Set model. 
Custom launcher icon. Secured Firebase (each user can only access their own data). 

I had meant to create a custom Dialog using a DialogFragment and inflating the color_dialog.xml file,
with each button setting the background color of the recyclerview items for the currently selected set,
however there were multiple bugs that came up from adding the userID field to my Set model. 

Regarding our meeting where the RecyclerViews were not being updated properly, the listener was failing to be attached to the recyclerview due to an index being required in order to
run the query where userID is used. Following the link in the message from the failed listener, I created an
index in the firebase console as follows:

![image](https://user-images.githubusercontent.com/55286258/114647530-41126f80-9cab-11eb-958c-a189887e2382.png)

