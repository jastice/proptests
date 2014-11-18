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

  /** Just the logistic curve. What could possibly go wrong?
    * Write some properties to test your assumptions.
    * see also: http://en.wikipedia.org/wiki/Sigmoid_function
    */
  def logistic(t: Double): Double = 1 / (1 + Math.exp(-t))

  /**
   * Union set of two sets.
   * Implement it using only add/remove operations against the predefined property tests.
   */
  def union[T](a: Set[T], b: Set[T]): Set[T] = ???

  /** Simplest definition of a linked list. */
  sealed trait ConsList[T]
  case class Cons[T](head: T, tail: ConsList[T]) extends ConsList[T]
  case object Empty extends ConsList[Nothing]

  /**
   * Calculate the size of a list.
   * Write properties and an implementation.
   */
  def size[T](list: ConsList[T]): Int = ???

  def contains[T](elem: T, list: ConsList[T]): Boolean = ???

  /**
   * Test if the list `list` starts with the elements of the list `start`.
   * Write properties and an implementation.
   */
  def startsWith[T](list: ConsList[T], start: ConsList[T]): Boolean = ???

  /**
   * Append b to a.
   * Write properties and an implementation.
   */
  def append[T](a: ConsList[T], b: ConsList[T]): ConsList[T] = ???

  /**
   * Reverse a list.
   * Write properties and an implementation.
   */
  def reverse[T](list: ConsList[T]): ConsList[T] = ???

  /**
   * Sum up the repeated elements of a list.
   * Write properties and am implementation.
   */
  def sumRepeated(list: ConsList[Int]): ConsList[Int] = ???

}
