package com.example.scoreboard.viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scoreboard.data.DataResponseCallback;
import com.example.scoreboard.data.DataService;
import com.example.scoreboard.model.SkillLeader;

import java.util.List;

public class SkillLeadersViewModel extends ViewModel {
    private MutableLiveData<List<SkillLeader>> skillLeaders;
    private MutableLiveData<Boolean> error = new MutableLiveData<>(false);
    private Handler handler = new Handler();

    public LiveData<List<SkillLeader>> getSkillLeaders() {
        if (skillLeaders == null) {
            skillLeaders = new MutableLiveData<>();
            refreshList();
        }
        return skillLeaders;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public void refreshList() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DataService.getSkillLeaders(new DataResponseCallback<List<SkillLeader>>() {
                    @Override
                    public void onResponse(List<SkillLeader> response) {
                        skillLeaders.setValue(response);
                        error.setValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        error.setValue(true);
                    }
                });
            }
        }, 1000);
    }
}