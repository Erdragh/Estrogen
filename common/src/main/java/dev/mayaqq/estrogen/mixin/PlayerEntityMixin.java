package dev.mayaqq.estrogen.mixin;

import dev.mayaqq.estrogen.registry.common.EstrogenAttributes;
import dev.mayaqq.estrogen.registry.common.EstrogenDamageSources;
import dev.mayaqq.estrogen.registry.common.EstrogenEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerEntityMixin {
    /*
     * Registers additional attributes for players without the use of events.
     * Should be compatible with any other mod.
     */
    @Inject(method = "createAttributes()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;", require = 1, allow = 1, at = @At("RETURN"))
    private static void additionalEntityAttributes$addPlayerAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(EstrogenAttributes.DASH_LEVEL.get())
                .add(EstrogenAttributes.BOOB_INITIAL_SIZE.get())
                .add(EstrogenAttributes.BOOB_GROWING_START_TIME.get());
    }

    // Modifies the damage source of fall damage if the player has the estrogen effect.
    @ModifyVariable(
            method = "hurt",
            at = @At(value = "HEAD"),
            index = 1,
            argsOnly = true
    )
    private DamageSource modifyDamageSource(DamageSource source) {
        Player player = (Player) (Object) this;
        if (source == DamageSource.FALL && player.hasEffect(EstrogenEffects.ESTROGEN_EFFECT)) {
            return EstrogenDamageSources.GIRLPOWER_DAMAGE_TYPE;
        }
        return source;
    }

    // If the damage source is the aforementioned damage source, reduce the damage by 1/3.
    @ModifyVariable(
            method = "hurt",
            at = @At(value = "HEAD"),
            index = 2,
            argsOnly = true
    )
    private float modifyFallDamage(float damage, DamageSource source) {
        if (source == EstrogenDamageSources.GIRLPOWER_DAMAGE_TYPE) {
            return damage / 1.5f;
        }
        return damage;
    }
}
