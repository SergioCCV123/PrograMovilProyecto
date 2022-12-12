package com.mycalories.repository

import androidx.lifecycle.MutableLiveData
import com.mycalories.data.HomepageDao
import com.mycalories.model.Homepage

class HomepageRepository (private val homepageDao: HomepageDao){

    val getHomepage : MutableLiveData<List<Homepage>> = homepageDao.getHomepage()

}