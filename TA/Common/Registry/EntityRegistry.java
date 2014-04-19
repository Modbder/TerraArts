package TA.Common.Registry;

import TA.Common.Entity.EntityFallingStar;
import TA.Mod.TerraArts;

public class EntityRegistry {
	public static void register()
	{
		cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityFallingStar.class, "fallingstar", TerraArts.cfg.mobID[0], TerraArts.instance, 32, 1, true);
	}
}
