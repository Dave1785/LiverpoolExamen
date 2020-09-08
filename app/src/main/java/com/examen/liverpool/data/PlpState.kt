package com.examen.liverpool.data

data class PlpState (
    var categoryId:String,
    var currentSortOption:String,
    var currentFilters:String,
    var firstRecNum:Int,
    var lastRecNum:Int,
    var recsPerPage:Int,
    var totalNumRecs:Int,
    var originalSearchTerm:String
)

