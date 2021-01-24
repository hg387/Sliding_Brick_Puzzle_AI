F1 = SBP-level1.txt
F2 = SBP-level0.txt
N = 3

AI_hw1: AI_hw1c
	@java -cp AI_hw1_classes main.java.Main $(F1) $(F2) $(N)

AI_hw1c:
	@javac AI_hw1_java/src/main/java/*.java  AI_hw1_java/src/main/resources/*.java -d AI_hw1_classes

clean:
	@rm -rf AI_hw1_classes/*
