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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class SkillsIndividualCommand
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{

		SkillsManager manager = SkillsManager.getInstance();
		for (Skill skill : manager.getSkills())
		{

			LiteralArgumentBuilder<CommandSource> skillCommand = Commands.literal("skills")
					.requires((commandSource) -> commandSource.hasPermission(1))
					.then(Commands.literal(skill.getId()).then(Commands.literal("level")
							.executes(commandContext -> sendMessage(commandContext,
									String.valueOf(manager
											.getLevel((PlayerEntity) commandContext.getSource().getEntity(), skill)))))
							.then(Commands.literal("exp")
									.executes(commandContext -> sendMessage(commandContext,
											String.valueOf(manager.getExp(
													(PlayerEntity) commandContext.getSource().getEntity(), skill)))))
							.then(Commands.literal("reset")
									.executes(commandContext -> sendMessage(commandContext,
											manager.resetSkill((PlayerEntity) commandContext.getSource().getEntity(),
													skill))))
							.executes(commandContext -> sendMessage(commandContext, "Insert level, exp or reset")));
			dispatcher.register(skillCommand);
		}
	}

	static int sendMessage(CommandContext<CommandSource> commandContext, String message) throws CommandSyntaxException
	{
		TranslationTextComponent finalText = new TranslationTextComponent("chat.type.announcement",
				commandContext.getSource().getDisplayName(),
				new StringTextComponent(message).withStyle(TextFormatting.GOLD));

		Entity entity = commandContext.getSource().getEntity();
		if (entity != null)
		{
			commandContext.getSource().getServer().getPlayerList().broadcastMessage(finalText, ChatType.CHAT,
					entity.getUUID());
		} else
		{
			commandContext.getSource().getServer().getPlayerList().broadcastMessage(finalText, ChatType.SYSTEM,
					Util.NIL_UUID);
		}

		return 1;
	}
}