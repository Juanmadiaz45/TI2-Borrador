package Experimentation

class DataGenerator(val dataSize: Int) {
  def generateData(): Array[Byte] = {
    val data = new Array[Byte](dataSize)
    new scala.util.Random().nextBytes(data)
    data
  }

  def loadData(filePath: String): Array[Byte] = {
    import java.nio.file.{Files, Paths}
    Files.readAllBytes(Paths.get(filePath))
  }
}