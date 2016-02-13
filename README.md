##Problem:

You are a drug trafficker. Every day you meet with a different nice older lady (the mule) and find out how much weight she can carry in her handbag. You then meet with your supplier who has packed various drugs into a myriad of collectible porcelain dolls. Once packed with drugs, each of the precious dolls has a unique combination of weight and street value. Sometimes your supplier has more dolls than the nice lady can carry, though space in her handbag is never an issue. Your job is to choose which dolls the kind soul will carry, maximizing street value, while not going over her weight restriction.

**Write a function in the [Clojure](http://clojure.org/) programming language** which given:

* a set of dolls with a name and unique weight and value combination
* an overall weight restriction

Produces the optimal set of drug-packed porcelain dolls which:

* are within the total weight restriction
* maximize the total street value of drugs delivered

Requirements:

* use leiningen - https://github.com/technomancy/leiningen
* include multiple high-level test cases to validate your solution (like the one included below)
* provide instructions in a README for running your test suite from the command line

Input:

    max weight: 400

    available dolls:

    name    weight value
    luke        9   150
    anthony    13    35
    candice   153   200
    dorothy    50   160
    puppy      15    60
    thomas     68    45
    randal     27    60
    april      39    40
    nancy      23    30
    bonnie     52    10
    marc       11    70
    kate       32    30
    tbone      24    15
    tommy      48    10
    uma        73    40
    grumpkin   42    70
    dusty      43    75
    grumpy     22    80
    eddie       7    20
    tory       18    12
    sally       4    50
    babe       30    10

Result:

    packed dolls:

    name    weight value
    sally       4    50
    eddie       7    20
    grumpy     22    80
    dusty      43    75
    grumpkin   42    70
    marc       11    70
    randal     27    60
    puppy      15    60
    dorothy    50   160
    candice   153   200
    anthony    13    35
    luke        9   150

Hint:

* read this - http://en.wikipedia.org/wiki/Knapsack_problem

<br>

##Requirements

* org.clojure/clojure
* org.clojure/tools/cli
* clojure-csv/clojure-csv

With Leiningen, these dependencies can be installed locally using 'lein deps' on the command line.

##Usage

Help for running this program can be found by running 'lein run -- -h':

    -f, --file FILE      ./input/dolls1.csv  Path to input file
    -w, --weight WEIGHT  400                 Maximum weight allowed
    -h, --help
    
Example:

    lein run -- -f ./input/dolls2.csv -w 300
    
Without defining input file and weight, the default values are:

    file: ./input/dolls1.csv
    weight: 400

##Input CSV example (name,weight,value):

    luke,1,150
    anthony,2,35
    candice,2,200
    dorothy,1,160

##Tests

Tests written for this program can be ran using the 'lein test' command:

    $ lein test
    lein test doll-smuggler.core-test
    Ran 10 tests containing 15 assertions.
    0 failures, 0 errors.
