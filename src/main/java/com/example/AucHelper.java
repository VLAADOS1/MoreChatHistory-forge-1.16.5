package com.example;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AucHelper {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void start() throws InterruptedException {
        ///asdasdsddasdsad
        while (AucFThelper.activAucHelper) {
            for (int i = 0; i < AucFThelper.hotBat; i++) {
                ItemStack stack = MinecraftClient.getInstance().player.inventory.getStack(i);
                if (stack == ItemStack.EMPTY) continue;
                Thread.sleep(1000);
                MinecraftClient.getInstance().player.inventory.selectedSlot = i;
                Thread.sleep(1000);
                MinecraftClient.getInstance().player.sendChatMessage("/ah sell " + AucFThelper.price);
            }

            Thread.sleep( 1000L);
            MinecraftClient.getInstance().execute(() -> {
                MinecraftClient.getInstance().openScreen(new InventoryScreen(MinecraftClient.getInstance().player));
            });

            Thread.sleep( 1000L);
            int f = 0;
            for (int i = 9; i < 36; i++) {
                ItemStack stack = MinecraftClient.getInstance().player.inventory.getStack(i);
                if (stack == ItemStack.EMPTY) continue;
                if(f <= 10) {
                    Thread.sleep( 300);
                    f++;
                    MinecraftClient.getInstance().interactionManager.clickSlot(MinecraftClient.getInstance().player.currentScreenHandler.syncId, i, 0, SlotActionType.QUICK_MOVE, MinecraftClient.getInstance().player);
                } else {
                    break;
                }
            }

            f = 0;

            MinecraftClient.getInstance().execute(() -> {
                MinecraftClient.getInstance().openScreen((Screen) null);
            });

            Thread.sleep(AucFThelper.time * 1000L);
            MinecraftClient.getInstance().player.sendChatMessage("/ah");
            Thread.sleep(3000);
            MinecraftClient.getInstance().interactionManager.clickSlot(MinecraftClient.getInstance().player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, MinecraftClient.getInstance().player);

            for (int i = 0; i < AucFThelper.hotBat; i++) {
                Thread.sleep(1000);
                MinecraftClient.getInstance().interactionManager.clickSlot(MinecraftClient.getInstance().player.currentScreenHandler.syncId, 0, 0, SlotActionType.PICKUP, MinecraftClient.getInstance().player);
            }
            Thread.sleep(1000);
            MinecraftClient.getInstance().execute(() -> {
                MinecraftClient.getInstance().openScreen((Screen) null);
            });
            Thread.sleep(1000);
            MinecraftClient.getInstance().player.inventory.selectedSlot = 0;

            Thread.sleep(2000);

            MinecraftClient.getInstance().execute(() -> {
                Vec3d pos = MinecraftClient.getInstance().player.getPos();
                MinecraftClient.getInstance().interactionManager.interactBlock(MinecraftClient.getInstance().player, MinecraftClient.getInstance().world, Hand.MAIN_HAND, new BlockHitResult(new Vec3d(pos.x, pos.y, pos.z), Direction.UP, new BlockPos(new Vec3d(pos.x, pos.y, pos.z)), false));
            });

            for (int i = 0; i < 54; i++) {
                Thread.sleep( 50);
                MinecraftClient.getInstance().interactionManager.clickSlot(MinecraftClient.getInstance().player.currentScreenHandler.syncId, i, 0, SlotActionType.QUICK_MOVE, MinecraftClient.getInstance().player);
            }

            MinecraftClient.getInstance().execute(() -> {
                MinecraftClient.getInstance().openScreen((Screen) null);
            });

            Thread.sleep(1000);
            MinecraftClient.getInstance().player.inventory.selectedSlot = 0;

        }

    }

}
