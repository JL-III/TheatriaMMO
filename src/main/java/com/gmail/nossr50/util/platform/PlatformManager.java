package com.gmail.nossr50.util.platform;

import com.gmail.nossr50.util.compat.CompatibilityManager;
import org.jetbrains.annotations.Nullable;

/**
 *
 * These classes are a band-aid solution for adding NMS support into 2.1.XXX
 * In 2.2 we are switching to modules and that will clean things up significantly
 *
 */
public class PlatformManager {
    protected Platform platform; //current platform

    public Platform getPlatform() {
        return platform;
    }

    public @Nullable CompatibilityManager getCompatibilityManager() {
        return platform.getCompatibilityManager();
    }
}
