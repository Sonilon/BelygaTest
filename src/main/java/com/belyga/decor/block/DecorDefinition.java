package com.belyga.decor.block;

public class DecorDefinition {
    public final int folderId;
    public final Integer slots;
    public final Integer light;
    public final boolean toggleable;

    private DecorDefinition(int folderId, Integer slots, Integer light, boolean toggleable) {
        this.folderId = folderId;
        this.slots = slots;
        this.light = light;
        this.toggleable = toggleable;
    }

    public static DecorDefinition crate(int folderId, int slots) {
        return new DecorDefinition(folderId, slots, null, false);
    }

    public static DecorDefinition lamp(int folderId, int light, boolean toggleable) {
        return new DecorDefinition(folderId, null, light, toggleable);
    }
}
