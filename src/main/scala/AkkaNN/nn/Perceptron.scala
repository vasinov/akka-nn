package AkkaNN.nn

import scala.util.Random

object Perceptron {
  val learningConstant = 0.01

  def apply(size: Int) = new Perceptron(size)

  def randomFloatInRange(): Float = Random.nextInt(2) match {
    case 0 => - Random.nextFloat()
    case 1 => Random.nextFloat()
  }

  def train(p: Perceptron, is: Vector[Float], desired: Int): Perceptron = {
    val guess = p.feedForward(is)
    val error = desired - guess
    val newWs = p.ws.view.zipWithIndex.map(w => newWeight(is(w._2), w._1, error)).toVector

    Perceptron(newWs)
  }

  def newWeight(i: Float, w: Float, e: Int): Float = (w + (learningConstant * e * i)).toFloat
}

case class Perceptron(ws: Vector[Float]) {
  def this(size: Int) = this(Vector.fill(size)(Perceptron.randomFloatInRange()))

  def feedForward(is: Vector[Float]): Int = Activator.default {
    (is zip ws).foldLeft(0F)((sum, z) => sum + (z._1 * z._2))
  }
}
