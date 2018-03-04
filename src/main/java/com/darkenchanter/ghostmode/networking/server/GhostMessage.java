package com.darkenchanter.ghostmode.networking.server;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class GhostMessage implements IMessage {
	private boolean ghost;
	public boolean getGhost() {
		return ghost;
	}
	public GhostMessage() {
		ghost = false;
	}
	public GhostMessage(boolean isGhostMode) {
		ghost = true;
	}
	@Override
	public void fromBytes (ByteBuf buf) {
		ghost = buf.readBoolean();
	}

	@Override
	public void toBytes (ByteBuf buf) {
		buf.writeBoolean(ghost);
	}
}
