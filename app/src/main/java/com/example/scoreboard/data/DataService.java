package com.example.scoreboard.data;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.example.scoreboard.api.ApiResponseCallback;
import com.example.scoreboard.api.GadsApiService;
import com.example.scoreboard.model.LearningLeader;
import com.example.scoreboard.model.SkillLeader;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//import java.util.logging.Handler;

public class DataService {
    public static Handler handler = new Handler();

    public static void getLearningLeaders(@NonNull final DataResponseCallback<List<LearningLeader>> callback) {
        GadsApiService.getLearningLeaders(new ApiResponseCallback<List<LearningLeader>>() {
            @Override
            public void onResponse(final List<LearningLeader> response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Collections.sort(response, new Comparator<LearningLeader>() {
                            @Override
                            public int compare(LearningLeader a, LearningLeader b) {
                                return (int) Math.signum( b.getHours()-a.getHours() );
                            }
                        });
                        callback.onResponse(response);
                    }
                });
            }

            @Override
            public void onError(Throwable error) {
                callback.onError(error);
            }
        });
    }

    public static void getSkillLeaders(@NonNull final DataResponseCallback<List<SkillLeader>> callback) {
        GadsApiService.getSkillLeaders(new ApiResponseCallback<List<SkillLeader>>() {
            @Override
            public void onResponse(final List<SkillLeader> response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Collections.sort(response, new Comparator<SkillLeader>() {
                            @Override
                            public int compare(SkillLeader a, SkillLeader b) {
                                return (int) Math.signum(b.getScore() -a.getScore()  );
                            }
                        });
                        callback.onResponse(response);
                    }
                });
            }

            @Override
            public void onError(final Throwable error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(error);
                    }
                });
            }
        });
    }
}