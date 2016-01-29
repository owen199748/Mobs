package pw.owen.mobs.bean.mob;

import org.bukkit.event.entity.EntityTargetEvent;

public enum TargetSelect {
	附近玩家(EntityTargetEvent.TargetReason.CLOSEST_PLAYER), 
	碰撞(EntityTargetEvent.TargetReason.COLLISION), 
	自定义(EntityTargetEvent.TargetReason.CUSTOM),
	保卫村庄(EntityTargetEvent.TargetReason.DEFEND_VILLAGE), 
	忘记目标(EntityTargetEvent.TargetReason.FORGOT_TARGET), 
	创建者攻击目标(EntityTargetEvent.TargetReason.OWNER_ATTACKED_TARGET),
	猪人选择目标(EntityTargetEvent.TargetReason.PIG_ZOMBIE_TARGET),
	随机目标(EntityTargetEvent.TargetReason.RANDOM_TARGET), 
	目标增援(EntityTargetEvent.TargetReason.REINFORCEMENT_TARGET),
	目标攻击实体(EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY),
	目标攻击附近实体(EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY),
	目标攻击创建者(EntityTargetEvent.TargetReason.TARGET_ATTACKED_OWNER);
	private EntityTargetEvent.TargetReason reason;

	TargetSelect(EntityTargetEvent.TargetReason reason) {
		this.reason = reason;
	}
	public EntityTargetEvent.TargetReason getReason() {
		return reason;
	}
	public static TargetSelect valuesOfReason(EntityTargetEvent.TargetReason reason) {
		for(int i=0;i<values().length;i++)
			if(values()[i].getReason()==reason)
				return values()[i];
		return null;
	}
}
