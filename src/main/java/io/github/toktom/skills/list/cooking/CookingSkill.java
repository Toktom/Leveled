package io.github.toktom.skills.list.cooking;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class CookingSkill extends Skill
{

	private CookingSkillEventHandler eventHandler;

	public CookingSkill()
	{
		super("cooking");
		eventHandler = new CookingSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
}
