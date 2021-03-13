package io.github.toktom.skills.list.smithing;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemSmeltedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SmithingSkillEventHandler extends SkillsEventHandler
{

	public SmithingSkillEventHandler(Skill skill)
	{
		super(skill);
	}

	@SuppressWarnings("resource")
	private void onSmelted(ItemSmeltedEvent event, Item item, long level, long exp)
	{
		PlayerEntity player = event.getPlayer();
		if (event.getSmelting().getItem().equals(item))
		{
			if (this.getLevel(player) >= level)
			{
				for(int i = 1; i <= event.getSmelting().getStack().getCount();i++) {
					this.addExp(player, exp);
				}
			} else
			{
				if (!event.getPlayer().getCommandSenderWorld().isClientSide)
				{
					event.getPlayer().getCommandSenderWorld().playSound((PlayerEntity) null,
							event.getPlayer().blockPosition(), SoundEvents.ANVIL_LAND, SoundCategory.BLOCKS, 1.0F, 1f);
					lowLevelMessage(event.getPlayer());
				}
			}
		}

	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void smithingOnSmelted(ItemSmeltedEvent event)
	{
		if (!event.getPlayer().getCommandSenderWorld().isClientSide)
		{
			onSmelted(event, Items.IRON_INGOT, 1, 30);
		}
	}

}
