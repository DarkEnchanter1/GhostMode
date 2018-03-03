package com.darkenchanter.ghostmode;

import com.darkenchanter.ghostmode.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModStart.MODID, name = ModStart.NAME, version = ModStart.VERSION)
public class ModStart
{
	public static final String MODID = "ghostmode";
	public static final String NAME = "Ghost Mode";
	public static final String VERSION = "0.0.1a";
	@SidedProxy(clientSide = "com.darkenchanter.ghostmode.proxy.ClientProxy", serverSide = "com.darkenchanter.ghostmode.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static Logger logger;

	public ModStart() {
		MinecraftForge.EVENT_BUS.register(com.darkenchanter.ghostmode.event.EventHandler.class); //Registers the event handling class.
	}

	/**
	 * Grabs the mod log and calls the proxy's preInit method.
	 * @param event
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		proxy.preInit(event);
	}

	/**
	 * Calls the proxy's init method.
	 * @param event
	 */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
}
