package AkkaNN

import akka.actor._

case class InitTraining(f: Float => Float)
case object ExampleTrained
case class Train(is: Vector[Float], desired: Int)
case class SolveFor(p: ActorRef, v: Vector[Int])
case class Solve(v: Vector[Float])

object NNTest extends App {
  trainAndSolve()

  def trainAndSolve() = {
    val system = ActorSystem("NN")

    val perceptron = system.actorOf(Props[PerceptronActor], name = "perceptron")

    val valueToSolveFor = Vector[Float](0, -10, 1)

    val trainer = system.actorOf(Props(new TrainerActor(perceptron, valueToSolveFor)), name = "trainer")

    trainer ! InitTraining((x: Float) => 2 * x + 1)
  }
}
