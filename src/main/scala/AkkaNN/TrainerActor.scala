package AkkaNN

import AkkaNN.training.DotsTrainingStrategy
import akka.actor.{ActorRef, Actor}

class TrainerActor(perceptron: ActorRef, value: Vector[Float]) extends Actor {
  var nOfResults: Int = 0
  var nOfExamples: Int = 0

  def receive = {
    case InitTraining(f) =>
      val examples = DotsTrainingStrategy.generateExamples(f)
      nOfExamples = examples.length

      println("Staring training")

      examples.foreach(te => perceptron ! Train(te.inputs, te.answer))
    case ExampleTrained =>
      nOfResults += 1

      if (nOfResults >=  nOfExamples) {
        println("Training complete")

        perceptron ! Solve(value)

        context.stop(self)
      }
  }
}
