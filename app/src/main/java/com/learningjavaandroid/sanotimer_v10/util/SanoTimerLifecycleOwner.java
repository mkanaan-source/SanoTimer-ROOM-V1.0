package com.learningjavaandroid.sanotimer_v10.util;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

public class SanoTimerLifecycleOwner implements LifecycleOwner {
    private final LifecycleRegistry lifecycleRegistry;

    public SanoTimerLifecycleOwner() {
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
    }

    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}

