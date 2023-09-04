package com.gmail.nossr50.util.platform;

import com.gmail.nossr50.util.compat.CompatibilityManager;
import org.jetbrains.annotations.NotNull;

/**
 *
 * These classes are a band-aid solution for adding NMS support into 2.1.XXX
 * In 2.2 we are switching to modules and that will clean things up significantly
 *
 */
public interface Platform {

    /**
     * Get the {@link CompatibilityManager} for this {@link Platform}
     * @return the {@link CompatibilityManager} for this platform
     */
    @NotNull CompatibilityManager getCompatibilityManager();


}
