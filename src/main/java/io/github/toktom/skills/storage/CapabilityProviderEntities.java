package io.github.toktom.skills.storage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.toktom.skills.SkillsPlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProviderEntities implements ICapabilitySerializable<INBT>
{

	private final Direction NO_SPECIFIC_SIDE = null;
	private SkillsPlayer skills = new SkillsPlayer();

	@SuppressWarnings("unchecked")
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing)
	{
		if (CapabilitySkills.CAPABILITY_SKILLS == capability)
		{
			return (LazyOptional<T>) LazyOptional.of(() -> skills);
		}

		return LazyOptional.empty();

	}

	private final static String SKILLS_NBT = "skills";

	@Override
	public INBT serializeNBT()
	{
		CompoundNBT nbt = new CompoundNBT();
		INBT skillsNBT = CapabilitySkills.CAPABILITY_SKILLS.writeNBT(skills, NO_SPECIFIC_SIDE);
		nbt.put(SKILLS_NBT, skillsNBT);
		return nbt;
	}

	@Override
	public void deserializeNBT(INBT nbt)
	{
		if (nbt.getId() != Constants.NBT.TAG_COMPOUND)
		{
			LOGGER.warn("Unexpected NBT type:" + nbt);
			return; // leave as default in case of error
		}
		CompoundNBT compoundNBT = (CompoundNBT) nbt;
		INBT skillsNBT = compoundNBT.get(SKILLS_NBT);

		CapabilitySkills.CAPABILITY_SKILLS.readNBT(skills, NO_SPECIFIC_SIDE, skillsNBT);
	}

	private static final Logger LOGGER = LogManager.getLogger();

}