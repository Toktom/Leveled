package io.github.toktom.skills.list.mining;

import io.github.toktom.skills.Skill;
import net.minecraftforge.common.MinecraftForge;

public class MiningSkill extends Skill
{

	private MiningSkillEventHandler eventHandler;

	public MiningSkill()
	{
		super("mining");
		eventHandler = new MiningSkillEventHandler(this);
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}
}
