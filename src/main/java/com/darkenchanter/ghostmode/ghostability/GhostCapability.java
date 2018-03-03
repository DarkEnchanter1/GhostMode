package com.darkenchanter.ghostmode.ghostability;

/**
 * The one and only implementation of IGhostCapability.
 */
public class GhostCapability implements IGhostCapability {
	private boolean isGhost = false;
	@Override
	public boolean getIsGhost () {
		return isGhost;
	} //TODO: Make this more capable, adding in things like a flight value
	public void setIsGhost(boolean newVal) {
		isGhost = newVal;
	}
}
