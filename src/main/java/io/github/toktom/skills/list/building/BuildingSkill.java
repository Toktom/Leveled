package io.github.toktom.skills.list.building;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class BuildingSkill extends Skill
{

	private BuildingSkillEventHandler eventHandler;

	public BuildingSkill()
	{
		super("building");
		eventHandler = new BuildingSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
}