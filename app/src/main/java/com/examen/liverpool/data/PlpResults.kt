package com.examen.liverpool.data

data class PlpResults (
    var label:String,
    var plpState:PlpState,
    var sortOptions: List<SortOption>?=null,
    var refinementGroups: List<RefinementGroups>?=null,
    var records:List<Records>
)
