package com.example.examplemod.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.network.IPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CChatMessagePacket;
import net.minecraft.network.play.client.CCustomPayloadPacket;
import net.minecraft.util.text.StringTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    boolean f = false;

    @Inject(method = "send(Lnet/minecraft/network/IPacket;)V", at = @At(value = "HEAD"))
    public void render(IPacket<?> p_179290_1_, CallbackInfo ci) {

    }
}