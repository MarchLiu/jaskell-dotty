package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import jaskell.parsec.Combinator._

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 19:48
 */
class ManyTillSpec extends AnyFlatSpec with Matchers{
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Simple" should "Test a many till match" in {
    val state = "hhhhhhlhhhll".state

    val m = manyTill(Eq('h'), Eq('l'))

    val re = m(state)
    re.map(_.size) should be (Success(6))
    for(item <- re.get) {
      item should be ('h')
    }
  }
}
