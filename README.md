# MS3-Coding-Exercise

## Purpose
THe purpose of this application is to consume a CSV file and store the data in a SQLite database.

## Steps for Running the Application
1. Download the source code including
2. In a terminal navigate to the folder of the source code
3. Run "mvn clean package"
4. Run "mvn exec:java -Dexec.mainClass="com.CodingExercise.GUI""

## Overview of Approach, Design Choices, and Assumptions
### Approach
I have previously written applications that both parsed CSV files and wrote to small SQLite databases so I knew that I wanted to use the sqlite-jdbc library.  In the past I have used a buffered reader and wrote my own parser, but in the spirit of using open source libraries I found opencsv.  I had no experience in writing to log files so I found log4j and a prewritten class to support it.  From here it was a matter of learning Maven (which, by far took the most of my time) and writing in the rest of the code.  I added a GUI after I had the functionality working for ease of use.

### Design Choices
I chose a simple GUI because the application essentially has one function and I didn't want to overcomplicate it.  There are only 3 substantive classes to the application: a GUI that drives the program; a parsing class that takes the CSV, parses it, writes to the log file, and creates the csv file for any poorly formatted records; and a database access class that allows the database to be created and insert that valid records.

### Assumptions
1. I assumed that this application would be tested with other CSV files that what were provided so I wanted to make sure that my application would handle both more and fewer columns.
2. I wasn't sure as to the future queries or transactions to be done in the database so I stored all values as Strings.  I didn't want to store fields as types other than text since I had no context.  The datatypes can always be reparsed if and when the table is queried.
3. I assumed that all CSV files to be tested on the application will have headers in the first row only.
4. I assumed that missing fields within a record did not constitute a poorly formatted record.

## Thoughts
I have not written much code in Java since graduating in May 2020.  I have primarily been doing my coding in Visual Basic for Excel.  This was a fun exercise that I really enjoyed.  Plus it was a great opportunity to shake of some rust.  On top of that I learned a bit of how to use Maven, which is incredible.  In school we never used and dependancies in our code so to see Maven take and handle all that plus creating the jar file and exectuing it was incredible.  I barely scratching the surface but it was an enjoyable and eye opening experience.
