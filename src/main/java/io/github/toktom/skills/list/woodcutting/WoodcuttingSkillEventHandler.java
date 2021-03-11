package io.github.toktom.skills.list.woodcutting;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import io.github.toktom.util.LeveledTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WoodcuttingSkillEventHandler extends SkillsEventHandler
{
	private static WoodcuttingLevels levels = new WoodcuttingLevels();
	private static WoodcuttingExps exps = new WoodcuttingExps();

	public WoodcuttingSkillEventHandler(Skill skill)
	{
		super(skill);
	}

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

	private void onStripping(PlayerInteractEvent.RightClickBlock event, Block block, int level, int xp)
	{

		BlockState blockstate = event.getWorld().getBlockState(event.getPos());
		Item item = event.getPlayer().getMainHandItem().getItem();
		PlayerEntity player = event.getPlayer();

		if (blockstate.getBlock().equals(block))
		{
			if (item instanceof AxeItem)
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
	}

	@SubscribeEvent
	public void woodcuttingOnBlockBreak(BlockEvent.BreakEvent event)
	{
		IWorld world = event.getPlayer().getCommandSenderWorld();
		if (!world.isClientSide())
		{
			// Misc
			onBlockBreak(event, Blocks.DEAD_BUSH, 2, 1);
			onBlockBreak(event, Blocks.VINE, 6, 2);

			// Leaves
			onBlockBreak(event, Blocks.OAK_LEAVES, levels.oak, 2);
			onBlockBreak(event, Blocks.SPRUCE_LEAVES, levels.oak, 2);
			onBlockBreak(event, Blocks.BIRCH_LEAVES, levels.oak, 2);
			onBlockBreak(event, Blocks.JUNGLE_LEAVES, 10, 4);
			onBlockBreak(event, Blocks.DARK_OAK_LEAVES, 10, 4);
			// Oak
			onBlockBreak(event, Blocks.OAK_LOG, levels.oak, exps.oak);
			onBlockBreak(event, Blocks.STRIPPED_OAK_LOG, levels.strippedOak, 0);
			onBlockBreak(event, Blocks.STRIPPED_OAK_WOOD, levels.strippedOak, 0);
			onBlockBreakGroupZero(event, LeveledTags.Blocks.OAK_BLOCKS, levels.oak);
			// Spruce
			onBlockBreak(event, Blocks.SPRUCE_LOG, levels.spruce, exps.spruce);
			onBlockBreak(event, Blocks.STRIPPED_SPRUCE_LOG, levels.strippedOak, 0);
			onBlockBreak(event, Blocks.STRIPPED_SPRUCE_WOOD, levels.strippedOak, 0);
			onBlockBreakGroupZero(event, LeveledTags.Blocks.SPRUCE_BLOCKS, levels.spruce);
			// Birch
			onBlockBreak(event, Blocks.BIRCH_LOG, levels.birch, exps.birch);
			onBlockBreak(event, Blocks.STRIPPED_BIRCH_LOG, levels.strippedOak, 0);
			onBlockBreak(event, Blocks.STRIPPED_BIRCH_WOOD, levels.strippedOak, 0);
			onBlockBreakGroupZero(event, LeveledTags.Blocks.BIRCH_BLOCKS, levels.birch);
			// Jungle
			onBlockBreak(event, Blocks.JUNGLE_LOG, levels.jungle, exps.jungle);
			onBlockBreak(event, Blocks.STRIPPED_JUNGLE_LOG, levels.strippedJungle, 0);
			onBlockBreak(event, Blocks.STRIPPED_JUNGLE_WOOD, levels.strippedJungle, 0);
			onBlockBreakGroupZero(event, LeveledTags.Blocks.JUNGLE_BLOCKS, levels.jungle);
			// Dark Oak
			onBlockBreak(event, Blocks.DARK_OAK_LOG, levels.jungle, exps.jungle);
			onBlockBreak(event, Blocks.STRIPPED_DARK_OAK_LOG, levels.strippedJungle, 0);
			onBlockBreak(event, Blocks.STRIPPED_DARK_OAK_WOOD, levels.strippedJungle, 0);
			onBlockBreakGroupZero(event, LeveledTags.Blocks.DARK_OAK_BLOCKS, levels.jungle);
			// Acacia
			onBlockBreak(event, Blocks.ACACIA_LOG, levels.acacia, exps.acacia);
			onBlockBreak(event, Blocks.STRIPPED_ACACIA_LOG, levels.strippedAcacia, 0);
			onBlockBreak(event, Blocks.STRIPPED_ACACIA_WOOD, levels.strippedAcacia, 0);
			onBlockBreakGroupZero(event, LeveledTags.Blocks.ACACIA_BLOCKS, levels.acacia);
			// Crimson
			onBlockBreak(event, Blocks.CRIMSON_STEM, levels.crimson, exps.crimson);
			onBlockBreak(event, Blocks.STRIPPED_CRIMSON_STEM, levels.strippedCrimson, 0);
			onBlockBreak(event, Blocks.STRIPPED_CRIMSON_HYPHAE, levels.strippedCrimson, 0);
			onBlockBreakGroupZero(event, LeveledTags.Blocks.CRIMSON_BLOCKS, levels.crimson);
			// Warped
			onBlockBreak(event, Blocks.WARPED_STEM, levels.warped, exps.warped);
			onBlockBreak(event, Blocks.STRIPPED_WARPED_STEM, levels.strippedWarped, 0);
			onBlockBreak(event, Blocks.STRIPPED_WARPED_HYPHAE, levels.strippedWarped, 0);
			onBlockBreakGroupZero(event, LeveledTags.Blocks.WARPED_BLOCKS, levels.warped);
		}
	}

	@SubscribeEvent
	public void woodcuttingOnStripping(PlayerInteractEvent.RightClickBlock event)
	{
		World world = event.getPlayer().getCommandSenderWorld();
		if (!world.isClientSide)
		{
			onStripping(event, Blocks.OAK_LOG, levels.strippedOak, exps.strippingOak);
			onStripping(event, Blocks.SPRUCE_LOG, levels.strippedOak, exps.strippingOak);
			onStripping(event, Blocks.BIRCH_LOG, levels.strippedOak, exps.strippingOak);
			onStripping(event, Blocks.JUNGLE_LOG, levels.strippedJungle, exps.strippingJungle);
			onStripping(event, Blocks.DARK_OAK_LOG, levels.strippedJungle, exps.strippingJungle);
			onStripping(event, Blocks.ACACIA_LOG, levels.strippedAcacia, exps.strippingAcacia);
			onStripping(event, Blocks.CRIMSON_STEM, levels.strippedCrimson, exps.strippingCrimson);
			onStripping(event, Blocks.WARPED_STEM, levels.strippedWarped, exps.strippingWarped);
		}
	}
}
