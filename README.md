# Memory Atlas
A project developed as a part of Google Applied CS with Android workshop.
This project is an application of the classic Atlas game with an extension that a player must repeat the entire sequence of recited places before adding the new place.

### Atlas

Atlas is a collection of maps. However it has been used frequently to play a multiplayer game.
Each player names a country, state or continent that starts with the alphabet which was the previous player's answer's last alphabet. The game goes on till the players who cannot name a place are eliminated.

### Memory game

Memory games are played to keep in mind the player's ability to remember maximum number of names/ countries or any other set of values. The player keeps repeating the entire sequence and adds a new word to the end of the sequence after successfully repeating the sequence. The game goes on till all those players who cannot remember the correct sequence are eliminated.

### How is Memory Atlas played?

Memory Atlas follows the following rules

  * Each player enters a country, place, continent which complies with the Atlas rules. They will be tested against a dictionary. The first letter of the word should be the last letter of the previous word

  * The next player repeats( enters into text field )the sequence of words upto the last word and adds a new word based on the rules.

  * After each successful turn the player's score is calculated based on the letters used by the player.The score is updated

  * A player can declare at any point of time.On declaring the players final score is calculated and he/she is locked out of the game. The other player can then keep adding sequence to maximize his score till it exceeds the opponent's score.

  * If a player fails to recall the sequence then he/she automatically gets locked out of the game till the other player declares.

### Data Structures used

We have used the following data structures as per the requirements of the game

      1. TreeSet
        * As our dictionary
        * To eliminate redundancy in sequence of words

    2. ArrayList
       * This stores the entire sequence of words in one game.

### Computing Score

To make things interesting, each alphabet has an associated score with it based on how rarely it is used in words. The final score upon declaring is the user's score. The player with the highest score wins!

### Instructions

There is a separate instructions option available for the user to view the instructions at any point of time within the game!
