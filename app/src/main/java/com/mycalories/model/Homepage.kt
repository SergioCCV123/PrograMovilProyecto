package com.mycalories.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Homepage (

        var Texto:String,
        var Titulo:String,
        var rutaImagen:String,

        ) : Parcelable {
            constructor() : this("","","")
        }