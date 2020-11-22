package jaskell.parsec
import scala.util.{Try}
import scala.language.implicitConversions

class Decimal extends Parsec[Char, String]:
  val sign: Parsec[Char, String] = new Attempt(Text("-")) <|> Pack("")
  val udicemal = new UDecimal()

  override def apply(st: State[Char]): Try[String] = {
    for {
      s <- sign ? st
      num <- udicemal ? st
    } yield s + num
  }