package com.darkenchanter.ghostmode.networking.client;

import com.darkenchanter.ghostmode.ModStart;
import com.darkenchanter.ghostmode.ghostability.GhostCapabilityProvider;
import com.darkenchanter.ghostmode.networking.server.GhostMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GhostRequestHandler implements IMessageHandler<GhostRequestMessage, GhostMessage> {
	@Override
	public GhostMessage onMessage (GhostRequestMessage message, MessageContext ctx) {
		ModStart.logger.info("Recieved request for data from player " + message.getEntityPlayer());
		return new GhostMessage(FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(message.getEntityPlayer())
				.getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null).getIsGhost()); //Using FMLCommonHandler, the server gets a list of players and
		// finds the player specified by the message, then gets whether or not the player is a ghost and sends a response message.
	}
}
