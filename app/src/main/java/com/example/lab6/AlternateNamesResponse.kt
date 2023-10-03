package com.example.lab6

data class AlternateNamesResponse(
    val alternate_names: List<AlternateName>
)

data class AlternateName(
    val name: String
)

