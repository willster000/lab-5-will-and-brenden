

The code in StableMatching.java implements the Gale-Shapley Algorithm to solve the stable matching problem. 

We have the results of several different runs with different input in the tests.txt file. 

Each input results in a output with no unsatisfactory pairs as far as we can tell. 

Our writeup: 

Explain why your algorithm is correct (i.e. it always stops and outputs a satisfactory pairing). You don't need to go into low-level details of your program, but your argument must be precise enough to convince someone who has not seen your program before that it is indeed correct.

Our algorithm is correct because it 

Find the efficiency of your algorithm in the worst case, justify your answer.

The worst case of our algorithm is O(n^2), where n is the number of programmers or companies, whichever is larger.



Sources:

https://www.geeksforgeeks.org/stable-marriage-problem/
https://en.wikipedia.org/wiki/Gale%E2%80%93Shapley_algorithm
