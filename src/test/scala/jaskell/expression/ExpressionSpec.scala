package jaskell.expression

import jaskell.expression.parsers.{Num, Parser}
import jaskell.parsec.{Parsec, State, TxtState}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/03 15:09
 */
class ExpressionSpec extends AnyFlatSpec with Matchers {
  def emptyEnv = new Env

  import jaskell.parsec.State.Instances.{given, *}
  import jaskell.parsec.Parsec.Instances.{given, *}
  import jaskell.parsec.Txt._

  val p = new Parser

  "Number" should "match a number" in {
    val content = "3.14".state
    val p = new Num
    val exp = p ? content
    exp.flatMap(_.eval(emptyEnv)) should be(Success(3.14))
  }

  "Basic" should "match a number" in {
    val content = "3.14".state
    val exp = p ? content
    exp.flatMap(_.eval(emptyEnv)) should be(Success(3.14))
  }

  "Add" should "match a add expression" in {
    val content = "3.14+2.53".state
    val exp = p ! content
    exp.eval(emptyEnv).get should be(5.67)
  }

  "Sub" should "match a sub expression" in {
    val content = "179- 8".state
    val exp = p ! content
    exp.eval(emptyEnv).get should be(171)
  }

  "Product" should "match a product expression" in {
    val content = "8 * -8".state
    val exp = p ! content
    exp.eval(emptyEnv) should be(Success(-64))
  }

  "Divide" should "match a divide expression" in {
    val st: TxtState = "128/8".state
    val exp = p(st)
    exp.flatMap(_.eval(emptyEnv)) should be(Success(16))
  }

  "Quote" should "match a quoted expression" in {
    val st: TxtState = "(128/8)".state
    val exp = p ! st
    exp.eval(emptyEnv) should be(Success(16))
  }

  "Priorities" should "compute a ploy expressio right to left" in {
    val st = "7 + 15 * 3".state
    val re = p(st).get
    val exp = re.makeAst
    println(exp.eval(emptyEnv))
    exp.eval(emptyEnv).get should be(52)
  }

  "Priorities flow" should "compute a ploy expression left to right" in {
    val content = "5 * 3 + 7".state
    val re = p ! content
    val exp = re.makeAst
    exp.eval(emptyEnv).get should be(22)
  }

  "Ploy Quote" should "compute a ploy expression include quote" in {
    val content: State[Char] = "5 * (3 + 7)".state
    val re = p ! content
    val exp = re.makeAst
    exp.eval(emptyEnv).get should be(50)
  }

  "Ploy Complex" should "compute a complex ploy expression include quote" in {
    val content = "5 * (3 + 7) - 22.5".state
    val re = p ! content
    val exp = re.makeAst
    exp.eval(emptyEnv).get should be(27.5)
  }

  "More Complex" should "compute a complex ploy expression has double sub" in {
    val content = "5 * (3 + 7) - -22.5".state
    val p = new Parser
    val re = p ! content
    val exp = re.makeAst
    exp.eval(emptyEnv).get should be(72.5)
  }

  "Scientific" should "compute a complex ploy expression has scientific notation" in {
    val content = "5 * (3 + 7e2) - -22.5".state
    val re = p ! content
    val exp = re.makeAst
    exp.eval(emptyEnv).get should be(3537.5)
  }

  "Scientific More" should "compute a complex ploy expression of more scientific notation numbers" in {
    val content = "5 * (3E-3 + 7) - -22.5e8".state
    val re = p ! content
    val exp = re.makeAst
    exp.eval(emptyEnv) should be(Success(2.250000035015E9))
  }

  "Normal Compute" should "compute a normal expression" in {
    val content = "3.14 + 7 * 8 - (2 + 3)".state
    p ? content flatMap {
      _.makeAst eval emptyEnv
    } should be(Success(54.14))
  }

  "Parameters Compute" should "compute a parameters expression" in {
    val env = emptyEnv
    env.put("x", 13)
    val content = "3.14 + 7 * 8 - (2 + x)".state
    p ? content flatMap {
      _.makeAst eval env
    } should be(Success(44.14))
  }

}
