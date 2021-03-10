package io.github.toktom.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsManager;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class SkillsStatsCommand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{

		LiteralArgumentBuilder<CommandSource> statsCommand = Commands.literal("skills")
				.requires((commandSource) -> commandSource.hasPermission(1))
				.then(Commands.literal("stats")
						.executes(commandContext -> sendStatsMessage(commandContext,
								(PlayerEntity) commandContext.getSource().getEntity())))
				.executes(commandContext -> sendMessage(commandContext, "Failed"));
		dispatcher.register(statsCommand);

	}

	static int sendStatsMessage(CommandContext<CommandSource> commandContext, PlayerEntity player)
			throws CommandSyntaxException
	{
		SkillsManager manager = SkillsManager.getInstance();
		for (Skill skill : manager.getSkills())
		{
			String skill_name = skill.getId().substring(0, 1).toUpperCase() + skill.getId().substring(1) + " level: ";
			String level = String.valueOf(manager.getLevel(player, skill));
			sendSkillsStats(commandContext, skill_name, level);
		}

		return 1;
	}

	static void sendSkillsStats(CommandContext<CommandSource> commandContext, String message, String appendable)
	{
		TranslationTextComponent text = new TranslationTextComponent("chat.type.announcement",
				commandContext.getSource().getDisplayName(),
				new StringTextComponent(message).withStyle(TextFormatting.GOLD)
						.append(new StringTextComponent(appendable).withStyle(TextFormatting.DARK_PURPLE)));

		_func_232641_a_(commandContext, text);

	}

	static int sendMessage(CommandContext<CommandSource> commandContext, String message) throws CommandSyntaxException
	{
		TranslationTextComponent text = new TranslationTextComponent("chat.type.announcement",
				commandContext.getSource().getDisplayName(),
				new StringTextComponent(message).withStyle(TextFormatting.GOLD));

		_func_232641_a_(commandContext, text);
		return 1;
	}
	
	private static void _func_232641_a_(CommandContext<CommandSource> commandContext, ITextComponent message) {
		Entity entity = commandContext.getSource().getEntity();
		if (entity != null)
		{
			commandContext.getSource().getServer().getPlayerList().broadcastMessage(message, ChatType.CHAT, entity.getUUID());
		} else
		{
			commandContext.getSource().getServer().getPlayerList().broadcastMessage(message, ChatType.SYSTEM,
					Util.NIL_UUID);
		}

	}
}
