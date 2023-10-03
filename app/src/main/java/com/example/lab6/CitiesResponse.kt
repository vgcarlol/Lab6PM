package com.example.lab6

data class CitiesResponse(
    val cities: List<City>
) : List<UaItem> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: UaItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<UaItem>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): UaItem {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: UaItem): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<UaItem> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: UaItem): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<UaItem> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<UaItem> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<UaItem> {
        TODO("Not yet implemented")
    }
}

data class City(
    val id: String,
    val name: String,
    val link: String
)
