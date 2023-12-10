# README
this is the submission for the home assignment for Ascenda 

## How to run

Open command promt/terminal at the directory and type
```
 java -jar ascenda.jar
```

Or you could run from the main function in the main class

```
The directory for the source code is:
src/main/java
```

When open, the program then tell you to enter the date, if it in wrong format then it will keep telling you until the correct format typed

If the input file is not exist nor in JSON, the program will throw an exception then exit

## The Logic
After reading the json string, each of the offer will be parsed into an offer object (and only parse the nearest merchant, the invalid date and category will be filtered out) and then that object will be put into a map that has the pair key and value is Category-Offer, Each category in the map only holds the offer that has the closed merchant. After that we only take the 2 offer with closest merchant and parse it to JSON then write to ouput
