package com.forgeessentials.core.environment;

import com.forgeessentials.core.ForgeEssentials;
import com.forgeessentials.util.output.LoggingHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

public class Environment
{

    private static boolean hasWorldEdit = false;

    private static boolean isClient = false;

    protected static boolean hasCauldron = false;
    public static void check()
    {
        FMLCommonHandler.instance().registerCrashCallable(new FECrashCallable());
        // Check if dedicated or integrated server
        try
        {
            Class.forName("net.minecraft.client.Minecraft");
            isClient = true;
        }
        catch (ClassNotFoundException e)
        {
            isClient = false;
        }

        if (Loader.isModLoaded("worldedit"))
        {
            hasWorldEdit = true;
            try
            {
                Class.forName("com.forgeessentials.compat.worldedit.WEIntegration");
            }
            catch (ClassNotFoundException cnfe)
            {
                LoggingHandler.felog.warn("Found WorldEdit, but not FE WorldEdit-module. You cannot use WorldEdit for FE without it.");
            }
        }

        if (Boolean.parseBoolean(System.getProperty("forgeessentials.developermode.we")))
        {
            LoggingHandler.felog.warn("WorldEdit integration tools force disabled.");
            hasWorldEdit = false;
            return;
        }
    }

    public static boolean isClient()
    {
        return isClient;
    }

    public static boolean hasWorldEdit()
    {
        return hasWorldEdit;
    }

}
