package Experimentation
import checksums._

class Experiment(var algorithm: CheckSumAlgorithm, var data: Array[Byte], var k: Int) {
  private var executionTime: Long = _

  def runExperiment(): Unit = {
    val startTime = System.nanoTime()
    algorithm.calculate(data, k)
    val endTime = System.nanoTime()
    executionTime = endTime - startTime
  }

  def getExecutionTime(): Long = executionTime
}