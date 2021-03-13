package io.github.toktom.skills.list.mining;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.ITag;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MiningSkillEventHandler extends SkillsEventHandler
{

	public MiningSkillEventHandler(Skill skill)
	{
		super(skill);
	}

	@SuppressWarnings("unused")
	private void onBlockBreak(BlockEvent.BreakEvent event, Block block, int level, int xp)
	{
		PlayerEntity player = event.getPlayer();
		if (event.getState().getBlock().equals(block))
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

	@SuppressWarnings("unused")
	private void onBlockBreakGroup(BlockEvent.BreakEvent event, ITag<Block> group, int level, int xp)
	{

		PlayerEntity player = event.getPlayer();

		if (event.getState().getBlock().is(group))
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

	@SuppressWarnings("unused")
	private void onBlockBreakGroupZero(BlockEvent.BreakEvent event, ITag<Block> group, int level)
	{
		PlayerEntity player = event.getPlayer();
		if (event.getState().getBlock().is(group))
		{
			if (!(this.getLevel(player) >= level))
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

	@SubscribeEvent
	public void miningOnBlockBreak(BlockEvent.BreakEvent event)
	{
		IWorld world = event.getPlayer().getCommandSenderWorld();
		if (!world.isClientSide())
		{
		}
	}
}
