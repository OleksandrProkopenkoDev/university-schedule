package ua.foxminded.task31.model.enums;

public enum LessonNumber {
    I(1),
    II(2),
    III(3),
    IV(4);

    private final int value;

    LessonNumber(int value) {
        this.value = value;
    }

    // Getter method to retrieve the int value
    public int getValue() {
        return value;
    }

    // Static method to get enum constant based on int value
    public static LessonNumber fromValue(int value) {
        for (LessonNumber enumItem : LessonNumber.values()) {
            if (enumItem.getValue() == value) {
                return enumItem;
            }
        }
        throw new IllegalArgumentException("No enum constant with value " + value);
    }
}
