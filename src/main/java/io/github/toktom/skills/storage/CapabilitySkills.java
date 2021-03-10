package io.github.toktom.skills.storage;

import io.github.toktom.skills.SkillsPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilitySkills
{
	@CapabilityInject(SkillsPlayer.class)
	public static Capability<SkillsPlayer> CAPABILITY_SKILLS = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(SkillsPlayer.class, new SkillsSerializer(), SkillsPlayer::new);
	}
}
