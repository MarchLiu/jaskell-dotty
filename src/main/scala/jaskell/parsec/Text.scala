package jaskell.parsec

import scala.util.control.NonLocalReturns.{returning, throwReturn}
import scala.util.{Failure, Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 */
class Text(val text: String, val caseSensitive: Boolean = true) extends Parsec[Char, String]:
  val content: String = if (caseSensitive) text else text.toLowerCase

  def apply(s: State[Char]): Try[String] = returning {
    var idx = 0
    val sb: StringBuilder = new StringBuilder
    for (c <- this.text) {
      s.next() match {
        case Success(data) =>
          val dataChar = if (caseSensitive) data else data.toLower
          if (c != dataChar) {
            throwReturn(s.trap(s"Expect $c of $text [$idx] (case sensitive $caseSensitive) at ${s.status} but get $data"))
          }
          idx += 1
          sb += data
        case Failure(error) =>
          throwReturn(Failure(error))
      }
    }
    Success(sb.toString())
  }

object Text:
  def apply(text: String, caseSensitive: Boolean): Text = new Text(text, caseSensitive)
  def apply(text: String): Text = new Text(text, true)
