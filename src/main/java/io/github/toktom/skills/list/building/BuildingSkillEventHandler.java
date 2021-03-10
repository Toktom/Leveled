package io.github.toktom.skills.list.building;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BuildingSkillEventHandler extends SkillsEventHandler
{

	public BuildingSkillEventHandler(Skill skill)
	{
		super(skill);
	}

	public void onBlockPlaced(BlockEvent.EntityPlaceEvent event, Block block, int level, int xp)
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

	@SubscribeEvent
	public void onBuildBlock(BlockEvent.EntityPlaceEvent event)
	{
		World world = event.getEntity().getCommandSenderWorld();
		if (!world.isClientSide)
		{
			onBlockPlaced(event, Blocks.OAK_PLANKS, 1, 10);
			onBlockPlaced(event, Blocks.ACACIA_PLANKS, 23, 30);
		}
	}
}
