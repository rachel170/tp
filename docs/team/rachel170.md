---
layout: page
title: Rachel's Project Portfolio Page
---

## Project: FlashNotes

FlashNotes is a desktop app for flashcards, built for students who wish to utilise flashcards for studying. FlashNotes is also optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Review Statistics Feature
  * What it does: Track and display statistics for user's review session. 
  * Justification: Allows the user see how many flashcards they got correct during a review sessions that they can track their topics' mastery.
  * Highlights:
    * Calculated and displayed statistics for Review session once user have answered all questions correctly.
    * Update FlashNotes' model with review statistics data
  * Credits: Review Session code base written by Sruthi and Siqi. 

* **New Feature**: Expanding Storage Feature to save `Deck` data
  * What it does: Allow FlashNotes to save data from `Deck` class and load `Deck` data from FlashNotes save file.
  * Justification: To allow user to track their topic mastery regardless of their instance of FlashNotes.
  * Highlights: Expanded Storage component to save `Deck` class data to the same save file as FlashcNotes' flashcard data.
  * Credits: `Deck` and `UniqueDeckList` classes implemented by Jacob and Peng Xiang.
  
* **New Feature**: `endReview` Command
  * What it does: Allow user to return to the main window showing the Card Mode they started their review session from.
  * Justification: Allows user to continue using FlashNotes if they wish to leave the review session for any reason. 
  * Highlights: Enabled return to Card View once Review Session ends (be it through `endReview` command or by closing of Review window).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=rachel170)

* **Project management**:
  * Attended and provided helpful input during team meetings, as well as directed several discussions.

* **Enhancements to existing features**:
  * Refactored `Person` class to `Flashcard` class.
  * Standardized one word command to not accept further arguments.
  * Enabled saving of FlashNotes' data upon manual closing of the application.

* **Documentation**:
  * User Guide:
    * Provided `exit`, `endReview` command usage description and examples.
    * Provided informative section about FlashNote's "Saving the data" and "About Review Statistics" features.
    * Updated Quick Start section for FlashNotes.

  * Developer Guide:
    * Added details about the `Storage` component with a class diagram for the section.
    * Provided implementation details for the 'Review Statistics Feature' under 'Implementation of Review Mode Features'.
    * Added Efforts section.

* **Community**:
  * Reviewed and approved PR for teammates. (example: [1](https://github.com/AY2021S1-CS2103T-T15-2/tp/pull/249))
