package io.github.toktom.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import io.github.toktom.client.gui.SkillsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class SkillsGuiCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{

		LiteralArgumentBuilder<CommandSource> skillGuiCommand = Commands.literal("skills")
				.requires((commandSource) -> commandSource.hasPermission(1))
				.then(Commands.literal("gui").executes(commandContext -> run(commandContext)));
		dispatcher.register(skillGuiCommand);

	}

	static int run(CommandContext<CommandSource> commandContext) throws CommandSyntaxException
	{
		Minecraft.getInstance().setScreen(new SkillsScreen());
		return 1;
	}
}