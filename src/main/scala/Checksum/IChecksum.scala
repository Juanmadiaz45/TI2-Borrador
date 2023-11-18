package checksums

trait IChecksum {
  def calculate(data: Array[Byte], k: Int): Int

  def stringToBits(input: String): Array[Byte]

  def intToBits(input: Int): Array[Byte]

  def fileToBytes(filePath: String): Array[Byte]
}
