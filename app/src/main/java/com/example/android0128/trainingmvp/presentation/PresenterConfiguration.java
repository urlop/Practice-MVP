package com.example.android0128.trainingmvp.presentation;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

// Such approach allows us configure presenter in runtime without hardcoded values.
// Also, it allows us easily change some parts of the presenter configuration for tests.
@AutoValue
public abstract class PresenterConfiguration {

    @NonNull
    public static Builder builder() {
        return new AutoValue_PresenterConfiguration.Builder();
    }

    @NonNull
    public abstract Scheduler ioScheduler();

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Builder ioScheduler(@NonNull Scheduler ioScheduler);

        @NonNull
        public abstract PresenterConfiguration build();
    }
}
