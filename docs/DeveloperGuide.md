---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------
## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

</div>

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `addDeck Singapore`.

<img src="images/ArchitectureSequenceDiagramUpdated.png" width="574" />

The sections below give more details of each component.

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* NUS FASS student who likes to write down lecture notes
* practices active learning techniques via flash cards
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage, read and create flashcards faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new deck                 |                                                                        |
| `* * *`  | user                                       | open a deck                    | see what flashcards I have created in that deck so far                 |
| `* * *`  | user                                       | delete a deck                  | get rid of decks of flashcards that I no longer need quickly           |
| `* * `   | user                                       | rename a deck                  | organize my decks better                                               |
| `* * *`  | user                                       | add a new card                 |                                                                        |
| `* * *`  | user                                       | delete a card                  | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a card by keyword         | locate certain cards without having to go through the entire list      |
| `* * *`  | user                                       | review a deck                  | test my knowledge about the content of the cards in that deck          |
| `* * *`  | user                                       | mark a flashcard as right or wrong | keep track of which cards I have already mastered and which cards I still need to review again |
| `* * *`  | user                                       | see how many cards I got correct after a review session | track my topics mastery and feel a sense of accomplishment for studying efficiently |
| `* *`    | user                                       | hide old cards                 | clear clutter when there are too many cards in the deck                |
| `*`      | user with many related cards in the app    | nest the card decks by tags    | locate a cards of the same group easily when reviewing                 |

### Use cases

(For all use cases below, the **System** is the `FlashNotes` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a card**

**MSS**

1.  User requests to list cards
2.  Flashnotes shows a list of cards
3.  User requests to delete a specific card in the list
4.  Flashnotes deletes the card

    Use case ends.

**Extensions**

* 2a. The list is empty.
  Use case ends.

* 3a. The given index is invalid.
    * 3a1. Flashnotes shows an error message.
      Use case resumes at step 2.

**Use case: Add a card**

**MSS**

1.  User add card
2.  Flashnotes adds the card
    Use case ends.

**Extensions**

* 2a. There is a duplicate question.
    * 2a1. Flashnotes shows an error message.
      Use case resumes at step 2.

**Use case: Edit a card**

**MSS**

1.  User requests to list cards
2.  Flashnotes shows a list of cards
3.  User requests to edit a specific card in the list
4.  Flashnotes edits the card

    Use case ends.

**Extensions**

* 2a. The list is empty.
  Use case ends.

* 3a. The given index is invalid.
    * 3a1. Flashnotes shows an error message.
      Use case resumes at step 2.


**Use case: Filtering cards by a certain tag**

**MSS**
1. User filters all cards using a certain tag.
2. Flashnotes shows a list of cards that contain the tag.

Use case ends.

**Extensions**

* 1a. User enters a tag that is not found
    * 1a1. Flashnotes returns no cards.
    Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 cards without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Searching for a flashcard should not take more than 2 seconds.
5.  The user can directly edit the data file to add or edit flashcards.
6.  The user can import or export the flashcards by adding/copying a new json file with the same name.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Flashnotes**: The software that stores flashcards.
* **Flashcard**: A card with a question and answer, and may contain a tag.
* **Deck**: A collection of flashcards.
* **Home screen**: The home screen displays a list of decks of flashcards.
* **Card screen**: The card screen displays a list of flashcards in a specific deck.
* **Review mode**: The mode in which users can navigate through flashcards to review, and test their knowledge on the content of those cards.
* **Tag**: A note to group cards of a certain category together.
* **Review card limit**: The maximum number of cards that can be reviewed in a single review session.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample flashcards. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }


### Deleting a card

1. Deleting a card while all persons are being shown

   1. Prerequisites: List all cards using the `list` command. Multiple cards in the list.

   1. Test case: `delete 1`<br>
      Expected: First card is deleted from the list. Details of the deleted card shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No card is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }


### Finding cards by tag
1. Finding cards via a specific tag

    1. Prerequisites: Flashnotes have cards containing tag 'Singapore' and 'Malaysia'
    1. Test case: `listTags Singapore` <br>
       Expected: All cards with the tag 'Singapore' will be shown. Cards with tags 'Malaysia' will not be shown.    
    1. Test case: `listTags singapore` <br>
       Expected: None of the cards are shown (as the keyword is case-sensitive)       
    1. Test case: `listTags Singapore Malaysia` <br>
       Expected: All cards with the tag 'Singapore' and cards with the tag 'Malaysia' will be shown.

### Reviewing a deck of cards
1. Opening the review window

    1. Prerequisites: User is in card screen
    
    1. Test case: `review` <br>
       Expected: A new window should pop up containing a command box, result display, and the question on the first flashcard.
    
    1. Test case: `review 7` <br>
       Expected: A new window should pop up containing a command box, result display, and the question on the first flashcard.
        
    1. Test case: `review hello` <br>
       Expected: A new window should pop up containing a command box, result display, and the question on the first flashcard.
            
1. Flipping a card

1. _{ more test cases …​ }

### Setting the review card limit
1. Setting the maximum number of cards that can be reviewed in a single review session.

    1. Prerequisites: User is in Home screen or Card screen.
    
    1. Test case: `set-review-limit 20` <br>
       Expected: The message "Review card limit successfully updated!" should appear in the result display box.
       
    1. Test case: `set-review-limit 0` <br>
       Expected: The message "Review card limit must be an integer greater than 0." should appear in the result display box.
        
    1. Test case: `set-review-limit all` <br>
       Expected: The message "Review card limit successfully updated!" should appear in the result display box.
       
    1. Test case: `set-review-limit 20` from the review window <br>
       Expected: The message "This command is not available in review mode. Please exit the review mode by typing 'endReview' and try again." should appear in the result display box.
