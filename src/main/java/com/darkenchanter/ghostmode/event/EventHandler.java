package com.darkenchanter.ghostmode.event;

import com.darkenchanter.ghostmode.ModStart;
import com.darkenchanter.ghostmode.ghostability.GhostCapabilityProvider;
import com.darkenchanter.ghostmode.ghostability.IGhostCapability;
import com.darkenchanter.ghostmode.networking.GMPacketHandler;
import com.darkenchanter.ghostmode.networking.client.GhostRequestMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class contains all handlers relating to events that Forge sends out. All handlers must be prefixed
 * with @SubscribeEvent.
 */
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
					e.setAmount(e.getAmount() / 4);               //TODO: Remove this and add in the real ghost features
					ModStart.logger.info("Ghost! Reducing damage");
				} else
					ModStart.logger.info("Not ghost, no benefits.");
			}
		}
	}
	/**
	 * TODO: Make this behavior configurable, and perhaps disabled by default.
	 * Copy data from dead player to the new player

	 */

	@SubscribeEvent
	public static void keepGhostData(PlayerEvent.Clone event) {
		EntityPlayer player = event.getEntityPlayer();      //This code pulls the data from the old player and puts it into the new one.
		IGhostCapability ghost = player.getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null);
		IGhostCapability oldGhost = event.getOriginal().getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null);
		ModStart.logger.info("Respawning player, keeping data. Previous value was " + oldGhost.getIsGhost());
		//TODO: Make this also configurable, along with the new server-based mode swapping
		ghost.setIsGhost(oldGhost.getIsGhost(), player);
		player.capabilities.allowFlying = oldGhost.getIsGhost();
	}

	/**
	 * TODO: Make this method toggleable by config file.
	 * This method makes players become ghosts when they die.
	 * @param e
	 */
	@SubscribeEvent
	public static void deathMakesForGhost(LivingDeathEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			IGhostCapability gc = e.getEntity().getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null);
			if (gc != null) {
				gc.setIsGhost(true, (EntityPlayer)e.getEntity());        //TODO: Make this a config option
				ModStart.logger.info("Player has died. " + e.getEntity().getName() + " is now " + (gc.getIsGhost() ? "a ghost." : "not a ghost."));
			}
		}
	}
	public static void stopFallsFromHurting(LivingFallEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			if (e.getEntity().getCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null).getIsGhost()) e.setCanceled(true);
		}
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void syncServerToClient(EntityJoinWorldEvent e) {
		if (!(e.getEntity() instanceof EntityPlayer)) return;
		ModStart.logger.info("Client Ghost Request");
		GMPacketHandler.INSTANCE.sendToServer(new GhostRequestMessage(e.getEntity().getName()));
	}
	@SubscribeEvent
	public static void attachGhostMode(AttachCapabilitiesEvent<Entity> e) {
		if (e.getObject() instanceof EntityPlayer) {
			if (!e.getObject().hasCapability(GhostCapabilityProvider.GHOST_CAPABILITY, null)) {
				e.addCapability(GHOST_CAP, new GhostCapabilityProvider());
				ModStart.logger.info("Attaching ghost mode!");
			}
		}
	}
}
