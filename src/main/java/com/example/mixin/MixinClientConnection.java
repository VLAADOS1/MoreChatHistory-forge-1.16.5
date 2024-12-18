package com.example.mixin;

import com.example.AucFThelper;
import com.example.AucHelper;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet_1, GenericFutureListener<? extends Future<? super Void>> genericFutureListener_1, CallbackInfo callback) throws InterruptedException {

        if (packet_1 instanceof ChatMessageC2SPacket) {
            System.out.println("/paket");
            ChatMessageC2SPacket paket = (ChatMessageC2SPacket) packet_1;
            if (paket.getChatMessage().startsWith("/ahhelp")) {
                MinecraftClient.getInstance().player.sendMessage(Text.of("Доступные команды:"),false);
                MinecraftClient.getInstance().player.sendMessage(Text.of("1) /autoah delay 10 | установка времени ожидания перед снятием предметов с акционна(10 - время в секундах)"),false);
                MinecraftClient.getInstance().player.sendMessage(Text.of("2) /autoah slot 3 | установка колличество предметов которые мод будет выставлять(3 - колличество слотов с хотбара)"),false);
                MinecraftClient.getInstance().player.sendMessage(Text.of("3) /autoah price 1000 | установка цены за 1 предмет(вместо 1000 ваша сумма)"),false);
                MinecraftClient.getInstance().player.sendMessage(Text.of("4) /autoah toggle start | запуск мода"),false);
                MinecraftClient.getInstance().player.sendMessage(Text.of("5) /autoah toggle stop | остановка мода"),false);
                callback.cancel();
            }
                if (paket.getChatMessage().startsWith("/autoah ")) {
                System.out.println("/autoah");
                String input = paket.getChatMessage().substring("/autoah ".length());
                String[] split = input.split(" ", -1);
                String command = split[0];
                String[] args = input.substring(command.length()).trim().split(" ");

                if (command.equalsIgnoreCase("delay")){
                    System.out.println("/delay");

                    int del = Integer.parseInt(args[0]);
                    AucFThelper.time = del;
                    MinecraftClient.getInstance().player.sendMessage(Text.of("Успешно! Время переустановки предметов изменено на " + del + " секунд."),false);
                } else if (command.equalsIgnoreCase("slot")) {
                    System.out.println("/slot");

                    int bar = Integer.parseInt(args[0]);
                    if (bar <= 8 && bar >= 0) {
                        AucFThelper.hotBat = bar;
                        MinecraftClient.getInstance().player.sendMessage(Text.of("Успешно! Колличество предметов на продажу изменено на " + bar + "."), false);
                    } else {
                        MinecraftClient.getInstance().player.sendMessage(Text.of("Колличество слотов должно быть от 0 до 8 (соответствует 1-9)."), false);
                    }

                } else if (command.equalsIgnoreCase("price")) {
                    System.out.println("/price");

                    int bar = Integer.parseInt(args[0]);
                    if ( bar > 0) {
                        AucFThelper.price = bar;
                        MinecraftClient.getInstance().player.sendMessage(Text.of("Успешно! Цена предметов на продажу изменена на " + bar + "."), false);
                    } else {
                        MinecraftClient.getInstance().player.sendMessage(Text.of("Цена должна быть больше 0."), false);
                    }

                } else if (command.equalsIgnoreCase("toggle")) {
                    System.out.println("/toggle");

                    String starter = args[0];
                    if (Objects.equals(starter, "start")) {
                        MinecraftClient.getInstance().player.sendMessage(Text.of("Успешно! Выставление предметов начато."), false);
                        AucFThelper.activAucHelper = true;
                        AucFThelper.thread = new Thread(() -> {
                            try {
                                AucHelper.start();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        AucFThelper.thread.start();
                    } else if (Objects.equals(starter, "stop")) {
                        MinecraftClient.getInstance().player.sendMessage(Text.of("Успешно! Выставление предметов оконченно."), false);
                        AucFThelper.activAucHelper = false;
                        AucFThelper.thread.interrupt();
                    } else {
                        MinecraftClient.getInstance().player.sendMessage(Text.of("Неизвестный аргумент, введите start или stop."), false);
                    }

                } else {
                    MinecraftClient.getInstance().player.sendMessage(Text.of("Неизвестный аргумент, введите: slot, delay, toggle или price"), false);
                }

                callback.cancel();

            }

        }

    }

}
