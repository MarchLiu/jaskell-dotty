package jaskell.parsec

import scala.util.{Failure, Success, Try}

/**
 * NoneOf success if get a item none of any in prepared
 *
 * @author Mars Liu
 * @version 1.0.0
 */
class NoneOf[E](val items: Set[E]) extends Parsec[E, E] {
  /**
   * @param s
   * @return
   */
  def apply(s: State[E]): Try[E] = {
    s.next() flatMap { re =>
      if (items.contains(re)) {
        s.trap(s"expect a item none of $items but got $re")
      } else {
        Success(re)
      }
    }
  }
}

object NoneOf:
  def parse[E](items: Set[E]): NoneOf[E] = new NoneOf(items)

