package com.darkenchanter.ghostmode.ghostability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class GhostStorage implements Capability.IStorage<IGhostCapability> {
	@Nullable
	@Override
	public NBTBase writeNBT (Capability<IGhostCapability> capability, IGhostCapability instance, EnumFacing side) {
		return new NBTTagInt(instance.getIsGhost() ? 1 : 0); //TODO: Make this store all the new information to be added to IGhostCapability.
	}

	@Override
	public void readNBT (Capability<IGhostCapability> capability, IGhostCapability instance, EnumFacing side, NBTBase nbt) {
		instance.setIsGhost(((NBTTagInt)nbt).getInt() == 1); //TODO: Same as #writeNBT
	}
}
