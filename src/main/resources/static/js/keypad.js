function startKeypad(targetInputSelector, maxLength, isCreditCard) {
    $(".keypad-key").click(function() {
        var digit = ($(this).attr("value"));
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