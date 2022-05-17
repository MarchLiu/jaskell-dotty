package jaskell.parsec

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2022/05/06 00:59
 */
case class CharsIn(elements: String, caseSensitive: Boolean=true) extends Parsec[Char, String] {
  import jaskell.Monad.Instances.{given, *}
  import jaskell.parsec.Parsec.Instances.{given, *}

  val parser: Parsec[Char, Seq[Char]] = Many(ChIn(elements, caseSensitive))
  val p: Parsec[Char,String] = parser >>= Txt.mkString
  override def apply(s: State[Char]): Try[String] = p(s)
}
