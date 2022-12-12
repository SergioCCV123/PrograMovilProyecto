package com.mycalories.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mycalories.data.HomepageDao
import com.mycalories.model.Homepage
import com.mycalories.repository.HomepageRepository

class HomepageViewModel(application: Application)  : AndroidViewModel(application){

    private val homepageRepository : HomepageRepository = HomepageRepository(HomepageDao())
    val getHomePage : MutableLiveData<List<Homepage>> = homepageRepository.getHomepage

}