package jaskell.parsec

import jaskell.parsec

import scala.util.Try

/**
 * Between parse the open parser, and then sub parser, and then close parser, return sub parser result if success.
 *
 * @author mars
 * @version 1.0.0
 */
case class Between[E, T](open: Parsec[E, _], close: Parsec[E, _], parsec: Parsec[E, T])
  extends Parsec[E, T] {

  def apply(s: State[E]): Try[T] = {
    for {
      _ <- open ? s
      re <- parsec ? s
      _ <- close ? s
    } yield re
  }
}

object Between {

  class Btw[E, T](val open: Parsec[E, _], val close: Parsec[E, _]) {
    def in(parsec: Parsec[E, T]) = new Between[E, T](this.open, this.close, parsec)
  }

  def apply[E, T](open: Parsec[E, _], close: Parsec[E, _]): (parsec: Parsec[E, T]) => Parsec[E, T] =  {
    parser => new Between(open, close, parser)
  }

  def apply[E, T](open: Parsec[E, _], close: Parsec[E, _], parsec: Parsec[E, T]) =
    new Between[E, T](open, close, parsec)
}

