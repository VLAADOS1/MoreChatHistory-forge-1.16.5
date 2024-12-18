package com.example.examplemod.mixin;

import com.example.examplemod.ExampleMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class MixinFps {
    @Shadow private static int fps;

    @Shadow private int frames;

    @Inject(at = @At("TAIL"), method = "runTick", cancellable = true)
    public void getFPS(boolean p_195542_1_, CallbackInfo ci) {
        if (ExampleMod.onfps) {
//            this.frames += ExampleMod.fps;
        }
    }
}
