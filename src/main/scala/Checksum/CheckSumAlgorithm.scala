package checksums

abstract class CheckSumAlgorithm extends IChecksum {
  override def calculate(data: Array[Byte], k: Int): Int

  override def stringToBits(input: String): Array[Byte] =
    input.getBytes.map(_.toByte)

  override def intToBits(input: Int): Array[Byte] =
    BigInt(input).toByteArray

  override def fileToBytes(filePath: String): Array[Byte] =
    java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath))

  def splitIntoBlocks(bits: Array[Byte], blockSize: Int): Array[Array[Byte]] =
    bits.grouped(blockSize).toArray
}