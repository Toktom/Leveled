package io.github.toktom.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import io.github.toktom.Leveled;
import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class SkillsScreen extends Screen
{

	private static final int WIDTH = 170;
	private static final int HEIGHT = 150;

	public SkillsScreen()
	{
		super(new TranslationTextComponent("screen.leveled.skills"));
		
	}

	@Override
	protected void init()
	{
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
	{
		int relX = (this.width - WIDTH) / 2;
		int relY = (this.height - HEIGHT) / 2;
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		this.skillsDraw(matrixStack, relX, relY);
		
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}

	private void skillsDraw(MatrixStack matrixStack, int relX, int relY)
	{
		IFormattableTextComponent title = new StringTextComponent("Skills Stats").withStyle(TextFormatting.WHITE);
		SkillsScreen.drawString(matrixStack, font, title, relX + 80, relY - 10, 1);
		SkillsManager manager = SkillsManager.getInstance();
		int i = 1;
		int j = 1;
		int k = 1;
		for (Skill skill : manager.getSkills())
		{
			if(i > 5)
			{
				j = 1;
				k = i - 5;
				
			}
			else
			{
				j = 0;
				k = i;
			}
			String level = String.valueOf(manager.getLevel(this.minecraft.player, skill));
			String exp = String.valueOf(manager.getExp(this.minecraft.player, skill));
			String skill_name_cap = skill.getId().substring(0, 1).toUpperCase() + skill.getId().substring(1);

			IFormattableTextComponent skill_name = new StringTextComponent(skill_name_cap)
					.withStyle(TextFormatting.GOLD);

			IFormattableTextComponent skill_level = new StringTextComponent("Level: ").withStyle(TextFormatting.WHITE)
					.append(new StringTextComponent(level).withStyle(TextFormatting.DARK_PURPLE));
			IFormattableTextComponent skill_exp = new StringTextComponent("Exp: ").withStyle(TextFormatting.WHITE)
					.append(new StringTextComponent(exp).withStyle(TextFormatting.DARK_GREEN));

			SkillsScreen.drawString(matrixStack, font, skill_name, relX + 10 + (j * 110), relY + (k * 20) - 10, 1);
			SkillsScreen.drawString(matrixStack, font, skill_level, relX + 10 + (j * 110), relY + 2 * (k * 10), 1);
			SkillsScreen.drawString(matrixStack, font, skill_exp, relX + 65 + (j * 110), relY + 2 * (k * 10), 1);

			i++;
		}
	}

	public static void open()
	{
		Minecraft.getInstance().setScreen(new SkillsScreen());
	}
}