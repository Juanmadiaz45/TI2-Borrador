import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.Assertions._
import checksums._
import Experimentation._

class ExperimentTestSuite extends AnyFunSuite {
  val toySize = 100
  val smallSize = 1000
  val mediumSize = 10000
  val largeSize = 100000
  val k = 16

  val algorithm = new CheckSumAlgorithm {
    override def calculate(data: Array[Byte], k: Int): Int = data.sum
  }

  test("ChecksumAlgorithm calculate") {
    val data: Array[Byte] = Array[Byte](1, 2, 3)
    assert(algorithm.calculate(data, k) == 6)
  }

  test("DataGenerator generateData") {
    val generator = new DataGenerator(toySize)
    assert(generator.generateData().length == toySize)
  }

  test("Experiment runExperiment and getExecutionTime") {
    val generator = new DataGenerator(toySize)
    val experiment = new Experiment(algorithm, generator.generateData(), k)
    experiment.runExperiment()
    assert(experiment.getExecutionTime() > 0)
  }

  test("ExperimentController addExperiment, runAllExperiments, and getAverageExecutionTime") {
    val controller = new ExperimentController
    val generator = new DataGenerator(toySize)
    val experiment = new Experiment(algorithm, generator.generateData(), k)
    controller.addExperiment(experiment)
    controller.runAllExperiments()
    assert(controller.getAverageExecutionTime(algorithm, toySize) == experiment.getExecutionTime())
  }

  test("Experiment with toy size data") {
    val generator = new DataGenerator(toySize)
    val experiment = new Experiment(algorithm, generator.generateData(), k)
    experiment.runExperiment()
    assert(experiment.getExecutionTime() > 0)
  }

  test("Experiment with small size data") {
    val generator = new DataGenerator(smallSize)
    val experiment = new Experiment(algorithm, generator.generateData(), k)
    experiment.runExperiment()
    assert(experiment.getExecutionTime() > 0)
  }

  test("Experiment with medium size data") {
    val generator = new DataGenerator(mediumSize)
    val experiment = new Experiment(algorithm, generator.generateData(), k)
    experiment.runExperiment()
    assert(experiment.getExecutionTime() > 0)
  }

  test("Experiment with large size data") {
    val generator = new DataGenerator(largeSize)
    val experiment = new Experiment(algorithm, generator.generateData(), k)
    experiment.runExperiment()
    assert(experiment.getExecutionTime() > 0)
  }

  test("ExperimentController with multiple experiments") {
    val controller = new ExperimentController
    val sizes = List(toySize, smallSize, mediumSize, largeSize)
    for (size <- sizes) {
      val generator = new DataGenerator(size)
      val experiment = new Experiment(algorithm, generator.generateData(), k)
      controller.addExperiment(experiment)
    }
    controller.runAllExperiments()
    for (size <- sizes) {
      assert(controller.getAverageExecutionTime(algorithm, size) > 0)
    }
  }
}