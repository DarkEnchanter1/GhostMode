package com.darkenchanter.ghostmode.ghostability;

import net.minecraft.entity.player.EntityPlayer;

public interface IGhostCapability {
	void setIsGhost(boolean isGhost);
	void setIsGhost(boolean isGhost, EntityPlayer player); //TODO: Add more possible values to ghost mode.
	boolean getIsGhost(); //ALSO TODO: Make this more generic, allowing for both Ghost Mode and Human Mode.
}
