package io.github.toktom.commands;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommandsInit
{

	@SubscribeEvent
	public static void onCommonSetupEvent(FMLCommonSetupEvent event)
	{
		MinecraftForge.EVENT_BUS.register(RegisterCommandEvent.class);
	}
}