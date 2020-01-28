package com.moviles.kiari.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.moviles.kiari.R;

public class PageViewModel extends ViewModel {

    public int[] videos_images ={
            R.drawable.fondo,
            R.drawable.fondo,
            R.drawable.fondo
    };
    public String [] videos_numeration = {
            "1/3",
            "2/3",
            "3/3"
    };
    public String [] videos_title = {
            "Wilmon, la historia de un Youtuber",
            "Una madre de casa en recuperacion",
            "Mi hijo el luchador"
    };
    public String [] videos_descripction = {
            "En este video podemos apreciar como se recupera  con normalidad el Sr. Wilmon, que le dio  un derrame cerebral debido a....",
            "La Sr. Franco se encuentra en mejora despues de una notable mejoria, su notable progreso se debe a su familia",
            "El joven Eduardo es un notable ejemplo del no darse por vencido su familia junto a sus amigos se encuentra con el"
    };



    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private LiveData<String> titleText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            int var=input-1;
            return videos_title[var];
        }
    });

    private LiveData<String> descriptionText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            int var=input-1;
            return videos_descripction[var];
        }
    });
    private LiveData<String> numerationText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            int var=input-1;
            return videos_numeration[var];
        }
    });


    public void setIndex(int index) {
        mIndex.setValue(index);
    }





    public LiveData<String> getTitleText() {
        return titleText;
    }

    public LiveData<String> getDescriptionText() {
        return descriptionText;
    }
    public LiveData<String> getNumerationText() {
        return numerationText;
    }

}