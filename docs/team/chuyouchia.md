---
layout: page
title: Jacob's Project Portfolio Page
---

## Project: FlashNotes

FlashNotes is a desktop address book application used for creating and accessing flashcards for studying. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 16 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: Implemented a new type of object named Decks.
  * What it does: Enables the application to store a different type of object
  * Justification: Implementing Decks is vital to ensuring that flashcards can be organised according to themes and topics for revision purposes. It also enables the tracking of revision statistics on the deck level, which is crucial in any flashcard application.
  * Highlights: This enhancement affects existing and future commands as many actions revolves around the object. It required an in-depth analysis of design alternatives to weigh the tradeoff between the value and the speed of development. Implementation in particular was challenging because creating a new object affects every single package in the original architecture. Caution needed to be exercised in developing the 
  * Credits: Jackson Json was used in the process of storing all objects in FlashNotes
  
* **New Feature 2**: Added the ability to Add New Decks.
  
* **New Feature 3**: Created a new UI for Home Screen to render Decks
    * What it does: Enable the user to visually separate a list of decks from list of cards
    * Justification: Having 2 visually separate screen to view flashcards in gives a sense of the hierarchical relationship between Decks and Flashcards. This helps to separate the 2 different types of objects rendered and the logic of operating in those differing modes.
    * Highlights: Deciding to split the root nodes out from the Main screen was important in allowing scene swaps to happen. Additionally, it enabled the further extension of including review window into the card mode screen, while excluding it from the main mode. The implementation of decks was also important in this process.
    * Credits: Oracle's JavaFx tutorial was helpful in explaining the hierarchy of nodes in the object rendering process. Due to the observer pattern used in the original implementation of AddressBook 3, it was easier to design a similar flow for Deck lists which was rendered in the main mode.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=chuyou&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=chuyouchia&tabRepo=AY2021S1-CS2103T-T15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code)

* **Project management**:
    * Managed the release from v1.2 to v1.4 (4 releases) as the Project Lead

* **Enhancements to existing features**:
    * Modified the GUI color scheme to provide visual separation between Decks and Flashcards
    * Wrote additional tests for new features to increase coverage from 0% to more than 95%

* **Documentation**:
  * User Guide:
    * Wrote documentation for the features`enterDeck`, `addDeck` and `Home`
    * Standardize documentation format for features in User Guide
  * Developer Guide:
    * Wrote documentation for implementation of deck
    * Add implementation details of `addDeck` command
    * Add `FlashNotesParser` and `Command` activity diagrams
    * Add design considerations and explanation for the 3 different UIs available in FlashNotes v1.4

* **Community**:
    * Contributed to forum discussions (example: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/103))
    * Review teammate commits (example: [1](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/139))
