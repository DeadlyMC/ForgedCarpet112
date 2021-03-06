package carpet.forge.mixin;

import net.minecraft.entity.passive.EntityVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityVillager.class)
public interface EntityVillagerAccessor
{
    @Accessor
    int getWealth();
}
