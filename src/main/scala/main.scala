package org.priyank

import zioConsole._
import runtime._

object main extends scala.App {
  lazy val program =
    for {
      _ <- putStrLn("-" * 100)
      _ <- putStrLn("Hello what is your name")
      name <- getStrLn
      _ <- putStrLn(s"Hello $name")
      _ <- ZIO.effect(throw new RuntimeException("Error"))
      _ <- putStrLn("-" * 100)

    } yield ()

  unsafeRunSync(program)
}

