package util;


public enum DBUniqueBusTypeEnum {
    
    HITCH_TABLE(1, "generate_convenience_id", "顺风车表ID"),
    HITCH_SMS_TABLE(2, "generate_convenience_sms_id", "顺风车短信表ID");
 

    private Integer index;
    private String name;
    private String desc;

    DBUniqueBusTypeEnum(Integer index, String name, String desc) {
        this.index = index;
        this.name = name;
        this.desc = desc;
    }
    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public static String getNameByIndex(Integer index) {
        for (DBUniqueBusTypeEnum b : DBUniqueBusTypeEnum.values()) {
            if (b.getIndex().equals(index)) {
                return b.name;
            }
        }
        return "";
    }

    public static String getDescByIndex(Integer index) {
        for (DBUniqueBusTypeEnum b : DBUniqueBusTypeEnum.values()) {
            if (b.getIndex().equals(index)) {
                return b.desc;
            }
        }
        return "";
    }

}
