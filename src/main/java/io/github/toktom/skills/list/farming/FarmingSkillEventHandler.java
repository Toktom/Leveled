package io.github.toktom.skills.list.farming;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FarmingSkillEventHandler extends SkillsEventHandler
{

	public FarmingSkillEventHandler(Skill skill)
	{
		super(skill);
	}

	private void onCropBreak(BlockEvent.BreakEvent event, Block block, long level, long exp)
	{
		PlayerEntity player = event.getPlayer();
		if (event.getState().getBlock().equals(block))
		{
			if (this.getLevel(player) >= level)
			{
				this.addExp(player, exp);

			} else
			{
				if (!player.getCommandSenderWorld().isClientSide())
				{
					player.getCommandSenderWorld().playSound((PlayerEntity) null, event.getPos(),
							SoundEvents.ANVIL_LAND, SoundCategory.BLOCKS, 1.0F, 1f);
					lowLevelMessage(player);
				}
				event.setCanceled(true);
			}
		}
	}

	public void onCropPlaced(BlockEvent.EntityPlaceEvent event, Block block, int level, int xp)
	{
		PlayerEntity player = (PlayerEntity) event.getEntity();
		if (event.getPlacedBlock().getBlock().equals(block))
		{

			if (this.getLevel(player) >= level)
			{

				this.addExp(player, xp);
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
	}

	private void onBoneMeal(BonemealEvent event)
	{
		PlayerEntity player = event.getPlayer();
		if (this.getLevel(player) >= 9)
		{
			if (this.getLevel(player) >= 36)
			{
				this.addExp(player, 60);
			} else if (this.getLevel(player) >= 27)
			{
				this.addExp(player, 35);
			} else if (this.getLevel(player) >= 16)
			{
				this.addExp(player, 20);
			} else
			{
				this.addExp(player, 10);
			}

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

	@SubscribeEvent
	public void farmingOnCropBreak(BlockEvent.BreakEvent event)
	{
		if (!event.getWorld().isClientSide())
		{
			onCropBreak(event, Blocks.CARROTS, 1, 10);
			onCropBreak(event, Blocks.POTATOES, 2, 20);
		}
	}

	@SubscribeEvent
	public void farmingOnCropPlaced(BlockEvent.EntityPlaceEvent event)
	{
		if (!event.getWorld().isClientSide())
		{
			onCropPlaced(event, Blocks.CARROTS, 4, 10);
			onCropPlaced(event, Blocks.POTATOES, 5, 20);
		}
	}
}
