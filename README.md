# Advent of Code 2024

This repo contains my solutions to the 2024 Advent of Code problems. I will not be making them to be hyper-optimized. Instead, I will be writing with readability and finishing all of the problems.

## Running the Code Locally

This project uses Gradle to execute each problem. To run locally, you will first need to populate the `src/main/resources` directory with the inputs as I am not allowed to provide them. You can retrieve them by going to [Advent of Code's website](https://adventofcode.com/2024/about).

```blockquote
Can I copy/redistribute part of Advent of Code?
Please don't. Advent of Code is free to use, not free to copy.
If you're posting a code repository somewhere, please don't include parts of Advent of Code like the puzzle text or your inputs.
If you're making a website, please don't make it look like Advent of Code or name it something similar.
```

Once you have populated the resources, you can execute each problem's code with the following console command where "4" is the problem you'd like to execute.

```
valoeaera@kitty:~/repos/advent-of-code-2024$ ./gradlew run --args="4"                                                                                                                                                                     
Reusing configuration cache.                                                                                                                                                                                                              
                                                                                                                                                                                                                                          
> Task :run                                                                                                                                                                                                                               
First Solution: xxxx
Advanced Solution: xxxx                                                                                                                                                                                                                  
                                                                                                                                                                                                                                          
BUILD SUCCESSFUL in 8s
3 actionable tasks: 2 executed, 1 up-to-date
Configuration cache entry reused.
```