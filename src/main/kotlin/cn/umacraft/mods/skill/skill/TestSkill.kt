package cn.umacraft.mods.skill.skill

class TestSkill : FatherSkill(), ISelfSpeedable {
    override val speed: Int
        get() = 1

    override val registryTag: String
        get() = "test_skill"

    override val isPassive: Boolean
        get() = false
}