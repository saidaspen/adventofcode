package util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GridTest {

    @Test
    fun size() {
        assertEquals(0, Grid<Char>().size())
        assertEquals(1, Grid(listOf(listOf(1))).size())
        assertEquals(9, Grid(listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))).size())
    }

    @Test
    fun getRow() {
        val g = Grid(listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)))
        assertEquals(listOf(1, 2, 3), g[0])
        assertEquals(listOf(4, 5, 6), g[1])
        assertEquals(listOf(7, 8, 9), g[2])
    }

    @Test
    fun map() {
        val g = Grid(listOf(listOf(1, 2, 3), listOf(4, 5, 6)))
        assertEquals(listOf('1', '2', '3', '4', '5', '6'), g.map { it.toString()[0] })
    }

    @Test
    fun mapElements() {
        val g = Grid(listOf(listOf(1, 2, 3), listOf(4, 5, 6)))
        val expected = Grid(listOf(listOf('1', '2', '3'), listOf('4', '5', '6')))
        assertEquals(expected, g.mapElements { it.toString()[0] })
    }

    @Test
    fun flipV() {
        val g = Grid(listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)))
        val expected = Grid(listOf(listOf(7, 8, 9), listOf(4, 5, 6), listOf(1, 2, 3)))
        assertEquals(expected, g.flipV())
    }

    @Test
    fun flipH() {
        val g = Grid(listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)))
        val expected = Grid(listOf(listOf(3, 2, 1), listOf(6, 5, 4), listOf(9, 8, 7)))
        assertEquals(expected, g.flipH())
    }

    @Test
    fun rotateCw() {
        val g = Grid(listOf(listOf(1, 2, 3),
                            listOf(4, 5, 6),
                            listOf(7, 8, 9)))
        val expected1 = Grid(listOf(listOf(7, 4, 1),
                                    listOf(8, 5, 2),
                                    listOf(9, 6, 3)))
        val expected2 = Grid(listOf(listOf(9, 8, 7),
                                    listOf(6, 5, 4),
                                    listOf(3, 2, 1)))
        val expected3 = Grid(listOf(listOf(3, 6, 9),
                                    listOf(2, 5, 8),
                                    listOf(1, 4, 7)))
        assertEquals(expected1, g.rotateCw(1))
        assertEquals(expected2, g.rotateCw(2))
        assertEquals(expected3, g.rotateCw(3))
        assertEquals(g, g.rotateCw(4))
    }

    @Test
    fun subGrid() {
        val g = Grid(listOf(listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9)))
        val expected1 = Grid(listOf(listOf(1, 2), listOf(4, 5)))
        val expected2 = Grid(listOf(listOf(4, 5, 6), listOf(7, 8, 9)))
        assertEquals(expected1, g.subGrid(0, 0, 2, 2))
        assertEquals(expected2, g.subGrid(1, 0, 2, 3))
    }

    @Test
    fun appendRight() {
        val left = Grid(listOf(listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9)))
        val right = Grid(listOf(listOf(1, 1),
                listOf(2, 2),
                listOf(3, 3)))
        val expected = Grid(listOf(listOf(1, 2, 3, 1, 1),
                listOf(4, 5, 6, 2, 2),
                listOf(7, 8, 9, 3, 3)))
        assertEquals(expected, left.appendRight(right))
    }

    @Test
    fun appendBottom() {
        val top = Grid(listOf(listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9)))
        val bottom = Grid(listOf(listOf(1, 1, 1),
                listOf(2, 2, 2),
                listOf(3, 3, 3)))
        val expected = Grid(listOf(listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9),
                listOf(1,1,1),
                listOf(2,2,2),
                listOf(3,3,3)))
        assertEquals(expected, top.appendBelow(bottom))
    }

}

