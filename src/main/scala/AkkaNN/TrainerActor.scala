package AkkaNN

import AkkaNN.training.DotsTrainingStrategy
import akka.actor.{ActorRef, Actor}

class TrainerActor(p: ActorRef) extends Actor {
  var nOfResults: Int = 0
  var nOfExamples: Int = 0

  def receive = {
    case InitTraining(f) =>
      println("TrainMessage received")

      val examples = DotsTrainingStrategy.generateExamples(f)
      nOfExamples = examples.length

      examples.foreach(te => p ! Train(te.inputs, te.answer))
    case ExampleTrained =>
      nOfResults += 1

      if (nOfResults >=  nOfExamples) {
        println("training stopped")

        context.stop(self)
      }
  }
}
