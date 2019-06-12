class CubeCalculatorTest extends org.scalatest.FunSuite {
  test("CubeCalculator.cube") {
    assert(CubeCalculator.cube(3) === 27)
    assert(CubeCalculator.cube(2) === 8)
    assert(CubeCalculator.cube(5) === 125)
  }
  test("CubeCalculator.cube2") {
    assert(CubeCalculator.cube(0) === 0)
  }
}
