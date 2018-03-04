package com.darkenchanter.ghostmode.ghostability;

import com.darkenchanter.ghostmode.ModStart;
import com.darkenchanter.ghostmode.networking.GMPacketHandler;
import com.darkenchanter.ghostmode.networking.server.GhostMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * The one and only implementation of IGhostCapability.
 */
public class GhostCapability implements IGhostCapability {
	private boolean isGhost = false;
	@Override
	public boolean getIsGhost () {
		return isGhost;
	} //TODO: Make this more capable, adding in things like a flight value
	public void setIsGhost(boolean newVal, EntityPlayer player) {
		if (!player.getEntityWorld().isRemote) { //Checks if running on server
			ModStart.logger.info("Message sent");
			GMPacketHandler.INSTANCE.sendTo(new GhostMessage(isGhost), (EntityPlayerMP) player);
		}
		isGhost = newVal;
	}

	/**
	 * Used purely for loading data from NBT. For any other implementation, use {@link #setIsGhost(boolean, EntityPlayer}
	 * @param newVal
	 */
	public void setIsGhost(boolean newVal) {
		isGhost = newVal; // I don't know what the fuck I'm doing
	}
}
