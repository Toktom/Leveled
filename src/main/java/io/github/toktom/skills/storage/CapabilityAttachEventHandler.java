package io.github.toktom.skills.storage;

import io.github.toktom.Leveled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Leveled.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityAttachEventHandler
{

	@SubscribeEvent
	public static void attachCapabilityToEntityHandler(AttachCapabilitiesEvent<Entity> event)
	{
		Entity entity = event.getObject();
		if (entity instanceof PlayerEntity)
		{
			event.addCapability(new ResourceLocation("leveled:skills_storage"), new CapabilityProviderEntities());
		}
	}
	
}