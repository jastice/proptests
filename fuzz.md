## Fuzzing

A loose relation to property based testing: Feeding "random" input to a program,
see what kind of errors pop up.

* the data input can be more general: files, streams, whatever
* test the well-behaved-ness of the system as a whole, rather than individual functions
* test resilience to bad input, rather than specific properties
* can automatically explore execution paths with

### Cool example

That jpeg generating from just a "hello" seed

### Practical example

TODO

* http://en.wikipedia.org/wiki/Fuzz_testing
* https://code.google.com/p/american-fuzzy-lop/
    - http://lcamtuf.blogspot.de/2014/11/pulling-jpegs-out-of-thin-air.html
* https://www.owasp.org/index.php/JBroFuzz

