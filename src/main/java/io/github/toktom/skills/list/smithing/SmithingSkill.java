package io.github.toktom.skills.list.smithing;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class SmithingSkill extends Skill
{

	private SmithingSkillEventHandler eventHandler;

	public SmithingSkill()
	{
		super("smithing");
		eventHandler = new SmithingSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
}
