package io.github.thevoidblock.idontwannascrollagain.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin extends Screen {

    @Shadow protected MultiplayerServerListWidget serverListWidget;

    protected MultiplayerScreenMixin(Text title) {
        super(title);
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "refresh", at = @At(value = "TAIL", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
    void refresh(CallbackInfo ci) {
        ((MultiplayerScreenAccessor)this.client.currentScreen).getServerListWidget().setScrollAmount(this.serverListWidget.getScrollAmount());
    }
}
