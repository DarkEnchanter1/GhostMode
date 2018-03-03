package com.darkenchanter.ghostmode.event;

import com.darkenchanter.ghostmode.ModStart;
import com.darkenchanter.ghostmode.ghostability.GhostCapabilityProvider;
import com.darkenchanter.ghostmode.ghostability.IGhostCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
	public static final ResourceLocation GHOST_CAP = new ResourceLocation(ModStart.MODID, "ghost_cap");
	@SubscribeEvent
	/**
	 * Checks if the player is a ghost, and if so, reduces damage by 4. Obviously, this is a temporary feature.
	 */
	public static void noMoreDamage(LivingHurtEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			IGhostCapability gc = e.getEntity().getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null);
			if (gc != null) {
				if (gc.getIsGhost()) {
					e.setAmount(e.getAmount() / 4);                   //TODO: Remove this and add in the real ghost features
					ModStart.logger.info("Ghost! Reducing damage");
				} else
					ModStart.logger.info("Not ghost, no benefits.");
			}
		}
	}/**

	 * Copy data from dead player to the new player

	 */

	@SubscribeEvent
	public static void keepGhostData(PlayerEvent.Clone event) {
		ModStart.logger.info("Respawning player, keeping data.");
		EntityPlayer player = event.getEntityPlayer();      //This code pulls the data from the old player and puts it into the new one.
		IGhostCapability ghost = player.getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null);
		IGhostCapability oldGhost = event.getOriginal().getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null);
		//TODO: Make this also configurable, along with the new server-based mode swapping
		ghost.setIsGhost(oldGhost.getIsGhost());
	}
	@SubscribeEvent
	public static void deathMakesForGhost(LivingDeathEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			IGhostCapability gc = e.getEntity().getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null);
			if (gc != null) {
				gc.setIsGhost(true);        //TODO: Make this a config option
				ModStart.logger.info("PLAYER DIED! NAME IS " + e.getEntity().getName() + " AND STATUS IS NOW " + gc.getIsGhost());
			}
		}
	}
	@SubscribeEvent
	public static void attachGhostMode(AttachCapabilitiesEvent<Entity> e) {
		if (e.getObject() instanceof EntityPlayer) {
			ModStart.logger.info("Attaching ghost mode!");
			e.addCapability(GHOST_CAP, new GhostCapabilityProvider());
		}
	}
}
