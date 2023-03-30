package jaskell.parsec

import scala.util.{Failure, Success, Try}
import scala.language.implicitConversions
import jaskell.Monad

import scala.annotation.targetName

trait Parsec[A, +B] {
  def apply(state: State[A]): Try[B]

  @targetName("parse")
  def ?(state: State[A]): Try[B] = this.apply(state)

  @targetName("must")
  def !(state: State[A]): B = this.apply(state).get

  def attempt: Parsec[A, B] = Attempt(this)

  def iterate[R >: B](state: State[A]): Iterator[A, R] = new Iterator(this, state)

  @targetName("choice")
  def <|>[R >: B](p: Parsec[A, R]): Parsec[A, R] = new Combine2(this, p)

  @targetName("otherwise")
  def `<?>`[C >: B](message: String): Parsec[A, C] = (s: State[A]) => {
    this ? s orElse s.trap(message)
  }
}

object Parsec {
  object Instances {
    given parsecConfig[E]: Monad[[T] =>> Parsec[E, T]] with {
      def pure[A](x: A): Parsec[E, A] = new Pack[E, A](x)

      extension[A, B] (x: Parsec[E, A]) {
        def flatMap(f: A => Parsec[E, B]): Parsec[E, B] = new Binder(x, f)
      }

    }

    given textParserConfig[T]: Monad[[T] =>> Parsec[Char, T]] with {
      def pure[A](x: A): Parsec[Char, A] = new Pack[Char, A](x)

      extension[A, B] (x: Parsec[Char, A]) {
        def flatMap(f: A => Parsec[Char, B]): Parsec[Char, B] = new Binder(x, f)
      }

      import State.Instances.{given, *}
      extension[A] (x: Parsec[Char, A]) {
        def apply(content: String): Try[A] = x.apply(content.state)
        def ?(content: String): Try[A] = x ? content.state
      }
    }
  }
}

