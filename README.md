companybook
===========

Programming test for Company Book

Introduction
====
The main ideas in this approach are:

1. Select the elements from the DOM with the highest density of names as a starting point
2. Extract and score names form the selected elements using name/frequency lists 

Main concepts
==========

1 Name Extraction
------------------------------

The names are extracted using name lists with frequencies from:

<a href="http://www.census.gov/genealogy/www/data/1990surnames/names_files.html">http://www.census.gov/genealogy/www/data/1990surnames/names_files.html</a>

If a name is present in one of the lists, regardless of capitalization, it will be extracted as a candidate name.

See src/main/java/com/thecompanybook/contacts/extract/NameExtractor.java for details

NOTES: Several name patterns are not yet included, such as:

* LASTNAME, FIRSTNAME
* FIRSTNAME LASTNAME {Jr.|Sr.|etc.}

Also, there are quite a few false positives related to boundary errors.

2 Selecting DOM elements
-----------
In order to find candidates, we search the DOM tree for "typical" places where name lists recide,
e.g. \<div\> \<span\> \<td\> \<tr\> \<li\>.

We then score each of the HTML tags for the density of names, and select the tag with the highest density as the one we contnue process

NOTE 1: This scoring is not yet optimal. It tends to select "too general" tags, i.e. too high in the DOM tree. Selecting elements lower in the tree will have two positive benefits:

1. Less text to process
2. Higher chance of the full content of the node being a name

NOTE 2: The HTML tags selected are often present other places in the document. I want to create a more specific DOM "signature", e.g. by adding class (or other attribute) information, parent, etc., in order to select e.g. TDs of just one table, ignoring the other TDs on the page.

See src/main/java/com/thecompanybook/contacts/dom/DomTagSelector.java for details


3 Name Scoring
-----------
 Every name is assigned a confidence scores, by the following factors:
 
 1. Frequency of the individual parts of the name
 2. Capitalization of the name
 3. Length of the name, ideal length is by trial and error set to 2.5 tokens

NOTE 1: I want to add name patterns to this class, so e.g. "LASTNAME FIRSTNAME" or "LASTNAME LASTNAME" are punished

NOTE 2: The scoring algorithm is not optimal

4 Documentation and unit tests
----
There is minimal documentation and unit tests. Every major class has a decription, and some of the functionality is tested.

5 How to explore
-----
Check src/main/java/com/thecompanybook/contacts/Main.java on how to run the program. 

Use src/main/java/com/thecompanybook/contacts/ContactFinder.java as an entry point when creating applications.