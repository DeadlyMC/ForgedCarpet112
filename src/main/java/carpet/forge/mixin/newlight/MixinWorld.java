package carpet.forge.mixin.newlight;

import carpet.forge.CarpetSettings;
import carpet.forge.utils.LightingEngine;
import carpet.forge.utils.mixininterfaces.IWorld;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// CREDITS : Nessie
@Mixin(World.class)
public abstract class MixinWorld implements IWorld
{
    @Final
    @Mutable
    private LightingEngine lightingEngine;
    
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(ISaveHandler saveHandlerIn, WorldInfo info, WorldProvider providerIn,
            Profiler profilerIn, boolean client, CallbackInfo ci)
    {
        if (CarpetSettings.newLight)
            this.lightingEngine = new LightingEngine((World) (Object) this);
    }
    
    @Inject(method = "checkLightFor", at = @At("HEAD"), cancellable = true)
    private void onCheckLightFor(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Boolean> cir)
    {
        if (CarpetSettings.newLight)
        {
            cir.cancel();
            this.getLightingEngine().scheduleLightUpdate(lightType, pos);
            cir.setReturnValue(true);
        }
    }
    
    @Override
    public LightingEngine getLightingEngine()
    {
        return this.lightingEngine;
    }
}
