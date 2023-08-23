package com.atikinbtw.firenotifier.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class OnFireMessageMixin {

    @Unique
    private int tickCounter = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.isOnFire()) {
            Text message;
            tickCounter++;

            if (isTouchingFireBlock(player) || player.isInLava()) {
                message = Text.of("§cYou are on fire blocks or in lava!");
                player.sendMessage(message, true);
                tickCounter = 0;
            } else tickCounter = 20;

            if (tickCounter == 20) {
                int fireTicks = player.getFireTicks();

                if (fireTicks > 0) {
                    message = Text.of("§6You will be on fire for the next: " + fireTicks / 20 + " seconds");
                    player.sendMessage(message, true);
                }
            }
        } else {
            tickCounter = 0;
        }
    }

    @Unique
    private boolean isTouchingFireBlock(@NotNull PlayerEntity player) {
        // Get the block state at the player's position
        return player.getEntityWorld().getBlockState(player.getBlockPos()).getBlock() == Blocks.FIRE ||
                player.getEntityWorld().getBlockState(player.getBlockPos()).getBlock() == Blocks.SOUL_FIRE; // Player is touching a fire block or a campfire
    }
}