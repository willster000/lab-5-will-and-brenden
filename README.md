

The code in StableMatching.java implements the Gale-Shapley Algorithm to solve the stable matching problem. 

We have the results of several different runs with different input in the tests.txt file. 

Each input results in a output with no unsatisfactory pairs as far as we can tell. 

Our writeup: 

Explain why your algorithm is correct (i.e. it always stops and outputs a satisfactory pairing). You don't need to go into low-level details of your program, but your argument must be precise enough to convince someone who has not seen your program before that it is indeed correct.

Our algorithm is correct because:

Initialization: 

At initialization the algorithm is correct as all companies and programmers are unassigned. This means that there are no unsatisfactory     pairs. 

Before and after the main loop

In each iteration of the main loop, a programmer who is not yet matched proposes to their most preferred company among those they have      not proposed to yet. The company being proposed to has two options:
If it is unassigned, it accepts the proposal and updates its matched programmer to be the proposing programmer.
If it is already assigned to a programmer, it compares the proposing programmer with its current match:
If it prefers the proposing programmer over its current match, it accepts the new proposal and updates its matched programmer to be the     proposing programmer. The current match becomes unmatched.
If it prefers its current match over the proposing programmer, it rejects the proposal, and no changes are made.
    
Termination:

The algorithm continues until all programmers are matched.
Since there are a finite number of programmers and companies, the algorithm is guaranteed to terminate.
When the program terminates there are no unsatisfactory pairs due to the correctness of the main loop. 
    

Find the efficiency of your algorithm in the worst case, justify your answer.

The worst case of our algorithm is O(n^2), where n is the number of programmers or companies, whichever is larger.

The while loop has a worst case of of running for "n" programmers. 

Within the while loop, each programmer iterates over their preference list (which has a length equal to the number of companies, also "n" in the worst case) to propose to companies. This results in "n" iterations for each of the "n" programmers, leading to "n^2" iterations in total.

When searching for companies or programmers by name, the efficiency is O(n). 





Sources:

https://www.geeksforgeeks.org/stable-marriage-problem/
https://en.wikipedia.org/wiki/Gale%E2%80%93Shapley_algorithm
https://chat.openai.com/

