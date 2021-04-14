# COMP3025Assignment

Author: Alex Blom (867295)
Updated: 04/13/2021

The purpose of this app is for the user to create sets of data (so lets say one titled Books) and then add 
books to it (so they end up with a list of books). Then they can hit the Randomize button with the "Books" 
set selected and it will give them a random book to read. The class they are creating is just the set, 
and the strings that the user inputs into that set is what is randomly returned. 

To put items into a Set, select a Set and then click the Edit Set button. On the Edit Set screen, 
input a new SetItem to add to the list of SetItems that can be randomly selected by the Randomizer.

The program has two model classes: Set and SetItem. SetItems belong to the Sets (so Lord of the Rings belongs to Books). Sets and SetItems are displayed as a vertical linear list
using an adapter.
