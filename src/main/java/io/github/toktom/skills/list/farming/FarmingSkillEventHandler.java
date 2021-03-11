package io.github.toktom.skills.list.farming;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FarmingSkillEventHandler extends SkillsEventHandler
{

	public FarmingSkillEventHandler(Skill skill)
	{
		super(skill);
	}

	private void onBoneMeal(BonemealEvent event)
	{
		PlayerEntity player = event.getPlayer();
		if (this.getLevel(player) >= 1)
		{
			this.addExp(player, 10);
		} else
		{
			if (!event.getWorld().isClientSide())
			{
				event.getWorld().playSound((PlayerEntity) null, event.getPos(), SoundEvents.ANVIL_LAND,
						SoundCategory.BLOCKS, 1.0F, 1f);
				lowLevelMessage(player);
			}
			event.setCanceled(true);
		}
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void farmingOnBoneMealUse(BonemealEvent event)
	{
		if (!event.getWorld().isClientSide)
		{
			onBoneMeal(event);
		}
	}
}
