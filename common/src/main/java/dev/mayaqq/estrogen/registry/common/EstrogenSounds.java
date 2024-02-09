package dev.mayaqq.estrogen.registry.common;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.mayaqq.estrogen.Estrogen;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;

public class EstrogenSounds {
    public static final Registrar<SoundEvent> SOUNDS = Estrogen.MANAGER.get().get(Registry.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> DASH = SOUNDS.register(Estrogen.id("dash"), () -> new SoundEvent(Estrogen.id("dash")));
    public static final RegistrySupplier<SoundEvent> G03C = SOUNDS.register(Estrogen.id("g03c"), () -> new SoundEvent(Estrogen.id("g03c")));

    public static void register() {
    }
}
