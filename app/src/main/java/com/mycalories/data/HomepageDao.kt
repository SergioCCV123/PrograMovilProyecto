package com.mycalories.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.mycalories.model.Homepage

class HomepageDao {

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init{
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getHomepage() : MutableLiveData<List<Homepage>>{
        val listaHomepage = MutableLiveData<List<Homepage>>()

        firestore.collection("MyCaloriesApp")
            .addSnapshotListener{ instant, e ->
                if(e != null){
                    return@addSnapshotListener
                }
                if(instant!=null){
                    val lista = ArrayList<Homepage>()

                    instant.documents.forEach{
                        val info = it.toObject(Homepage::class.java)
                        if(info != null){
                            lista.add(info)
                        }
                    }
                    listaHomepage.value = lista
                }
            }
        return listaHomepage
    }

}