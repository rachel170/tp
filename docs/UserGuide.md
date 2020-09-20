---
layout: page
title: User Guide
---

FlashNotes is a **desktop app for flashcards**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Flashnotes can help you create word-based flashcards faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `flashnotes.jar` from [here](https://github.com/AY2021S1-T15-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FlashNotes.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window. Some example commands you can try:
   * **`list`** : list all card
   
   * **`add`**`q/What is a question? a/Something that elicits thought.`: create new card
   
   * **`delete`**`3` : Deletes the 3rd card shown in the current list.
   
   * **`clear`** : Deletes all contacts.
   
   * **`find`**`question`: Returns all cards with description matching question.
   
   * **`exit`** : Exits the app.


1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add q/QUESTION`, `QUESTION` is a parameter which can be used as `add q/What is the question?`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `q/QUESTION a/ANSWER`, `a/ANSWER q/QUESTION` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a card: `add`

Adds a card to the currently stored notes.

Format: `add n/QUESTION a/ANSWER`

Examples:
* `add q/When did Singapore gain independence? a/9th August 1965`

### Listing all persons : `list`

Shows a list of all flashcards.

Format: `list`

### Opening a flashcard: `open`

Shows the answer to the specific flashcard from the flashcard deck.

Format: `open INDEX`

* Opens the flashcard at the specified `INDEX`.
* The index refers to the index number shown in the displayed list of flashcards.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `list` followed by `open 2` opens the 2nd flashcard in the deck.
* `find Macroeconomics` followed by `open 1` opens the 1st flashcard in the results of the find command.


### Editing a person : `edit`

Edits an existing flashcard in the flashcard deck.

Format: `edit INDEX [q/QUESTION] [a/ANSWER]`

* Edits the flashcard at the specified `INDEX`. The index refers to the index number shown in the displayed desk list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 2 a/Lee Kuan Yew` Edits the answer of the 2nd flashcard to be `Lee Kuan Yew`

### Finding flashcards: `find`

Finds flashcards which contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `test` will match `Test`
* Only full words will be matched e.g. `Art` will not match `Artifact`
* Flashcards matching at least one keyword will be returned. (e.g. `Conflict Resolution` will return `Handling Conflict`, `Resolution`)

Examples:
* `find history` returns `History of WWII` and `History of Cuban war of Independence`
* `find Business` returns `Business Management`
* `find conflict resolution` returns `Handling Conflict` and `Resolution`
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all flashcards from the program.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FlashNotes home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `Format: add q/QUESTION a/ANSWER `
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [q/QUESTION] [a/ANSWER]`<br> e.g.,`edit 2 q/What is the real question? a/This is a real question.`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
