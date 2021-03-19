package io.github.toktom.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegisterCommandEvent
{
	@SubscribeEvent
	public static void onRegisterCommandEvent(RegisterCommandsEvent event)
	{
		CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
		SkillsIndividualCommand.register(commandDispatcher);
		SkillsStatsCommand.register(commandDispatcher);
		SkillsDevCommand.register(commandDispatcher);
		SkillsGuiCommand.register(commandDispatcher);
	}
}