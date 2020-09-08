package com.examen.liverpool.data

data class RefinementGroups(
    var name:String="",
    var refinement:List<Refinement>?=null,
    var multiSelect: Boolean,
    var dimensionName:String
)