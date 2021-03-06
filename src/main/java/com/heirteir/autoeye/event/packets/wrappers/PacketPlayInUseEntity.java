/*
 * Created by Justin Heflin on 4/19/18 8:21 PM
 * Copyright (c) 2018.
 *
 * Can be redistributed non commercially as long as credit is given to original copyright owner.
 *
 * last modified: 4/19/18 7:22 PM
 */
package com.heirteir.autoeye.event.packets.wrappers;

import com.heirteir.autoeye.util.reflections.Reflections;
import com.heirteir.autoeye.util.reflections.types.WrappedClass;
import com.heirteir.autoeye.util.reflections.types.WrappedField;
import lombok.Getter;
import org.bukkit.World;
import org.bukkit.entity.Entity;

@Getter public class PacketPlayInUseEntity extends PacketAbstract {
    private static final WrappedField actionTypeField, idField;
    private final ActionType actionType;
    private final Entity entity;

    static {
        WrappedClass packetPlayInUseEntity = Reflections.getNMSClass("PacketPlayInUseEntity");
        actionTypeField = packetPlayInUseEntity.getFieldByName("action");
        idField = packetPlayInUseEntity.getFirstFieldByType(int.class);
    }

    public PacketPlayInUseEntity(World world, Object packet) {
        super(packet);
        this.actionType = ActionType.fromString(((Enum) actionTypeField.get(packet)).name());
        int id = idField.get(packet);
        Entity tempEntity = null;
        for (Entity entity : world.getEntities()) {
            if (entity.getEntityId() == id) {
                tempEntity = entity;
                break;
            }
        }
        this.entity = tempEntity;
    }

    public enum ActionType {
        ATTACK, UNKNOWN;

        public static ActionType fromString(String name) {
            for (ActionType actionType : ActionType.values()) {
                if (actionType.name().equals(name)) {
                    return actionType;
                }
            }
            return UNKNOWN;
        }
    }
}
