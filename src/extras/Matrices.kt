package extras

import extras.MyPair as Pair

@Suppress("DataClassPrivateConstructor")
data class Matrix
    private constructor(
        val rowSize: Int,
        val columnSize: Int,
        private val defaultValue: Float
    ) {
        companion object {
            fun createMatrix(
                rowSize: Int,
                columnSize: Int,
                defaultValue: Float = -1.0f
            ): Matrix = Matrix(rowSize, columnSize, defaultValue)
        }

        val dimensions = Pair(this.rowSize, this.columnSize)
        private val matrix: Array<FloatArray> = Array(rowSize) {
            FloatArray(columnSize) {
                this.defaultValue
            }
        }
    
        operator fun get(
            columnRowPair: Pair<Int, Int>
        ): Float =
            this.matrix[columnRowPair.firstElement][columnRowPair.secondElement]
    
        operator fun set(
            columnRowPair: Pair<Int, Int>, value: Float
        ) {
            this.matrix[columnRowPair.firstElement][columnRowPair.secondElement] = value
        }

        operator fun plus(other: Matrix): Matrix {
            checkForSameDimensions(other)

            val sumMatrix = createMatrix(this.rowSize, this.columnSize)
            repeat(this.rowSize) { r ->
                repeat(this.columnSize) { c ->
                    sumMatrix[Pair(r,c)] = this[Pair(r,c)] + other[Pair(r,c)]
                }
            }
            return sumMatrix
        }

        operator fun minus(other: Matrix): Matrix {
            this.checkForSameDimensions(other)

            val differenceMatrix = createMatrix(this.rowSize, this.columnSize)
            repeat(this.rowSize) { r ->
                repeat(this.columnSize) { c ->
                    differenceMatrix[Pair(r,c)] = this[Pair(r,c)] - other[Pair(r,c)]
                }
            }
            return differenceMatrix
        }

        operator fun times(other: Matrix): Matrix {
            require(this.columnSize == other.rowSize) {
                "Multiplication not possible."
            }

            val resultMatrix = createMatrix(this.rowSize, other.columnSize,0f)
            repeat(this.rowSize) { i ->
                repeat(other.columnSize) { j ->
                    repeat(other.rowSize) { k ->
                        resultMatrix[Pair(i,j)] += this[Pair(i,k)] * other[Pair(k,j)]
                    }
                }
            }
            return resultMatrix
        }

        private fun checkForSameDimensions(otherMatrix: Matrix) {
            require(
                this.dimensions == otherMatrix.dimensions
            ) {
                "Dimensions aren't the same."
            }
        }
    
        override fun toString(): String =
            buildString {
                append("\n")
                repeat(rowSize) { i ->
                    repeat(columnSize) { j ->
                        append(
                            "${matrix[i][j]}\t"
                        )
                    }
                    append("\n")
                }
            }

    fun isEqualTo(other: Matrix): Boolean {
        checkForSameDimensions(otherMatrix = other)

        return this.matrix.contentDeepEquals(other.matrix)
    }
}