package io.github.toktom.skills.list.fishing;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class FishingSkill extends Skill
{

	private FishingSkillEventHandler eventHandler;

	public FishingSkill()
	{
		super("fishing");
		eventHandler = new FishingSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
}