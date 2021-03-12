package io.github.toktom.skills.list.excavation;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class ExcavationSkill extends Skill
{

	private ExcavationSkillEventHandler eventHandler;

	public ExcavationSkill()
	{
		super("excavation");
		eventHandler = new ExcavationSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}

}
