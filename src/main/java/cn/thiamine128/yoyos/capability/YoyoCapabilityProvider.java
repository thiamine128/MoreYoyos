package cn.thiamine128.yoyos.capability;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class YoyoCapabilityProvider implements ICapabilityProvider {
    private LazyOptional<YoyoCapability> yoyoCapability = LazyOptional.of(YoyoCapability::new);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == YoyosCapabilities.YOYO ? yoyoCapability.cast() : LazyOptional.empty();
    }
}
