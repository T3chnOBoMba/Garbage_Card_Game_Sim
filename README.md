# Garbage_Card_Game_Sim

This is a program that runs a simulation of a game of Garbage (some apparently call it Trash). 
Each player starts off with 10 face-down cards like this:
[*] [*] [*] [*] [*]
[*] [*] [*] [*] [*]

The goal is to get each place to have the corresponding number (3 in the third card spot, 4 in the fourth card spot, etc.):
[A] [2] [3] [4] [5]
[6] [7] [8] [9] [10]

The rules can be found at https://www.pagat.com/patience/trash.html
Few changes in the rules for this program:
* Kings are the wildcards, not the Jacks
* Once a player wins a round, the game starts over but the winner plays with one less card and therefore needs less to win the next round.
For ex, if a player won the first round, his board would look like this to start the second:
[*] [*] [*] [*] [*]
[*] [*] [*] [*]

* Goes until a player wins while starting with only two cards and is left with one:
[A][2] ---> the win state

Code for Player actions/stats are in the Player class, and the game is run in the Garbage class.
There is a main function in Garbage that shows an example of how the program is run. I hardcoded the setup of the Player objects in the
Garbage constructor since it was a simple program for fun. If I were to make it into a full program, I was initialize the list of Player()
objects in Gargabe to be empty and let the user customize the number of player, player names, etc.
