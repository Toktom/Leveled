package io.github.toktom.skills.list.woodcutting;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class WoodcuttingSkill extends Skill
{

	private WoodcuttingSkillEventHandler eventHandler;

	public WoodcuttingSkill()
	{
		super("woodcutting");
		eventHandler = new WoodcuttingSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}

}
