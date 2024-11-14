
on invariant is that the card always has 4 attack values

COORDINATE SYSTEM FOR GRAPHICS:
The Graphics use a coordinate system where I took the grid columns + 2 (for each hand of the player)
and I made it so that I divided the height of the frame by this value to find the length of cards
I stored this value and divided by mouse coordinates and casted to an integer to find the specific
coordinates of the length. I did the same thing with the height by seeing how many rows there were and
dividing to find the card length and divided by mouse coordinates and casted to get the specific column it existed in
if it was in the hand column, i divided by the number of cards in the hand instead to get the appropriate coordinates for
that section.
COORDINATES ARE CENTERED AT THE TOP LEFT CORNER OF THE SCREEN WITH X axis pointing to the left positive direction
and the Y axis pointing to the down positive direction.



The purpose of this codebase is to create a two-player game version of Triple Triad.
The main components of our system so far is the grid, deck, player, model, and view. The grid is
represented as a list of a list of CardCells. The deck is a list of cards that will be dealt to the
two players in the game. The player interface and class is used to represent any players in the game
triple triad. The model connects all these components to create the actual game of TripleTriad.
It allows the players to access and modify the grid using their hand of cards, which will be made from
the deck. The view then gives the user a glimpse into the game. The view takes the model and creates a
string that shows the user what player's turn it is and their hand and the grid.
The main subcomponents are the ones of the grid. The grid is represented as a list of a list of CardCells.
A CardCell can be either a card, hole, or placeholder. A card represents an actual card that is in the deck.
A Card has a String name, a color, and a map of AttackValues and AttackDirections. AttackValues is represented
as an enumeration, to ensure that the values given are valid. The AttackDirections represent the attack directions
of the card and are represented as an enumeration (North, South, East, West). A card also represents a cell in the
grid that has been filled. The hole class represents a hole in the grid that cannot contain a card. The PlaceHolder
represents an empty cell that can contain a card.
In order to start the code you can create a grid, that contains a certain number of cells, which can
be either card cells or holes. The number of card cells must be odd in order to be a valid grid. You can
also use one of the grids that are provided in the doc folder. You must also have a deck of cards that
is greater than or equal to the number of card cells plus one. There also must be two players, one blue
and one red. The red player's turn is first.
 Using the desired grid and deck you can create a model and start the game. For Example:

 TTModel model = new TripleTriadModel();
 model.startGame(deck, grid);

 in order to play the game the red player must go first and play a card to the grid.
 Must be valid indices for the players hand, row in grid, and column in grid.
 For Example:

 model.playToGrid(redPlayer, 1, 0, 1);

 Because our code uses 0-based index this call to playToGrid will play the redPlayer's
 second card in hand to the first row and second column of the grid. It will then become
 the blue Player's turn.

 If the user wanted to see the grid or see the current state of the game they can create a new view.
 For Example:

 TTView view = new TTStringView(model);
 view.toString();

In regards to the organization of our code, you can find all relevant code to the program in the src.
Within the src, we have several packages, each corresponding to a key part in our program. Our main
package is titled cs3500.tripleTriad, and within this main package we have several small packages,
including ConfigReaders, gameComponents, model, player, and view. In the package ConfigReaders, we have
three classes. Each of these classes are designed to receive input from a file and translate it into
a grid or deck of cards. The package gameComponents contains several classes relating to the parts of
the game like the grid, different representations of the contents of the grid (holes, filled card cells,
and empty card cells). It also contains the enumerations like direction and attack values that are
fields of the card. The model package contains the interface and implementation of the model. This is
where you can find the actual functionality of the game, as it connects the gameComponents together and
creates the actual model for the game. The package player contains information regarding the players of
the game. Here you can find the interface for player and the class that implements it. In the view package
you can find the code for displaying the view of the game. This package allows the player to see inside the
model. There is an interface for the view and a class that implements it inside this package. We also have
a main class titled ThreeTrios, which allows us to run the game. You can find the tests for our program inside
the test folder. Each directory in the src has a corresponding package where you can find the relevant tests.

In the strategies package there is a Strategy interface, a strategy abstract class, a Strategy One Class
and a Strategy Two class. The abstract class contains the constructor and both the strategy one and
strategy two classes. This allows us to have more control over where these methods are called. It now throws
an exception if you try and call the strategy one method on the strategy two class or the strategy two
method on the strategy two class. Each strategy can be used to determine the best card and the best position
to placed the card on the grid. Strategy one takes into account the best card and position
that flips the most cards of the other players. Strategy two takes into account the corners
because only two of the attack values are exposed. It also determines the card with the highest two
attack values that are exposed.

INVARIANT EXAMPLES:
- the card always has four attack values paired to each direction
- the grid cells must be one of hole, card, or placeholder


CHANGES:
- We added an interface for both the deck and the grid, so we did not use the concrete classes.
- We moved the config readers to the controller package.
- We added more methods to our model that would be helpful when implementing the strategies and view.

