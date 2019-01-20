# Open-Source-Code
This repository is to hold and make available all of my open source code to the public for review and scrutiny. 
Also to make any programmers struggle a little less teary eyed.
Although if any of this work is used, I MUST receive recognition for it.(I'm looking for a job ;D)
If you have any questions, our find an problems with anything please email me right away at: jaabkesse2022@gmail.com


WordSearch Object;
This object has two "given" data fields. The first field is the 2d array that stores the location and state of each 
character. This is copied into the private data field when given to it. The second field is a String array which will
hold the location and state of each word given to the object. Once this objected is created, there is no way to change
it's data fields, meaning this class is immutable.
The object also has two non-accesible fields which store the behavior and movement.

After creation you can have the wordsearch object search for all the words in the word bank.
You may also search for some given string. 

User Class;
This class is a pretty straight forward UI. This class handles file reading and communicating with the user via
System.in.

This class does not handle exceptions fluidly, meaning if you encounter any user input error or potential system error
it(should) will stop running and tell you what the problem is. 
