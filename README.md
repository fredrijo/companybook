companybook
===========

Programming test for Company Book

Main ideas
==========

1. Name extraction and scoring
------------------------------

The names are extracted and scored using name lists with frequencies from
http://www.census.gov/genealogy/www/data/1990surnames/. 

Names are in addition scored for length, capitalization and pattern.

2. DOM tree
-----------
In order to find candidates, we search the DOM tree for "typical" places where name lists recide,
e.g. <div> <span> <td> <tr> <li>. We input the text of these nodes to 1.
