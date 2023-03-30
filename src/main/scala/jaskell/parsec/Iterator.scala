package jaskell.parsec

import scala.util.Try

/**
 * @param p
 * @param state
 * @tparam E
 * @tparam T
 */
class Iterator[E, T](val p: Parsec[E, T], val state: State[E]) extends scala.collection.Iterator[T] {
  val psc: Parsec[E, T] = p.attempt
  var item: Try[T] = psc ? state

  /**
   * @return
   */
  override def hasNext: Boolean = item.isSuccess

  /**
   * @return
   */
  def next(): T = {
    val re = item.get
    item = psc ? state
    re
  }
}