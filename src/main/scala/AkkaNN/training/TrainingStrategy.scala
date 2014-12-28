package AkkaNN.training

case class TrainingExample(x: Float, y: Float, answer: Int) {
  val inputs = Vector(x, y, 1F)
}

trait TrainingStrategy {
  def generateExamples(f: Float => Float): Vector[TrainingExample]
}
