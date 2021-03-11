package io.github.toktom.skills.list.fishing;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.EventAccessTransformer;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FishingSkillEventHandler extends SkillsEventHandler
{

	public FishingSkillEventHandler(Skill skill)
	{
		super(skill);
	}

	// TODO: Fix this shit
	private void onFishedEvent(ItemFishedEvent event, Item fish, long level, long exp)
	{
		World world = event.getPlayer().getCommandSenderWorld();
		PlayerEntity player = event.getPlayer();
		if (this.getLevel(player) >= level)
		{
			if (event.getDrops().equals(fish))
			{
				this.addExp(player, exp);
			} else
			{
				if (!world.isClientSide)
				{
					event.setCanceled(true);
					this.lowLevelMessage(player);
				}
			}
		} else
		{
			if (!world.isClientSide)
			{
				event.setCanceled(true);
				this.lowLevelMessage(player);
			}
		}
	}

	@SubscribeEvent
	public void fishingFishCatchedEvent(ItemFishedEvent event)
	{
		if (!event.getPlayer().getCommandSenderWorld().isClientSide)
		{
			onFishedEvent(event, Items.COD, 1, 40);
			onFishedEvent(event, Items.SALMON, 1, 40);
			onFishedEvent(event, Items.PUFFERFISH, 1, 40);
			onFishedEvent(event, Items.TROPICAL_FISH, 1, 40);
			onFishedEvent(event, Items.COD, 1, 40);
		}
	}

}
