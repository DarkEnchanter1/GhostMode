package com.darkenchanter.ghostmode.proxy;

import com.darkenchanter.ghostmode.ghostability.GhostCapability;
import com.darkenchanter.ghostmode.ghostability.GhostStorage;
import com.darkenchanter.ghostmode.ghostability.IGhostCapability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class CommonProxy {
	@OverridingMethodsMustInvokeSuper //Used by each of these to make sure that I don't clutz up and make client side not run necessary code
	public void preInit(FMLPreInitializationEvent e) {
		CapabilityManager.INSTANCE.register(IGhostCapability.class, new GhostStorage(), GhostCapability::new); //Registers the ghost mode capability into the game.
	}

	@OverridingMethodsMustInvokeSuper
	public void init(FMLInitializationEvent e) {

	}

}
