package AkkaNN.training

import scala.util.Random

object DotsTrainingStrategy extends TrainingStrategy {
  def generateExamples(f: Float => Float): Vector[TrainingExample] = {
    val width = 640
    val height = 480
    def rnd(height: Int): Int = Random.nextInt(height + 1)

    Vector.fill(width)(rnd(width)).foldLeft(Vector[TrainingExample]()) { (ts, x) =>
      val y = rnd(height)
      val answer = if (y >= f(x)) 1 else -1

      ts :+ TrainingExample(x, y, answer)
    }
  }
}
