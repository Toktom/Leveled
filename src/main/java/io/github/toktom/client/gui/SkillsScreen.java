package io.github.toktom.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.toktom.skills.Skill;
import io.github.toktom.skills.SkillsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class SkillsScreen extends Screen
{

	private static final int WIDTH = 240;
	private static final int HEIGHT = 150;
	private final PlayerEntity player;

	public SkillsScreen(PlayerEntity playerEntity)
	{
		super(new TranslationTextComponent("screen.leveled.skills"));
		this.player = playerEntity;

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

		long[] exps = new long[]
		{ 50, 104, 163, 228, 298, 375, 460, 552, 652, 762, 881, 1011, 1153, 1308, 1477, 1661, 1862, 2080, 2318, 2578,
				2861, 3169, 3505, 3871, 4270, 4704, 5178, 5694, 6256, 6869, 7537, 8265, 9059, 9924, 10866, 11893, 13013,
				14233, 15564, 17014, 18594, 20317, 22195, 24243, 26475, 28908, 31561, 34453, 37606 };

		IFormattableTextComponent title = new StringTextComponent("Skills Stats").withStyle(TextFormatting.WHITE);
		SkillsScreen.drawString(matrixStack, font, title, relX + 100, relY - 10, 1);
		SkillsManager manager = SkillsManager.getInstance();

		int i = 1;
		for (Skill skill : manager.getSkills())
		{
			int column = 1;
			int row = 1;
			if (i > 5)
			{
				column = 1;
				row = i - 5;

			} else
			{
				column = 0;
				row = i;
			}
			String level = String.valueOf(manager.getLevel(this.player, skill));
			String exp = String.valueOf(manager.getExp(this.player, skill));
			String skill_name_cap = skill.getId().substring(0, 1).toUpperCase() + skill.getId().substring(1);

			int porcent = (int) Math.ceil(
					(100 * manager.getExp(this.player, skill)) / exps[(int) (manager.getLevel(this.player, skill) - 1)]);
			
			int x = (19 * porcent)/100;
			
			IFormattableTextComponent skill_progress = new StringTextComponent("-".repeat(x))
					.withStyle(TextFormatting.DARK_PURPLE).append(new StringTextComponent("-".repeat(19-x)).withStyle(TextFormatting.WHITE));

			IFormattableTextComponent skill_name = new StringTextComponent(skill_name_cap)
					.withStyle(TextFormatting.GOLD);

			IFormattableTextComponent skill_level = new StringTextComponent("Level: ").withStyle(TextFormatting.WHITE)
					.append(new StringTextComponent(level).withStyle(TextFormatting.DARK_PURPLE));
			IFormattableTextComponent skill_exp = new StringTextComponent("Exp: ").withStyle(TextFormatting.WHITE)
					.append(new StringTextComponent(exp).withStyle(TextFormatting.DARK_GREEN));

			SkillsScreen.drawString(matrixStack, font, skill_name, relX + 10 + (column * 125), relY + (row * 30) - 15,
					1);
			SkillsScreen.drawString(matrixStack, font, skill_level, relX + 10 + (column * 125), relY + 2 * (row * 15),
					1);
			SkillsScreen.drawString(matrixStack, font, skill_exp, relX + 65 + (column * 125), relY + 2 * (row * 15), 1);
			SkillsScreen.drawString(matrixStack, font, skill_progress, relX + 10 + (column * 125),
					relY + 2 * (row * 15) + 7, 1);
			SkillsScreen.drawString(matrixStack, font, skill_progress, relX + 10 + (column * 125),
					relY + 2 * (row * 15) + 8, 1);

			i++;
		}
	}

	public void open()
	{
		Minecraft.getInstance().setScreen(new SkillsScreen(player));
	}
}