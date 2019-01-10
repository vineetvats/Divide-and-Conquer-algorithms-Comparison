---------------------------- READ ME --------------------------

This is a readme file containing instructions for executing code for 

Short Project 9: Divide and Conquer algorithms

Team No: 33

@Authors:
Vineet Vats: vxv180008
Yash Pradhan: ypp170130

Instructions to execute code:

The uploaded folder with name as my net id: ypp170130 contains java file "SP9.java", which also contains driver stub in main method

NOTE: while executing from command prompt, the pwd should be the directory containing the directory ypp170130

Steps for running code from the cmd prompt

1. Compile the "SP9.java" by executing the following command
> javac ypp170130/SP9.java

2. Run Driver stub by invoking main method and provide the command line arguments
> java ypp170130/SP9 N choice

where N and choice are command line arguments (both expects an integer)
N: size of input to algorithm
choice:
1: Insertion Sort
2: Merge Sort Take 1
3: Merge Sort Take 2
4: Merge Sort Take 3

Example:

> java ypp170130/SP9 1000000 2

This would sort an array of 1000000 elements using merge sort take 1.

Note:
1. Also attached is an pdf report containing the observations of providing various sizes of input to these algorithms.
2. Insertion Sort would take over 2 minutes for input size of about 1000000; hence marked as infinity
3. For the merge sort all versions; the time reported is the observed average of 100 trials.
4. When N was provided as over 64M; like 128M, We got out of memory exception, so we stopped there.