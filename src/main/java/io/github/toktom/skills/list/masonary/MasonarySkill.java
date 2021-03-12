package io.github.toktom.skills.list.masonary;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class MasonarySkill extends Skill
{

	private MasonarySkillEventHandler eventHandler;

	public MasonarySkill()
	{
		super("masonary");
		eventHandler = new MasonarySkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}

}
