package io.github.toktom.skills.list.cooking;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemSmeltedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CookingSkillEventHandler extends SkillsEventHandler
{

	public CookingSkillEventHandler(Skill skill)
	{
		super(skill);
	}

	@SuppressWarnings("resource")
	private void onCooked(ItemSmeltedEvent event, Item item, long level, long exp)
	{
		PlayerEntity player = event.getPlayer();
		if (event.getSmelting().getItem().equals(item))
		{
			if (this.getLevel(player) >= level)
			{
				for (int i = 1; i <= event.getSmelting().getStack().getCount(); i++)
				{
					this.addExp(player, exp);
				}
			} else
			{
				if (!player.getCommandSenderWorld().isClientSide)
				{
					player.getCommandSenderWorld().playSound((PlayerEntity) null, player.blockPosition(),
							SoundEvents.ANVIL_LAND, SoundCategory.BLOCKS, 1.0F, 1f);
					lowLevelMessage(player);
				}
			}
		}

	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void cookingOnCooked(ItemSmeltedEvent event)
	{
		if (!event.getPlayer().getCommandSenderWorld().isClientSide)
		{
			onCooked(event, Items.COOKED_PORKCHOP, 1, 30);
			onCooked(event, Items.COOKED_CHICKEN, 1, 30);
			onCooked(event, Items.COOKED_BEEF, 1, 30);
		}
	}
}
