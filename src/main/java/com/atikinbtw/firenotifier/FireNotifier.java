package com.atikinbtw.firenotifier;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FireNotifier implements ModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger("FireNotifier");

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized.");
    }
}
