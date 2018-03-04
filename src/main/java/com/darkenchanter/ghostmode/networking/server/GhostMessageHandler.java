package com.darkenchanter.ghostmode.networking.server;

import com.darkenchanter.ghostmode.ModStart;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GhostMessageHandler implements IMessageHandler<GhostMessage, IMessage> {
	@Override
	public IMessage onMessage (GhostMessage message, MessageContext ctx) {
			if (ctx.side.isClient()) {
				ModStart.logger.info("Message received. Setting value to " + message.getGhost() + " for player " + Minecraft.getMinecraft().player.getName());
				// The value that was sent
				boolean isGhost = message.getGhost();
				Minecraft.getMinecraft().player.capabilities.allowFlying = isGhost;
			}
		// No response packet
		return null;
	}
}
