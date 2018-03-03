package com.darkenchanter.ghostmode.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		e.getModLog().info("Client Side!");
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}
}
