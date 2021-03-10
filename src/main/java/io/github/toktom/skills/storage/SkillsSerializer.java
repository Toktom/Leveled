package io.github.toktom.skills.storage;

import java.util.HashMap;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsManager;
import io.github.toktom.skills.SkillsPlayer;
import io.github.toktom.skills.SkillsPlayer.SkillsPlayerEntry;
import net.minecraftforge.common.util.Constants;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SkillsSerializer implements Capability.IStorage<SkillsPlayer>
{

	@Override
	public INBT writeNBT(Capability<SkillsPlayer> capability, SkillsPlayer instance, Direction side)
	{
		CompoundNBT nbt = new CompoundNBT();
		for (SkillsPlayer.SkillsPlayerEntry skillsPlayerEntry : instance.getSkills().values())
		{
			CompoundNBT skillsNBT = new CompoundNBT();
			skillsNBT.putLong("level", skillsPlayerEntry.getLevel());
			skillsNBT.putLong("exp", skillsPlayerEntry.getExp());
			nbt.put(skillsPlayerEntry.getSkill().getId(), skillsNBT);

		}

		return nbt;
	}

	@Override
	public void readNBT(Capability<SkillsPlayer> capability, SkillsPlayer instance, Direction side, INBT nbt)
	{
		SkillsManager manager = SkillsManager.getInstance();

		HashMap<String, SkillsPlayerEntry> skills = new HashMap<>();

		if (nbt instanceof CompoundNBT)
		{
			CompoundNBT compoundnbt = (CompoundNBT) nbt;
			for (Skill skill : manager.getSkills())
			{
				if (compoundnbt.contains(skill.getId(), Constants.NBT.TAG_COMPOUND))
				{

					long level = compoundnbt.getCompound(skill.getId()).getLong("level");
					long exp = compoundnbt.getCompound(skill.getId()).getLong("exp");

					SkillsPlayerEntry entry = new SkillsPlayerEntry(skill, level, exp);

					skills.put(skill.getId(), entry);
				} else
				{
					SkillsPlayerEntry entry = new SkillsPlayerEntry(skill, 1, 0);
					skills.put(skill.getId(), entry);
				}

			}
		} else
		{
			for (Skill skill : manager.getSkills())
			{

				SkillsPlayerEntry entry = new SkillsPlayerEntry(skill, 1, 0);
				skills.put(skill.getId(), entry);

			}

		}
		instance.setSkills(skills);
	}
}