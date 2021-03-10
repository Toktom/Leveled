package io.github.toktom.skills.list.farming;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class FarmingSkill extends Skill
{

	private FarmingSkillEventHandler eventHandler;

	public FarmingSkill()
	{
		super("farming");
		eventHandler = new FarmingSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
}