package io.github.toktom.skills.list.crafting;

import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsEventHandler;
import io.github.toktom.skills.SkillsPlayer;
import io.github.toktom.skills.SkillsPlayer.SkillsPlayerEntry;
import io.github.toktom.skills.list.woodcutting.WoodcuttingSkill;
import io.github.toktom.skills.storage.CapabilitySkills;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CraftingSkillEventHandler extends SkillsEventHandler
{

	public CraftingSkillEventHandler(Skill skill)
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

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void craftingWoodcuttingItem(PlayerEvent.ItemCraftedEvent event)
	{
		PlayerEntity player = event.getPlayer();
		SkillsPlayer skills = player.getCapability(CapabilitySkills.CAPABILITY_SKILLS).orElse(null);
		SkillsPlayerEntry wc = skills.getSkill(new WoodcuttingSkill());

		if (!event.getPlayer().getCommandSenderWorld().isClientSide)
		{
			onItemCrafted(event, Items.OAK_PLANKS, 2, 10, wc, 5, 1);
			onItemCrafted(event, Items.STONE_AXE, 2, 10, wc, 5, 1);
		}
	}
}
