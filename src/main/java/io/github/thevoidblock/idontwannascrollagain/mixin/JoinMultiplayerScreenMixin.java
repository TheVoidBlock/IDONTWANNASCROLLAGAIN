package io.github.thevoidblock.idontwannascrollagain.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JoinMultiplayerScreen.class)
public class JoinMultiplayerScreenMixin extends Screen {
    @Shadow protected ServerSelectionList serverSelectionList;

    protected JoinMultiplayerScreenMixin(Component title) {
        super(title);
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "refreshServerList", at = @At(value = "TAIL", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
    void refresh(CallbackInfo ci) {
        ((JoinMultiplayerScreenAccessor)this.minecraft.screen).getServerSelectionList().setScrollAmount(this.serverSelectionList.scrollAmount());
    }
}
