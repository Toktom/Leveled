package io.github.toktom.skills.list.masonary;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import io.github.toktom.skills.SkillsPlayer.SkillsPlayerEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MasonarySkillEventHandler extends SkillsEventHandler
{

	public MasonarySkillEventHandler(Skill skill)
	{
		super(skill);
	}
	
	@SuppressWarnings("resource")
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event, Item item, long level, long exp,
			SkillsPlayerEntry skill, long sec_level, long sec_exp)
	{
		PlayerEntity player = event.getPlayer();
		if (item.getItem().equals(item))
		{
			if (this.getLevel(player) >= level || skill.getLevel() >= sec_level)
			{
				this.addExp(player, exp);
				skill.addExp(sec_exp);
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

	@SubscribeEvent
	public void craftingMasonaryItem(PlayerEvent.ItemCraftedEvent event)
	{
	
	}
}
