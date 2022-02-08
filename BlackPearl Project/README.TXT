# PF2 Programming Project

This is a starter repository for the programming project in PF2.

Arianna Bianchi and Razvan Petrica Onciu 

The game has two interfaces: the TUI (TextUserInterface) and the GUI (GraphicUserInterface).


TUI
We start calling the static main method of the class Main, that instantiates the class 
TextUserInterface and on this, calls the method run.

The TUI asks you what you would like to do among place, shoot or exit the program. 
The place can be done when we have less than 6 ships, the shoot when we have at least 
one ship placed and the exit whenever we want.

HAPPY PATH 

(place)

If we place a ship we have to choose a ship’s dimension between a small (2 cells), 
medium (3 cells) or large (4 cells) ship. After the dimension we are asked to give 
the orientation of the ship among right, down, left or up. The least step for placing 
is given one coordinate that corresponds to the coordinate of the ship’s starting point. 
Now the ship is placed on the grid.

ERROR: if the dimension differs from the strings small, medium or large (independently 
lower or upper case) the TUI returns that the ship is invalid.
If the orientation differs from the strings up, right, down or left, it returns that the 
coordinate is invalid.
If the coordinates differs from the string “[A-J][0-9]” return that the coordinates are 
invalid, while if the coordinates would place a ship whose end point has a coordinate 
differs from the string string “[A-J][0-9]”, it returns that the ship is out of the grid.
In each error case, the TUI won’t place any ship.


At this point the TUI ask again what you would like to do among place, shoot or exit the 
program but since there is at least one ship placed the shoot is allowed.


(shoot)

To shoot a ship the first thing to do is choose a coordinate that corresponds to the one 
cell on the grid and the TUI says you if there is a ship or you missed the ship. In case 
you hit the cell in which a ship was placed, the TUI keep asks you to choose other coordinates 
in order to sunk the entire ship. If we manage to sink an entire and the only ship, 
we have to enter exit to return at the initial question what would you like to do, 
because we wanted to test the shooting processing. 

ERROR: If the coordinate of the ship that we want to shoot differs from the string “[A-J][0-9]” 
it returns that the coordinates are invalid and it won’t shoot any ship. 


GUI 
We start calling the static mainGui method of the class Main, that instantiates the class 
GraphicalUserInterface and on this, calls the method run which takes two instantiated Player 
as parameter (myself and computer). 

The game starts with a window that shows two grid: on the left our grid in which we placed the 
ships, and on the right the grid of the computer that placed randomly six ships but we can’t see them. 
To place a ship we can choose to press the place button which will ask us to input the dimension the 
orientation and the coordinate of the ship. Until we don’t have six ship placed, we can’t start shooting. 
To palace six ships faster, we can press the button “Place ships randomly” and six random ships will be 
placed randomly on our grid. 

Now it’s time to shoot a cell of the computer grid, to do this we can choose to press the button “Shoot” 
and input the coordinate. Now on the computer grid we will see the cell (at our input coordinate) that
 changes colour: red if we hit a cell of a ship, blue if we found the see and gold if we found the treasure.

(Treasure)
The treasure appears randomly on the computer grid, and if we hit it, we can be lucky and it will shoot 
an entire horizontal line of the computer grid (maybe with some cell of a ship :D) or we can be cursed and 
the shoot of the line will appear on our grid (shooting some of our cell ship D:). 

Now the computer will do its move only if we move our cursor: if we shoot a cell and we don’t move, we can
 see on the right a message that states what we have done in our round. 

If we manage to hit all the cells of a ship, the ship will be coloured of black which means that it’s all sinked. 

The two main action that we do are place and shoot. The aim of the game is to try to sink all the six ships 
on the computer grid by shooting cell per cell.

Gerarchy:

We have the classes SmallShip, MediumShip and LargeShip that inherits from the class Ship. 
These classes instantiates a ship with its fields (dimension). 

The class Player is the superclass for Myself and Computer. This class contains the information 
about who is the winner, and the score. 

Then we have three abstract classes: Draw, Mouse, Button.
With the abstract class Draw that extends JComponent, is useful to create coloured rectangle 
with their border, that is a method in common for the class GridDraw and ShipDraw that relatively 
draw the grid and the ship. 

Then we have the abstract class Mouse because we separate the method that would run for the event 
mouse clicked and the method for the event mouse moved. 

The last abstract class is Button that has the method for enable and disable the button and the method 
that would run when we click on the relatives button. 

We split all the classes in order to avoid pmd and cpd errors.

BUGS:
we have spotted that once in a while if the cursor moves too fast (because for the computer, to shoot
it uses the position of the cursor) it can be that the computer uses more than once the lucky shoot.
This is a minor bug that is due to the implementation of doing the shooting when moving the cursor, but
since is in the GUI and is a really unlucky case bug (happens once in a while under certain conditions), we
let this pass.



