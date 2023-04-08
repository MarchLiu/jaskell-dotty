package jaskell.parsec

import scala.util.Try

/**
 * package a parser as interator
 *
 * @param p the parsec parser
 * @param state state that been parsed
 * @tparam E Element Type
 * @tparam T Result Type
 */
class Iterator[E, T](val p: Parsec[E, T], val state: State[E]) extends scala.collection.Iterator[T] {
  val psc: Parsec[E, T] = p.attempt
  var item: Try[T] = psc ? state

  /**
   * @return if iterator has next element
   */
  override def hasNext: Boolean = item.isSuccess

  /**
   * @return next element
   */
  def next(): T = {
    val re = item.get
    item = psc ? state
    re
  }
}