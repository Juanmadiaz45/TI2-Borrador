package checksums

class SingleSumAlgorithm extends CheckSumAlgorithm {
  override def calculate(data: Array[Byte], k: Int): Int = {
    val M: Int = Math.pow(2, k).toInt - 1
    var sum: Int = 0
    for (byte <- data) {
      sum = (sum + byte) % M
    }
    sum
  }

  override def stringToBits(input: String): Array[Byte] =
    input.getBytes.map(_.toByte)

  override def intToBits(input: Int): Array[Byte] =
    BigInt(input).toByteArray

  override def fileToBytes(filePath: String): Array[Byte] =
    java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath))

  override def splitIntoBlocks(bits: Array[Byte], blockSize: Int): Array[Array[Byte]] =
    bits.grouped(blockSize).toArray
    
}
