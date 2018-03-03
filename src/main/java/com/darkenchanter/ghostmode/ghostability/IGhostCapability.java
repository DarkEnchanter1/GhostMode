package com.darkenchanter.ghostmode.ghostability;

public interface IGhostCapability {
	void setIsGhost(boolean isGhost); //TODO: Add more possible values to ghost mode.
	boolean getIsGhost(); //ALSO TODO: Make this more generic, allowing for both Ghost Mode and Human Mode.
}
