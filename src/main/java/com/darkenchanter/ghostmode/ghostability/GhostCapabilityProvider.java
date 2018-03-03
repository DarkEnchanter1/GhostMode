package com.darkenchanter.ghostmode.ghostability;


import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GhostCapabilityProvider implements ICapabilitySerializable<NBTBase> {
	@CapabilityInject(IGhostCapability.class)
	public static final Capability<IGhostCapability> GHOST_CAPABILITY = null;
	private IGhostCapability instance = GHOST_CAPABILITY.getDefaultInstance();
	@Override
	public boolean hasCapability (@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == GHOST_CAPABILITY; //Literally just checks if they are the same capability
	}

	@Nullable
	@Override
	public <T> T getCapability (@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == GHOST_CAPABILITY ? GHOST_CAPABILITY.<T> cast(this.instance) : null;
		// 'casts' the instance into what is guaranteed to be itself and returns it if the capability is the same.
	}

	@Override
	public NBTBase serializeNBT () {
		return GHOST_CAPABILITY.getStorage().writeNBT(GHOST_CAPABILITY, this.instance, null);
		// Wrapper for the GhostStorage#writeNBT function.
	}

	@Override
	public void deserializeNBT (NBTBase nbt) {
		GHOST_CAPABILITY.getStorage().readNBT(GHOST_CAPABILITY, this.instance, null, nbt);
		// Wrapper for the GhostStorage#readNBT function.
	}
}
