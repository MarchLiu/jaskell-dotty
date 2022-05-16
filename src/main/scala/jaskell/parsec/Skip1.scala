package jaskell.parsec

import scala.util.{Success, Try}
import scala.language.implicitConversions

/**
 * Skip1 p applies the parser p one or more times, skipping its result.
 *
 * @author mars
 * @version 1.0.0
 */
case class Skip1[E](val psc: Parsec[E, _]) extends Parsec[E, Unit] :

  import Parsec.Instances.{given, *}

  val skip = Skip(psc)
  val parser: Parsec[E, _] = psc *> skip

  def apply(s: State[E]): Try[Unit] =
    (parser ? s).flatMap(_ => Success(()))

