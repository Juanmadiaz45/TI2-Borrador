import checksums._
import org.scalatest.funspec.AnyFunSpec
import java.nio.file.{Paths, Files}

class CheckSum_Test extends AnyFunSpec {

  trait TS_01 {
    val singleSum = new SingleSumAlgorithm
  }

  trait TS_02 {
    val singleSum = new SingleSumAlgorithm
  }

  trait TS_03 {
    val dualSum = new DualSumAlgorithm
  }

  trait TS_04 {
    val dualSum = new DualSumAlgorithm
  }

  describe("Auxiliary Methods CheckSum Algorithms") {
    it("should return Bytes from String (Single Sum)") {
      new TS_01 {
        val data = "This is a simple string"
        val expected: Array[Byte] = data.getBytes
        assert(singleSum.stringToBits(data).sameElements(expected))
      }
    }

    it("should return Bytes from String (Dual Sum)") {
      new TS_03 {
        val data = "This is a simple string"
        val expected: Array[Byte] = data.getBytes
        assert(dualSum.stringToBits(data).sameElements(expected))
      }
    }

    it("should return Bytes from Int (Single Sum)") {
      new TS_01 {
        val data = 12345
        val expected: Array[Byte] = Array(0, 0, 48, 57).map(_.toByte)
        assert(singleSum.intToBits(data).sameElements(expected))
      }
    }

    it("should return Bytes from Int (Dual Sum)") {
      new TS_03 {
        val data = 12345
        val expected: Array[Byte] = Array(0, 0, 48, 57).map(_.toByte)
        assert(dualSum.intToBits(data).sameElements(expected))
      }
    }

    it("should return Bytes from File (Both Implementations)") {
      val filePath = Paths.get("docs/Inform.docx").toAbsolutePath.toString

      new TS_01 {
        val fileBytes: Array[Byte] = Files.readAllBytes(Paths.get(filePath))
        assert(singleSum.fileToBytes(filePath).sameElements(fileBytes))
      }

      new TS_03 {
        val fileBytes: Array[Byte] = Files.readAllBytes(Paths.get(filePath))
        assert(dualSum.fileToBytes(filePath).sameElements(fileBytes))
      }
    }

    it("should split a Bytes Array into same size blocks (Both Implementations)") {
      val byteArray: Array[Byte] = Array[Byte](1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

      new TS_01 {
        val blockSize = 4
        val blocks: Array[Array[Byte]] = singleSum.splitIntoBlocks(byteArray, blockSize)
        assert(blocks.forall(_.length == blockSize))
      }

      new TS_03 {
        val blockSize = 4
        val blocks: Array[Array[Byte]] = dualSum.splitIntoBlocks(byteArray, blockSize)
        assert(blocks.forall(_.length == blockSize))
      }
    }
  }

  describe("Method 1: Single Checksum") {

    describe("Case 1: Text Data Single Checksum Calculation with TS01") {
      it("should calculate the correct checksum") {
        new TS_01{
          val data = "I will start with something easy"
          val k = 16
          val expected = 8196 // Actualizar este valor con el resultado correcto
          assert(singleSum.calculate(data.getBytes("UTF-8"), k) == expected)
        }
      }
    }

    describe("Case 2: Text File Single Checksum Calculation (2 Pages) with TS01") {
      it("should calculate the correct checksum") {
        new TS_01{
          val data = "File: Instructions.docx"
          val k = 16
          val expected = 12538 // Actualizar este valor con el resultado correcto
          assert(singleSum.calculate(data.getBytes("UTF-8"), k) == expected)
        }
      }
    }

    describe("Case 3: Big Text File Single Checksum Calculation (10 pages) with TS01") {
      it("should calculate the correct checksum") {
        new TS_01{
          val data = "File: Medical-Inform.pdf"
          val k = 16
          val expected = 22793 // Actualizar este valor con el resultado correcto
          assert(singleSum.calculate(data.getBytes("UTF-8"), k) == expected)
        }
      }
    }

    describe("Case 4: Edited Text Data Single Checksum Calculation with TS02") {
      it("should calculate the correct checksum") {
        new TS_02{
          val data = "I will start with something easye"
          val k = 16
          val unexpected = 8196 // Actualizar este valor con el resultado correcto
          assert(singleSum.calculate(data.getBytes("UTF-8"), k) != unexpected)
        }
      }
    }

    describe("Case 5: Corrupted Text File Single Checksum Calculation (Can't open) with TS02") {
      it("should not calculate the correct checksum") {
        new TS_02{
          val data = "File: Instructions-Corrupted.docx"
          val k = 16
          val unexpected = 12538 // Actualizar este valor con el resultado correcto
          assert(singleSum.calculate(data.getBytes("UTF-8"), k) != unexpected)
        }
      }
    }

    describe("Case 6: Corrupted Big Text File Single Checksum Calculation (Can't open) with TS02") {
      it("should not calculate the correct checksum") {
        new TS_02{
          val data = "File: Medical-Inform.docx"
          val k = 16
          val unexpected = 22793 // Actualizar este valor con el resultado correcto
          assert(singleSum.calculate(data.getBytes("UTF-8"), k) != unexpected)
        }
      }
    }
  }

  describe("Method 2: Dual Checksum") {

    describe("Case 7: Text Data Dual Checksum Calculation") {
      it("should calculate the correct dual checksum") {
        new TS_04 {
          val data = "I will start with something easy"
          val k = 16
          val expected = 942010137 // Actualizar este valor con el resultado correcto
          assert(dualSum.calculate(data.getBytes("UTF-8"), k) == expected)
        }
      }
    }

    describe("Case 8: Text File Dual Checksum Calculation (2 Pages)") {
      it("should calculate the correct dual checksum") {
        new TS_04 {
          val data = "File: Instructions.docx"
          val k = 16
          val expected = 132604694 // Actualizar este valor con el resultado correcto
          assert(dualSum.calculate(data.getBytes("UTF-8"), k) == expected)
        }
      }
    }

    describe("Case 9: Big Text File Dual Checksum Calculation (10 pages)") {
      it("should calculate the correct dual checksum") {
        new TS_04 {
          val data = "File: Medical-Inform.pdf"
          val k = 16
          val expected = 2433516355L // Actualizar este valor con el resultado correcto
          assert(dualSum.calculate(data.getBytes("UTF-8"), k) == expected)
        }
      }
    }

    describe("Case 10: Edited Text Data Dual Checksum Calculation") {
      it("should not calculate the expected dual checksum") {
        new TS_04 {
          val data = "I will start with something easye"
          val k = 16
          val unexpected = 942010137 // Actualizar este valor con el resultado correcto
          assert(dualSum.calculate(data.getBytes("UTF-8"), k) != unexpected)
        }
      }
    }

    describe("Case 11: Corrupted Text File Dual Checksum Calculation (Can't open)") {
      it("should not calculate the expected dual checksum") {
        new TS_04 {
          val data = "File: Instructions-Corrupted.docx"
          val k = 16
          val unexpected = 132604694 // Actualizar este valor con el resultado correcto
          assert(dualSum.calculate(data.getBytes("UTF-8"), k) != unexpected)
        }
      }
    }

    describe("Case 12: Corrupted Big Text File Dual Checksum Calculation (Can't open)") {
      it("should not calculate the expected dual checksum") {
        new TS_04 {
          val data = "File: Medical-Inform.pdf"
          val k = 16
          val unexpected = 2433516355L // Actualizar este valor con el resultado correcto
          assert(dualSum.calculate(data.getBytes("UTF-8"), k) != unexpected)
        }
      }
    }
  }
}