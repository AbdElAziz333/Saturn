package com.abdelaziz.saturn.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Saturn.MOD_ID)
public class Saturn {
    public static final String MOD_ID = "saturn";
    private static final Logger LOGGER = LogManager.getLogger();

    public Saturn() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}