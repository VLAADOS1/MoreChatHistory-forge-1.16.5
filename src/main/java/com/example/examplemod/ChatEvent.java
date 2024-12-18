package com.example.examplemod;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;
import java.util.Objects;

import static com.example.examplemod.ExampleMod.*;

public class ChatEvent {



    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        String message = event.getMessage().trim();
        String[] parts = message.split(" ");

        if (parts[0].equalsIgnoreCase("/fpshelp")) {
            event.setCanceled(true);
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("Доступные команды:"), Minecraft.getInstance().player.getUUID());
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("1) /countfps on - включает прибавление фпс"), Minecraft.getInstance().player.getUUID());
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("2) /countfps off - отключает прибавление фпс"), Minecraft.getInstance().player.getUUID());
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("3) /countfps set 1000 - добавляет фпс"), Minecraft.getInstance().player.getUUID());

        }

            if (parts.length > 0 && parts[0].equalsIgnoreCase("/countfps")) {
            event.setCanceled(true);

            if (parts.length < 2) {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("Доступные аргументы: off, set <1000>"), Minecraft.getInstance().player.getUUID());
                return;
            }

            String subCommand = parts[1];

            switch (subCommand.toLowerCase()) {
                case "off":
                        fps = 0;
                    Minecraft.getInstance().player.sendMessage(new StringTextComponent("Успешно отключено."), Minecraft.getInstance().player.getUUID());

                    break;
                case "set":
                    if (parts.length < 3) {
                        Minecraft.getInstance().player.sendMessage(new StringTextComponent("Использование: /zicountAttack set <значение>"), Minecraft.getInstance().player.getUUID());
                        return;
                    }

                    try {

                        Minecraft.getInstance().player.sendMessage(new StringTextComponent("Успешно изменено."), Minecraft.getInstance().player.getUUID());

                        String subCommands = parts[2];
                        fps = Integer.parseInt(subCommands);

                    } catch (NumberFormatException e) {
                        Minecraft.getInstance().player.sendMessage(new StringTextComponent("Значение должно быть целым числом."), Minecraft.getInstance().player.getUUID());
                    }
                    break;

                case "on":
                    onfps = true;
                    Minecraft.getInstance().player.sendMessage(new StringTextComponent("Успешно включено."), Minecraft.getInstance().player.getUUID());
                    break;
                default:
                    Minecraft.getInstance().player.sendMessage(new StringTextComponent("Неверная подкоманда для /countfps. Доступные: off, set <число>"), Minecraft.getInstance().player.getUUID());
                    break;
            }
        }
    }
}

