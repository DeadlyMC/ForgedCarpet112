package carpet.forge.utils;

import carpet.forge.ForgedCarpet;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class OptifinePlugin implements IMixinConfigPlugin
{
    @Override
    public void onLoad(String mixinPackage) { }
    
    @Override
    public String getRefMapperConfig() { return null; }
    
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        try
        {
            Class.forName("optifine.OptiFineForgeTweaker");
            ForgedCarpet.logger.info("Optifine detected!");
            return true;
        }
        catch (ClassNotFoundException e)
        {
            ForgedCarpet.logger.info("Optifine not detected!");
            return false;
        }
    }
    
    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }
    
    @Override
    public List<String> getMixins() { return null; }
    
    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
    
    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
}
