package com.examen.liverpool.data

class RequestDTO {
    var valueSearch:String=""
    var page:Int=0
    var numberOfItems:Int=0
    var forcePlp:Boolean=false

    constructor(forcePlp:Boolean,valueSearch:String,page:Int,numberOfItems:Int){
        this.forcePlp=forcePlp
        this.valueSearch=valueSearch
        this.page=page
        this.numberOfItems=numberOfItems
    }

}