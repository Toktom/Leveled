package io.github.toktom.skills;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvents;

public class SkillsPlayer
{

	private HashMap<String, SkillsPlayerEntry> skills;

	public SkillsPlayer()
	{
		this.skills = new HashMap<>();
	}

	public HashMap<String, SkillsPlayerEntry> getSkills()
	{
		return skills;
	}

	public void setSkills(HashMap<String, SkillsPlayerEntry> skills)
	{
		this.skills = skills;
	}

	public SkillsPlayerEntry getSkill(Skill skill)
	{
		if (!skills.containsKey(skill.getId()))
		{
			skills.put(skill.getId(), new SkillsPlayerEntry(skill, 1, 0));
		}
		return skills.get(skill.getId());

	}

	public static class SkillsPlayerEntry
	{
		private long level;
		private long exp;
		private Skill skill;

		public SkillsPlayerEntry(Skill skill, long level, long exp)
		{
			this.skill = skill;
			this.level = level;
			this.exp = exp;

		}

		public String getName()
		{
			return skill.getId();
		}

		public long getLevel()
		{
			return level;
		}

		public void setLevel(long level)
		{
			this.level = level;
		}

		public Skill getSkill()
		{
			return skill;
		}

		public void setSkill(Skill skill)
		{
			this.skill = skill;
		}

		public long getExp()
		{
			return exp;
		}

		public void setExp(long exp)
		{
			this.exp = exp;
		}

		public void addExp(long exp)
		{
			this.exp += exp;
			this.levelUp();
		}

		public void levelUp()
		{
			long[] exp_list = new long[]
			{ 50, 104, 163, 228, 298, 375, 460, 552, 652, 762, 881, 1011, 1153, 1308, 1477, 1661, 1862, 2080, 2318,
					2578, 2861, 3169, 3505, 3871, 4270, 4704, 5178, 5694, 6256, 6869, 7537, 8265, 9059, 9924, 10866,
					11893, 13013, 14233, 15564, 17014, 18594, 20317, 22195, 24243, 26475, 28908, 31561, 34453, 37606 };
			int up_count = 0; // To be removed

			if (this.getExp() >= exp_list[(int) (this.getLevel() - 1)])
			{
				while (this.getExp() >= exp_list[(int) (this.getLevel() - 1)])
				{
					this.setExp(this.getExp() - exp_list[(int) (this.getLevel() - 1)]);
					this.setLevel(this.getLevel() + 1);
					up_count++; // To be removed
					Minecraft.getInstance().player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1f); // Kinda works...
				}
				this.setExp(this.getExp());
			} else
			{
				this.setExp(this.getExp());
			}
			
			
			// To be removed
			if (up_count == 1)
			{
				System.out.println("You have increased " + up_count + " level!");
			} else if (up_count > 1)
			{
				System.out.println("You have increased " + up_count + " levels!");
			}
		}

	}

}
