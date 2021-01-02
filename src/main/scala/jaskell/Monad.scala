package jaskell
import scala.util.{Try, Success, Failure}

trait Monad[F[_]] extends Applicative[F]:
  extension [A, B](x: F[A])
    def >>= (f: A=> F[B]): F[B] = {
      x.flatMap(f)
    } 

    /** The `map` operation can now be defined in terms of `flatMap` */
    def map(f: A => B): F[B] = x.flatMap(f.andThen(pure))

    def >> (f: A => B): F[B] = map(f)

given listMonad: Monad[List] with
  def pure[A](x: A): List[A] =
    List(x)
  extension [A, B](xs: List[A])
    def flatMap(f: A => List[B]): List[B] = 
      xs.flatMap(f) // rely on the existing `flatMap` method of `List`

given optionMonad: Monad[Option] with
  def pure[A](x: A): Option[A] = Option(x)

  extension [A, B](xo: Option[A])
    def flatMap(f: A => Option[B]): Option[B] = xo match
      case Some(x) => f(x)
      case None => None

given tryMonad: Monad[Try] with
  def pure[A](x: A) = Success(x)
  extension [A, B](xt: Try[A])
      def flatMap(f: A => Try[B]): Try[B] = xt.flatMap(f)