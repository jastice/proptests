package com.clueda

/**
 * Various functions we will test
 */
package object props {

  /** Greatest common divisor function.
    * Implement it and test against the specification.
    * Is the specification correct?
    */
  def gcd(a: Int, b: Int): Int = if (b==0) a else gcd(b, a%b)

  /**
   * Adds a tenth of the number to itself.
   * Write some properties to test your assumptions.
   */
  def addTenth(n: Int) = n + n/10

  /** Black-box unit-test this! */
  object WeirdlyBehavedObject {
    var acc = 0

    def accumulate(n: Int): Unit = {
      // sure, why not the 999th prime
      if (acc % 7907 == 0) acc = 23
      acc += n
    }
  }


  /** Just the logistic curve. What could possibly go wrong?
    * Write some properties to test your assumptions.
    * see also: http://en.wikipedia.org/wiki/Sigmoid_function
    */
  def logistic(t: Double): Double = 1 / (1 + Math.exp(-t))

  /**
   * Union set of two sets.
   * Implement it using only add/remove operations against the predefined property tests.
   */
  def union[T](a: Set[T], b: Set[T]): Set[T] =
    a.foldLeft(b) { (acc,e) => acc + e }

  /** Datatype for game of rock/paper/scissors. */
  sealed trait RockPaperScissors
  case object Rock extends RockPaperScissors
  case object Paper extends RockPaperScissors
  case object Scissors extends RockPaperScissors

  /**
   * Play a game of rock, paper, scissors. Returns the winning object.
   * Write properties to check your assumptions.
   */
  def rockPaperScissors(a: RockPaperScissors, b: RockPaperScissors): RockPaperScissors =
    (a,b) match {
      // manually enumerating the possibilities because it's not that many
      case (Rock,Scissors) => Rock
      case (Rock,Paper) => Paper
      case (Scissors,Rock) => Rock
      case (Scissors,Paper) => Scissors
      case (Paper,Rock) => Paper
      case (Paper,Scissors) => Scissors
      case _ => a // both are the same
    }


  /** Simplest definition of a linked list. */
  sealed trait ConsList[+T]
  case class Cons[T](head: T, tail: ConsList[T]) extends ConsList[T]
  case object Empty extends ConsList[Nothing]

  /**
   * Calculate the size of a list.
   * Write properties and an implementation.
   */
  def size[T](list: ConsList[T]): Int = list match {
    case Empty => 0
    case Cons(h,t) => 1 +  size(t)
  }

  def contains[T](elem: T, list: ConsList[T]): Boolean = list match {
    case Empty => false
    case Cons(e,list) => elem == e && contains(elem, list)
  }

  /**
   * Test if the list `list` starts with the elements of the list `start`.
   * Write properties and an implementation.
   */
  def startsWith[T](list: ConsList[T], start: ConsList[T]): Boolean = (list,start) match {
    case (_,Empty) => true
    case (Empty,_) => false
    case (Cons(h1,t1), Cons(h2,t2)) => h1 == h2 && startsWith(t1,t2)
  }

  /**
   * Append b to a.
   * Write properties and an implementation.
   */
  def append[T](a: ConsList[T], b: ConsList[T]): ConsList[T] = a match {
    case Empty => b
    case Cons(h,t) => Cons(h, append(t,b))
  }

  /**
   * Reverse a list.
   * Write properties and an implementation.
   */
  def reverse[T](list: ConsList[T]): ConsList[T] = list match {
    case Empty => Empty
    case Cons(h,t) => Cons(h, reverse(t))
  }

  /**
   * Sum up the repeated numbers in a list.
   * Write properties and am implementation.
   */
  def sumRepeated(list: ConsList[Int]): ConsList[Int] =
    sumRepeatedAcc(Empty,Empty,0)

  private def sumRepeatedAcc(list:ConsList[Int], stack: ConsList[Int], prev: Int): ConsList[Int] =
    (list,stack) match {
      case (Empty,_) => stack
      case (Cons(h,t), Cons(sh,st)) if h==prev => sumRepeatedAcc(t, Cons(sh+h,st),prev)
      case (Cons(h,t), _) => sumRepeatedAcc(t, Cons(h,stack),h)
    }

}
