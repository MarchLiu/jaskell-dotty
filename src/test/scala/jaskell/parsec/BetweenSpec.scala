package jaskell.parsec

import jaskell.parsec.Txt.mkString
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success


/**
 * Between Tester.
 *
 * @author Mars Liu
 *
 *
 */
class BetweenSpec extends AnyFlatSpec with Matchers {

  import Combinator.many
  import Txt.ch
  import Atom.neqs

  "Simple" should "test basic between case" in {

    val state = "hello".state;

    val bmw = Between[Char, Char](
      ch('h'),
      ch('l'),
      ch('e')
    );

    val c = bmw(state);

    c should be(Success('e'))
  }

  "Brackets" should "test brackets pairs" in {

    val state = "[hello]".state;
    val parser = Between(
      ch('['),
      ch(']'),
      many(neqs(']')) >>= mkString
    )

    val re = parser(state)
    re should be(Success("hello"))
  }
} 
