package com.example.android0128.trainingmvp;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

public class TestUtils {

    private TestUtils() {
        throw new IllegalStateException("No instances, please");
    }

    @NonNull
    public static MvpApp app() {
        return (MvpApp) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }
}
