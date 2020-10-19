---
layout: page
title: Rachel's Project Portfolio Page
---

## Project: FlashNotes

FlashNotes is a desktop address book application used for creating and accessing flashcards for studying. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * What it does:
  * Justification:
  * Highlights:
  * Credits:

* **New Feature**:

* **Code contributed**: [RepoSense link]()

* **Project management**:


* **Enhancements to existing features**:

* **Documentation**:
  * User Guide:

  * Developer Guide:

* **Community**:

* **Tools**:

* _{you can add/remove categories in the list above}

### Review Statistics feature

#### Implementation

FlashNotes application supports testing of the user's knowledge of the flashcards through a review session. To provide more value to the review session, FlashNotes should be able to track the number of cards answered correctly by the user on their first attempt at the question during the review session. This statistical value will be displayed to the user at the end of the review session and saved only when the user ends the review session properly.

To support this feature, a new command have been added to FlashNotes:
* `endReview` - A command that closes the review session's window at the end of the review session, and handles the ending process of review session. (Only available in review mode.)

To support the storage of each deck's review statistic, a new class has been added to the Storage component in FlashNotes:
* `JsonAdaptedDeck` object contain two variable for Deck Storage, `String deckName` to identify the deck, and `String resultStatistics` to contain the deck's review statistics.
* `JsonAdaptedDeck#updateModel(FlashNotes flashNotes)` is a method used to update the generated model from reading the flashcard data with the deck's data. It depends on `FlashNotes#updateDeckPerformanceScore(Integer reviewScore, String deckName)` to update the generated model with the deck data from the save file.

`JsonSerializableFlashNotes` object has been adjusted to depend on a list of `JsonAdaptedDeck` objects to read and write each deck's data to the FlashNotes save file.

Additionally, the following operations have been implemented to support this feature:
* Model component:
    * `FlashNotes#updateDeckPerformanceScore(Integer reviewScore, String deckName)` - Updates the reviewStatistics attribute of a specific deck (through deckName) with the given Integer value (reviewScore).
    * `FlashNotes#getUniqueDeckList()` - Return the FlashNotes' model's `UnqiueDeckList`.
    * `UniqueDeckList#findDeck(String deck)` - Returns an existing `Deck` object from its `internalList` with the same `deckName` as the given String input. If no such `Deck` object exist, a `null` object is returned instead.
* UI component:
    * `IndividualFlashcard#displayStatistics()` - Calculates the user's review session's score, updates the model with the generated statistics and set display of the end of the review session.

`Model` interface depends on  `Flashnotes#updateDeckPerformanceScore(Integer reviewScore, String deckName)` to support functionality of `Model#updateDeckPerformanceScore(Integer reviewScore, String deckName)` that `FlashNotes` implements. Similarly, `Logic` interface depends on  `Model#updateDeckPerformanceScore(Integer reviewScore, String deckName)` to support functionality of `LogicManager#updateDeckPerformanceScore(Integer reviewScore)` that implements it.

Further more, the following operations have been adjusted to support the feature:
* `Storage#saveFlashNotes(ReaOnlyFlashNotes flashNotes)` - This operation, and all methods dependent on it, has been expanded to accept an additional parameter `UniqueDeckList decklist` to facilitate the saving of the deck data in FlashNotes.
* `FlashNotes#resetData(ReadOnlyFlashNotes newData)` - This operation has been adjusted to include the recreation of the FlashNotes model's deck data read from Storage.

To provide the UI display and changes related to review statistics, the following UI component have received several code additions:
* `DeckCard` - Changed constructor method to for display of review statistics at the end of a review session.
* `ReviewWindow#handleExit()` - Adjusted to return to card view upon execution of `endReview` command.

##### Given below is a basic description of the backend process of the feature:

1. User reaches the end of the review session (by correctly answering the last of the questions that has not been answered yet or has been answered wrongly before). 

2. The UI component will calculate the user's score by generating the percentage of cards the user answered correctly on the first try during the session.

3. The generated review session statistic is passed to the Model component through the Logic component, where the `FlashNotes` model updates the relevant deck with the generated value.

4. FlashNotes' UI component will display the review statistics generated as part of the end of review session message.

5. User enters `endReview` command to end the review session.

6. The processing of the `endReview` command through the Parser component will lead to the command execution in Logic component and trigger the save function of FlashNotes, thus updating FlashNote's jaon file with the new review session statistic for the deck.

##### Corresponding sequence diagram for `endReview`:

The following sequence diagram shows how the endReview command operation works:

![EndReviewSequenceDiagram](images/EndReviewSequenceDiagram.png)


#### Design consideration:

* **Current choice:** Expand save feature to include decks' data instead of only saving flashcards' data.
  * Pros: Partial implementation available to build on. Implementation can provide base code for future addition to Deck data that needs to be saved as well.
  * Cons: Design and implementation for Deck and UniqueDeckList is not a concrete feature yet, changes done now may clash with future expansion of the feature.

* **Alternative 2:** Store review statistics as an attribute of Tag
  * Pros: Easier to implement, simply expand tag feature to include review statistics data of the deck that the tag is representing.
  * Cons: Will result in storing several repetitions of the statistics since it is an add-on to each instance of a unique tag in the json file. This can needlessly take up more space if there are a huge amount of flashcards and only a few decks.


