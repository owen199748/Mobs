package pw.owen.mobs.bean.mob;


public enum TargetSelect{

	附近玩家("CLOSEST_PLAYER"), 
	碰撞("COLLISION"), 
	自定义("CUSTOM"),
	保卫村庄("DEFEND_VILLAGE"), 
	忘记目标("FORGOT_TARGET"), 
	创建者攻击目标("OWNER_ATTACKED_TARGET"),
	猪人选择目标("PIG_ZOMBIE_TARGET"),
	随机目标("RANDOM_TARGET"), 
	目标增援("REINFORCEMENT_TARGET"),
	目标攻击实体("TARGET_ATTACKED_ENTITY"),
	目标攻击附近实体("TARGET_ATTACKED_NEARBY_ENTITY"),
	目标攻击创建者("TARGET_ATTACKED_OWNER");

	private String reason;

	TargetSelect(String reason) {
		this.reason = reason;
	}

public String getReason(){
		return reason;
	}
	public static TargetSelect valuesOfReason(String string) {
		for(int i=0;i<values().length;i++)
			if(values()[i].getReason().equals(string))
				return values()[i];
		return null;
	}
}
