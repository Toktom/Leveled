package io.github.toktom.skills.list.crafting;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class CraftingSkill extends Skill
{

	private CraftingSkillEventHandler eventHandler;

	public CraftingSkill()
	{
		super("crafting");
		eventHandler = new CraftingSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
}
