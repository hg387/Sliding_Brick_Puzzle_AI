Himanshu Gupta
CS 510 HW1: README

It is basically a sliding brick puzzle solver , more info about the puzzle is given in:
HW1_2018.pdf

Contents of the Zip file:
-> hw1 : script for running the program
-> makefile : makefile to compile all the code, it is called in the script automatically
-> AI_hw1_java : directory containing all the java source code
-> AI_hw1_classes : directory containing all the java byte code
-> output.txt : sample output of program
-> SBP-level1.txt, SBP-level0.txt : sample game levels

Please do not change names or delte content of the directories
-> In case of recomplilation use "make" clean

How to run the program:
-> I have attached the script hw1, to run this just do : "./hw1"
-> Now you can also output the result as "./hw1 > out.txt", output 
	is formatted to make a bit less stranny on eyes.

-> I have also attached results for my run, just by default 
	random walker works on SBP-level0.txt with N=3, and all the searches
	works on SBP-level1.txt.

-> However, to change the defaults, just follow these conventions:
	-f : for filename required to do all seaches on
	-r : filename for random walker
	-n : for no. of iterations for random walker

	Example:
		./hw1 -f SBPlevel1.txt -r SBPlevel0.txt -n 4

	Arguments can be passed in any arbitary order

	Program will work even if no arguments are given

	Also, place all the files at the same level as the script


