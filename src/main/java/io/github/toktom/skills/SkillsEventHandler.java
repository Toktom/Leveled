package io.github.toktom.skills;

import io.github.toktom.skills.storage.CapabilitySkills;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SkillsEventHandler
{
	private Skill skill;

	public SkillsEventHandler(Skill skill)
	{
		this.skill = skill;
	}

	public void addExp(PlayerEntity player, long exp)
	{
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		skills.getSkill(skill).addExp(exp);
	}

	public long getLevel(PlayerEntity player)
	{
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);

		return skills.getSkill(skill).getLevel();
	}

	public void lowLevelMessage(PlayerEntity player)
	{
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		player.sendMessage(new StringTextComponent("Your " + skills.getSkill(skill).getName() + " level is to low.")
				.withStyle(TextFormatting.RED), player.getUUID());
	}
	
	@SubscribeEvent
	public void onPlayerDeath(PlayerEvent.Clone event)
	{
		PlayerEntity player = event.getPlayer();
		SkillsPlayer newSkills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		SkillsPlayer oldSkills = event.getOriginal().getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		newSkills.setSkills(oldSkills.getSkills());
	
	}
	
}
