package com.darkenchanter.ghostmode.proxy;

import com.darkenchanter.ghostmode.ghostability.GhostCapability;
import com.darkenchanter.ghostmode.ghostability.GhostStorage;
import com.darkenchanter.ghostmode.ghostability.IGhostCapability;
import com.darkenchanter.ghostmode.networking.GMPacketHandler;
import com.darkenchanter.ghostmode.networking.client.GhostRequestHandler;
import com.darkenchanter.ghostmode.networking.client.GhostRequestMessage;
import com.darkenchanter.ghostmode.networking.server.GhostMessage;
import com.darkenchanter.ghostmode.networking.server.GhostMessageHandler;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class CommonProxy {
	@OverridingMethodsMustInvokeSuper //Used by each of these to make sure that I don't clutz up and make client side not run necessary code
	public void preInit(FMLPreInitializationEvent e) {
		GMPacketHandler.INSTANCE.registerMessage(GhostMessageHandler.class, GhostMessage.class, 354, Side.CLIENT);
		GMPacketHandler.INSTANCE.registerMessage(GhostRequestHandler.class, GhostRequestMessage.class, 355, Side.SERVER);
		CapabilityManager.INSTANCE.register(IGhostCapability.class, new GhostStorage(), GhostCapability::new); //Registers the ghost mode capability into the
		// game.
	}

	@OverridingMethodsMustInvokeSuper
	public void init(FMLInitializationEvent e) {

	}

}
