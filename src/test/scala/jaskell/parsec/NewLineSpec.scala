package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import jaskell.parsec.Txt._

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 19:52
 */
class NewLineSpec extends AnyFlatSpec with Matchers{
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "NewLine" should "Test new line" in {
    val state = "\r\n".state

    val enter = new Newline()

    val c = for{
      _ <- ch('\r') ? state
      re <- enter ? state
    } yield re

    assert(c.isSuccess)
    c foreach(re => re should be ('\n'))
  }

  "CRLF" should "test \\r\\n match" in {
    val state = "\r\n".state

    val parser = crlf

    parser(state) should be (Success("\r\n"))
  }
}
