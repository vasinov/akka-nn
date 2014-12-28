package AkkaNN

import AkkaNN.nn.Perceptron
import akka.actor.Actor

class PerceptronActor extends Actor {
  var p = Perceptron(3)

  def receive = {
    case Train(is, a) => Some(Perceptron.train(p, is, a)).map { newP =>
      p = newP
      sender ! ExampleTrained
    }
  }
}
