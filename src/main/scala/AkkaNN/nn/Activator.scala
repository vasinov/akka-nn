package AkkaNN.nn

object Activator {
  def default(sum: Float): Int = {
    if (sum > 0) 1
    else -1
  }
}
