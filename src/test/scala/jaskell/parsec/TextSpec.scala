package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 12:35
 */
class TextSpec extends AnyFlatSpec with Matchers {
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Simple" should "Run some simple tests" in {
    import Txt._
    val state = "Hello World".state
    for {
      head <- text("Hello") ? state
      _ <- skipSpaces ? state
      tail <- text("world", caseSensitive = false) ? state
    } yield {s"$head $tail"} match {
      case content: String => content should be ("Hello World")
    }
  }
}
