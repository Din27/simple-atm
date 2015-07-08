function startKeypad(targetInputSelector, maxLength, isCreditCard) {
    var keyClearSelector = ".keypad-clear-key";
    var keySelector = ".keypad-key";

    $(keyClearSelector).click(function () {
        $(targetInputSelector).val("");
    });

    $(keySelector).click(function () {
        var digit = ($(this).prop("value"));
        var oldVal = $(targetInputSelector).val();
        var oldValWithoutDashes = oldVal.replace(/-/g, "");
        if (oldVal.length < maxLength) {
            var newVal = oldVal;
            if (isCreditCard && oldValWithoutDashes.length != 0 && oldValWithoutDashes.length % 4 === 0) {
                newVal += "-";
            }
            newVal += digit;
            $(targetInputSelector).val(newVal);
        }
    });
}