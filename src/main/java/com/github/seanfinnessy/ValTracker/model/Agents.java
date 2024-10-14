package com.github.seanfinnessy.ValTracker.model;

public enum Agents {
    GEKKO("e370fa57-4757-3604-3648-499e1f642d3f", "Gekko"),
    FADE("dade69b4-4f5a-8528-247b-219e5a1facd6", "Fade"),
    BREACH("5f8d3a7f-467b-97f3-062c-13acf203c006", "Breach"),
    DEADLOCK("cc8b64c8-4b25-4ff9-6e7f-37b4da43d235", "Deadlock"),
    RAZE("f94c3b30-42be-e959-889c-5aa313dba261", "Raze"),
    CHAMBER("22697a3d-45bf-8dd7-4fec-84a9e28c69d7", "Chamber"),
    KAYO("601dbbe7-43ce-be57-2a40-4abd24953621", "KAY/O"),
    SKYE("6f2a04ca-43e0-be17-7f36-b3908627744d", "Skye"),
    CYPHER("117ed9e3-49f3-6512-3ccf-0cada7e3823b", "Cypher"),
    SOVA("ded3520f-4264-bfed-162d-b080e2abccf9", "Sova"),
    SOVA_1("320b2a48-4d9b-a075-30f1-1f93a9b638fa", "Sova"),
    KILLJOY("1e58de9c-4950-5125-93e9-a0aee9f98746", "Killjoy"),
    HARBOR("95b78ed7-4637-86d9-7e41-71ba8c293152", "Harbor"),
    VYSE("efba5359-4016-a1e5-7626-b1ae76895940", "Vyse"),
    VIPER("707eab51-4836-f488-046a-cda6bf494859", "Viper"),
    PHOENIX("eb93336a-449b-9c1b-0a54-a891f7921d69", "Phoenix"),
    ASTRA("41fb69c1-4189-7b37-f117-bcaf1e96f1bf", "Astra"),
    BRIMSTONE("9f0d8ba9-4140-b941-57d3-a7ad57c6b417", "Brimstone"),
    ISO("0e38b510-41a8-5780-5e8f-568b2a4f2d6c", "Iso"),
    CLOVE("1dbf2edd-4729-0984-3115-daa5eed44993", "Clove"),
    NEON("bb2a4828-46eb-8cd1-e765-15848195d751", "Neon"),
    YORU("7f94d92c-4234-0a36-9646-3a87eb8b5c89", "Yoru"),
    SAGE("569fdd95-4d10-43ab-ca70-79becc718b46", "Sage"),
    REYNA("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc", "Reyna"),
    OMEN("8e253930-4c05-31dd-1b6c-968525494517", "Omen"),
    JETT("add6443a-41bd-e414-f6ad-e58d267f4e95", "Jett");

    private final String uuid;
    private final String agentName;

    Agents(String uuid, String agentName) {
        this.agentName = agentName;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAgentName() {
        return agentName;
    }

    public static Agents getAgentNameWithUUID(String uuid) {
        for (Agents agent: Agents.values()) {
            if (agent.getUuid().equals(uuid)) {
                return agent;
            }
        }
        throw new IllegalArgumentException("Invalid agent uuid: " + uuid);
    }
}
