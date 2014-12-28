package AkkaNN

import akka.actor._

case class InitTraining(f: Float => Float)
case object ExampleTrained
case class Train(is: Vector[Float], desired: Int)

object NNTest extends App {
  trainAndSolve()

  def trainAndSolve() = {
    val system = ActorSystem("NN")

    val perceptron = system.actorOf(Props[PerceptronActor], name = "perceptron")

    val solver = system.actorOf(Props[SolverActor], name = "solver")

    val trainer = system.actorOf(Props(new TrainerActor(perceptron)), name = "trainer")

    trainer ! InitTraining((x: Float) => 2 * x + 1)

    //  println(trainedP.feedForward(Vector(0, -10, 1))) // after the nn is trained, try it with values
  }
}
