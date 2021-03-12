package io.github.toktom.skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.toktom.skills.list.building.BuildingSkill;
import io.github.toktom.skills.list.crafting.CraftingSkill;
import io.github.toktom.skills.list.farming.FarmingSkill;
import io.github.toktom.skills.list.fishing.FishingSkill;
import io.github.toktom.skills.list.woodcutting.WoodcuttingSkill;
import io.github.toktom.skills.storage.CapabilitySkills;
import net.minecraft.entity.player.PlayerEntity;

public class SkillsManager
{

	private static SkillsManager INSTANCE;

	private HashMap<String, Skill> skills;

	private SkillsManager()
	{
		this.skills = new HashMap<>();

	}

	public static SkillsManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new SkillsManager();
		}
		return INSTANCE;
	}

	private void addSkill(Skill skill)
	{
		skills.put(skill.getId(), skill);
	}

	public List<Skill> getSkills()
	{
		return new ArrayList<Skill>(skills.values());
	}

	public long getLevel(PlayerEntity player, Skill skill)
	{
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		return skills.getSkill(skill).getLevel();
	}

	public int setLevel(PlayerEntity player, Skill skill, long level)
	{
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		skills.getSkill(skill).setLevel(level);
		return 1;
	}

	public long getExp(PlayerEntity player, Skill skill)
	{
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		return skills.getSkill(skill).getExp();
	}

	public int setExp(PlayerEntity player, Skill skill, long exp)
	{
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		skills.getSkill(skill).setExp(exp);
		return 1;
	}

	public String resetSkill(PlayerEntity player, Skill skill)
	{
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		skills.getSkill(skill).setExp(0);
		skills.getSkill(skill).setLevel(1);
		return skill.getId() + " reset!";
	}

	public void init()
	{
		this.addSkill(new WoodcuttingSkill());
		this.addSkill(new CraftingSkill());
		this.addSkill(new BuildingSkill());
		this.addSkill(new FishingSkill());
		this.addSkill(new FarmingSkill());
	}
}
