/*
 * Created by Justin Heflin on 4/19/18 8:21 PM
 * Copyright (c) 2018.
 *
 * Can be redistributed non commercially as long as credit is given to original copyright owner.
 *
 * last modified: 4/19/18 7:22 PM
 */
package com.heirteir.autoeye.player.data;

import com.heirteir.autoeye.event.packets.wrappers.PacketPlayInUseEntity;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;

@Getter @Setter public class AttackData {
    private Entity lastEntity;
    private PacketPlayInUseEntity.ActionType lastActionType;
}
