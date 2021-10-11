package jaskell.parsec

import scala.util.Try

/**
 * Parsec Combinators
 *
 * @author mars
 * @version 1.0.0
 */
object Combinator:
  def ahead[E, T](parser: Parsec[E, T]): Ahead[E, T] = new Ahead[E, T](parser)

  def choice[E, T](parsers: Parsec[E, T]*): Choice[E, T] = new Choice[E, T](parsers)

  def many[E, T](parser: Parsec[E, T]): Many[E, T] = Many[E, T](parser)

  def many1[E, T](parser: Parsec[E, T]): Many1[E, T] = Many1[E, T](parser)

  def manyTill[E, T, L](parser: Parsec[E, T], end: Parsec[E, L]): ManyTill[E, T, L] = 
    new ManyTill[E, T, L](parser, end)

  def skip[E](parser: Parsec[E, _]): Skip[E] = new Skip[E](parser)

  def skip1[E](parser: Parsec[E, _]): Skip1[E] = new Skip1[E](parser)

  def sepBy[T, Sep, E](parser: Parsec[E, T], by: Parsec[E, Sep]): SepBy[E, T] =
    new SepBy[E, T](parser, by)

  def sepBy1[E, T](parser: Parsec[E, T], by: Parsec[E, _]): SepBy1[E, T] = new SepBy1[E, T](parser, by)

  def find[E, T](parser: Parsec[E, T]): Find[E, T] = new Find[E, T](parser)

  def between[E, T](open: Parsec[E, _], close: Parsec[E, _], parser: Parsec[E, T]): Parsec[E, T] =
    new Between[E, T](open, close, parser)

  def between[E, T](open: Parsec[E, _], close: Parsec[E, _]): (parser: Parsec[E, T]) => Parsec[E, T] = 
    Between[E, T](open, close)

extension [E, T](parsec: Parsec[E, T]) {
  def attempt: Parsec[E, T] = Attempt(parsec)
  def ahead: Parsec[E, T] = Ahead(parsec)
  def or(other: Parsec[E, T]): Parsec[E, T] = Attempt(parsec) <|> other
  def many: Parsec[E, Seq[T]] = Many(parsec)
  def many1: Parsec[E, Seq[T]] = Many1(parsec)
  def manyTill(end: Parsec[E, _]): Parsec[E, Seq[T]] = ManyTill(parsec, end)
  def skip: Parsec[E, Unit] = Skip(parsec)
  def skip1: Parsec[E, Unit] = Skip1(parsec)
  def sepBy(by: Parsec[E, _]): Parsec[E, Seq[T]] = SepBy(parsec, by)
  def sepBy1(by: Parsec[E, _]): Parsec[E, Seq[T]] = SepBy1(parsec, by)
  def find: Parsec[E, T] = Find(parsec)
  def between(open: Parsec[E, _], close: Parsec[E, _]): Parsec[E, T] = Between(open, close, parsec)
}

