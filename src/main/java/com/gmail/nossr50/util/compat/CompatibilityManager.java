package com.gmail.nossr50.util.compat;

import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.util.LogUtils;
import com.gmail.nossr50.util.compat.layers.bungee.AbstractBungeeSerializerCompatibilityLayer;
import com.gmail.nossr50.util.compat.layers.bungee.BungeeModernSerializerCompatibilityLayer;
import com.gmail.nossr50.util.compat.layers.skills.AbstractMasterAnglerCompatibility;
import com.gmail.nossr50.util.compat.layers.skills.MasterAnglerCompatibilityLayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 *
 * These classes are a band-aid solution for adding NMS support into 2.1.XXX
 * In 2.2 we are switching to modules and that will clean things up significantly
 *
 */
//TODO: I need to delete this crap
public class CompatibilityManager {
    private @NotNull HashMap<CompatibilityType, Boolean> supportedLayers;
    private boolean isFullyCompatibleServerSoftware = true; //true if all compatibility layers load successfully

    /* Compatibility Layers */
    private AbstractBungeeSerializerCompatibilityLayer bungeeSerializerCompatibilityLayer;
    private AbstractMasterAnglerCompatibility masterAnglerCompatibility;

    public CompatibilityManager() {
        LogUtils.debug(mcMMO.p.getLogger(), "Loading compatibility layers...");
        init();
        LogUtils.debug(mcMMO.p.getLogger(), "Finished loading compatibility layers.");
    }

    private void init() {
        initSupportedLayersMap();
        initCompatibilityLayers();
    }

    private void initSupportedLayersMap() {
        supportedLayers = new HashMap<>(); //Init map

        for(CompatibilityType compatibilityType : CompatibilityType.values()) {
            supportedLayers.put(compatibilityType, false); //All layers are set to false when initialized
        }
    }

    /**
     * Initialize all necessary compatibility layers
     * For any unsupported layers, load a dummy layer
     */
    private void initCompatibilityLayers() {
        initBungeeSerializerLayer();
        initMasterAnglerLayer();

        isFullyCompatibleServerSoftware = true;
    }

    private void initMasterAnglerLayer() {
        masterAnglerCompatibility = new MasterAnglerCompatibilityLayer();
    }

    private void initBungeeSerializerLayer() {
        bungeeSerializerCompatibilityLayer = new BungeeModernSerializerCompatibilityLayer();
        supportedLayers.put(CompatibilityType.BUNGEE_SERIALIZER, true);
    }

    public boolean isCompatibilityLayerOperational(@NotNull CompatibilityType compatibilityType) {
        return supportedLayers.get(compatibilityType);
    }

    public boolean isFullyCompatibleServerSoftware() {
        return isFullyCompatibleServerSoftware;
    }

    public AbstractBungeeSerializerCompatibilityLayer getBungeeSerializerCompatibilityLayer() {
        return bungeeSerializerCompatibilityLayer;
    }

    public @Nullable AbstractMasterAnglerCompatibility getMasterAnglerCompatibilityLayer() {
        return masterAnglerCompatibility;
    }

}
