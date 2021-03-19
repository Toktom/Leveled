package io.github.toktom;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.toktom.skills.SkillsManager;
import io.github.toktom.skills.storage.CapabilitySkills;

@Mod("leveled")
public class Leveled
{

	public static final Logger LOGGER = LogManager.getLogger();

	public static final String MOD_ID = "leveled";

	final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

	public Leveled()
	{
		// Register the setup method for modloading
		bus.addListener(this::onCommonSetupEvent);

		bus.register(io.github.toktom.commands.CommandsInit.class);

		MinecraftForge.EVENT_BUS.register(this);

		SkillsManager.getInstance().init();
	}

	@SubscribeEvent
	public void onCommonSetupEvent(FMLCommonSetupEvent event)
	{
		CapabilitySkills.register();
	}

}
