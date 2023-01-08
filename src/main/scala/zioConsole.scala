package org.priyank

object zioConsole {
    type Thunk[E, A] = () => Either[E, A]

    final case class ZIO[+E, A](thunk: Thunk[E, A]) {
      def flatMap[E1 >: E, B](azb: A => ZIO[E1, B]): ZIO[E1, B] =
        ZIO { () =>
          val either = thunk()
          val res = either match {
            case Left(value) => ZIO.fail(value)
            case Right(value) => azb(value)
          }

          res.thunk()
        }

      def map[B](ab: A => B): ZIO[E, B] =
        ZIO { () =>
          val either = thunk()
          either match {
            case Left(value) => Left(value)
            case Right(value) => Right(ab(value))
          }
        }
    }

    object ZIO {
      def succeed[A](a: => A): ZIO[Nothing, A] = ZIO(() => Right(a))
      def fail[E](e: => E): ZIO[E, Nothing] = ZIO(() => Left(e))
      def effect[A](a: => A): ZIO[Throwable, A] = ZIO { () =>
        try {
          Right(a)
        } catch {
          case e: Throwable => Left(e)
        }
      }
    }

    def putStrLn(line: => String): ZIO[Nothing, Unit] = ZIO.succeed(println(line))

    def getStrLn: ZIO[Nothing, String] = ZIO.succeed(scala.io.StdIn.readLine())
}
