package ladysnake.illuminations.mixin;

import ladysnake.illuminations.client.Config;
import ladysnake.illuminations.client.IlluminationsClient;
import ladysnake.illuminations.client.UpdateToast;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {
    @Inject(at = @At(value = "RETURN"), method = "render")
    protected void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (IlluminationsClient.getLatestVersion() != null || FabricLoader.getInstance().isDevelopmentEnvironment() && Config.getAutoUpdate()) {
            UpdateToast.add();
        }
    }
}
