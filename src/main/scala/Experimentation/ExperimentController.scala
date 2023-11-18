package Experimentation
import checksums._

class ExperimentController {
  private var experiments: List[Experiment] = List()

  def addExperiment(experiment: Experiment): Unit = {
    experiments = experiment :: experiments
  }

  def runAllExperiments(): Unit = {
    experiments.foreach(_.runExperiment())
  }

  def getAverageExecutionTime(algorithm: CheckSumAlgorithm, dataSize: Int): Long = {
    val relevantExperiments = experiments.filter(e => e.algorithm == algorithm && e.data.length == dataSize)
    val totalExecutionTime = relevantExperiments.foldLeft(0L)(_ + _.getExecutionTime())
    totalExecutionTime / relevantExperiments.length
  }
}
